package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Logging;

public enum Face implements Customizer {
    NORMAL("normal"),
    LASHES("lashes"),
    BLANK("blank", 5),
    BLANK_LASHES("blanklashes", 5),
    SLEEP("sleep", 10),
    BIG_EYES("bigeyes", 15),
    COOL("cool", 20),
    DOG("dog", 30),
    CAT("cat", 40),
    INVERTED("inverted", 50),
    BUNNY("bunny", 60),
    STARE("stare", 80),
    CRAZY("crazy", 100),
    EYE("eye", 120),
    PUMPKIN("pumpkin", 150),
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
