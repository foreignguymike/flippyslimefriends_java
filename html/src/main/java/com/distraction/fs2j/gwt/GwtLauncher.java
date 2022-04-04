package com.distraction.fs2j.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.FlippySlime2J;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
		@Override
		public GwtApplicationConfiguration getConfig () {
			// Resizable application, uses available space in browser with no padding:
			GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
			cfg.padVertical = 0;
			cfg.padHorizontal = 0;
			return cfg;
			// If you want a fixed size application, comment out the above resizable section,
			// and uncomment below:
//			return new GwtApplicationConfiguration(Constants.DESKTOP_WIDTH, Constants.DESKTOP_HEIGHT);
		}

		@Override
		public ApplicationListener createApplicationListener () {
			return new FlippySlime2J();
		}
}
