package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class WizardHat extends Accessory {

    private TextureRegion image;

    public WizardHat(Player player) {
        super(player);
        image = AccessoryType.WIZARD_HAT.getSprites(player.context)[0];
        offset.x = -15f;
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 14f : 13f;
                break;
            case Player.CROUCH:
                offset.y = 10f;
                break;
            case Player.JUMP:
                offset.y = 18f;
                break;
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        normalRender(sb, image);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        normalRender(sb, image);
    }
}
