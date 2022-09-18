package com.distraction.fs2j;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextFont {

    private final TextureRegion[] font;

    private String text;
    private boolean centered;
    private float width;

    public float x;
    public float y;

    public TextFont(Context context, String text, float x, float y) {
        this(context, text, false, x, y);
    }

    public TextFont(Context context, String text, boolean centered, float x, float y) {
        font = new TextureRegion[37];
        for (int i = 0; i < font.length; i++) font[i] = context.getImage("font1", i);

        setText(text);
        this.centered = centered;
        this.x = x;
        this.y = y;
    }

    public void setText(String text) {
        if (text == null) text = "";
        if (!text.equals(this.text)) {
            text = text.toUpperCase().replaceAll("[^A-Z0-9 ]", "");
            this.text = text;
            calculateWidth(text);
        }
    }

    public String getText() {
        return text;
    }

    private void calculateWidth(String text) {
        width = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = getIndex(text.charAt(i));
            if (index != -1) {
                width += font[index].getRegionWidth() + 1;
            }
        }
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
    }

    private int getIndex(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        if (c == ' ') return 36;
        return -1;
    }

    public void render(SpriteBatch sb) {
        float sx = centered ? x - width / 2f : x;
        int offset = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = getIndex(text.charAt(i));
            if (index != -1) {
                sb.draw(font[index], sx + offset, y);
                offset += font[index].getRegionWidth() + 1;
            }
        }
    }
}
