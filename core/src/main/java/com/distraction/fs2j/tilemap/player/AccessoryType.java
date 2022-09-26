package com.distraction.fs2j.tilemap.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Logging;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.accessories.Accessory;
import com.distraction.fs2j.tilemap.player.accessories.AngelWings;
import com.distraction.fs2j.tilemap.player.accessories.BasicHat;
import com.distraction.fs2j.tilemap.player.accessories.BunnyEars;
import com.distraction.fs2j.tilemap.player.accessories.CatEars;
import com.distraction.fs2j.tilemap.player.accessories.Crown;
import com.distraction.fs2j.tilemap.player.accessories.DevilWings;
import com.distraction.fs2j.tilemap.player.accessories.DogEars;
import com.distraction.fs2j.tilemap.player.accessories.Fish;
import com.distraction.fs2j.tilemap.player.accessories.HeadBubble;
import com.distraction.fs2j.tilemap.player.accessories.Headband;
import com.distraction.fs2j.tilemap.player.accessories.SantaHat;
import com.distraction.fs2j.tilemap.player.accessories.Sunglasses;
import com.distraction.fs2j.tilemap.player.accessories.Wieldable;

import java.util.ArrayList;
import java.util.List;

public enum AccessoryType implements Customizer {

    HEAD_BUBBLE("headbubble"),
    HEADBAND_WHITE("headbandwhite", 3, 30, 10),
    HEADBAND_BLUE("headbandblue", 5, 30, 10),
    HEADBAND_GREEN("headbandgreen", 7, 30, 10),
    HEADBAND_RED("headbandred", 10, 30, 10),
    SANTA_HAT("santahat", 15, 27, 20),
    HEADBAND_ORANGE("headbandorange", 25, 30, 10),
    WIZARD_HAT("wizardhat", 30),
    HEADBAND_YELLOW("headbandyellow", 35, 30, 10),
    SUNGLASSES("sunglasses", 40, 22, 7),
    DOG_EARS("dogears", 50, 10, 10),
    HEADBAND_BLACK("headbandblack", 50, 30, 10),
    BAMBOO_HAT("bamboohat", 50),
    CAT_EARS("catears", 60, 9, 10),
    WOOD_STAFF("woodstaff", 65),
    CROWN("crown", 70, 13, 11),
    HALO("halo", 75),
    BAMBOO_HAT_GREEN("bamboohatgreen", 75),
    HEADBAND_BLACK_YELLOW_TRIM("headbandblackyellowtrim", 80, 30, 10),
    CANDY_CANE("candycane", 90),
    BUNNY_EARS("bunnyears", 100, 8, 16),
    HEADBAND_COBRA_KAI("headbandcobrakai", 100, 30, 10),
    BAMBOO_HAT_BLUE("bamboohatblue", 110),
    FISH("fish", 120, 14, 7),
    HEADBAND_MIYAGI("headbandmiyagi", 150, 30, 10),
    BAMBOO_HAT_RED("bamboohatred", 175),
    DEVIL_WINGS("devilwings", 175),
    BAMBOO_HAT_BLACK("bamboohatblack", 190),
    ANGEL_WINGS("angelwings", 200, 15, 14),
    WAND("wand", 220);

    private static final AccessoryType[] headbands = new AccessoryType[]{
            HEADBAND_BLUE, HEADBAND_WHITE, HEADBAND_GREEN, HEADBAND_RED, HEADBAND_ORANGE, HEADBAND_YELLOW, HEADBAND_BLACK,
            HEADBAND_BLACK_YELLOW_TRIM,
            HEADBAND_COBRA_KAI, HEADBAND_MIYAGI
    };

    private static final AccessoryType[] basicHats = new AccessoryType[]{
            WIZARD_HAT,
            HALO,
            BAMBOO_HAT, BAMBOO_HAT_GREEN, BAMBOO_HAT_BLUE, BAMBOO_HAT_RED, BAMBOO_HAT_BLACK
    };

    private static final AccessoryType[] wieldable = new AccessoryType[]{
            WOOD_STAFF, CANDY_CANE, WAND
    };

    static {
        // sanity check
        AccessoryType[] accs = AccessoryType.values();
        int diamondCount = 0;
        for (int i = 0; i < accs.length; i++) {
            if (diamondCount > accs[i].getDiamonds()) {
                throw new IllegalStateException(accs[i] + " is in wrong spot");
            }
            diamondCount = accs[i].getDiamonds();
        }
    }

    public String key;
    private int diamond = 0;
    private int width = -1;
    private int height = -1;

    // for icon
    public float xoffset = 0;
    public float yoffset = 0;

    AccessoryType(String key) {
        this.key = key;
    }

    AccessoryType(String key, int diamond) {
        this.key = key;
        this.diamond = diamond;
    }

    AccessoryType(String key, int diamond, int width, int height) {
        this.key = key;
        this.diamond = diamond;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getDiamonds() {
        return diamond;
    }

    public static AccessoryType find(String key) {
        for (AccessoryType it : values()) if (it.key.equals(key)) return it;
        Logging.error("cannot find accessory " + key);
        throw new IllegalArgumentException();
    }

    public static List<Accessory> loadAccessories(Player player, List<AccessoryType> accessoryTypes) {
        List<Accessory> accessories = new ArrayList<>();
        for (AccessoryType it : accessoryTypes) {
            if (it == FISH) accessories.add(new Fish(player));
            else if (it == HEAD_BUBBLE) accessories.add(new HeadBubble(player));
            else if (it == SANTA_HAT) accessories.add(new SantaHat(player));
            else if (it == SUNGLASSES) accessories.add(new Sunglasses(player));
            else if (it == DOG_EARS) accessories.add(new DogEars(player));
            else if (it == BUNNY_EARS) accessories.add(new BunnyEars(player));
            else if (it == CROWN) accessories.add(new Crown(player));
            else if (it == CAT_EARS) accessories.add(new CatEars(player));
            else if (it == ANGEL_WINGS) accessories.add(new AngelWings(player));
            else if (it == DEVIL_WINGS) accessories.add(new DevilWings(player));
            else if (Utils.contains(headbands, it)) accessories.add(new Headband(player, it));
            else if (Utils.contains(basicHats, it)) accessories.add(new BasicHat(player, it));
            else if (Utils.contains(wieldable, it)) accessories.add(new Wieldable(player, it));
        }
        return accessories;
    }

    public TextureRegion[] getSprites(Context context) {
        if (width == -1 || height == -1) return new TextureRegion[]{context.getImage("acc" + key)};
        else return context.getImage("acc" + key).split(width, height)[0];
    }
}
