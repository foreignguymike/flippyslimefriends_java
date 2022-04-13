package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Logging;
import com.distraction.fs2j.tilemap.data.GameColor;

public enum Skin implements Customizer {
    GREEN("green", GameColor.LIME_GREEN),
    RED("red", GameColor.RED_ORANGE),
    BLUE("blue", GameColor.BLUE),
    VIOLET("violet", GameColor.VIOLET, 15),
    CYAN("cyan", GameColor.CYAN, 30),
    EGG("egg", GameColor.LIGHT_GRAY, 40),
    PURPLE("purple", GameColor.PURPLE, 45),
    LIGHTBLUE("lightblue", GameColor.SKY_BLUE, 50),
    YELLOW("yellow", GameColor.YELLOW, 60),
    ORANGE("orange", GameColor.ORANGE, 70),
    BROWNFUR("brownfur", GameColor.TAN, 80),
    DARK("dark", GameColor.DARK_GRAY, 90),
    WHITEFUR("whitefur", GameColor.WHITE, 100);

    public String key;
    public Color color;
    private int diamond;

    Skin(String key, Color color) {
        this.key = key;
        this.color = color;
    }

    Skin(String key, Color color, int diamond) {
        this.key = key;
        this.color = color;
        this.diamond = diamond;
    }

    @Override
    public int getDiamonds() {
        return diamond;
    }

    public TextureRegion[] getSprites(Context context) {
        return context.getImage("skin" + key).split(Player.SPRITE_WIDTH, Player.SPRITE_HEIGHT)[0];
    }

    public static Skin find(String key) {
        for (Skin it : values()) if (it.key.equals(key)) return it;
        Logging.info("cannot find skin " + key);
        throw new IllegalArgumentException();
    }
}
