package com.distraction.fs2j.tilemap.data;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;
import java.util.List;

public enum Area {

    MEADOW("meadowfinal", "meadow", GameColor.DARK_GREEN, "meadowbg", "meadowbgicon", GameColor.GREEN, "tilegrass", "tilegrass"),
    TUNDRA("tundrafinal", "tundra", GameColor.LIGHT_GRAY_PURPLE, "tundrabg", "tundrabgicon", GameColor.WHITE, "tilesnow", "tilesnow"),
    RUINS("ruinsfinal", "ruins", GameColor.SAND, "ruinsbg", "ruinsbgicon", GameColor.BRIGHT_YELLOW, "tileruins", "tileruins"),
    UNDERSEA("underseafinal", "undersea", GameColor.DARK_BLUE, "underseabg", "underseabgicon", GameColor.BLUE, "tilesea", "tilesea"),
    MATRIX("matrixfinal", "matrix", GameColor.BLACK, "pixel", "matrixbgicon", GameColor.NEON_GREEN, "tiledark", "tiledarkoff"),
    CHALLENGE("challengefinal", "challenge", GameColor.LIGHT_GRAY, "meadowbg", "challengebgicon", GameColor.GRAY, "tiletutorial", "tiletutorial");

    public String text;
    public String name;
    public Color color;
    public String bg;
    public String bgIcon;
    public Color bgIconColor;
    public String tilesetOn;
    public String tilesetOff;

    Area(String text, String name, Color color, String bg, String bgIcon, Color bgIconColor, String tilesetOn, String tilesetOff) {
        this.text = text;
        this.name = name;
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
