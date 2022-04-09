package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ImageButton {

    protected TextureRegion image;
    public float padding = 0f;

    public Vector2 pos = new Vector2();
    public float width;
    public float height;

    private float lerpAlpha = -1f;
    private Vector2 destination = new Vector2();

    public float scale = 1f;
    public float alpha = 1f;

    public boolean flipped = false;

    public ImageButton(TextureRegion image) {
        this(image, 0f, 0f);
    }

    public ImageButton(TextureRegion image, float x, float y) {
        this(image, x, y, 0f);
    }

    public ImageButton(TextureRegion image, float x, float y, float padding) {
        setImage(image);
        pos.set(x, y);
        this.padding = padding;
        setPosition(x, y);
    }

    public void setImage(TextureRegion image) {
        this.image = image;
        if (image != null) {
            width = image.getRegionWidth();
            height = image.getRegionHeight();
        }
    }

    public void setPosition(float x, float y) {
        pos.set(x, y);
    }

    public void lerpTo(float x, float y) {
        lerpTo(x, y, 4f);
    }

    public void lerpTo(float x, float y, float a) {
        destination.set(x, y);
        lerpAlpha = a;
    }

    public boolean containsPoint(Vector3 p) {
        return containsPoint(p.x, p.y);
    }

    public boolean containsPoint(float x, float y) {
        float scaledWidth = width * scale;
        float scaledHeight = height * scale;
        return pos.x - scaledWidth / 2 - padding <= x
                && pos.x + scaledWidth / 2 + padding >= x
                && pos.y - scaledHeight / 2 - padding <= y
                && pos.y + scaledHeight / 2 + padding >= y;
    }

    public void update(float dt) {
        if (lerpAlpha >= 0f) {
            pos.lerp(destination, lerpAlpha * dt);
        }
        if (Utils.diff(destination, pos) < 0.1f) {
            lerpAlpha = -1f;
        }
    }

    public void render(SpriteBatch sb) {
        if (image != null) {
            float scaledWidth = width * scale;
            float scaledHeight = height * scale;
            float temp = sb.getColor().a;
            Utils.setAlpha(sb, alpha);
            if (flipped) {
                Utils.drawHFlip(sb, image, pos.x + scaledWidth / 2, pos.y - scaledHeight / 2, scaledWidth, scaledHeight);
            } else {
                sb.draw(image, pos.x - scaledWidth / 2, pos.y - scaledHeight / 2, scaledWidth, scaledHeight);
            }
            Utils.setAlpha(sb, temp);
        }
    }

}
