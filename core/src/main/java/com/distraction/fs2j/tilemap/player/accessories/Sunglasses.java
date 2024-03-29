package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class Sunglasses extends Accessory {

    private final TextureRegion[] sprites;

    public Sunglasses(Player player) {
        super(player);
        sprites = AccessoryType.SUNGLASSES.getSprites(player.context);
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                if (player.forward())
                    offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 8f : 7f;
                else
                    offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 12f : 11f;
                break;
            case Player.CROUCH:
                if (player.forward()) offset.y = 5f;
                else offset.y = 8f;
                break;
            case Player.JUMP:
                if (player.forward()) offset.y = 12f;
                else offset.y = 14f;
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
