package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.MyViewport;

public abstract class GameState {

    protected Context context;
    protected TextureRegion pixel;
    protected Camera camera;
    protected Viewport viewport;

    public boolean ignoreInput = false;
    public Vector3 touchPoint = new Vector3();

    protected GameState(Context context) {
        this.context = context;
        pixel = context.getImage("pixel");

        viewport = new MyViewport(Constants.WIDTH, Constants.HEIGHT);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera = viewport.getCamera();
    }

    protected void unprojectTouch() {
        unprojectTouch(viewport);
    }

    protected void unprojectTouch(Viewport viewport) {
        touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        viewport.unproject(touchPoint);
    }

    public void resize(int w, int h) {
        viewport.update(w, h);
    }

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

}
