package com.distraction.fs2j.tilemap.data;

import com.badlogic.gdx.graphics.Color;

public enum Area {

    TUTORIAL("tutorial", GameColor.SKY_BLUE, "bgs", GameColor.WHITE, "tiletutorial", "tiletutorial"),
    MEADOW("meadow", GameColor.DARK_GREEN, "meadowbg", GameColor.GREEN, "tilegrass", "tilegrass"),
    TUNDRA("tundra", GameColor.LIGHT_GRAY_PURPLE, "tundrabg", GameColor.WHITE, "tilesnow", "tilesnow"),
    RUINS("ruins", GameColor.TAN, "ruinsbg", GameColor.BRIGHT_YELLOW, "tileruins", "tileruins"),
    UNDERSEA("undersea", GameColor.DARK_BLUE, "underseabg", GameColor.CALM_BLUE, "tilesea", "tilesea"),
    MATRIX("matrix", GameColor.BLACK, "matrixbg", GameColor.NEON_GREEN, "tiledark", "tiledarkoff");

    public String text;
    public Color color;
    public String bg;
    public Color bgIconColor;
    public String tilesetOn;
    public String tilesetOff;

    Area(String text, Color color, String bg, Color bgIconColor, String tilesetOn, String tilesetOff) {
        this.text = text;
        this.color = color;
        this.bg = bg;
        this.bgIconColor = bgIconColor;
        this.tilesetOn = tilesetOn;
        this.tilesetOff = tilesetOff;
    }

    public Color colorCopy() {
        return new Color(color);
    }
}
