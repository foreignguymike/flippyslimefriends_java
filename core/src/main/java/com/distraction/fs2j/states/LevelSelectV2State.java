package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.LevelBackground;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.data.MapData;

import java.util.List;

class LevelSelectV2State extends GameState {

    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 5;
    private static final int PAGE_SIZE = NUM_ROWS * NUM_COLS;

    private static final float WIDTH_PADDING = 120f;
    private static final float HEIGHT_PADDING = 60f;
    private static final float CELL_WIDTH = (Constants.WIDTH - 2 * WIDTH_PADDING) / NUM_COLS;
    private static final float CELL_HEIGHT = (Constants.HEIGHT - 2 * HEIGHT_PADDING) / NUM_ROWS;

    private int level;
    private Area previousArea = Area.MEADOW;
    private Area currentArea = Area.MEADOW;

    private final TextureRegion diamond;
    private final TextureRegion diamondEmpty;
    private int page;

    private final List<MapData> levelData;
    private final int numLevels;
    private final int maxPages;

    private final ImageButton[] levels;
    private final TextFont[] numberFonts;

    private final BreathingImage levelSelectedBorder;
    private final TextFont levelSelectText;
    private final IconButton backButton;
    private final IconButton audioButton;
    private final OrthographicCamera staticCam;
    private final BreathingImage leftButton;
    private final BreathingImage rightButton;

    private final LevelBackground[] backgrounds;

    public LevelSelectV2State(Context context) {
        this(context, 0);
    }

    public LevelSelectV2State(Context context, int level) {
        super(context);
        this.level = level;

        backgrounds = new LevelBackground[Area.values().length];
        for (int i = 0; i < backgrounds.length; i++) {
            backgrounds[i] = new LevelBackground(context, Area.values()[i]);
            backgrounds[i].setAlpha(i == currentArea.ordinal() ? 1f : 0f);
        }

        page = level / PAGE_SIZE;
        updateArea(true);

        levelData = context.gameData.getAllMapData();
        numLevels = levelData.size();

        maxPages = MathUtils.ceil(1f * numLevels / PAGE_SIZE);

        diamond = context.getImage("leveldiamondicon");
        diamondEmpty = context.getImage("leveldiamondemptyicon");

        levels = new ImageButton[numLevels];
        numberFonts = new TextFont[numLevels];
        for (int i = 0; i < levels.length; i++) {
            int page = i / PAGE_SIZE;
            int row = (i % PAGE_SIZE) / NUM_COLS;
            int col = i % NUM_COLS;
            float x = WIDTH_PADDING + col * CELL_WIDTH + CELL_WIDTH / 2 + Constants.WIDTH * page;
            float y = Constants.HEIGHT - HEIGHT_PADDING - (row * CELL_HEIGHT + CELL_HEIGHT / 2f);
            levels[i] = new ImageButton(context.getImage("levelbutton"), x, y);
            numberFonts[i] = new TextFont(context, TextFont.FontType.FONT3, Integer.toString(i + 1), true, x, y - 13);
        }

        levelSelectedBorder = new BreathingImage(
                context.getImage("levelselectedborder"),
                level < 0 ? 0f : levels[level].pos.x,
                level < 0 ? 0f : levels[level].pos.y,
                0, 1f, 0.03f
        );
        levelSelectText = new TextFont(context, TextFont.FontType.FONT3, "level select", true, Constants.WIDTH / 2f, Constants.HEIGHT - 38f);
        backButton = new IconButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);
        audioButton = new IconButton(context.getImage("audioicon"), context.getImage("iconbuttonbg"), 65f, Constants.HEIGHT - 25f, 5f);
        audioButton.enabled = !context.audioHandler.isMuted();
        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        leftButton = new BreathingImage(context.getImage("areaselectarrow"), 50f, Constants.HEIGHT / 2f, 10f);
        leftButton.flipped = true;
        rightButton = new BreathingImage(context.getImage("areaselectarrow"), Constants.WIDTH - 50f, Constants.HEIGHT / 2f - 5f, 10f);

        camera.position.set(Constants.WIDTH * page + Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f);
        camera.update();

        rightButton.setPosition(Constants.WIDTH + (page >= maxPages - 1 ? 50f : -50f), rightButton.pos.y);
        leftButton.setPosition(page == 0 ? -50f : 50f, leftButton.pos.y);

