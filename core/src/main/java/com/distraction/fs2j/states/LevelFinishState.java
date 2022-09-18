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

class LevelFinishState extends GameState {

    private static final float MAX_DIM_ALPHA = 0.4f;
    private static final float ALPHA_TIME = 0.5f;

    private final Area area;
    private final int level;
    private final int moves;
    private final int goal;

    private final Color dimColor = new Color(0, 0, 0, 0);
    private final OrthographicCamera staticCam;

    private float time = 0f;
    public float alpha = 0f;
    public float scale = 0f;

    private final InfoBox infoBox;

    private final ImageButton completeImage;
    private final ImageButton diamondEmpty;
    private final ImageButton diamond;
    private final ImageButton star;

    private final SpinningLights lights;
    private final SpinningLights diamondLights;

    private final NumberLabel bestLabel;
    private final NumberLabel goalLabel;
    private final NumberLabel movesLabel;

    private final TextButton backButton;
    private final TextButton restartButton;
    private final TextButton nextButton;

    public LevelFinishState(Context context, Area area, int level, int moves, int best, int goal) {
        super(context);
        this.area = area;
        this.level = level;
        this.moves = moves;
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
        diamondEmpty = new ImageButton(context.getImage("diamondfinishempty"), Constants.WIDTH / 2f + 80f, Constants.HEIGHT / 2f - infoBox.height / 2 + 64f);
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
                new Vector2(Constants.WIDTH / 2f - 60f, Constants.HEIGHT / 2f - infoBox.height / 2 + 64f),
                best
        );
        goalLabel = new NumberLabel(
                context,
                context.getImage("goal"),
                new Vector2(Constants.WIDTH / 2f + 10, Constants.HEIGHT / 2f - infoBox.height / 2 + 54f),
                goal
        );
        movesLabel = new NumberLabel(
                context,
                context.getImage("moves"),
                new Vector2(Constants.WIDTH / 2f + 10f, Constants.HEIGHT / 2f - infoBox.height / 2 + 74f),
                moves
        );
        backButton = new TextButton(
                context.getImage("backicon"),
                context.getImage("iconbuttonbg"),
                Constants.WIDTH / 2f - 80f,
                Constants.HEIGHT / 2f - infoBox.height / 2 + 26f,
                5f
        );
        restartButton = new TextButton(
                context.getImage("restarticon"),
                context.getImage("iconbuttonbg"),
                Constants.WIDTH / 2f - 40f,
                Constants.HEIGHT / 2f - infoBox.height / 2 + 26f,
                5f
        );
        nextButton = new TextButton(
                context.getImage("next"),
                context.getImage("buttonbg"),
                Constants.WIDTH / 2f + 50f,
                Constants.HEIGHT / 2f - infoBox.height / 2 + 26f,
                5f
        );

        context.gsm.depth++;
        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
    }

    private void goToNextLevel() {
        if (level < context.gameData.getMapData(area).size() - 1) {
            // hide secret level
            if (area != Area.TUTORIAL || level != 3) {
                ignoreInput = true;
                context.gsm.push(new CheckeredTransitionState(context, new PlayState(context, area, level + 1), 2));
                context.audioHandler.playSound("select", 0.3f);
            }
        }
    }

    private void backToLevelSelect() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new LevelSelectState(context, area, level), 2));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void restart() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new PlayState(context, area, level), 2));
        context.audioHandler.playSound("select", 0.3f);
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

        if (dimColor.a < MAX_DIM_ALPHA) {
            dimColor.a += 2f * dt;
            if (dimColor.a > MAX_DIM_ALPHA) {
                dimColor.a = MAX_DIM_ALPHA;
            }
        }
        time += dt;
        if (time > ALPHA_TIME) {
            alpha = (time - ALPHA_TIME) / (ALPHA_TIME / 2);
            if (alpha > 1f) {
                alpha = 1f;
            }
            scale = 50f / (49f * (time - ALPHA_TIME) / (ALPHA_TIME / 2) + 1f);
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
            if (level < context.gameData.getMapData(area).size() - 1) {
                // hide secret level
                if (area != Area.TUTORIAL || level != 3) {
                    nextButton.render(sb);
                }
            }
        }
        sb.end();
    }
}
