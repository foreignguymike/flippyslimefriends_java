package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;

public class Ice extends TileObject {

    private final TextureRegion image;

    public Ice(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        setPositionFromTile(row, col);
        image = context.getImage("ice");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        Utils.drawAlpha(sb, image, isop.x - image.getRegionWidth() / 2f, isop.y - image.getRegionHeight() / 2f, 0.85f);
    }
}
