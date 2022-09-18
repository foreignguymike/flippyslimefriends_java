package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

    private final TextureRegion[] sprites;
    private final float delay;
    private final int repeatCount;

    private float time = 0f;
    private int frameIndex = 0;
    private int playCount = 0;

    public Animation(TextureRegion[] sprites, float delay) {
        this(sprites, delay, -1);
    }

    public Animation(TextureRegion[] sprites, float delay, int repeatCount) {
        this.sprites = sprites;
        this.delay = delay;
        this.repeatCount = repeatCount;
    }

    public Animation(TextureRegion sprite) {
        this(sprite, 1f / 60f);
    }

    public Animation(TextureRegion sprite, float delay) {
        this(sprite, delay, -1);
    }

    public Animation(TextureRegion sprite, float delay, int repeatCount) {
        this(new TextureRegion[]{sprite}, delay, repeatCount);
    }

    public int currentFrame() {
        return frameIndex;
    }

    public TextureRegion getImage() {
        return sprites[frameIndex];
    }

    public boolean hasPlayedOnce() {
        return playCount > 0;
    }

    public void reset() {
        time = 0f;
        frameIndex = 0;
        playCount = 0;
    }

    public void update(float dt) {
        if (playCount == repeatCount) return;
        if (delay < 0) return;
        time += dt;
        while (time >= delay) {
            time -= delay;
            frameIndex++;
            if (frameIndex >= sprites.length) {
                playCount++;
                frameIndex = playCount != repeatCount ? 0 : sprites.length - 1;
            }
        }
    }

}
