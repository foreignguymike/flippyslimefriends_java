package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;

public abstract class GameState {

    protected Context context;
    protected TextureRegion pixel;
    protected OrthographicCamera camera;

    public boolean ignoreInput = false;
    public Vector3 touchPoint = new Vector3();

    protected GameState(Context context) {
        this.context = context;
        pixel = context.getImage("pixel");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
    }

    protected void unprojectTouch() {
        unprojectTouch(camera);
    }

    protected void unprojectTouch(Camera camera) {
        touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        camera.unproject(touchPoint);
    }

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

}
