package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class BreathingImage extends ImageButton {

    private final float interval;
    private final float offset;

    private float time = 0;

    private boolean clicked;
    private float clickedScale = 0.75f;

    public BreathingImage(TextureRegion image) {
        this(image, 0, 0, 0);
    }

    public BreathingImage(TextureRegion image, float x, float y, float padding) {
        this(image, x, y, padding, 1, 0.1f);
    }

    public BreathingImage(TextureRegion image, float x, float y, float padding, float interval, float offset) {
        super(image, x, y, padding);
        this.interval = interval;
        this.offset = offset;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        time += dt;
        if (clicked) {
            scale = clickedScale;
        } else {
            scale = 1 + offset * MathUtils.sin(MathUtils.PI2 * time / interval);
        }
    }
}
