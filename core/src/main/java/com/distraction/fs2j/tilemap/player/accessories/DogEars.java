package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.Accessory;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class DogEars extends Accessory {

    private TextureRegion image;

    public DogEars(Player player) {
        super(player);
        image = AccessoryType.DOGEARS.getSprites(player.context)[0];
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.x = -15;
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 11f : 10f;
                break;
            case Player.CROUCH:
                offset.x = -18;
                offset.y = 5f;
                break;
            case Player.JUMP:
                offset.x = -13;
                offset.y = 15f;
                break;
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        normalRender(sb, image);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        normalRender(sb, image, !player.right(), 0, 1);
    }
}
