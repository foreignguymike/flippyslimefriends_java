package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextButton extends ImageButton {

    private final TextFont textFont;

    public TextButton(Context context, String text, TextureRegion bgImage, float x, float y, float padding) {
        super(bgImage, x, y, padding);
        textFont = new TextFont(context, TextFont.FontType.NORMAL2, text, true, x, y);
    }

    public void setText(String text) {
        textFont.setText(text);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        textFont.render(sb);
    }
}
