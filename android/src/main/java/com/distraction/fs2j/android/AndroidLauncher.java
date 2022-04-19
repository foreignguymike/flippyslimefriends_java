package com.distraction.fs2j.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.distraction.fs2j.FlippySlime2J;

import de.golfgl.gdxgamesvcs.GpgsClient;

/**
 * Launches the Android application.
 */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
//        GpgsClient client = new GpgsClient();
//        client.initialize(this, false);
//        initialize(new FlippySlime2J(client), configuration);
        initialize(new FlippySlime2J(), configuration);
    }
}