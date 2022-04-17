package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Placement {

    public int rank;

    private TextureRegion end;
    private TextureRegion bg;
    private TextureRegion trophy;

    public float x;
    public float y;
    private float xdest;
    private float xmin;

    private int score;
    private int newScore;
    private boolean hiding;

    public Placement(Context context, int rank) {
        // sanity check
        if (rank < 1 || rank > 7)
            throw new IllegalArgumentException("invalid rank " + rank + ", must be 1-7");

        this.rank = rank;

        String key;
        if (rank == 1) key = "gold";
        else if (rank == 2) key = "silver";
        else if (rank == 3) key = "bronze";
        else key = "place";

        end = context.getImage("challenge" + key + "endbg");
        bg = context.getImage("challenge" + key + "bg");
        if (!key.equals("place")) trophy = context.getImage(key + "trophy");

        if (rank <= 3) xmin = 2f * Constants.WIDTH / 5f + (rank - 1) * 10;
        else xmin = 2f * Constants.WIDTH / 5f + 20 + (rank - 1) * 10;

        x = Constants.WIDTH;

        if (rank <= 3) y = Constants.HEIGHT - (rank) * 50;
        else y = Constants.HEIGHT - 150 - (rank - 3) * 30;
    }

    public void setScore(int newScore) {
        newScore = score;
        xdest = Constants.WIDTH;
        hiding = true;
    }

    public void update(float dt) {
        x = MathUtils.lerp(x, xdest, 8 * dt);
        if (hiding) {
            if (Math.abs(x - xdest) < 1) {
                hiding = false;
                xdest = xmin;
                score = newScore;
            }
        }
    }

    public void render(SpriteBatch sb) {
        // draw layout first
        sb.draw(end, xmin, y);
        sb.draw(bg, xmin + end.getRegionWidth(), y, Constants.WIDTH - xmin, bg.getRegionHeight());
        if (trophy != null) sb.draw(trophy, xmin + end.getRegionWidth() + 10, y + 2);
    }

}
