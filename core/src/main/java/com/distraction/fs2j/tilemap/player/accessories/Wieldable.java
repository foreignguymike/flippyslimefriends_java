package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class Wieldable extends Accessory {

    private static final float MAX_DEG = 30f;

    protected final TextureRegion image;
    protected final TextureRegion image2;

    private float time;
    protected float deg;

    protected final float xoffset;
    protected final float yoffset;

    public Wieldable(Player player, AccessoryType type, float xoffset, float yoffset) {
        super(player);
        TextureRegion[] sprites = type.getSprites(player.context);
        image = sprites[0];
        if (sprites.length > 1) image2 = sprites[1];
        else image2 = null;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    @Override
    public void update(float dt) {
        time += dt;
        float idleDeg = MathUtils.sin(time * 3) * 4 - 2;
        deg = MathUtils.lerp(deg, player.moving ? player.right() ? MAX_DEG : -MAX_DEG : idleDeg, 8f * dt);
        if (player.forward()) offset.x = player.right() ? -xoffset : 0f;
        else offset.x = player.right() ? xoffset : xoffset + 5f;
        offset.y = yoffset;
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        Vector3 isop = player.isop;
        if (player.right() && player.forward() || !player.right() && !player.forward()) {
            sb.draw(
                    player.forward() ? image : image2 != null ? image2 : image,
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
                    player.forward() ? image : image2 != null ? image2 : image,
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
