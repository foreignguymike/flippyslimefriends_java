package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.TileMap;

public class BubbleBase extends TileObject {

    public static final float RESET_INTERVAL = 1.9f;
    public static final float BLOW_UP_TIME = 1f;

    private final BreathingImage bubble;

    public float resetTime;
    public boolean resetting = false;

    public BubbleBase(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        setPositionFromTile(row, col);

        bubble = new BreathingImage(context.getImage("bubbleidle"));
        bubble.scale = 0f;

        resetTime = RESET_INTERVAL;
        setResetting(false);
    }

    public void setResetting(boolean resetting) {
        this.resetting = resetting;
        if (resetting) {
            resetTime = 0f;
        }
    }

    @Override
    public void update(float dt) {
        bubble.update(dt);
        if (resetting) {
            resetTime += dt;
            if (resetTime >= RESET_INTERVAL) {
                setResetting(false);
                resetTime = 0f;
            } else {
                bubble.scale = Math.max(0f, (resetTime - BLOW_UP_TIME) / (RESET_INTERVAL - BLOW_UP_TIME));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        bubble.setPosition(isop.x, isop.y + 15);
        bubble.render(sb);
    }
}
