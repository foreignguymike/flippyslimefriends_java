package com.distraction.fs2j.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.FlippySlime2J;

/**
 * Launches the GWT application.
 */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        if (Constants.FULLSCREEN) {
            GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
            cfg.padVertical = 0;
            cfg.padHorizontal = 0;
            return cfg;
        } else {
            return new GwtApplicationConfiguration(Constants.DESKTOP_WIDTH, Constants.DESKTOP_HEIGHT);
        }
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new FlippySlime2J();
    }
}
