package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.Accessory;
import com.distraction.fs2j.tilemap.player.Player;

public class SantaHat extends Accessory {

    private TextureRegion image;
    private TextureRegion imageR;

    public SantaHat(Player player) {
        super(player);
        image = player.context.getImage("santa_hat");
        imageR = player.context.getImage("santa_hat_r");
        offset.x = -17f;
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
            case Player.IDLER:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 14f : 13f;
                break;
            case Player.CROUCH:
            case Player.CROUCHR:
                offset.y = 10f;
                break;
            case Player.JUMP:
            case Player.JUMPR:
                offset.y = 18f;
                break;
        }
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        if (!player.forward()) normalRender(sb, imageR);
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        if (player.forward()) normalRender(sb, image);
    }
}
