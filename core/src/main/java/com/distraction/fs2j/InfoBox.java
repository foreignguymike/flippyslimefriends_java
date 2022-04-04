package com.distraction.fs2j;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.tilemap.data.GameColor;

public class InfoBox extends ImageButton {

    private Color color;

    private TextureRegion[] patch;
    private TextureRegion pixel;

    private float left;
    private float right;
    private float top;
    private float bottom;

    public InfoBox(Context context, float x, float y, float width, float height) {
        this(context, x, y, width, height, GameColor.PURPLE);
    }

    public InfoBox(Context context, float x, float y, float width, float height, Color color) {
        super(context.getImage("pixel"), x, y);
        this.color = color;

        patch = new TextureRegion[8];
        for (int i = 0; i < patch.length; i++) {
            patch[i] = context.getImage("infobox", i);
        }
        pixel = context.getImage("pixel");

        left = x - width / 2;
        right = x + width / 2;
        top = y + height / 2;
        bottom = y - height / 2;

        this.width = width;
        this.height = height;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(pixel, left, bottom, width, height);
        sb.setColor(GameColor.DARK_TEAL);
        sb.draw(pixel, left, bottom, width / 5, height);
        sb.draw(pixel, left + 2 * width / 5, bottom, width / 5, height);
        sb.draw(pixel, left + 4 * width / 5, bottom, width / 5, height);
        sb.setColor(1, 1, 1, 1);
        Utils.drawCentered(sb, patch[1], left, top, width, patch[1].getRegionHeight());
        Utils.drawCentered(sb, patch[6], left, bottom, width, patch[6].getRegionHeight());
        Utils.drawCentered(sb, patch[3], left, bottom, patch[3].getRegionWidth(), height);
        Utils.drawCentered(sb, patch[4], right, bottom, patch[3].getRegionWidth(), height);
        Utils.drawCentered(sb, patch[0], left, top);
        Utils.drawCentered(sb, patch[2], right, top);
        Utils.drawCentered(sb, patch[5], left, bottom);
        Utils.drawCentered(sb, patch[7], right, bottom);
    }
}
