package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.Player;

public abstract class Accessory {

    public Player player;

    protected Vector2 offset = new Vector2(0f, 15f * Constants.SCALE);
    private TextureRegion pixel;

    public Accessory(Player player) {
        this.player = player;
        pixel = player.context.getImage("pixel");
    }

    public void update(float dt) {
    }

    /**
     * Render things that should go behind the player in game space.
     */
    public void renderBehind(SpriteBatch sb) {
    }

    /**
     * Render things that should go in front of the player in game space.
     */
    public void renderFront(SpriteBatch sb) {
    }

    protected void normalRender(SpriteBatch sb, TextureRegion image) {
        normalRender(sb, image, player.right(), 0, 0);
    }

    protected void normalRender(SpriteBatch sb, TextureRegion image, boolean right) {
        normalRender(sb, image, right, 0, 0);
    }

    protected void normalRender(SpriteBatch sb, TextureRegion image, float xo, float yo) {
        normalRender(sb, image, player.right(), xo, yo);
    }

    protected void normalRender(SpriteBatch sb, TextureRegion image, boolean right, float xo, float yo) {
        Vector3 isop = player.isop;
        if (right)
            sb.draw(image, isop.x - image.getRegionWidth() / 2f + offset.x * Constants.SCALE + xo, isop.y + player.p.z + offset.y * Constants.SCALE + yo);
        else
            Utils.drawHFlip(sb, image, isop.x + image.getRegionWidth() / 2f - offset.x * Constants.SCALE + xo, isop.y + player.p.z + offset.y * Constants.SCALE + yo);
    }
}
