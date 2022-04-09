package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;

public class SuperJumpLight extends TileObject {

    private TextureRegion image;
    private float duration = 1f;
    private float time = 0f;

    public SuperJumpLight(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        image = context.getImage("superjump");
        setPositionFromTile(row, col);
        p.z = 0f;
        speed = 15f;
    }

    @Override
    public void update(float dt) {
        time += dt;
        p.z += speed * dt;
        if (time > duration) {
            remove = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        sb.setColor(1, 1, 1, 1);
        if (duration - time < 0.5f) {
            Utils.setAlpha(sb, (duration - time) / 0.5f);
        }
        sb.draw(image, isop.x - image.getRegionWidth() / 2f, isop.y - image.getRegionHeight() / 2f + p.z);
        sb.setColor(1, 1, 1, 1);
    }
}
