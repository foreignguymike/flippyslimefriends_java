package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class IconButton extends ImageButton {

    private TextureRegion iconImage;

    public IconButton(TextureRegion iconImage, TextureRegion bgImage) {
        this(iconImage, bgImage, 0, 0, 0);
    }

    public IconButton(TextureRegion iconImage, TextureRegion bgImage, float x, float y) {
        this(iconImage, bgImage, x, y, 0);
    }

    public IconButton(TextureRegion iconImage, TextureRegion bgImage, float x, float y, float padding) {
        super(bgImage, x, y, padding);
        this.iconImage = iconImage;
    }

    public void setIconImage(TextureRegion iconImage) {
        this.iconImage = iconImage;
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        float scaledWidth = iconImage.getRegionWidth() * scale;
        float scaledHeight = iconImage.getRegionHeight() * scale;
        sb.draw(iconImage, pos.x - scaledWidth / 2, pos.y - scaledHeight / 2, scaledWidth, scaledHeight);
    }
}