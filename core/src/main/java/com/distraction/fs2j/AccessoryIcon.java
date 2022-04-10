package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AccessoryIcon extends ImageButton {

    private TextureRegion iconImage;
    private Vector2 offset = new Vector2();
    public boolean disabled = false;

    public AccessoryIcon(Context context, TextureRegion image, float x, float y) {
        super(context.getImage("accessoryiconbg"), x, y);
        iconImage = image;
    }

    public void setOffset(float x, float y) {
        offset.set(x, y);
    }

    public void setIconImage(TextureRegion iconImage) {
        this.iconImage = iconImage;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (disabled) sb.setColor(0.5f, 0.5f, 0.5f, 1f);
        else sb.setColor(1, 1, 1, 1);
        super.render(sb);
        if (iconImage != null) {
            sb.draw(iconImage, pos.x - width / 2 + offset.x, pos.y - height / 2 + offset.y);
        }
    }
}
