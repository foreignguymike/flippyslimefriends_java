package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;

public enum Face {
    NORMAL("normal"),
    LASHES("lashes"),
    COOL("cool")
    ;

    public String key;

    Face(String key) {
        this.key = key;
    }

    public TextureRegion[] getSprites(Context context) {
        return context.getImage("face" + key).split(Player.SPRITE_WIDTH, Player.SPRITE_HEIGHT)[0];
    }

    public static Face find(String key) {
        for (Face it : values()) if (it.key.equals(key)) return it;
        throw new IllegalArgumentException("cannot find face " + key);
    }
}
