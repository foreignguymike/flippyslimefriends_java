package com.distraction.fs2j.tilemap.player;

import com.distraction.fs2j.tilemap.player.accessories.Fish;
import com.distraction.fs2j.tilemap.player.accessories.HeadBubble;
import com.distraction.fs2j.tilemap.player.accessories.SantaHat;
import com.distraction.fs2j.tilemap.player.accessories.Sunglasses;

import java.util.ArrayList;
import java.util.List;

public enum AccessoryType {

    FISH("fish"),
    HEAD_BUBBLE("headbubble"),
    SANTA_HAT("santahat"),
    SUNGLASSES("sunglasses");

    public String key;

    AccessoryType(String key) {
        this.key = key;
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
}