        context.audioHandler.stopAllMusic();
    }

    private void incrementPage() {
        if (page < maxPages - 1) {
            page++;
            updateNavButtons();
            updateArea();
        }
    }

    private void decrementPage() {
        if (page > 0) {
            page--;
            updateNavButtons();
            updateArea();
        }
    }

    private void incrementLevel(int amount) {
        if (level + amount < numLevels) {
            level += amount;
            page = level / PAGE_SIZE;
            updateNavButtons();
            updateLevelSelectedBorder();
            updateArea();
        }
    }

    private void decrementLevel(int amount) {
        if (level - amount >= 0) {
            level -= amount;
            page = level / PAGE_SIZE;
            updateNavButtons();
            updateLevelSelectedBorder();
            updateArea();
        }
    }

    public void updateArea() {
        updateArea(false);
    }

    private void updateArea(boolean force) {
        Area newArea = Area.values()[page / 3];
        if (currentArea != newArea || force) {
            previousArea = currentArea;
            currentArea = newArea;
            backgrounds[previousArea.ordinal()].setVisible(false);
            backgrounds[currentArea.ordinal()].setVisible(true);
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

    private int getCurrentLevelIndex(int level) {
        return level % 45;
    }

    private Area getAreaFromLevel(int level) {
        return Area.values()[level / 45];
    }

    private float getCamPosition() {
        return Constants.WIDTH * page + Constants.WIDTH / 2f;
    }

    private void back() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new TitleState(context)));
        context.audioHandler.playSound("select", 0.3f);
        context.audioHandler.stopAllMusic();
    }

    private void goToLevel(int level) {
        if (level >= 0 && level < numLevels) {
            this.level = level;
            updateLevelSelectedBorder();
            ignoreInput = true;
            context.gsm.push(new CheckeredTransitionState(context, new PlayState(context, currentArea, level)));
            context.audioHandler.playSound("select", 0.3f);
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
            if (audioButton.containsPoint(touchPoint))
                audioButton.enabled = !context.audioHandler.toggleMute();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToLevel(level);
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            incrementLevel(level % NUM_COLS == NUM_COLS - 1 ? PAGE_SIZE - NUM_COLS + 1 : 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            decrementLevel(level % NUM_COLS == 0 ? PAGE_SIZE - NUM_COLS + 1 : 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (level % PAGE_SIZE < PAGE_SIZE - NUM_COLS) incrementLevel(NUM_COLS);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (level % PAGE_SIZE >= NUM_COLS) decrementLevel(NUM_COLS);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) back();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        camera.position.set(Utils.lerp(camera.position, getCamPosition(), Constants.HEIGHT / 2f, 0f, 6f * dt));
        camera.update();
        levelSelectedBorder.update(dt);
        leftButton.update(dt);
        rightButton.update(dt);
        for (LevelBackground bg : backgrounds) {
            bg.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);
            backgrounds[previousArea.ordinal()].render(sb);
            backgrounds[currentArea.ordinal()].render(sb);
            sb.setColor(GameColor.MIDNIGHT_BLUE);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, 60f);
            sb.draw(pixel, 0f, Constants.HEIGHT - 60f, Constants.WIDTH, 60f);
            sb.setColor(1, 1, 1, 1);
            sb.draw(pixel, 0f, 56f, Constants.WIDTH, 1f);
            sb.draw(pixel, 0f, Constants.HEIGHT - 58f, Constants.WIDTH, 1f);

            levelSelectText.render(sb);
            backButton.render(sb);
            audioButton.render(sb);

            sb.setProjectionMatrix(camera.combined);
            int lower = Math.max(page - 2, 0) * 15;
            int upper = Math.min(page + 3, 3 * 5) * 15;
            for (int i = lower; i < upper; i++) {
                ImageButton it = levels[i];
                sb.setColor(1, 1, 1, 1);
                int best = context.scoreHandler.getScores(getAreaFromLevel(i))[getCurrentLevelIndex(i)];
                it.enabled = best != 0;
                it.render(sb);
                numberFonts[i].render(sb);
                if (best == 0) sb.setColor(0.3f, 0.3f, 0.3f, 1);
                else sb.setColor(1, 1, 1, 1);
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
