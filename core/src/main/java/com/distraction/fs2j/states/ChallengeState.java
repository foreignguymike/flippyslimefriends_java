package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.distraction.fs2j.AudioButton;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.MyViewport;
import com.distraction.fs2j.Placement;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.Player;

import java.util.List;

import de.golfgl.gdxgamesvcs.leaderboard.ILeaderBoardEntry;

public class ChallengeState extends GameState {

    private static final int PAGE_WIDTH = Constants.WIDTH * 2;

    private final IconButton backButton;
    private final IconButton refreshButton;
    private final AudioButton audioButton;

    private final Viewport staticViewport;

    private final Placement[] placements;

    private final BreathingImage leftButton;
    private final BreathingImage rightButton;

    private int level = -1;
    private final TileMap[] tileMaps;
    private final Viewport[] viewports;
    private final InfoBox[] infoBoxes;
    private final TextFont[] levelTitles;
    private final TextFont[] bestMoves;
    private final ImageButton[] playButtons;

    private final TextFont[] refreshTexts;

    public ChallengeState(Context context) {
        this(context, 0);
    }

    public ChallengeState(Context context, int level) {
        super(context);
        pixel = context.getImage("pixel");

        backButton = new IconButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);
        refreshButton = new IconButton(context.getImage("restarticon"), context.getImage("iconbuttonbg"), 65f, Constants.HEIGHT - 25, 5f);
        audioButton = new AudioButton(context, context.audioHandler.getAudioState(), 105f, Constants.HEIGHT - 25f, 5f);

        staticViewport = new MyViewport(Constants.WIDTH, Constants.HEIGHT);

        placements = new Placement[7];
        for (int i = 0; i < placements.length; i++) placements[i] = new Placement(context, i + 1);

        leftButton = new BreathingImage(context.getImage("areaselectarrow"), 20f, Constants.HEIGHT / 2f, 10f);
        leftButton.flipped = true;
        rightButton = new BreathingImage(context.getImage("areaselectarrow"), Constants.WIDTH / 2f - 50f, Constants.HEIGHT / 2f - 5f, 10f);

        tileMaps = new TileMap[context.gameData.getMapData(Area.CHALLENGE).size()];
        infoBoxes = new InfoBox[tileMaps.length];
        levelTitles = new TextFont[tileMaps.length];
        bestMoves = new TextFont[tileMaps.length];
        playButtons = new ImageButton[tileMaps.length];
        viewports = new MyViewport[tileMaps.length];
        for (int i = 0; i < tileMaps.length; i++) {
            tileMaps[i] = new TileMap(context, null, Area.CHALLENGE, i);
            infoBoxes[i] = new InfoBox(context, 36, -Constants.HEIGHT + Constants.HEIGHT / 8f + 10, Constants.WIDTH / 2f, Constants.HEIGHT / 4f);
            levelTitles[i] = new TextFont(context, TextFont.FontType.BIG, "level " + (i + 1), true, -15, -Constants.HEIGHT + Constants.HEIGHT / 8f + 20);
            int score = context.scoreHandler.getScores(Area.CHALLENGE)[i];
            bestMoves[i] = new TextFont(context, TextFont.FontType.NORMAL2, "best " + (score == 0 ? "-" : score), true, -15, -Constants.HEIGHT + Constants.HEIGHT / 8f - 1);
            playButtons[i] = new IconButton(context.getImage("play"), context.getImage("buttonbgsmall"), 90, -Constants.HEIGHT + Constants.HEIGHT / 8f + 13, 5);
            viewports[i] = new MyViewport(Constants.WIDTH, Constants.HEIGHT);
            Camera cam = viewports[i].getCamera();
            cam.position.x = 150 + i * PAGE_WIDTH;
            cam.position.y = -Constants.HEIGHT / 2f;
            cam.update();
        }

        refreshTexts = new TextFont[3];
        refreshTexts[0] = new TextFont(context, TextFont.FontType.NORMAL, "press refresh", true, 3f * Constants.WIDTH / 4f, Constants.HEIGHT / 2f + 15);
        refreshTexts[1] = new TextFont(context, TextFont.FontType.NORMAL, "to update the", true, 3f * Constants.WIDTH / 4f, Constants.HEIGHT / 2f);
        refreshTexts[2] = new TextFont(context, TextFont.FontType.NORMAL, "leaderboards", true, 3f * Constants.WIDTH / 4f, Constants.HEIGHT / 2f - 15);
        setLeaderboardsEnabled(context.leaderboardsInitialized);
        setLevel(level);

