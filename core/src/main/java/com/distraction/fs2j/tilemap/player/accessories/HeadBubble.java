package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class HeadBubble extends Accessory {

    private final TextureRegion image;
    private final TextureRegion pixel;

    public HeadBubble(Player player) {
        super(player);
        image = AccessoryType.HEAD_BUBBLE.getSprites(player.context)[0];
        offset.x = -4;
        pixel = player.context.getImage("pixel");
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
        Color c = sb.getColor();
        sb.setColor(player.playerRenderer.skin.color);
        normalRender(sb, image);

        sb.setColor(1, 1, 1, 1);
        Vector3 isop = player.isop;
        if (player.right())
            sb.draw(pixel, isop.x + offset.x - 2, isop.y + player.p.z + offset.y + 11);
        else sb.draw(pixel, isop.x + offset.x + 9, isop.y + player.p.z + offset.y + 11);
        sb.setColor(c);
    }
}
