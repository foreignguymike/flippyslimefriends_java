package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Animation;
import com.distraction.fs2j.tilemap.player.Accessory;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class Fish extends Accessory {

    private Animation animation;
    private float time = 0f;
    private boolean right = false;
    private float flipping = 0f;

    public Fish(Player player) {
        super(player);
        animation = new Animation(AccessoryType.FISH.getSprites(player.context), 0.25f);
        key = "fish";
    }

    @Override
    public void update(float dt) {
        time += dt;
        float siny = MathUtils.sin(2 * time);
        flipping = MathUtils.cos(2 * time + MathUtils.PI / 2f);
        offset.x = 30 * MathUtils.cos(2 * time);
        offset.y = 10 * siny + 4;
        right = siny < 0f;
        animation.update(dt);
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        Vector3 isop = player.isop;
        if (player.forward() && !right || !player.forward() && right) {
            sb.setColor(1, 1, 1, 1);
            sb.draw(
                    animation.getImage(),
                    isop.x + offset.x,
                    isop.y + player.p.z + offset.y,
                    animation.getImage().getRegionWidth() * flipping,
                    animation.getImage().getRegionHeight() * 1f
            );
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        Vector3 isop = player.isop;
        if (player.forward() && right || !player.forward() && !right) {
            sb.setColor(1, 1, 1, 1);
            sb.draw(
                    animation.getImage(),
                    isop.x + offset.x,
                    isop.y + player.p.z + offset.y,
                    animation.getImage().getRegionWidth() * flipping,
                    animation.getImage().getRegionHeight() * 1f
            );
        }
    }
}
