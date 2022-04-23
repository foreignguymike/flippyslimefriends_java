package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.NumberLabel;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;

public class ChallengeFinishState extends GameState {

    private TextureRegion pixel;

    private int level;
    private int moves;
    private int best;

    private float alpha;
    private OrthographicCamera staticCam;

    private InfoBox infoBox;
    private InfoBox border;

    private NumberLabel title;
    private NumberLabel movesLabel;
    private NumberLabel bestLabel;

    private TextFont nameFont;
    private TextButton changeNameButton;
    private TextButton submitButton;
    private TextButton backButton;

    private TextFont warning;
    private TextFont warning2;

    public ChallengeFinishState(Context context, int level, int moves, int best) {
        super(context);
        this.level = level;
        this.moves = moves;
        this.best = best;

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
        border = new InfoBox(
                context,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f,
                2f * Constants.WIDTH / 4f,
                0
        );

        camera.position.y = Constants.HEIGHT * 2;
        camera.update();

        title = new NumberLabel(
                context,
                context.getImage("leveltitle"),
                new Vector2(Constants.WIDTH / 2f, Constants.HEIGHT / 2f + infoBox.height / 2f - 20),
                level + 1,
                NumberFont.NumberSize.LARGE
        );
        bestLabel = new NumberLabel(
                context,
                context.getImage("best"),
                new Vector2(Constants.WIDTH / 2f + 40f, Constants.HEIGHT / 2f + infoBox.height / 2 - 64f),
                best
        );
        movesLabel = new NumberLabel(
                context,
                context.getImage("moves"),
                new Vector2(Constants.WIDTH / 2f - 60f, Constants.HEIGHT / 2f + infoBox.height / 2 - 64f),
                moves
        );

        nameFont = new TextFont(
                context,
                context.playerDataHandler.name,
                true,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f
        );

        changeNameButton = new TextButton(
                context.getImage("editname"),
                context.getImage("buttonbg"),
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f - 30,
                5f
        );

        warning = new TextFont(context, "you must have a name", true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + 20);
        warning2 = new TextFont(context, "to submit a score", true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f);

        backButton = new TextButton(
                context.getImage("backicon"),
                context.getImage("iconbuttonbg"),
                Constants.WIDTH / 2f - infoBox.width / 2 + 30,
                Constants.HEIGHT / 2f - infoBox.height / 2 + 26f,
                5f
        );
        submitButton = new TextButton(
                context.getImage("submit"),
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
            if (changeNameButton.containsPoint(touchPoint)) goToEditName();
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
            movesLabel.render(sb);
            bestLabel.render(sb);

            if (context.playerDataHandler.name == null || context.playerDataHandler.name.isEmpty()) {
                warning.render(sb);
                warning2.render(sb);
            } else {
                nameFont.render(sb);
                submitButton.render(sb);
            }
            changeNameButton.render(sb);
            backButton.render(sb);
        }
        sb.end();
    }
}
