package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.player.accessories.Fish;
import com.distraction.fs2j.tilemap.player.accessories.HeadBubble;
import com.distraction.fs2j.tilemap.player.accessories.SantaHat;
import com.distraction.fs2j.tilemap.player.accessories.Sunglasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum AccessoryType {

    FISH("fish", 14, 7, 8, 11),
    HEAD_BUBBLE("headbubble", 10f, 8f),
    SANTA_HAT("santahat", 27, 20, 1, 4),
    SUNGLASSES("sunglasses", 22, 7, 3, 11);

    public String key;
    private int width = -1;
    private int height = -1;
    public float xoffset = 0;
    public float yoffset = 0;

    AccessoryType(String key) {
        this.key = key;
    }

    AccessoryType(String key, int width, int height) {
        this.key = key;
        this.width = width;
        this.height = height;
    }

    AccessoryType(String key, float xoffset, float yoffset) {
        this.key = key;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    AccessoryType(String key, int width, int height, float xoffset, float yoffset) {
        this.key = key;
        this.width = width;
        this.height = height;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    public static AccessoryType find(String key) {
        for (AccessoryType it : values()) if (it.key.equals(key)) return it;
        throw new IllegalArgumentException("cannot find accessory " + key);
    }

    public static List<Accessory> loadAccessories(Player player, List<AccessoryType> accessoryTypes) {
        List<Accessory> accessories = new ArrayList<>();
        for (AccessoryType it : accessoryTypes) {
            if (it == FISH) accessories.add(new Fish(player));
            if (it == HEAD_BUBBLE) accessories.add(new HeadBubble(player));
            if (it == SANTA_HAT) accessories.add(new SantaHat(player));
            if (it == SUNGLASSES) accessories.add(new Sunglasses(player));
        }
        return accessories;
    }

    public TextureRegion[] getSprites(Context context) {
        if (width == -1 || height == -1) return new TextureRegion[] { context.getImage("acc" + key) };
        else return context.getImage("acc" + key).split(width, height)[0];
    }
}
