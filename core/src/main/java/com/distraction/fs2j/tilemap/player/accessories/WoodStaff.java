package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class WoodStaff extends Accessory {

    private static final float MAX_DEG = 30f;

    private TextureRegion image;

    private float time;
    private float deg;
    private float idleDeg;

    public WoodStaff(Player player) {
        super(player);
        image = AccessoryType.WOOD_STAFF.getSprites(player.context)[0];
    }

    @Override
    public void update(float dt) {
        time += dt;
        idleDeg = MathUtils.sin(time * 3) * 4 - 2;
        deg = MathUtils.lerp(deg, player.moving ? player.right() ? MAX_DEG : -MAX_DEG : idleDeg, 8f * dt);
        if (player.forward()) offset.x = player.right() ? -10f : 0f;
        else offset.x = player.right() ? 10f : 20f;
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        Vector3 isop = player.isop;
        if (player.right() && player.forward() || !player.right() && !player.forward()) {
            sb.draw(
                    image,
                    isop.x + offset.x - image.getRegionWidth() / 2f,
                    isop.y + player.p.z + offset.y - image.getRegionHeight() / 2f,
                    image.getRegionWidth() / 2f,
                    image.getRegionHeight() / 2f,
                    (player.right() ? 1f : -1f) * image.getRegionWidth(),
                    1f * image.getRegionHeight(),
                    1f,
                    1f,
                    deg
            );
        }
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        Vector3 isop = player.isop;
        if (!player.right() && player.forward() || player.right() && !player.forward()) {
            sb.draw(
                    image,
                    isop.x + offset.x - image.getRegionWidth() / 2f,
                    isop.y + player.p.z + offset.y - image.getRegionHeight() / 2f,
                    image.getRegionWidth() / 2f,
                    image.getRegionHeight() / 2f,
                    (player.right() ? 1f : -1f) * image.getRegionWidth(),
                    1f * image.getRegionHeight(),
                    1f,
                    1f,
                    deg
            );
        }
    }
}
