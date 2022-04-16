package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class NumberLabel {
    private TextureRegion image;
    private Vector2 pos;

    public NumberFont font;

    public NumberLabel(Context context, TextureRegion image, Vector2 pos) {
        this(context, image, pos, -1);
    }

    public NumberLabel(Context context, TextureRegion image, Vector2 pos, int num) {
        this(context, image, pos, num, NumberFont.NumberSize.MEDIUM);
    }

    public NumberLabel(Context context, TextureRegion image, Vector2 pos, int num, NumberFont.NumberSize size) {
        this.image = image;
        this.pos = pos;

        font = new NumberFont(context, false, size);
        font.num = num;
    }

    public void render(SpriteBatch sb) {
        sb.draw(image, pos.x - image.getRegionWidth() / 2f, pos.y - image.getRegionHeight() / 2f);
        font.render(sb, pos.x + image.getRegionWidth() / 2f + 5f * Constants.SCALE, pos.y);
    }
}
