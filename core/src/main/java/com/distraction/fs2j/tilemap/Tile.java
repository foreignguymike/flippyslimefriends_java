package com.distraction.fs2j.tilemap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.PathPointData;
import com.distraction.fs2j.tilemap.tileobjects.Arrow;
import com.distraction.fs2j.tilemap.tileobjects.FinishTile;
import com.distraction.fs2j.tilemap.tileobjects.Ice;
import com.distraction.fs2j.tilemap.tileobjects.SuperJumpLight;
import com.distraction.fs2j.tilemap.tileobjects.TeleportLight;
import com.distraction.fs2j.tilemap.tileobjects.TileObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tile {

    public interface TileMoveListener {
        void onTileStartMove(Tile tile, int oldRow, int oldCol, int newRow, int newCol);

        void onTileEndMove(Tile tile, int oldRow, int oldCol, int newRow, int newCol);
    }

    private static final int TILE_OFF = 0;
    private static final int TILE_ON = 1;
    private static final List<Class<? extends TileObject>> tileObjectRenderOrder = Arrays.asList(Ice.class, Arrow.class, SuperJumpLight.class, TeleportLight.class);
    private static final Comparator<TileObject> sorter = Comparator.comparingInt(t -> tileObjectRenderOrder.indexOf(t.getClass()));

    private Context context;
    private TileMap tileMap;
    int row;
    int col;
    int index;
    private Area area;

    public List<TileObject> objects;
    private List<TileObject> topObjects;
    private List<TileObject> topObjectsToAdd;

    public List<PathPointData> path;
    private int pathIndex = 0;
    private float speed = 100f;
    private float stayTimer = 0f;
    public boolean lock = false;
    public boolean moving = false;
    public ArrayList<TileMoveListener> moveListeners;

    private int prevRow;
    private int prevCol;

    public Vector3 p = new Vector3();
    public Vector3 isop = new Vector3();
    private Vector3 pdest = new Vector3();

    private TextureRegion image;
    private TextureRegion bottomImage;

    public Tile(Context context, TileMap tileMap, int row, int col, int index, Area area) {
        this.context = context;
        this.tileMap = tileMap;
        this.row = row;
        this.col = col;
        this.index = index;
        this.area = area;

        objects = new ArrayList<>();
        topObjects = new ArrayList<>();
        topObjectsToAdd = new ArrayList<>();

        moveListeners = new ArrayList<>();

        prevRow = row;
        prevCol = col;

        image = context.gameData.getTile(index);
        bottomImage = context.getImage(area.tilesetOn);

        tileMap.toPosition(row, col, p);
        setType(index);
    }

    public void setPath(List<PathPointData> path) {
        this.path = path;
        if (path != null) {
            if (area == Area.RUINS) {
                bottomImage = context.getImage(area.tilesetOn + "glow");
            }
        }
    }

    public boolean toggle() {
        if (index == TILE_OFF) setType(TILE_ON);
        else if (index == TILE_ON) setType(TILE_OFF);
        else return false;
        return true;
    }

    public void setType(int index) {
        this.index = index;
        this.image = context.gameData.getTile(index);
        if (area == Area.MATRIX) {
            bottomImage = context.getImage(index == TILE_OFF ? area.tilesetOff : area.tilesetOn);
        }
        for (TileObject it : objects) {
            if (it instanceof FinishTile) {
                ((FinishTile) it).active = index == TILE_ON;
            }
        }
    }

    public void addObject(TileObject tileObject) {
        objects.add(tileObject);
        Collections.sort(objects, sorter);
    }

    public void addTopObject(TileObject tileObject) {
        topObjectsToAdd.add(tileObject);
        Collections.sort(topObjectsToAdd, sorter);
    }

    public boolean isActive() {
        return index == 1;
    }

    public boolean isMovingTile() {
        return path != null;
    }

    public boolean isBlocked() {
        return index == 5;
    }

    private void goNext() {
        if (path != null) {
            pathIndex++;
            if (pathIndex == path.size()) pathIndex = 0;
            row = path.get(pathIndex).row;
            col = path.get(pathIndex).col;
            tileMap.toPosition(row, col, pdest);
        }
    }

    public void update(float dt) {
        if (path != null) {
            if (!moving) {
                stayTimer += dt;
                if (stayTimer >= path.get(pathIndex).time && !lock) {
                    goNext();
                    moving = true;
                    for (TileMoveListener ml : moveListeners) {
                        ml.onTileStartMove(this, prevRow, prevCol, row, col);
                    }
                    stayTimer = 0f;
                }
            } else {
                // travel to next destination
                Utils.moveTo(p, pdest, speed * dt);

                // move tile objects also
                for (TileObject tileObject : objects) tileObject.setPosition(p.x, p.y);

                // check if we arrived
                if (p.x == pdest.x && p.y == pdest.y) {
                    row = path.get(pathIndex).row;
                    col = path.get(pathIndex).col;
                    moving = false;
                    for (TileMoveListener ml : moveListeners) {
                        ml.onTileEndMove(this, prevRow, prevCol, row, col);
                    }
                    prevRow = row;
                    prevCol = col;

                    // edge case, do not stop when the stayTimer for this path point is 0
                    // this is to prevent the player from jumping on to a moving tile that has
                    // not yet reached a path point where it must stop
                    update(0f);
                }
            }
        }
        if (topObjectsToAdd.size() > 0) {
            topObjects.addAll(topObjectsToAdd);
            Collections.sort(topObjects, sorter);
            topObjectsToAdd.clear();
        }
        for (TileObject it : objects) it.update(dt);
        for (TileObject it : topObjects) it.update(dt);
        for (TileObject it : objects) it.update(dt);
        objects.removeIf(it -> it.remove);
    }

    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        Utils.drawPadded(sb, image, isop.x - TileMap.TILE_IWIDTH / 2f, isop.y - TileMap.TILE_IHEIGHT / 2f);
        for (TileObject it : objects) it.render(sb);
    }

    public void renderBottom(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        Utils.drawPadded(sb, bottomImage, isop.x - bottomImage.getRegionWidth() / 2f, isop.y - bottomImage.getRegionHeight() + TileMap.TILE_IHEIGHT / 2f + 1);
    }

    public void renderTop(SpriteBatch sb) {
        for (TileObject it : topObjects) it.render(sb);
    }

}
