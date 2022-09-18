package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class AngelWings extends Accessory {

    private final TextureRegion[] sprites;

    private float time = 0f;
    private float deg;

    public AngelWings(Player player) {
        super(player);
        sprites = AccessoryType.ANGEL_WINGS.getSprites(player.context);
    }

    @Override
    public void update(float dt) {
        time += dt;
        String key = player.playerRenderer.animationSet.currentAnimationKey;
        if (key.equals(Player.JUMP))
            if (player.getRemainingDistance() < player.totalDist / 2)
                deg = MathUtils.lerp(deg, 50, 8f * dt);
            else deg = MathUtils.lerp(deg, -50, 8f * dt);
        else deg = MathUtils.lerp(deg, MathUtils.sin(time * 2) * 15, 8 * dt);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        Vector3 isop = player.isop;
        TextureRegion image = sprites[player.forward() ? 0 : 1];
        Utils.drawRotated(
                sb,
                image,
                isop.x - image.getRegionWidth() / 2f + offset.x - 16 + (player.right() ? 0 : 62),
                isop.y + player.p.z + offset.y,
                deg * (player.right() ? -1 : 1),
                image.getRegionWidth() * (player.right() ? 1 : -1),
                image.getRegionHeight() / 2f,
                image.getRegionWidth() * (player.right() ? 1 : -1),
                image.getRegionHeight(),
                1);

        Utils.drawRotated(
                sb,
                image,
                isop.x - image.getRegionWidth() / 2f + offset.x - 16 + (player.right() ? 60 : 2),
                isop.y + player.p.z + offset.y + (player.forward() ? 2 : -2),
                deg * (player.right() ? 1 : -1),
                image.getRegionWidth() * (player.right() ? -1 : 1),
                image.getRegionHeight() / 2f,
                image.getRegionWidth() * (player.right() ? -1 : 1),
                image.getRegionHeight(),
                1);
    }
}
