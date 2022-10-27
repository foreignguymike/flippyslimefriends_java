package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.MyViewport;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;

public class ChallengeFinishState extends GameState {

    public static class SubmitScoreResponse {
        public Response response;

        public static class Response {
            public boolean success;
        }
    }

    private final TextureRegion pixel;

    private final int level;
    private final int moves;

    private float alpha;
    private final Viewport staticViewport;

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

        staticViewport = new MyViewport(Constants.WIDTH, Constants.HEIGHT);

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
        newRecordText = best < previousBest ? new TextFont(context, TextFont.FontType.NORMAL2, "new record!", true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + infoBox.height / 2f - 48f) : null;
        bestLabel = new TextFont(context, TextFont.FontType.NORMAL2, "best " + best, true, Constants.WIDTH / 2f + 50, Constants.HEIGHT / 2f + infoBox.height / 2 - 72f);
        movesLabel = new TextFont(context, TextFont.FontType.NORMAL2, "moves " + moves, true, Constants.WIDTH / 2f - 50, Constants.HEIGHT / 2f + infoBox.height / 2 - 72f);

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
        if (context.playerDataHandler.name == null || context.playerDataHandler.name.isEmpty())
            return;
        if (!submitButton.enabled) return;

        submitButton.enabled = false;
        submitButton.setText("submit");
        context.client.setGuestName(context.playerDataHandler.name);
        boolean success = context.client.submitToLeaderboard(Integer.toString(level), moves, context.playerDataHandler.serialize(), 10000, new Net.HttpResponseListener() {

            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String res = httpResponse.getResultAsString();
                Json json = new Json();
                json.setIgnoreUnknownFields(true);
                SubmitScoreResponse response = json.fromJson(SubmitScoreResponse.class, res);
                if (response.response.success) {
                    submitButton.setText("sent!");
                } else {
                    submitButton.enabled = true;
                    submitButton.setText("try again");
                }
            }

            @Override
            public void failed(Throwable t) {
                submitButton.enabled = true;
                submitButton.setText("try again");
            }

            @Override
            public void cancelled() {
                submitButton.enabled = true;
                submitButton.setText("try again");
            }
        });
        if (!success) {
            submitButton.enabled = true;
            submitButton.setText("try again");
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
    public void resize(int w, int h) {
        super.resize(w, h);
        staticViewport.update(w, h);
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        alpha += dt * 2f;
        if (alpha > 0.4f) alpha = 0.4f;
        nameFont.setText(context.playerDataHandler.name);

        Utils.lerp(camera.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f, 10f * dt);
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(staticViewport.getCamera().combined);
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
