package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class SpinningLights extends ImageButton {

    private float rotationSpeedDeg;

    private final TextureRegion spotlight;
    private final List<Float> degrees;

    public SpinningLights(Context context, float x, float y, float spokes) {
        this(context, x, y, spokes, 1);
    }

    public SpinningLights(Context context, float x, float y, float spokes, float scale) {
        this(context, x, y, spokes, scale, -40f);
    }

    public SpinningLights(Context context, float x, float y, float spokes, float scale, float rotationSpeedDeg) {
        super(context.getImage("pixel"), x, y);
        this.rotationSpeedDeg = rotationSpeedDeg;

        spotlight = context.getImage("spotlight");
        degrees = new ArrayList<>();
        for (int i = 0; i < spokes; i++) {
            degrees.add(i * 360f / spokes);
        }

        pos.y = y + spotlight.getRegionHeight() / 2f;
        this.scale = scale;
    }

    @Override
    public void update(float dt) {
        for (int i = 0; i < degrees.size(); i++) {
            degrees.set(i, degrees.get(i) + rotationSpeedDeg * dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (Float it : degrees)
            Utils.drawRotated(sb, spotlight, pos.x, pos.y, it, spotlight.getRegionWidth() / 2f, 0f, scale);
    }
}