        context.audioHandler.stopMusic();
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new TitleState(context)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void setLevel(int level) {
        changeLevel(level - this.level);
    }

    private void changeLevel(int amount) {
        if (level + amount < 0 || level + amount >= tileMaps.length) return;
        if (level != -1) {
            context.audioHandler.playSound("selectshort2", 0.4f);
        }
        level += amount;
        if (level >= context.playerDataHandler.leaderboards.size()) {
            for (Placement it : placements) it.setScore(0, "");
            return;
        }
        List<ILeaderBoardEntry> entries = context.playerDataHandler.leaderboards.get(level);
        for (int i = 0; i < 7; i++) {
            if (i >= entries.size()) {
                for (int j = i; j < 7; j++) placements[j].setScore(0, "");
                break;
            }
            ILeaderBoardEntry entry = entries.get(i);
            int score = Integer.parseInt(entry.getFormattedValue());
            String name = entry.getUserDisplayName();
            String tag = entry.getScoreTag();
            int[] custom = context.playerDataHandler.deserialize(tag);
            Player player = new Player(context, null, null, 0, 0, false);
            player.playerRenderer.setCustomization(custom);
            placements[i].setScore(score, player, name);
        }
    }

    private void setLeaderboardsEnabled(boolean enabled) {
        for (Placement it : placements) it.setEnabled(enabled);
    }

    private void goToLevel() {
        if (level >= 0 && level < context.gameData.getMapData(Area.CHALLENGE).size()) {
            ignoreInput = true;
            context.gsm.push(new CheckeredTransitionState(context, new PlayState(context, Area.CHALLENGE, level)));
            context.audioHandler.playSound("select", 0.3f);
        }
    }

    private void refresh() {
        context.fetchLeaderboards(() -> {
            setLeaderboardsEnabled(true);
            changeLevel(0);
        });
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch(staticViewport);
            if (backButton.containsPoint(touchPoint)) goBack();
            if (refreshButton.containsPoint(touchPoint)) refresh();
            if (audioButton.containsPoint(touchPoint))
                audioButton.setState(context.audioHandler.nextAudioState());
            if (leftButton.containsPoint(touchPoint)) changeLevel(-1);
            if (rightButton.containsPoint(touchPoint)) changeLevel(1);
            for (int i = 0; i < playButtons.length; i++) {
                ImageButton button = playButtons[i];
                unprojectTouch(viewports[i]);
                if (button.containsPoint(touchPoint)) goToLevel();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) changeLevel(-1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) changeLevel(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToLevel();
    }

    @Override
    public void resize(int w, int h) {
        super.resize(w, h);
        staticViewport.update(w, h);
        for (Viewport v : viewports) {
            v.update(w, h);
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();

        for (Placement it : placements) it.update(dt);
        for (int i = 0; i < tileMaps.length; i++) {
            tileMaps[i].update(dt);
            Camera cam = viewports[i].getCamera();
            cam.position.x = MathUtils.lerp(cam.position.x, 150 - i * PAGE_WIDTH + level * PAGE_WIDTH, 8 * dt);
            cam.update();
        }

        leftButton.update(dt);
        rightButton.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setColor(GameColor.DARK_GRAY);
            sb.draw(pixel, 0, 0, Constants.WIDTH, Constants.HEIGHT);
            for (int i = 0; i < tileMaps.length; i++) {
                sb.setColor(1, 1, 1, 1);
                sb.setProjectionMatrix(viewports[i].getCamera().combined);
                tileMaps[i].render(sb);
                tileMaps[i].renderTop(sb, null);
                infoBoxes[i].render(sb);
                levelTitles[i].render(sb);
                bestMoves[i].render(sb);
                playButtons[i].render(sb);
            }

            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(staticViewport.getCamera().combined);
            leftButton.render(sb);
            rightButton.render(sb);

            for (int i = placements.length - 1; i >= 0; i--) placements[i].render(sb);
            backButton.render(sb);
            refreshButton.render(sb);
            audioButton.render(sb);
            if (!context.leaderboardsInitialized) {
                for (TextFont textFont : refreshTexts) {
                    textFont.render(sb);
                }
            }
        }
        sb.end();
    }
}
