package com.distraction.fs2j.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.FlippySlime2J;

import de.golfgl.gdxgamesvcs.GameJoltClient;
import de.golfgl.gdxgamesvcs.IGameServiceIdMapper;

/**
 * Launches the desktop (LWJGL3) application.
 */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(Constants.TITLE);
        config.useVsync(true);
        config.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        if (Constants.FULLSCREEN) {
            config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        } else {
            config.setWindowedMode(Constants.DESKTOP_WIDTH, Constants.DESKTOP_HEIGHT);
        }
        config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

        GameJoltClient client = new GameJoltClient();
        client.setGjScoreTableMapper(id -> {
            if (id.equals("BETA_1")) return Constants.BETA_1_ID;
            return -1;
        });
        client.initialize(Constants.APP_ID, Constants.API_KEY);
        new Lwjgl3Application(new FlippySlime2J(client), config);
    }
}