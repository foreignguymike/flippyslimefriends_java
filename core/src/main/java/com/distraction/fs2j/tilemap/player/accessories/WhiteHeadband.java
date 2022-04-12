package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class WhiteHeadband extends Accessory {

    private TextureRegion[] sprites;

    public WhiteHeadband(Player player) {
        super(player);
        sprites = AccessoryType.WHITEHEADBAND.getSprites(player.context);
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 9f : 8f;
                break;
            case Player.CROUCH:
                offset.y = 6f;
                break;
            case Player.JUMP:
                offset.y = 15f;
                break;
        }
        offset.x = player.forward() ? -17f : -15f;
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        normalRender(sb, sprites[player.forward() ? 0 : 1]);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        normalRender(sb, sprites[player.forward() ? 0 : 1]);
    }

}
