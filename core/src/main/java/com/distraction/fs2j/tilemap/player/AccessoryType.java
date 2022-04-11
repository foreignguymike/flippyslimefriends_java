package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.player.accessories.BunnyEars;
import com.distraction.fs2j.tilemap.player.accessories.DogEars;
import com.distraction.fs2j.tilemap.player.accessories.Fish;
import com.distraction.fs2j.tilemap.player.accessories.HeadBubble;
import com.distraction.fs2j.tilemap.player.accessories.SantaHat;
import com.distraction.fs2j.tilemap.player.accessories.Sunglasses;

import java.util.ArrayList;
import java.util.List;

public enum AccessoryType {

    FISH("fish", 14, 7),
    HEAD_BUBBLE("headbubble"),
    SANTA_HAT("santahat", 27, 20),
    SUNGLASSES("sunglasses", 22, 7),
    DOGEARS("dogears", 10, 10),
    BUNNYEARS("bunnyears", 8, 16)
    ;

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
        System.out.println("cannot find accessory " + key);
        throw new IllegalArgumentException();
    }

    public static List<Accessory> loadAccessories(Player player, List<AccessoryType> accessoryTypes) {
        List<Accessory> accessories = new ArrayList<>();
        for (AccessoryType it : accessoryTypes) {
            if (it == FISH) accessories.add(new Fish(player));
            if (it == HEAD_BUBBLE) accessories.add(new HeadBubble(player));
            if (it == SANTA_HAT) accessories.add(new SantaHat(player));
            if (it == SUNGLASSES) accessories.add(new Sunglasses(player));
            if (it == DOGEARS) accessories.add(new DogEars(player));
            if (it == BUNNYEARS) accessories.add(new BunnyEars(player));
        }
        return accessories;
    }

    public TextureRegion[] getSprites(Context context) {
        if (width == -1 || height == -1) return new TextureRegion[] { context.getImage("acc" + key) };
        else return context.getImage("acc" + key).split(width, height)[0];
    }
}
