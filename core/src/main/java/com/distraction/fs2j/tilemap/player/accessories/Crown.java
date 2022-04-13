package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class Crown extends Accessory {

    private TextureRegion[] sprites;

    public Crown(Player player) {
        super(player);
        sprites = AccessoryType.CROWN.getSprites(player.context);
        offset.x = -6f;
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 16f : 15f;
                break;
            case Player.CROUCH:
                offset.y = 10f;
                break;
            case Player.JUMP:
                offset.y = 20f;
                break;
        }
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        if (!player.forward()) normalRender(sb, sprites[1]);
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        if (player.forward()) normalRender(sb, sprites[0]);
    }

}
