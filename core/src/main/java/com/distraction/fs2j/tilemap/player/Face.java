package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Logging;

public enum Face implements Customizer {
    NORMAL("normal"),
    LASHES("lashes"),
    SLEEP("sleep", 10),
    COOL("cool", 20),
    DOG("dog", 40),
    INVERTED("inverted", 45),
    BUNNY("bunny", 50),
    STARE("stare", 80),
    EYE("eye", 100),
    EMIL("emil", 200);

    public String key;
    private int diamond;

    Face(String key) {
        this.key = key;
    }

    Face(String key, int diamond) {
        this.key = key;
        this.diamond = diamond;
    }

    @Override
    public int getDiamonds() {
        return diamond;
    }

    public TextureRegion[] getSprites(Context context) {
        return context.getImage("face" + key).split(Player.SPRITE_WIDTH, Player.SPRITE_HEIGHT)[0];
    }

    public static Face find(String key) {
        for (Face it : values()) if (it.key.equals(key)) return it;
        Logging.error("cannot find face " + key);
        throw new IllegalArgumentException();
    }
}
