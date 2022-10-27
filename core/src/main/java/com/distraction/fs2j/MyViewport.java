package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MyViewport extends FitViewport {

    public MyViewport(float worldWidth, float worldHeight) {
        super(worldWidth, worldHeight);
        update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

}
