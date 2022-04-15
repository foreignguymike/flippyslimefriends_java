package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.GameColor;

import java.util.Arrays;

public class TitleState extends GameState {

    private TextureRegion pixel;
    private ImageButton title;
    private TextButton playButton;
    private TextButton customizeButton;

    private NumberFont major;
    private NumberFont minor;
    private NumberFont build;
    private int total = 0;

    private int unlockCount = 0;

    public TitleState(Context context) {
        super(context);

        pixel = context.getImage("pixel");

        title = new ImageButton(context.getImage("title"), Constants.WIDTH / 2, Constants.HEIGHT + 100f, 0);
        playButton = new TextButton(context.getImage("play"), context.getImage("buttonbg"), Constants.WIDTH / 3, 30, 0);
        customizeButton = new TextButton(context.getImage("avatar"), context.getImage("buttonbg"), 2 * Constants.WIDTH / 3, 30, 0);

        title.lerpTo(Constants.WIDTH / 2, Constants.HEIGHT / 2);

        String[] v = Constants.VERSION.split("\\.");
        v[0] = v[0].substring(1);
        Arrays.stream(v).map(Integer::parseInt).forEach(System.out::println);
        major = new NumberFont(context, false, NumberFont.NumberSize.SMALL, Integer.parseInt(v[0]));
        minor = new NumberFont(context, false, NumberFont.NumberSize.SMALL, Integer.parseInt(v[1]));
        build = new NumberFont(context, false, NumberFont.NumberSize.SMALL, Integer.parseInt(v[2]));
        total = major.getTotalWidth() + minor.getTotalWidth() + build.getTotalWidth() + 16;
    }

    private void goToAreaSelect() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new AreaSelectState(context)));
    }

    private void goToCustomize() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new CustomizeState(context)));
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (title.containsPoint(touchPoint)) {
                unlockCount++;
                if (unlockCount == 5) {
                    Utils.UNLOCK_ALL = true;
                    goToCustomize();
                }
            } else unlockCount = 0;

            if (playButton.containsPoint(touchPoint)) goToAreaSelect();
            if (customizeButton.containsPoint(touchPoint)) goToCustomize();
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
            customizeButton.render(sb);

            sb.setColor(1, 1, 1, 1);
            major.render(sb, Constants.WIDTH - total, 12);
            sb.draw(pixel, Constants.WIDTH - total + major.getTotalWidth() + 1, 10);
            minor.render(sb, Constants.WIDTH - total + major.getTotalWidth() + 3, 12);
            sb.draw(pixel, Constants.WIDTH - total + major.getTotalWidth() + minor.getTotalWidth() + 4, 10);
            build.render(sb, Constants.WIDTH - total + major.getTotalWidth() + minor.getTotalWidth() + 6, 12);
        }
        sb.end();
    }

}
