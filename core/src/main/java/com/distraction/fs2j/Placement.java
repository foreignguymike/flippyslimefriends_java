package com.distraction.fs2j;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.Player;

public class Placement {

    public int rank;

    private final TextureRegion end;
    private final TextureRegion bg;
    private final TextureRegion icon;
    private final TextureRegion emptySlime;

    public float x;
    public float y;
    private float xdest;
    private final float xmin;
    private int totalOffset;

    private int newScore;
    private boolean hiding;

    private Player player;
    private Player newPlayer;
    private final TextFont scoreFont;
    private final TextFont nameFont;

    private String newName;

    private boolean enabled;

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
        icon = context.getImage("place" + rank);
        emptySlime = context.getImage("slimeempty");

        if (rank <= 3) xmin = 2f * Constants.WIDTH / 5f + (rank - 1) * 10;
        else xmin = 2f * Constants.WIDTH / 5f + 20 + (rank - 1) * 10;

        x = Constants.WIDTH;

        if (rank <= 3) y = Constants.HEIGHT - (rank) * 50;
        else y = Constants.HEIGHT - 150 - (rank - 3) * 30;

        scoreFont = new TextFont(
                context,
                TextFont.FontType.BIG,
                "",
                false,
                x + totalOffset + (rank <= 3 ? Player.SPRITE_WIDTH / 2f + 30 : -10),
                y + end.getRegionHeight() / 2f
        );
        if (rank <= 3) setScore(0, null, null);
        else setScore(0, null);

        totalOffset += end.getRegionWidth(); // end
        totalOffset += 10; // padding
        totalOffset += icon.getRegionWidth(); // place icon
        totalOffset += Player.SPRITE_WIDTH / 2f;

        nameFont = new TextFont(context, TextFont.FontType.NORMAL, "",false,  totalOffset + (rank <= 3 ? Player.SPRITE_WIDTH / 2f + 30 : -10), y + end.getRegionHeight() / 2f);
    }


    public void setScore(int score, Player player, String name) {
        newPlayer = player;
        newScore = score;
        newName = name;
        xdest = Constants.WIDTH;
        hiding = true;
    }

    public void setScore(int score, String name) {
        newPlayer = null;
        newScore = score;
        newName = name;
        xdest = Constants.WIDTH;
        hiding = true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void update(float dt) {
        x = MathUtils.lerp(x, xdest, 8 * dt);

        if (hiding && Math.abs(x - xdest) < 20) {
            hiding = false;
            xdest = xmin;
            player = newPlayer;

            if (newScore > 0) scoreFont.setText(Integer.toString(newScore));
            else scoreFont.setText("-");
            nameFont.setText(newName);
        }

        if (player != null) player.update(dt);
    }

    public void render(SpriteBatch sb) {
        // draw layout first
        if (enabled) sb.setColor(1, 1, 1, 1);
        else sb.setColor(0.5f, 0.5f, 0.5f, 1);
        sb.draw(end, xmin, y);
        sb.draw(bg, xmin + end.getRegionWidth(), y, Constants.WIDTH - xmin, bg.getRegionHeight());
        sb.draw(icon, xmin + end.getRegionWidth() + 10, y + 2);

        // draw player
        if (rank <= 3) {
            if (player != null && enabled) {
                player.isop.x = x + totalOffset + Player.SPRITE_WIDTH / 2f;
                player.isop.y = y + 2;
                player.render(sb);
            } else {
                sb.draw(emptySlime, x + end.getRegionWidth() + icon.getRegionWidth() + Player.SPRITE_WIDTH, y + 2);
            }
        }

        // draw score
        scoreFont.render(sb);

        // draw name
        scoreFont.x = x + totalOffset + (rank <= 3 ? Player.SPRITE_WIDTH / 2f + 30 : -10);
        nameFont.x = x + totalOffset + scoreFont.getTotalWidth() + 15 + (rank <= 3 ? Player.SPRITE_WIDTH / 2f + 30 : -10);
        sb.setColor(GameColor.BLACK);
        nameFont.render(sb);
    }

}
