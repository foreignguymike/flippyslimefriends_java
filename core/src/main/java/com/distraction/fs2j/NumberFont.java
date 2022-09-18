package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NumberFont {

    public enum NumberSize {
        SMALL("num1"),
        MEDIUM("num2"),
        LARGE("num3");

        String key;

        NumberSize(String key) {
            this.key = key;
        }
    }

    private final boolean centerAlign;

    private final TextureRegion[] images;
    private final TextureRegion nan;
    private int length = 0;

    public int num;

    public NumberFont(Context context, boolean centerAlign, NumberSize size) {
        this.centerAlign = centerAlign;
        images = new TextureRegion[10];
        for (int i = 0; i < images.length; i++) {
            images[i] = context.getImage(size.key, i);
        }
        nan = context.getImage("-");
    }

    public NumberFont(Context context, boolean centerAlign, NumberSize size, int num) {
        this.centerAlign = centerAlign;
        images = new TextureRegion[10];
        for (int i = 0; i < images.length; i++) {
            images[i] = context.getImage(size.key, i);
        }
        nan = context.getImage("-");
        setNum(num);
    }

    private int charToInt(char c) {
        if (c < '0' || c > '9') throw new NumberFormatException("invalid char");
        else return (int) c - (int) '0';
    }

    public void setNum(int num) {
        if (num < 0) {
            this.num = num;
            length = nan.getRegionWidth();
            return;
        }
        String s = String.valueOf(num);
        length = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '-') {
                int n = charToInt(c);
                length += images[n].getRegionWidth() + 1;
            }
        }
        this.num = num;
    }

    public int getTotalWidth() {
        return length;
    }

    public void render(SpriteBatch sb, float x, float y) {
        if (num < 0) {
            if (centerAlign) sb.draw(nan, x - length / 2f, y - nan.getRegionHeight() / 2f);
            else sb.draw(nan, x, y - nan.getRegionHeight() / 2f);
            return;
        }
        String s = String.valueOf(num);
        int offset = 0;
        if (centerAlign) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int n = charToInt(c);
                sb.draw(images[n], x + offset - length / 2f, y - images[n].getRegionHeight() / 2f);
                offset += images[n].getRegionWidth() + 1;
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int n = charToInt(c);
                sb.draw(images[n], x + offset, y - images[n].getRegionHeight() / 2f);
                offset += images[n].getRegionWidth() + 1;
            }
        }
    }

}
