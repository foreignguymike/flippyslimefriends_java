package com.distraction.fs2j.tilemap.player.accessories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Player;

public class DiamondSpin extends Accessory {

    private static final int MAX_INTERVAL = 10;

    private final TextureRegion image;
    private final TextureRegion pixel;

    private float time = 0f;
    boolean right = false;
    private int interval = 0;

    private final DiamondInfo[] infos = new DiamondInfo[30];

    static class DiamondInfo {
        Vector2 offset = new Vector2();
        boolean right = false;
        public void set(DiamondInfo other) {
            offset.x = other.offset.x;
            offset.y = other.offset.y;
            right = other.right;
        }
    }

    public DiamondSpin(Player player) {
        super(player);
        image = AccessoryType.DIAMOND.getSprites(player.context)[0];
        pixel = player.context.getImage("pixel");
        for (int i = 0; i < infos.length; i++) {
            infos[i] = new DiamondInfo();
        }
    }

    @Override
    public void update(float dt) {
        time += dt;
        float siny = MathUtils.sin(2 * time);
        offset.x = 30 * MathUtils.cos(2 * time);
        offset.y = 10 * siny + 4;
        right = siny < 0f;
        interval++;
        if (interval == MAX_INTERVAL) {
            interval = 0;
            infos[0].offset.x = 30 * MathUtils.cos(2 * time) + MathUtils.random(-2, 2);
            infos[0].offset.y = 10 * siny + 4 + MathUtils.random(-2, 2);
            infos[0].right = siny < 0f;
            for (int i = infos.length - 2; i > 0; i--) {
                infos[i].set(infos[i - 1]);
            }
        }
        for (int i = 0; i < infos.length; i++) {
            infos[i].offset.y += 10 * dt;
        }
    }

    @Override
    public void renderBehind(SpriteBatch sb) {
        Vector3 isop = player.isop;
        for (int i = infos.length - 1; i >= 0; i--) {
            DiamondInfo info = infos[i];
            if (player.forward() && !info.right || !player.forward() && info.right) {
                Utils.setAlpha(sb, 1f - 1f * i / infos.length);
                sb.draw(
                        pixel,
                        isop.x + info.offset.x,
                        isop.y + player.p.z + info.offset.y,
                        2,
                        2
                );
            }
        }
        sb.setColor(GameColor.WHITE);
        if (player.forward() && !right || !player.forward() && right) {
            sb.draw(
                    image,
                    isop.x + offset.x - image.getRegionWidth() / 2f,
                    isop.y + player.p.z + offset.y,
                    image.getRegionWidth(),
                    image.getRegionHeight()
            );
        }
    }

    @Override
    public void renderFront(SpriteBatch sb) {
        Vector3 isop = player.isop;
        for (int i = infos.length - 1; i >= 0; i--) {
            DiamondInfo info = infos[i];
            if (player.forward() && info.right || !player.forward() && !info.right) {
                Utils.setAlpha(sb, 1f - 1f * i / infos.length);
                sb.draw(
                        pixel,
                        isop.x + info.offset.x,
                        isop.y + player.p.z + info.offset.y,
                        2,
                        2
                );
            }
        }
        sb.setColor(GameColor.WHITE);
        if (player.forward() && right || !player.forward() && !right) {
            sb.draw(
                    image,
                    isop.x + offset.x - image.getRegionWidth() / 2f,
                    isop.y + player.p.z + offset.y,
                    image.getRegionWidth(),
                    image.getRegionHeight()
            );
        }
    }
}