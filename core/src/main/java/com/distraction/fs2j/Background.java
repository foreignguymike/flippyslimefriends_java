package com.distraction.fs2j;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.tilemap.data.Area;

import java.util.ArrayList;
import java.util.List;

public class Background {

    private static final float SPEED = 5f;
    private static final float INTERVAL = SPEED * 2f;

    private final TextureRegion pixel;
    private final TextureRegion image;
    private final Color color;
    private final Color bgIconColor;

    private final List<Vector3> bgs = new ArrayList<>();
    private float time = INTERVAL;
    private float time2 = 0f;
    private float rot = 0f;

    public Background(Context context, Area area) {
        this(context, context.getImage(area.bg), area.colorCopy(), area.bgIconColor);
    }

    public Background(Context context, TextureRegion image, Color color, Color bgIconColor) {
        pixel = context.getImage("pixel");
        this.image = image;
        this.color = color;
        this.bgIconColor = bgIconColor;

        for (int row = 0; row <= 5; row++) {
            for (int col = -4; col <= 4; col++) {
                bgs.add(new Vector3(1f * col * Constants.WIDTH / 4f + (row + 1) * SPEED * INTERVAL, row * SPEED * INTERVAL, 0f));
            }
        }
    }

    public void update(float dt) {
        time += dt;
        time2 += dt;
        while (time > INTERVAL) {
            time -= INTERVAL;
            for (int i = -4; i <= 4; i++) {
                bgs.add(new Vector3(1f * i * Constants.WIDTH / 4f, -SPEED * INTERVAL, 0f));
            }
        }
        rot = time2 * 5;
        for (Vector3 it : bgs) {
            it.x += SPEED * dt;
            it.y += SPEED * dt;
            it.z = rot;
        }
        bgs.removeIf(it -> it.x > Constants.WIDTH && it.y > Constants.HEIGHT);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(pixel, 0f, 0f, Constants.WIDTH, Constants.HEIGHT);
        sb.setColor(bgIconColor);
        for (Vector3 it : bgs) Utils.drawRotated(sb, image, it.x, it.y, rot);
        sb.setColor(1, 1, 1, 1);
    }

}
