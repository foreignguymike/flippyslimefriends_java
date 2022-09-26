package com.distraction.fs2j;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelBackground {

    private static final Map<Area, Color> colorMap = new HashMap<>() {{
        put(Area.MEADOW, GameColor.SKY_BLUE);
        put(Area.TUNDRA, GameColor.LIGHT_GRAY_PURPLE);
        put(Area.RUINS, GameColor.CYAN);
        put(Area.UNDERSEA, GameColor.SKY_BLUE);
        put(Area.MATRIX, GameColor.VERY_DARK_GRAY);
    }};

    private final TextureRegion pixel;

    private final Area area;
    private final TextureRegion bg;
    private final float bgWidth;
    private final int bgCount;

    private float alpha = 1;
    private float step = 0f;

    private final List<Vector2> bgIcons;
    private final TextureRegion bgIcon;
    private float spawnTimer = 0f;
    private final float spawnInterval;
    private final Vector2 vel;

    public LevelBackground(Context context, Area area) {
        this.area = area;
        pixel = context.getImage("pixel");
        bg = context.getImage(area.bg);
        bgWidth = bg.getRegionWidth();
        bgCount = (int) (Constants.WIDTH / bgWidth + 1);

        bgIcons = new ArrayList<>();
        if (area == Area.TUNDRA) {
            spawnInterval = 0.3f;
            bgIcon = pixel;
            vel = new Vector2(0, -5);
        } else if (area == Area.UNDERSEA) {
            spawnInterval = .6f;
            bgIcon = context.getImage("underseabglevelicon");
            vel = new Vector2(0, 9);
        } else if (area == Area.MATRIX) {
            spawnInterval = 3f;
            bgIcon = context.getImage("matrixbgicon");
            vel = new Vector2(10, -10);
        } else {
            spawnInterval = 0f;
            bgIcon = null;
            vel = new Vector2();
        }

        for (int i = 0; i < 1000; i++) {
            update(1 / 60f);
        }
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setVisible(boolean visible) {
        step = visible ? 3f : -3f;
    }

    public void update(float dt) {
        alpha += dt * step;
        alpha = MathUtils.clamp(alpha, 0f, 1f);
        spawnTimer += dt;

        // area specific bg stuff
        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0;
            if (area == Area.TUNDRA) {
                bgIcons.add(new Vector2(MathUtils.random(Constants.WIDTH), Constants.HEIGHT - 60));
            } else if (area == Area.UNDERSEA) {
                bgIcons.add(new Vector2(MathUtils.random(Constants.WIDTH), 50));
            } else if (area == Area.MATRIX) {
                for (int i = -Constants.WIDTH; i < Constants.WIDTH; i += Constants.WIDTH / 6) {
                    bgIcons.add(new Vector2(i, Constants.HEIGHT - 50));
                }
            }

        }

        for (Vector2 pos : bgIcons) {
            pos.add(vel.x * dt, vel.y * dt);
            if (area != Area.MATRIX) {
                pos.add(MathUtils.random(-.2f, .2f), 0);
            }
        }
        bgIcons.removeIf(i -> i.x < -Constants.WIDTH || i.x > Constants.WIDTH || i.y < 0 || i.y > Constants.HEIGHT);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(colorMap.get(area));
        Utils.setAlpha(sb, alpha);
        sb.draw(pixel, 0, 0, Constants.WIDTH, Constants.HEIGHT);

        sb.setColor(GameColor.WHITE);
        Utils.setAlpha(sb, alpha);
        for (int i = 0; i < bgCount; i++) {
            sb.draw(bg, i * bgWidth, 60);
        }
        for (Vector2 pos : bgIcons) {
            sb.draw(bgIcon, pos.x, pos.y);
        }
    }

}
