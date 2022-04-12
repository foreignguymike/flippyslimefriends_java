package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;

public class Logging {

    public static void info(String message) {
        Gdx.app.log("info", message);
    }

    public static void error(String message) {
        Gdx.app.error("error", message);
    }
}
