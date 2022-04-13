package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class CatEars extends Accessory {

    private TextureRegion[] sprites;

    public CatEars(Player player) {
        super(player);
        sprites = AccessoryType.CAT_EARS.getSprites(player.context);
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.x = -12;
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 15f : 14f;
                break;
            case Player.CROUCH:
                offset.x = -15;
                offset.y = 9f;
                break;
            case Player.JUMP:
                offset.x = -13;
                offset.y = 18f;
                break;
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        normalRender(sb, sprites[0]);
        if (player.forward()) normalRender(sb, sprites[2], player.right(), 0, -1);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        normalRender(sb, sprites[1], player.right(), player.right() ? 15 : -15, player.forward() ? 1 : -3);
    }

}
