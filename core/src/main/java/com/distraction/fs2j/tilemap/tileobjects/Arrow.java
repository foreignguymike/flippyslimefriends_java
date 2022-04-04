package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Animation;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Direction;

public class Arrow extends TileObject {

    public Direction direction;

    private Animation animation;

    public Arrow(Context context, TileMap tileMap, int row, int col, Direction direction) {
        super(context, tileMap);
        setPositionFromTile(row, col);
        this.direction = direction;

        animation = new Animation(new TextureRegion[]{
                context.getImage("arrow", 1),
                context.getImage("arrow", 2)},
                0.5f
        );
    }

    @Override
    public void update(float dt) {
        animation.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        TextureRegion image = animation.getImage();
        if (direction == Direction.RIGHT)
            sb.draw(image, isop.x - image.getRegionWidth() / 2f, isop.y - image.getRegionHeight() / 2f - 1);
        else if (direction == Direction.DOWN)
            Utils.drawHFlip(sb, image, isop.x + image.getRegionWidth() / 2f, isop.y - image.getRegionHeight() / 2f);
        else if (direction == Direction.UP)
            Utils.drawVFlip(sb, image, isop.x - image.getRegionWidth() / 2f, isop.y + image.getRegionHeight() / 2f + 1);
        else if (direction == Direction.LEFT)
            Utils.drawVHFlip(sb, image, isop.x + image.getRegionWidth() / 2f, isop.y + image.getRegionHeight() / 2f + 1);
    }
}
