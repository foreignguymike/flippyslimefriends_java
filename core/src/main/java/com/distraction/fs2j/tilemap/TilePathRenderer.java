package com.distraction.fs2j.tilemap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Direction;
import com.distraction.fs2j.tilemap.data.PathPointData;
import com.distraction.fs2j.tilemap.tileobjects.TileObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TilePathRenderer {

    private final List<TilePathRenderObject> renderList = new ArrayList<>();
    private final List<TilePathRenderObject> renderCenters = new ArrayList<>();
    private final List<TilePathRenderObject> orderedList;

    private float timer = 0f;

    public TilePathRenderer(Context context, TileMap tileMap, List<List<PathPointData>> paths) {
        for (List<PathPointData> path : paths) {
            int currRow = path.get(0).row;
            int currCol = path.get(0).col;
            for (int i = 0; i < path.size() - 1; i++) {
                PathPointData path1 = path.get(i);
                PathPointData path2 = path.get(i + 1);
                while (currRow != path2.row || currCol != path2.col) {
                    int newRow = currRow;
                    int newCol = currCol;
                    Direction direction;
                    if (path2.row < path1.row) {
                        direction = Direction.UP;
                        newRow = currRow - 1;
                    } else if (path2.row > path1.row) {
                        direction = Direction.DOWN;
                        newRow = currRow + 1;
                    } else if (path2.col < path1.col) {
                        direction = Direction.LEFT;
                        newCol = currCol - 1;
                    } else if (path2.col > path1.col) {
                        direction = Direction.RIGHT;
                        newCol = currCol + 1;
                    } else {
                        throw new IllegalArgumentException("invalid path");
                    }
                    renderList.add(new TilePathRenderObject(context, tileMap, direction, currRow, currCol));
                    renderList.add(new TilePathRenderObject(context, tileMap, direction.opposite(), newRow, newCol));
                    currRow = newRow;
                    currCol = newCol;
                }
            }
            for (PathPointData it : path) {
                if (it.time > 0f) {
                    renderCenters.add(new TilePathRenderObject(context, tileMap, null, it.row, it.col));
                }
            }
        }
        List<TilePathRenderObject> sortedRenderList =
                renderList
                        .stream()
                        .sorted(Comparator.comparingInt(t -> t.direction.ordinal()))
                        .collect(Collectors.toList());
        orderedList = new ArrayList<>(new HashSet<>(sortedRenderList));

    }

    public void update(float dt) {
        timer += dt;

        for (int i = 0; i < renderList.size(); i++) {
            renderList.get(i).alpha = MathUtils.sin((i * -0.2f + timer) * 1.5f * MathUtils.PI) * 0.375f + 0.5f;
        }
        for (TilePathRenderObject it : renderCenters) it.alpha = (renderList.get(0).alpha + 1) / 2f;
    }

    public void render(SpriteBatch sb) {
        for (TilePathRenderObject it : orderedList) it.render(sb);
        sb.setColor(1, 1, 1, 1);
        for (TilePathRenderObject it : renderCenters) it.render(sb);
    }

    static class TilePathRenderObject extends TileObject {

        public Direction direction;

        private final TextureRegion center;
        private final TextureRegion left;
        private final TextureRegion up;
        private final TextureRegion right;
        private final TextureRegion down;

        public float alpha = 1f;

        public TilePathRenderObject(Context context, TileMap tileMap, Direction direction, int row, int col) {
            super(context, tileMap);
            setPositionFromTile(row, col);
            this.direction = direction;

            center = context.getImage("tilepathcenter");
            left = context.getImage("tilepathleft");
            up = context.getImage("tilepathup");
            right = context.getImage("tilepathright");
            down = context.getImage("tilepathdown");
        }

        @Override
        public void update(float dt) {

        }

        @Override
        public void render(SpriteBatch sb) {
            tileMap.toIsometric(p.x, p.y, isop);
            sb.setColor(1, 1, 1, alpha);
            if (direction == Direction.UP)
                Utils.drawCentered(sb, up, isop.x + up.getRegionWidth() / 2f - 1, isop.y + up.getRegionHeight() / 2f - 1);
            else if (direction == Direction.RIGHT)
                Utils.drawCentered(sb, right, isop.x + right.getRegionWidth() / 2f - 2, isop.y - right.getRegionHeight() / 2f + 2);
            else if (direction == Direction.DOWN)
                Utils.drawCentered(sb, down, isop.x - down.getRegionWidth() / 2f, isop.y - down.getRegionHeight() / 2f + 2);
            else if (direction == Direction.LEFT)
                Utils.drawCentered(sb, left, isop.x - left.getRegionWidth() / 2f, isop.y + left.getRegionHeight() / 2f - 1);
            else Utils.drawCentered(sb, center, isop.x, isop.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TilePathRenderObject other = (TilePathRenderObject) o;
            if (direction != other.direction) return false;
            if (row != other.row) return false;
            return col == other.col;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

}
