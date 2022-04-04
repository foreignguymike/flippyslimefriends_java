package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextButton extends ImageButton {

    private TextureRegion textImage;

    public TextButton(TextureRegion textImage, TextureRegion bgImage) {
        this(textImage, bgImage, 0, 0, 0);
    }
    public TextButton(TextureRegion textImage, TextureRegion bgImage, float x, float y) {
        this(textImage, bgImage, x, y, 0);
    }

    public TextButton(TextureRegion textImage, TextureRegion bgImage, float x, float y, float padding) {
        super(bgImage, x, y, padding);
        this.textImage = textImage;
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        float scaledWidth = textImage.getRegionWidth() * scale;
        float scaledHeight = textImage.getRegionHeight() * scale;
        sb.draw(textImage, pos.x - scaledWidth / 2, pos.y - scaledHeight / 2, scaledWidth, scaledHeight);
    }
}
