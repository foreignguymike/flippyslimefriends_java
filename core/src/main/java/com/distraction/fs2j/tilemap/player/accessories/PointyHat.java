package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class PointyHat extends Accessory {

    private final TextureRegion[] sprites;

    public PointyHat(Player player, AccessoryType type) {
        super(player);
        sprites = type.getSprites(player.context);
        offset.x = -3f;
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 12f : 11f;
                break;
            case Player.CROUCH:
                offset.y = 8f;
                break;
            case Player.JUMP:
                offset.y = 16f;
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
