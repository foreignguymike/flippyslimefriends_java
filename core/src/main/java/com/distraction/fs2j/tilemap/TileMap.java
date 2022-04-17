package com.distraction.fs2j.tilemap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.ArrowData;
import com.distraction.fs2j.tilemap.data.BubbleData;
import com.distraction.fs2j.tilemap.data.FinishTileData;
import com.distraction.fs2j.tilemap.data.IceData;
import com.distraction.fs2j.tilemap.data.MapData;
import com.distraction.fs2j.tilemap.data.PathPointData;
import com.distraction.fs2j.tilemap.data.SuperJumpData;
import com.distraction.fs2j.tilemap.data.TeleportData;
import com.distraction.fs2j.tilemap.data.TileObjectData;
import com.distraction.fs2j.tilemap.player.Player;
import com.distraction.fs2j.tilemap.tileobjects.Arrow;
import com.distraction.fs2j.tilemap.tileobjects.Bubble;
import com.distraction.fs2j.tilemap.tileobjects.FinishTile;
import com.distraction.fs2j.tilemap.tileobjects.Ice;
import com.distraction.fs2j.tilemap.tileobjects.SuperJump;
import com.distraction.fs2j.tilemap.tileobjects.Teleport;
import com.distraction.fs2j.tilemap.tileobjects.TileLight;
import com.distraction.fs2j.tilemap.tileobjects.TileObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TileMap implements Tile.TileMoveListener {

    public interface TileListener {
        void onTileToggled(TileMap tileMap);
    }

    public static final float TILE_SIZE = 30f;
    public static final float TILE_IWIDTH = 60f;
    public static final float TILE_IHEIGHT = 30f;

    private Context context;
    private TileListener tileListener;
    public Area area;

    public MapData mapData;
    public boolean startBubble = false;

    // cache finish tiles
    private List<FinishTile> finishTiles = new ArrayList<>();

    public int numRows;
    public int numCols;
    public ArrayList<Tile> map;

    private int numTilesMoving = 0;
    private ArrayList<Tile> orderedMap;

    private TilePathRenderer tilePathRenderer = null;

    public TileMap(Context context, TileListener tileListener, Area area, int level) {
        this.context = context;
        this.tileListener = tileListener;
        this.area = area;

        mapData = context.gameData.getMapData(area).get(level);
        startBubble = mapData.startBubble;

        numRows = mapData.numRows;
        numCols = mapData.numCols;
        map = parseMapData(mapData.map);
        orderedMap = map.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(t -> (int) t.isop.y))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Tile> parseMapData(int[] map) {
        ArrayList<Tile> ret = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            if (mapData.map[i] < 0) {
                ret.add(null);
                continue;
            }
            int row = i / numCols;
            int col = i % numCols;

            Tile tile = new Tile(context, this, row, col, map[i], area);

            // add paths
            List<List<PathPointData>> paths = mapData.paths;
            if (paths != null) {
                for (List<PathPointData> path : paths) {
                    if (row == path.get(0).row && col == path.get(0).col) {
                        tile.path = path;
                        tile.moveListeners.add(this);
                    }
                }
            }

            // add tile objects
            for (TileObjectData it : mapData.objects) {
                if (it.row != row || it.col != col) continue;
                TileObject tileObject;
                if (it instanceof ArrowData) {
                    tileObject = new Arrow(context, this, row, col, ((ArrowData) it).direction);
                } else if (it instanceof SuperJumpData) {
                    tileObject = new SuperJump(context, this, row, col);
                } else if (it instanceof IceData) {
                    tileObject = new Ice(context, this, row, col);
                } else if (it instanceof TeleportData) {
                    tileObject = new Teleport(context, this, row, col, ((TeleportData) it).destRow, ((TeleportData) it).destCol);
                } else if (it instanceof BubbleData) {
                    Bubble bubble = new Bubble(context, this, row, col);
                    tileObject = bubble;
                    tile.addTopObject(bubble.bubbleBase);
                } else if (it instanceof FinishTileData) {
                    FinishTile finishTile = new FinishTile(context, this, row, col);
                    tileObject = finishTile;
                    finishTiles.add(finishTile);
                } else {
                    throw new IllegalArgumentException("incorrect tile object data");
                }
                tileObject.currentTile = tile;
                tile.addObject(tileObject); // lol
            }
            if (paths != null) {
                tilePathRenderer = new TilePathRenderer(context, this, paths);
            }
            ret.add(tile);
        }
        return ret;
    }

    private int toIndex(int row, int col) {
        return MathUtils.clamp(row * numCols + col, 0, map.size() - 1);
    }

    public void toggleTile(int row, int col) {
        Tile tile = getTile(row, col);
        if (tile != null) {
            if (tile.toggle()) {
                tileListener.onTileToggled(this);
            }
            if (tile.isActive()) {
                tile.addTopObject(new TileLight(context, this, row, col));
            }
        }
    }

    public Tile getTile(int row, int col) {
        return map.get(toIndex(row, col));
    }

    public void toIsometric(float x, float y, Vector3 p) {
        float xo = x / TILE_SIZE;
        float yo = y / TILE_SIZE;
        p.x = (xo - yo) * TILE_IWIDTH / 2;
        p.y = (-xo - yo) * TILE_IHEIGHT / 2;
    }

    public void toPosition(int row, int col, Vector3 p) {
        p.x = col * TILE_SIZE;
        p.y = row * TILE_SIZE;
    }

    public float toPosition(int tile) {
        return tile * TILE_SIZE;
    }

    public boolean isValidTile(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) return false;
        Tile tile = getTile(row, col);
        return tile != null && !tile.moving && !tile.isBlocked();
    }

    public boolean isFinished(List<Player> players) {
        for (Tile it : map) if (it != null && it.index == 0) return false;
        for (FinishTile it : finishTiles) {
            boolean exist = false;
            for (Player p : players) {
                if (!p.atDestination()) continue;
                if (p.row == it.row && p.col == it.col) {
                    exist = true;
                    break;
                }
            }
            if (!exist) return false;
        }
        return true;
    }

    @Override
    public void onTileStartMove(Tile tile, int oldRow, int oldCol, int newRow, int newCol) {
        numTilesMoving++;
    }

    @Override
    public void onTileEndMove(Tile tile, int oldRow, int oldCol, int newRow, int newCol) {
        numTilesMoving--;
        map.set(toIndex(oldRow, oldCol), null);
        map.set(toIndex(newRow, newCol), tile);
    }

    public void update(float dt) {
        for (Tile it : map) if (it != null) it.update(dt);
        if (tilePathRenderer != null) tilePathRenderer.update(dt);
    }

    public void render(SpriteBatch sb) {
        if (numTilesMoving > 0) {
            // lazy sort, sort on every frame
            // could be optimized to sort only when required
            // although sorting an already sorted list should be quick (?) so just leave it
            // plus i don't have to care about figuring out when to sort
            Collections.sort(orderedMap, (t1, t2) -> (int) t2.isop.y - (int) t1.isop.y);
        }
        for (Tile it : orderedMap) {
            it.renderBottom(sb);
            it.render(sb);
        }
        tilePathRenderer.render(sb);
    }

    public void renderTop(SpriteBatch sb, List<Player> sortedPlayers) {
        int playerIndex = 0;
        for (int i = 0; i < orderedMap.size() - 1; i++) {
            Tile item = orderedMap.get(i);
            Tile item2 = orderedMap.get(i + 1);
            if (sortedPlayers != null) {
                if (i < orderedMap.size() - 1 && playerIndex < sortedPlayers.size()) {
                    float playery = -sortedPlayers.get(playerIndex).isop.y;
                    if (playery >= -item.isop.y && playery <= -item2.isop.y) {
                        sortedPlayers.get(playerIndex).render(sb);
                        playerIndex++;
                    }
                }
            }
            item.renderTop(sb);
        }
        if (sortedPlayers != null) for (int i = playerIndex; i < sortedPlayers.size(); i++)
            sortedPlayers.get(i).render(sb);
        orderedMap.get(orderedMap.size() - 1).renderTop(sb);
    }
}
