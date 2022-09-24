package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextFont {

    private static final int PERIOD = 36;
    private static final int BANG = 37;
    private static final int DASH = 38;
    private static final int SPACE = 100;

    public enum FontType {
        TINY("font0"),
        FONT1("font1"),
        FONT2("font2"),
        FONT3("font3");

        private final String name;

        FontType(String name) {
            this.name = name;
        }
    }

    private final TextureRegion[] font;

    private String text;
    private final boolean centered;
    private float width;

    public float x;
    public float y;

    public TextFont(Context context, String text, float x, float y) {
        this(context, text, false, x, y);
    }

    public TextFont(Context context, String text, boolean centered, float x, float y) {
        this(context, FontType.FONT1, text, centered, x, y);
    }

    public TextFont(Context context, FontType fontType, String text, boolean centered, float x, float y) {
        font = new TextureRegion[39];
        for (int i = 0; i < font.length; i++) {
            try {
                font[i] = context.getImage(fontType.name, i);
            } catch (IllegalStateException ignored) {
            }
        }

        setText(text);
        this.centered = centered;
        setPosition(x, y);
    }

    public void setText(String text) {
        if (text == null) text = "";
        if (!text.equals(this.text)) {
            text = text.toUpperCase().replaceAll("[^A-Z0-9 .!]", "");
            this.text = text;
            calculateWidth(text);
        }
    }

    public String getText() {
        return text;
    }

    public float getTotalWidth() {
        return width;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private void calculateWidth(String text) {
        width = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = getIndex(text.charAt(i));
            if (index != -1) {
                if (index == SPACE) {
                    width += font[0].getRegionWidth() + 1;
                } else {
                    width += font[index].getRegionWidth() + 1;
                }
            }
        }
    }

    private int getIndex(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        if (c == '.') return PERIOD;
        if (c == '!') return BANG;
        if (c == '-') return DASH;
        if (c == ' ') return SPACE;
        return -1;
    }

    public void render(SpriteBatch sb) {
        float sx = centered ? x - width / 2f : x;
        int offset = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = getIndex(text.charAt(i));
            if (index != -1) {
                if (index == SPACE) {
                    offset += font[0].getRegionWidth() + 1;
                } else {
                    sb.draw(font[index], sx + offset, y);
                    offset += font[index].getRegionWidth() + 1;
                }
            }
        }
    }
}
