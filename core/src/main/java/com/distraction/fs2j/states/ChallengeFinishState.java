package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;

public class ChallengeFinishState extends GameState {

    private final TextureRegion pixel;

    private final int level;
    private final int moves;

    private float alpha;
    private final OrthographicCamera staticCam;

    private final InfoBox infoBox;

    private final TextFont title;
    private final TextFont newRecordText;
    private final TextFont movesLabel;
    private final TextFont bestLabel;

    private final TextFont nameFont;
    private final TextButton editNameButton;
    private final TextButton submitButton;
    private final IconButton backButton;

    private final TextFont warning;
    private final TextFont warning2;

    public ChallengeFinishState(Context context, int level, int moves, int best, int previousBest) {
        super(context);
        this.level = level;
        this.moves = moves;

        pixel = context.getImage("pixel");

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        infoBox = new InfoBox(
                context,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f,
                2f * Constants.WIDTH / 4f,
                4f * Constants.HEIGHT / 5f + 10
        );

        camera.position.y = Constants.HEIGHT * 2;
        camera.update();

        title = new TextFont(context, TextFont.FontType.BIG, "level " + (level + 1), true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + infoBox.height / 2f - 28);
        newRecordText = best < previousBest ? new TextFont(context, TextFont.FontType.NORMAL, "new record!", true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + infoBox.height / 2f - 48f) : null;
        bestLabel = new TextFont(context, TextFont.FontType.NORMAL, "best " + best, true, Constants.WIDTH / 2f + 50, Constants.HEIGHT / 2f + infoBox.height / 2 - 72f);
        movesLabel = new TextFont(context, TextFont.FontType.NORMAL, "moves " + moves, true, Constants.WIDTH / 2f - 50, Constants.HEIGHT / 2f + infoBox.height / 2 - 72f);

        nameFont = new TextFont(
                context,
                context.playerDataHandler.name,
                true,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f
        );

        editNameButton = new TextButton(
                context,
                "edit name",
                context.getImage("buttonbg"),
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f - 30,
                5f
        );

        warning = new TextFont(context, "you must have a name", true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + 20);
        warning2 = new TextFont(context, "to submit a score", true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f);

        backButton = new IconButton(
                context.getImage("backicon"),
                context.getImage("iconbuttonbg"),
                Constants.WIDTH / 2f - infoBox.width / 2 + 30,
                Constants.HEIGHT / 2f - infoBox.height / 2 + 26f,
                5f
        );
        submitButton = new TextButton(
                context,
                "submit",
                context.getImage("buttonbg"),
                Constants.WIDTH / 2f + 20,
                Constants.HEIGHT / 2f - infoBox.height / 2 + 26f,
                5f
        );

        context.gsm.depth++;
    }

    private void submitScore() {
        if (context.playerDataHandler.name != null) {
            context.client.setGuestName(context.playerDataHandler.name);
            context.client.submitToLeaderboard("BETA_1", moves, context.playerDataHandler.serialize());
            goBack();
        }
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new ChallengeState(context, level), 2));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void goToEditName() {
        ignoreInput = true;
        context.gsm.push(new NameState(context, this));
        context.gsm.depth++;
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (editNameButton.containsPoint(touchPoint)) goToEditName();
            if (backButton.containsPoint(touchPoint)) goBack();
            if (submitButton.enabled && submitButton.containsPoint(touchPoint)) submitScore();
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        alpha += dt * 2f;
        if (alpha > 0.4f) alpha = 0.4f;
        nameFont.setText(context.playerDataHandler.name);

        Utils.lerp(camera.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f, 10f * dt);
        camera.update();
        submitButton.enabled = context.playerDataHandler.name != null && !context.playerDataHandler.name.isEmpty();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);
            sb.setColor(0, 0, 0, alpha);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, Constants.HEIGHT);

            sb.setProjectionMatrix(camera.combined);
            sb.setColor(1, 1, 1, 1);
            infoBox.render(sb);
            title.render(sb);
            if (newRecordText != null) {
                newRecordText.render(sb);
            }
            movesLabel.render(sb);
            bestLabel.render(sb);

            if (context.playerDataHandler.name == null || context.playerDataHandler.name.isEmpty()) {
                warning.render(sb);
                warning2.render(sb);
            } else {
                nameFont.render(sb);
                submitButton.render(sb);
            }
            editNameButton.render(sb);
            backButton.render(sb);
        }
        sb.end();
    }
}
