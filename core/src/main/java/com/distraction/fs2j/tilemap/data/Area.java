package com.distraction.fs2j.tilemap.data;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;
import java.util.List;

public enum Area {

    //    TUTORIAL("tutorial", GameColor.SKY_BLUE, "bgs", GameColor.WHITE, "tiletutorial", "tiletutorial"),
    MEADOW("meadow", GameColor.DARK_GREEN, "meadowbg", "meadowbgicon", GameColor.GREEN, "tilegrass", "tilegrass"),
    TUNDRA("tundra", GameColor.LIGHT_GRAY_PURPLE, "tundrabg", "tundrabgicon", GameColor.WHITE, "tilesnow", "tilesnow"),
    RUINS("ruins", GameColor.SAND, "ruinsbg", "ruinsbgicon", GameColor.BRIGHT_YELLOW, "tileruins", "tileruins"),
    UNDERSEA("undersea", GameColor.DARK_BLUE, "underseabg", "underseabgicon", GameColor.BLUE, "tilesea", "tilesea"),
    MATRIX("matrix", GameColor.BLACK, "pixel", "matrixbgicon", GameColor.NEON_GREEN, "tiledark", "tiledarkoff"),

    CHALLENGE("challenge", GameColor.DARK_GRAY, "meadowbg", "challengebgicon", GameColor.LIGHT_GRAY, "tiletutorial", "tiletutorial");

    public String text;
    public Color color;
    public String bg;
    public String bgIcon;
    public Color bgIconColor;
    public String tilesetOn;
    public String tilesetOff;

    Area(String text, Color color, String bg, String bgIcon, Color bgIconColor, String tilesetOn, String tilesetOff) {
        this.text = text;
        this.color = color;
        this.bg = bg;
        this.bgIcon = bgIcon;
        this.bgIconColor = bgIconColor;
        this.tilesetOn = tilesetOn;
        this.tilesetOff = tilesetOff;
    }

    public static List<Area> getNormalAreas() {
        return Arrays.asList(MEADOW, TUNDRA, RUINS, UNDERSEA, MATRIX);
    }
}
