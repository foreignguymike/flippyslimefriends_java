package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.GameColor;

import java.util.ArrayList;
import java.util.List;

public class TeleportLight extends TileObject {

    private TextureRegion image;
    private TextureRegion pixel;
    private Color color = GameColor.BRIGHT_SKY_BLUE;
    private List<Vector3> particles;

    private float interval = 0.1f;
    private float time = interval;

    public TeleportLight(Context context, TileMap tileMap, int row, int col) {
        super(context, tileMap);
        setPositionFromTile(row, col);
        p.z = 8f;
        height = 32f;
        speed = 40f;

        image = context.getImage("teleport");
        pixel = context.getImage("pixel");
        particles = new ArrayList<>();
    }

    @Override
    public void update(float dt) {
        time += dt;
        while (time >= interval) {
            time -= interval;
            Vector3 v = new Vector3(p.x, p.y, 0);
            tileMap.toIsometric(v.x, v.y, v);
            v.x += MathUtils.round(MathUtils.random() * (image.getRegionWidth() - 1) - image.getRegionWidth() / 2f);
            v.y += MathUtils.random() * 5;
            v.z = v.y + height;
            particles.add(v);
        }
        for (Vector3 it : particles) it.y += speed * dt;
        particles.removeIf(it -> it.y > it.z);
    }

    @Override
    public void render(SpriteBatch sb) {
        tileMap.toIsometric(p.x, p.y, isop);
        sb.setColor(1, 1, 1, 1);
        sb.draw(image, isop.x - image.getRegionWidth() / 2f, isop.y - image.getRegionHeight() / 2f + p.z);
        Color c = sb.getColor();
        sb.setColor(color);
        for (Vector3 it : particles) {
            color.a = (it.z - it.y) / height;
            sb.setColor(color);
            sb.draw(pixel, it.x, it.y, 2f, 2f);
        }
        sb.setColor(c);
    }
}
