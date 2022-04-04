package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;

public class Bubble extends TileObject {

    private TextureRegion bubbleMaker;
    public BubbleBase bubbleBase;

    public Bubble(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        setPositionFromTile(row, col);

        bubbleMaker = context.getImage("bubblemaker");
        bubbleBase = new BubbleBase(context, tileMap, row, col);
    }

    @Override
    public void update(float dt) {
        bubbleBase.setPosition(p.x, p.y);
    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        Utils.drawCentered(sb, bubbleMaker, isop.x, isop.y);
    }
}

