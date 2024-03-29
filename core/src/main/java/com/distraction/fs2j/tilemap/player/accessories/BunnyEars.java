package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class BunnyEars extends Accessory {

    private final TextureRegion[] sprites;
    private final Vector2 offset2 = new Vector2();

    public BunnyEars(Player player) {
        super(player);
        sprites = AccessoryType.BUNNY_EARS.getSprites(player.context);
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.x = -8;
                offset.y = animationSet.currentAnimation.currentFrame() == 0 ? 15f : 14f;
                offset2.x = 18;
                break;
            case Player.CROUCH:
                offset.x = -10;
                offset.y = 9f;
                offset2.x = 20;
                break;
            case Player.JUMP:
                offset.x = -6;
                offset.y = 18f;
                offset2.x = 15;
                break;
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        if (player.forward()) normalRender(sb, sprites[0]);
        else normalRender(sb, sprites[2]);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        if (player.forward())
            normalRender(sb, sprites[1], player.right(), offset2.x * (player.right() ? 1 : -1), 0);
        else normalRender(sb, sprites[3], player.right(), offset2.x * (player.right() ? 1 : -1), 0);
    }

}
