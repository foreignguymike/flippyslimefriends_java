package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.TileMap;

public class SuperJump extends TileObject {

    public static final float INTERVAL = 0.6f;

    private float time = INTERVAL;

    public SuperJump(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        setPositionFromTile(row, col);
    }

    @Override
    public void update(float dt) {
        time += dt;
        while (time > INTERVAL) {
            time -= INTERVAL;
            if (currentTile != null) {
                SuperJumpLight light = new SuperJumpLight(context, tileMap, row, col);
                light.currentTile = currentTile;
                light.p.x = p.x;
                light.p.y = p.y;
                currentTile.addTopObject(light);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        // nothing to render here, rendering is done in SuperJumpLight
    }
}
