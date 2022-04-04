package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.TileMap;

public class Teleport extends TileObject {

    public int row2;
    public int col2;
    public boolean first = true;

    public Teleport(Context context, TileMap tileMap, int row, int col, int destRow, int destCol) {
        super(context, tileMap);
        setPositionFromTile(row, col);
        this.row2 = destRow;
        this.col2 = destCol;
    }

    @Override
    public void update(float dt) {
        if (first) {
            first = false;
            if (currentTile != null) {
                TeleportLight light = new TeleportLight(context, tileMap, row, col);
                light.currentTile = currentTile;
                currentTile.addTopObject(light);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        // nothing to render, see TeleportLight
    }
}
