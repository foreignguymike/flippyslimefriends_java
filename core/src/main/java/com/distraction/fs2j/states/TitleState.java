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

public class TitleState extends GameState {

    private final TextureRegion pixel;
    private final TextButton audioButton;
    private final ImageButton title;
    private final TextButton playButton;
    private final TextButton customizeButton;
    private final TextButton challengeButton;

    private final NumberFont major;
    private final NumberFont minor;
    private final NumberFont build;
    private final int total;

    private int unlockCount = 0;

    public TitleState(Context context) {
        super(context);

        pixel = context.getImage("pixel");

        audioButton = new TextButton(context.getImage("audioicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25f, 5f);
        audioButton.enabled = !context.audioHandler.isMuted();
        title = new ImageButton(context.getImage("title"), Constants.WIDTH / 2f, Constants.HEIGHT + 100f, 0);
        playButton = new TextButton(context.getImage("play"), context.getImage("buttonbg"), Constants.WIDTH / 4f, 30, 0);
        customizeButton = new TextButton(context.getImage("slimetext"), context.getImage("buttonbg"), 2f * Constants.WIDTH / 4f, 30, 0);
        challengeButton = new TextButton(context.getImage("challenge"), context.getImage("buttonbg"), 3f * Constants.WIDTH / 4f, 30, 0);

        title.lerpTo(Constants.WIDTH / 2f, Constants.HEIGHT / 2f);

        String[] v = Constants.VERSION.split("\\.");
        v[0] = v[0].substring(1);
        major = new NumberFont(context, false, NumberFont.NumberSize.SMALL, Integer.parseInt(v[0]));
        minor = new NumberFont(context, false, NumberFont.NumberSize.SMALL, Integer.parseInt(v[1]));
        build = new NumberFont(context, false, NumberFont.NumberSize.SMALL, Integer.parseInt(v[2]));
        total = major.getTotalWidth() + minor.getTotalWidth() + build.getTotalWidth() + 6 + 1;

        context.audioHandler.stopAllMusic();
    }

    private void goToAreaSelect() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new AreaSelectState(context)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void goToCustomize(boolean preview) {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new CustomizeState(context, preview)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void goToChallenge() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new ChallengeState(context)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (title.containsPoint(touchPoint)) {
                unlockCount++;
                if (unlockCount == 5) {
                    goToCustomize(true);
                }
            } else unlockCount = 0;

            if (playButton.containsPoint(touchPoint)) goToAreaSelect();
            if (customizeButton.containsPoint(touchPoint)) goToCustomize(false);
            if (challengeButton.containsPoint(touchPoint)) goToChallenge();
            if (audioButton.containsPoint(touchPoint))
                audioButton.enabled = !context.audioHandler.toggleMute();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToAreaSelect();
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            ignoreInput = true;
            context.gsm.push(new NameState(context, this));
            context.gsm.depth++;
        }
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

            audioButton.render(sb);
            title.render(sb);
            playButton.render(sb);
            customizeButton.render(sb);
            challengeButton.render(sb);

            sb.setColor(1, 1, 1, 1);
            major.render(sb, Constants.WIDTH - total, 4);
            sb.draw(pixel, Constants.WIDTH - total + major.getTotalWidth() + 1, 1);
            minor.render(sb, Constants.WIDTH - total + major.getTotalWidth() + 3, 4);
            sb.draw(pixel, Constants.WIDTH - total + major.getTotalWidth() + minor.getTotalWidth() + 4, 1);
            build.render(sb, Constants.WIDTH - total + major.getTotalWidth() + minor.getTotalWidth() + 6, 4);
        }
        sb.end();
    }

}
