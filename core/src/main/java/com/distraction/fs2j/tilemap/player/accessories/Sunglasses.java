package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AnimationSet;
import com.distraction.fs2j.tilemap.player.Accessory;
import com.distraction.fs2j.tilemap.player.Player;

public class Sunglasses extends Accessory {

    private TextureRegion image;
    private TextureRegion imageR;

    public Sunglasses(Player player) {
        super(player);
        image = player.context.getImage("sunglasses");
        imageR = player.context.getImage("sunglasses_r");
    }

    @Override
    public void update(float dt) {
        AnimationSet animationSet = player.playerRenderer.animationSet;
        switch (animationSet.currentAnimationKey) {
            case Player.IDLE:
                offset.set(-11f, animationSet.currentAnimation.currentFrame() == 0 ? 8f : 7f);
                break;
            case Player.IDLER:
                offset.set(5f, animationSet.currentAnimation.currentFrame() == 0 ? 12f : 11f);
                break;
            case Player.CROUCH:
                offset.set(-11f, 5f);
                break;
            case Player.CROUCHR:
                offset.set(5f, 8f);
                break;
            case Player.JUMP:
                offset.set(-11f, 12f);
                break;
            case Player.JUMPR:
                offset.set(5f, 12f);
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
