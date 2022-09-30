package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;

public class FinishTile extends TileObject {

    private final TextureRegion on;
    private final TextureRegion off;

    public boolean active = false;

    public FinishTile(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        on = context.getImage("finishtileon");
        off = context.getImage("finishtileoff");

        setPositionFromTile(row, col);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        Utils.drawCentered(sb, active ? on : off, isop.x, isop.y);
    }
}
