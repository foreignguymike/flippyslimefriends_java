package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.NumberLabel;
import com.distraction.fs2j.SpinningLights;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;

class LevelFinishState extends GameState {

    private Area area;
    private int level;
    private int moves;
    private int best;
    private int goal;

    private Color dimColor = new Color(0, 0, 0, 0);
    private float maxDimAlpha = 0.4f;
    private OrthographicCamera staticCam;

    private float time = 0f;
    private float time1 = 0.5f;
    public float alpha = 0f;
    public float scale = 0f;

    private InfoBox infoBox;

    private ImageButton completeImage;
    private ImageButton diamondEmpty;
    private ImageButton diamond;
    private ImageButton star;

    private SpinningLights lights;
    private SpinningLights diamondLights;

    private NumberLabel bestLabel;
    private NumberLabel goalLabel;
    private NumberLabel movesLabel;

    private TextButton backButton;
    private TextButton restartButton;
    private TextButton nextButton;

    public LevelFinishState(Context context, Area area, int level, int moves, int best, int goal) {
        super(context);
        this.area = area;
        this.level = level;
        this.moves = moves;
        this.best = best;
        this.goal = goal;

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        infoBox = new InfoBox(
                context,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f,
                2f * Constants.WIDTH / 4f,
                4f * Constants.HEIGHT / 5f + 10
        );

        completeImage = new ImageButton(context.getImage("complete"), Constants.WIDTH / 2f, Constants.HEIGHT - 50f);
        diamondEmpty = new ImageButton(context.getImage("diamondfinishempty"), Constants.WIDTH / 2f + 80f, Constants.HEIGHT / 2 - infoBox.height / 2 + 64f);
        diamond = new ImageButton(context.getImage("diamondfinish"), diamondEmpty.pos.x, diamondEmpty.pos.y);
        star = new ImageButton(context.getImage("starfinish"), Constants.WIDTH / 2f, Constants.HEIGHT / 2f + 30f);

        diamond.alpha = 0f;
        diamond.scale = 20f;
        star.alpha = 0f;
        star.scale = 20f;

        lights = new SpinningLights(context, star.pos.x, star.pos.y, 5);
        diamondLights = new SpinningLights(context, diamond.pos.x, diamond.pos.y, 5, 0.5f);

        bestLabel = new NumberLabel(
                context,
                context.getImage("best"),
                new Vector2(Constants.WIDTH / 2f - 60f, Constants.HEIGHT / 2 - infoBox.height / 2 + 64f),
                best
        );
        goalLabel = new NumberLabel(
                context,
                context.getImage("goal"),
                new Vector2(Constants.WIDTH / 2f + 10, Constants.HEIGHT / 2 - infoBox.height / 2 + 54f),
                goal
        );
        movesLabel = new NumberLabel(
                context,
                context.getImage("moves"),
                new Vector2(Constants.WIDTH / 2f + 10f, Constants.HEIGHT / 2 - infoBox.height / 2 + 74f),
                moves
        );
        backButton = new TextButton(
                context.getImage("backicon"),
                context.getImage("iconbuttonbg"),
                Constants.WIDTH / 2 - 80f,
                Constants.HEIGHT / 2 - infoBox.height / 2 + 26f,
                5f
        );
        restartButton = new TextButton(
                context.getImage("restarticon"),
                context.getImage("iconbuttonbg"),
                Constants.WIDTH / 2 - 40f,
                Constants.HEIGHT / 2 - infoBox.height / 2 + 26f,
                5f
        );
        nextButton = new TextButton(
                context.getImage("next"),
                context.getImage("buttonbg"),
                Constants.WIDTH / 2f + 50f,
                Constants.HEIGHT / 2 - infoBox.height / 2 + 26f,
                5f
        );

        context.gsm.depth++;
        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
    }

    private void goToNextLevel() {
        if (level < context.gameData.getMapData(area).size() - 1) {
            ignoreInput = true;
            context.gsm.push(new CheckeredTransitionState(context, new PlayState(context, area, level + 1), 2));
        }
    }

    private void backToLevelSelect() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new LevelSelectState(context, area, level), 2));
    }

    private void restart() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new PlayState(context, area, level), 2));
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (nextButton.containsPoint(touchPoint)) goToNextLevel();
            if (backButton.containsPoint(touchPoint)) backToLevelSelect();
            if (restartButton.containsPoint(touchPoint)) restart();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) goToNextLevel();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) backToLevelSelect();
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) restart();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();

        if (dimColor.a < maxDimAlpha) {
            dimColor.a += 2f * dt;
            if (dimColor.a > maxDimAlpha) {
                dimColor.a = maxDimAlpha;
            }
        }
        time += dt;
        if (time > time1) {
            alpha = (time - time1) / (time1 / 2);
            if (alpha > 1f) {
                alpha = 1f;
            }
            scale = 50f / (49f * (time - time1) / (time1 / 2) + 1f);
            if (scale < 1f) {
                scale = 1f;
            }

            diamond.alpha = alpha;
            diamond.scale = scale;
            star.alpha = alpha;
            star.scale = scale;
        }
        Utils.lerp(camera.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f, 10f * dt);
        camera.update();
        lights.update(dt);
        diamondLights.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);

            sb.setColor(dimColor);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, Constants.HEIGHT);

            sb.setProjectionMatrix(camera.combined);

            sb.setColor(1, 1, 1, 1);
            infoBox.render(sb);
            diamondEmpty.render(sb);
            if (moves <= goal) {
                if (diamond.scale < 2) {
                    diamondLights.render(sb);
                }
                diamond.render(sb);
            }
            if (star.scale < 2) {
                lights.render(sb);
            }
            star.render(sb);
            completeImage.render(sb);
            goalLabel.render(sb);
            bestLabel.render(sb);
            movesLabel.render(sb);

            restartButton.render(sb);
            backButton.render(sb);
            if (level < context.gameData.getMapData(area).size() - 1) nextButton.render(sb);
        }
        sb.end();
    }
}
