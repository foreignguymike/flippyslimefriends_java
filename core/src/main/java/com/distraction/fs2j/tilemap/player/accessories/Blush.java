package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class Blush extends Accessory {

    private final TextureRegion image;

    public Blush(Player player) {
        super(player);
        image = AccessoryType.BLUSH.getSprites(player.context)[0];
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 5f : 4f;
                break;
            case Player.CROUCH:
                offset.y = 5f;
                break;
            case Player.JUMP:
                offset.y = 10f;
                break;
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        sb.setColor(GameColor.WHITE);
        normalRender(sb, image, player.right() ? -5f : 5f, 0f);
        normalRender(sb, image, player.right() ? 8f : -8f, 1f);
    }

}
