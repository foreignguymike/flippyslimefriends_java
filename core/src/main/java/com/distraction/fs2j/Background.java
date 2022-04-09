package com.distraction.fs2j;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.tilemap.data.Area;

import java.util.ArrayList;
import java.util.List;

public class Background {

    private TextureRegion pixel;
    private TextureRegion image;
    private Color color;
    private Color bgIconColor;

    private List<Vector3> bgs = new ArrayList<>();
    private float speed = 5f;
    private float interval = 5f * 2;
    private float time = interval;
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
                bgs.add(new Vector3(1f * col * Constants.WIDTH / 4f + (row + 1) * speed * interval, row * speed * interval, 0f));
            }
        }
    }

    public void update(float dt) {
        time += dt;
        time2 += dt;
        while (time > interval) {
            time -= interval;
            for (int i = -4; i <= 4; i++) {
                bgs.add(new Vector3(1f * i * Constants.WIDTH / 4f, -speed * interval, 0f));
            }
        }
        rot = time2 * 5;
        for (Vector3 it : bgs) {
            it.x += speed * dt;
            it.y += speed * dt;
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
