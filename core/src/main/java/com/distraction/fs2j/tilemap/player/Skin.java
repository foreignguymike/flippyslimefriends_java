package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Logging;
import com.distraction.fs2j.tilemap.data.GameColor;

public enum Skin implements Customizer {
    GREEN("green", GameColor.LIME_GREEN),
    RED("red", GameColor.ORANGE_RED),
    BLUE("blue", GameColor.BLUE),
    VIOLET("violet", GameColor.VIOLET, 15),
    CYAN("cyan", GameColor.CYAN, 30),
    EGG("egg", GameColor.LIGHT_GRAY, 40),
    PURPLE("purple", GameColor.PURPLE, 45),
    LIGHTBLUE("lightblue", GameColor.SKY_BLUE, 50),
    METAL("metal", GameColor.LIGHT_GRAY_PURPLE, 50),
    PINK("pink", GameColor.PINK, 55),
    YELLOW("yellow", GameColor.YELLOW, 60),
    ORANGE("orange", GameColor.ORANGE, 70),
    DARK("dark", GameColor.DARK_GRAY, 80),
    WHITE("white", GameColor.WHITE, 100),
    BROWNFUR("brownfur", GameColor.TAN, 100, true),
    WHITEFUR("whitefur", GameColor.WHITE, 100, true),
    PUMPKIN("pumpkin", GameColor.ORANGE, 150, true);

    public String key;
    public Color color;
    private int diamond;
    private boolean reversible;

    Skin(String key, Color color) {
        this.key = key;
        this.color = color;
    }

    Skin(String key, Color color, int diamond) {
        this.key = key;
        this.color = color;
        this.diamond = diamond;
    }

    Skin(String key, Color color, int diamond, boolean reversible) {
        this.key = key;
        this.color = color;
        this.diamond = diamond;
        this.reversible = reversible;
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

    public boolean isReversible() {
        return reversible;
    }
}
