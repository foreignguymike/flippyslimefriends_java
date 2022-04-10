package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.Accessory;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class HeadBubble extends Accessory {

    private TextureRegion image;

    public HeadBubble(Player player) {
        super(player);
        image = AccessoryType.HEAD_BUBBLE.getSprites(player.context)[0];
        offset.x = -10f;
        key = "headbubble";
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 18f : 17f;
                break;
            case Player.CROUCH:
                offset.y = 12f;
                break;
            case Player.JUMP:
                offset.y = 22f;
                break;
        }
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        sb.setColor(player.playerRenderer.skin.color);
        normalRender(sb, image);
    }
}
