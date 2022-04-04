package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.TileMap;

public class TileLight extends TileObject {

    public static final float LIFE_TIME = 1f;

    private float timer = 0f;
    private TextureRegion image;

    public TileLight(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        setPositionFromTile(row, col);
        height = 0f;
        image = context.getImage("tilelight");
    }

    @Override
    public void update(float dt) {
        timer += dt;
        height = Math.min(image.getRegionHeight(), image.getRegionHeight() * timer / LIFE_TIME * 5f);
        if (timer >= LIFE_TIME) remove = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        sb.setColor(1, 1, 1, 1f - timer / LIFE_TIME);
        sb.draw(image.getTexture(),
                isop.x - image.getRegionWidth() / 2f,
                isop.y - TileMap.TILE_IHEIGHT / 2f + 2,
                image.getRegionWidth(),
                height,
                image.getRegionX(),
                image.getRegionY() + image.getRegionHeight(),
                image.getRegionWidth(),
                (int) -height,
                false,
                true);
        sb.setColor(1, 1, 1, 1);
    }
}
