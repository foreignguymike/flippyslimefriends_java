package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.GameColor;

public class TitleState extends GameState {

    private ImageButton title;
    private TextButton playButton;

    public TitleState(Context context) {
        super(context);

        title = new ImageButton(context.getImage("title"), Constants.WIDTH / 2, Constants.HEIGHT + 100f, 0);
        playButton = new TextButton(context.getImage("play"), context.getImage("button"), Constants.WIDTH / 2, 30, 0);

        title.lerpTo(Constants.WIDTH / 2, Constants.HEIGHT / 2);
    }

    private void goToAreaSelect() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new AreaSelectState(context)));
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (playButton.containsPoint(touchPoint)) goToAreaSelect();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToAreaSelect();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) {
            handleInput();
        }
        title.update(dt);
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        Utils.clearScreen(GameColor.SKY_BLUE);
        sb.begin();
        {
            sb.setProjectionMatrix(camera.combined);
            sb.setColor(GameColor.MIDNIGHT_BLUE);
            sb.draw(pixel, 0, 0, Constants.WIDTH, 60);
            sb.draw(pixel, 0, Constants.HEIGHT - 60, Constants.WIDTH, 60);
            sb.setColor(1, 1, 1, 1);
            sb.draw(pixel, 0, 56, Constants.WIDTH, 1);
            sb.draw(pixel, 0, Constants.HEIGHT - 57, Constants.WIDTH, 1);

            title.render(sb);
            playButton.render(sb);
        }
        sb.end();
    }

}
