package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.data.MapData;

import java.util.List;

class LevelSelectState extends GameState {

    private Area area;
    private int level = -1;

    private TextureRegion diamond;
    private TextureRegion diamondEmpty;
    private int numRows = 3;
    private int numCols = 5;
    private int pageSize = numRows * numCols;
    private float widthPadding = 120f;
    private float heightPadding = 60f;
    private float cellWidth = (Constants.WIDTH - 2 * widthPadding) / numCols;
    private float cellHeight = (Constants.HEIGHT - 2 * heightPadding) / numRows;
    private int page = level / pageSize;

    private List<MapData> levelData;
    private int numLevels;
    private int maxPages;

    private ImageButton[] levels;

    private NumberFont numberFont;
    private BreathingImage levelSelectedBorder;
    private TextureRegion levelSelectImage;
    private TextButton backButton;
    private Color disableColor = new Color(0.3f, 0.3f, 0.3f, 1);
    private OrthographicCamera staticCam;
    private BreathingImage leftButton;
    private BreathingImage rightButton;
    private Color color;

    public LevelSelectState(Context context, Area area) {
        this(context, area, -1);
    }

    public LevelSelectState(Context context, Area area, int level) {
        super(context);
        this.area = area;
        this.level = level;
        page = level / pageSize;

        levelData = context.gameData.getMapData(area);
        if (area == Area.TUTORIAL) numLevels = 4;
        else numLevels = levelData.size();
        maxPages = MathUtils.ceil(1f * numLevels / pageSize);

        diamond = context.getImage("leveldiamondicon");
        diamondEmpty = context.getImage("leveldiamondemptyicon");

        levels = new ImageButton[numLevels];
        for (int i = 0; i < levels.length; i++) {
            int page = i / pageSize;
            int row = (i % pageSize) / numCols;
            int col = i % numCols;
            float x = widthPadding + col * cellWidth + cellWidth / 2 + Constants.WIDTH * page;
            float y = Constants.HEIGHT - heightPadding - (row * cellHeight + cellHeight / 2f);
            levels[i] = new ImageButton(context.getImage("levelbutton"), x, y);
        }

        numberFont = new NumberFont(context, true, NumberFont.NumberSize.LARGE);
        levelSelectedBorder = new BreathingImage(
                context.getImage("levelselectedborder"),
                level < 0 ? 0f : levels[level].pos.x,
                level < 0 ? 0f : levels[level].pos.y,
                0, 1f, 0.03f
        );
        levelSelectImage = context.getImage("levelselect");
        backButton = new TextButton(context.getImage("back"), context.getImage("buttonbg"), Constants.WIDTH / 2f, 20f);
        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        leftButton = new BreathingImage(context.getImage("areaselectarrow"), 50f, Constants.HEIGHT / 2f, 10f);
        leftButton.flipped = true;
        rightButton = new BreathingImage(context.getImage("areaselectarrow"), Constants.WIDTH - 50f, Constants.HEIGHT / 2f - 5f, 10f);
        color = area.colorCopy();

        camera.position.set(Constants.WIDTH * page + Constants.WIDTH / 2, Constants.HEIGHT / 2, 0f);
        camera.update();

        rightButton.setPosition(Constants.WIDTH + (page >= maxPages - 1 ? 50f : -50f), rightButton.pos.y);
        leftButton.setPosition(page == 0 ? -50f : 50f, leftButton.pos.y);
    }

    private void incrementPage() {
        if (page < maxPages - 1) {
            page++;
            updateNavButtons();
        }
    }

    private void decrementPage() {
        if (page > 0) {
            page--;
            updateNavButtons();
        }
    }

    private void incrementLevel() {
        incrementLevel(1);
    }

    private void incrementLevel(int amount) {
        if (level + amount < numLevels) {
            level += amount;
            page = level / pageSize;
            updateNavButtons();
            updateLevelSelectedBorder();
        }
    }

    private void decrementLevel() {
        decrementLevel(1);
    }

