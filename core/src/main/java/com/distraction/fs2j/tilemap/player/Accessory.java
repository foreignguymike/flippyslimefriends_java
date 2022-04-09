package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Utils;

public abstract class Accessory {

    public Player player;
    public String key;

    protected Vector2 offset = new Vector2(0f, 15f);

    public Accessory(Player player) {
        this.player = player;
    }

    public void update(float dt) {
    }

    public void renderBehind(SpriteBatch sb) {
    }

    public void renderFront(SpriteBatch sb) {
    }

    protected void normalRender(SpriteBatch sb, TextureRegion image) {
        normalRender(sb, image, player.right());
    }

    protected void normalRender(SpriteBatch sb, TextureRegion image, boolean right) {
        Vector3 isop = player.isop;
        if (right) sb.draw(image, isop.x + offset.x, isop.y + player.p.z + offset.y);
        else Utils.drawHFlip(sb, image, isop.x - offset.x, isop.y + player.p.z + offset.y);
    }
}
