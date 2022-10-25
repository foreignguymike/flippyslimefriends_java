package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AudioButton;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.GameColor;

public class TitleState extends GameState {

    private final TextureRegion pixel;
    private final AudioButton audioButton;
    private final ImageButton title;
    private final IconButton playButton;
    private final IconButton customizeButton;
    private final IconButton challengeButton;
    private final TextFont creditsButton;

    private final TextFont build;

    private int unlockCount = 0;

    public TitleState(Context context) {
        super(context);

        pixel = context.getImage("pixel");

        audioButton = new AudioButton(context, context.audioHandler.getAudioState(), 25f, Constants.HEIGHT - 25f, 5f);
        title = new ImageButton(context.getImage("title"), Constants.WIDTH / 2f, Constants.HEIGHT + 100f, 0);
        playButton = new IconButton(context.getImage("play"), context.getImage("buttonbg"), Constants.WIDTH / 4f, 30, 0);
        customizeButton = new IconButton(context.getImage("slimetext"), context.getImage("buttonbg"), 2f * Constants.WIDTH / 4f, 30, 0);
        challengeButton = new IconButton(context.getImage("challenge"), context.getImage("buttonbg"), 3f * Constants.WIDTH / 4f, 30, 0);
        creditsButton = new TextFont(context, TextFont.FontType.NORMAL2, "credits", true, Constants.WIDTH - 40f, Constants.HEIGHT - 20f);

        title.lerpTo(Constants.WIDTH / 2f, Constants.HEIGHT / 2f);

        String[] v = Constants.VERSION.split("\\.");
        v[0] = v[0].substring(1);
        build = new TextFont(context, TextFont.FontType.TINY, Constants.VERSION, false, 0, 0);
        build.setPosition(Constants.WIDTH - build.getTotalWidth(), 4);

        context.audioHandler.stopMusic();
    }

    private void goToLevelSelect() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new LevelSelectV2State(context)));
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

    private void goToCredits() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new CreditsState(context)));
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

            if (playButton.containsPoint(touchPoint)) goToLevelSelect();
            if (customizeButton.containsPoint(touchPoint)) goToCustomize(false);
            if (challengeButton.containsPoint(touchPoint)) goToChallenge();
            if (audioButton.containsPoint(touchPoint))
                audioButton.setState(context.audioHandler.nextAudioState());
            if (creditsButton.containsPoint(touchPoint)) goToCredits();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToLevelSelect();
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
            sb.setColor(GameColor.DARK_TEAL);
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
            creditsButton.render(sb);

            sb.setColor(1, 1, 1, 1);
            build.render(sb);
        }
        sb.end();
    }

}
