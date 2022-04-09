package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.data.GameColor;

public enum Skin {
    GREEN("green", GameColor.GREEN),
    RED("red", GameColor.RED),
    BLUE("blue", GameColor.BLUE),
    YELLOW("yellow", GameColor.YELLOW),
    CYAN("cyan", GameColor.CYAN),
    VIOLET("violet", GameColor.VIOLET);

    public String key;
    public Color color;

    Skin(String key, Color color) {
        this.key = key;
        this.color = color;
    }

    public TextureRegion[] getSprites(Context context) {
        String key = "player" + this.key;
        return context.getImage(key).split(Player.SPRITE_WIDTH, Player.SPRITE_HEIGHT)[0];
    }

    public static Skin find(String key) {
        for (Skin it : values()) if (it.key.equals(key)) return it;
        throw new IllegalArgumentException("cannot find skin " + key);
    }
}