    private void decrementLevel(int amount) {
        if (level - amount >= 0) {
            level -= amount;
            page = level / pageSize;
            updateNavButtons();
            updateLevelSelectedBorder();
        }
    }

    private void updateNavButtons() {
        if (page >= maxPages - 1) rightButton.lerpTo(Constants.WIDTH + 50f, rightButton.pos.y, 8f);
        else rightButton.lerpTo(Constants.WIDTH - 50f, rightButton.pos.y, 8f);
        if (page == 0) leftButton.lerpTo(-50f, leftButton.pos.y, 8f);
        else leftButton.lerpTo(50f, leftButton.pos.y, 8f);
    }

    private void updateLevelSelectedBorder() {
        levelSelectedBorder.setPosition(
                level < 0 ? 0f : levels[level].pos.x,
                level < 0 ? 0f : levels[level].pos.y
        );
    }

    private float getCamPosition() {
        return Constants.WIDTH * page + Constants.WIDTH / 2f;
    }

    private void back() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new AreaSelectState(context, area.ordinal())));
    }

    private void goToLevel(int level) {
        if (level >= 0 && level < numLevels) {
            this.level = level;
            updateLevelSelectedBorder();
            ignoreInput = true;
            context.gsm.push(new CheckeredTransitionState(context, new PlayState(context, area, level)));
        }
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Math.abs(getCamPosition() - camera.position.x) < 10) {
                unprojectTouch();
                for (int i = 0; i < levels.length; i++) {
                    if (levels[i].containsPoint(touchPoint) && i < numLevels) goToLevel(i);
                }
            }

            unprojectTouch(staticCam);
            if (backButton.containsPoint(touchPoint)) back();
            if (leftButton.containsPoint(touchPoint)) decrementPage();
            if (rightButton.containsPoint(touchPoint)) incrementPage();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToLevel(level);
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            incrementLevel(level % numCols == numCols - 1 ? pageSize - numCols + 1 : 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            decrementLevel(level % numCols == 0 ? pageSize - numCols + 1 : 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (level % pageSize < pageSize - numCols) incrementLevel(numCols);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (level % pageSize >= numCols) decrementLevel(numCols);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) back();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        camera.position.set(Utils.lerp(camera.position, getCamPosition(), Constants.HEIGHT / 2f, 0f, 8f * dt));
        camera.update();
        levelSelectedBorder.update(dt);
        leftButton.update(dt);
        rightButton.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        Utils.clearScreen(color);
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);
            sb.setColor(GameColor.MIDNIGHT_BLUE);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, 60f);
            sb.draw(pixel, 0f, Constants.HEIGHT - 60f, Constants.WIDTH, 60f);
            sb.setColor(1, 1, 1, 1);
            sb.draw(pixel, 0f, 56f, Constants.WIDTH, 1f);
            sb.draw(pixel, 0f, Constants.HEIGHT - 58f, Constants.WIDTH, 1f);

            sb.draw(
                    levelSelectImage,
                    (Constants.WIDTH - levelSelectImage.getRegionWidth()) / 2f,
                    Constants.HEIGHT - levelSelectImage.getRegionHeight() - 8f
            );
            backButton.render(sb);

            sb.setProjectionMatrix(camera.combined);
            for (int i = 0; i < levels.length; i++) {
                ImageButton it = levels[i];
                sb.setColor(1, 1, 1, 1);
                int best = context.scoreHandler.getScores(area)[i];
                if (best == 0) sb.setColor(disableColor);
                it.render(sb);
                numberFont.setNum(i + 1);
                numberFont.render(sb, it.pos.x, it.pos.y - 5);
                sb.setColor(1, 1, 1, 1);
                sb.draw(
                        best > 0 && best <= levelData.get(i).goal ? diamond : diamondEmpty,
                        it.pos.x - diamond.getRegionWidth() / 2f,
                        it.pos.y - diamond.getRegionHeight() / 2f + 13
                );
                if (level == i) levelSelectedBorder.render(sb);
            }
            sb.setProjectionMatrix(staticCam.combined);
            leftButton.render(sb);
            rightButton.render(sb);
        }
        sb.end();
    }
}
