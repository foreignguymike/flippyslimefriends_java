package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Logging;
import com.distraction.fs2j.tilemap.data.GameColor;

public enum Skin implements Customizer {
    GREEN("green", GameColor.LIME_GREEN),
    GREENBLOCK("greenblock", GameColor.LIME_GREEN),
    RED("red", GameColor.ORANGE_RED),
    REDBLOCK("redblock", GameColor.ORANGE_RED),
    BLUE("blue", GameColor.BLUE),
    BLUEBLOCK("blueblock", GameColor.BLUE),
    PEACH("peach", GameColor.PEACH, 10),
    PEACHBLOCK("peachblock", GameColor.PEACH, 10),
    VIOLET("violet", GameColor.VIOLET, 15),
    VIOLETBLOCK("violetblock", GameColor.VIOLET, 15),
    CYAN("cyan", GameColor.CYAN, 20),
    CYANBLOCK("cyanblock", GameColor.CYAN, 20),
    EGG("egg", GameColor.LIGHT_GRAY, 25),
    PURPLE("purple", GameColor.PURPLE, 30),
    PURPLEBLOCK("purpleblock", GameColor.PURPLE, 30),
    LIGHTBLUE("lightblue", GameColor.SKY_BLUE, 35),
    LIGHTBLUEBLOCK("lightblueblock", GameColor.SKY_BLUE, 35),
    NEON("neon", GameColor.NEON_GREEN, 40),
    NEONBLOCK("neonblock", GameColor.NEON_GREEN, 40),
    LEMONLIME("lemonlime", GameColor.BRIGHT_YELLOW, 45),
    LEMONLIMEBLOCK("lemonlimeblock", GameColor.BRIGHT_YELLOW, 45),
    METAL("metal", GameColor.LIGHT_GRAY_PURPLE, 50),
    PINK("pink", GameColor.PINK, 55),
    PINKBLOCK("pinkblock", GameColor.PINK, 55),
    YELLOW("yellow", GameColor.YELLOW, 60),
    YELLOWBLOCK("yellowblock", GameColor.YELLOW, 60),
    ORANGE("orange", GameColor.ORANGE, 70),
    ORANGEBLOCK("orangeblock", GameColor.ORANGE, 70),
    DARK("dark", GameColor.DARK_GRAY, 75),
    DARKBLOCK("darkblock", GameColor.DARK_GRAY, 75),
    WHITE("white", GameColor.WHITE, 80),
    WHITEBLOCK("whiteblock", GameColor.WHITE, 80),
    BROWNFUR("brownfur", GameColor.TAN, 100, true),
    WHITEFUR("whitefur", GameColor.WHITE, 100, true),
    DARKFUR("darkfur", GameColor.DARK_GRAY, 110, true),
    BLACKFUR("blackfur", GameColor.VERY_DARK_GRAY, 120, true),
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
