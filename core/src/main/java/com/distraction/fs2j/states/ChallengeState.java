package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.NumberLabel;
import com.distraction.fs2j.Placement;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.Player;

import java.util.List;

import de.golfgl.gdxgamesvcs.leaderboard.ILeaderBoardEntry;

public class ChallengeState extends GameState {

    private static final int PAGE_WIDTH = Constants.WIDTH * 2;

    private TextButton backButton;
    private TextButton refreshButton;

    private OrthographicCamera staticCam;

    private Placement[] placements;

    private BreathingImage leftButton;
    private BreathingImage rightButton;

    private int level = 0;
    private TileMap[] tileMaps;
    private OrthographicCamera[] cameras;
    private InfoBox[] infoBoxes;
    private NumberLabel[] levelTitles;
    private NumberLabel[] bestMoves;
    private ImageButton[] playButtons;

    public ChallengeState(Context context) {
        this(context, 0);
    }

    public ChallengeState(Context context, int level) {
        super(context);

        backButton = new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);
        refreshButton = new TextButton(context.getImage("restarticon"), context.getImage("iconbuttonbg"), 65f, Constants.HEIGHT - 25, 5f);

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        placements = new Placement[7];
        for (int i = 0; i < placements.length; i++) placements[i] = new Placement(context, i + 1);

        leftButton = new BreathingImage(context.getImage("areaselectarrow"), 20f, Constants.HEIGHT / 2f, 10f);
        leftButton.flipped = true;
        rightButton = new BreathingImage(context.getImage("areaselectarrow"), Constants.WIDTH / 2f - 50f, Constants.HEIGHT / 2f - 5f, 10f);

        tileMaps = new TileMap[context.gameData.getMapData(Area.CHALLENGE).size()];
        infoBoxes = new InfoBox[tileMaps.length];
        levelTitles = new NumberLabel[tileMaps.length];
        bestMoves = new NumberLabel[tileMaps.length];
        playButtons = new ImageButton[tileMaps.length];
        cameras = new OrthographicCamera[tileMaps.length];
        for (int i = 0; i < tileMaps.length; i++) {
            tileMaps[i] = new TileMap(context, null, Area.CHALLENGE, i);
            infoBoxes[i] = new InfoBox(context, 36, -Constants.HEIGHT + Constants.HEIGHT / 8f + 10, Constants.WIDTH / 2f, Constants.HEIGHT / 4f);
            levelTitles[i] = new NumberLabel(context, context.getImage("leveltitle"), new Vector2(-30, -Constants.HEIGHT + Constants.HEIGHT / 8f + 20), i + 1, NumberFont.NumberSize.LARGE);
            int score = context.scoreHandler.getScores(Area.CHALLENGE)[i];
            if (score == 0) score = -1;
            bestMoves[i] = new NumberLabel(context, context.getImage("best"), new Vector2(-30, -Constants.HEIGHT + Constants.HEIGHT / 8f), score);
            playButtons[i] = new TextButton(context.getImage("play"), context.getImage("buttonbgsmall"), 90, -Constants.HEIGHT + Constants.HEIGHT / 8f + 13, 5);
            cameras[i] = new OrthographicCamera();
            cameras[i].setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
            cameras[i].position.x = 150 + i * PAGE_WIDTH;
            cameras[i].position.y = -Constants.HEIGHT / 2f;
            cameras[i].update();
        }
        setLeaderboardsEnabled(true);
        changeLevel(level);

        // todo make challenge levels music, using placeholder for now
        context.audioHandler.playMusic("mystery", 0.5f, true);
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new TitleState(context)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void changeLevel(int amount) {
        if (level + amount < 0 || level + amount >= tileMaps.length) return;
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
        context.fetchLeaderboards(() -> changeLevel(0));
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch(staticCam);
            if (backButton.containsPoint(touchPoint)) goBack();
            if (refreshButton.containsPoint(touchPoint)) refresh();
            if (leftButton.containsPoint(touchPoint)) changeLevel(-1);
            if (rightButton.containsPoint(touchPoint)) changeLevel(1);
            for (int i = 0; i < playButtons.length; i++) {
                ImageButton button = playButtons[i];
                unprojectTouch(cameras[i]);
                if (button.containsPoint(touchPoint)) goToLevel();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();

        for (Placement it : placements) it.update(dt);
        for (int i = 0; i < tileMaps.length; i++) {
            tileMaps[i].update(dt);
            cameras[i].position.x = MathUtils.lerp(cameras[i].position.x, 150 - i * PAGE_WIDTH + level * PAGE_WIDTH, 8 * dt);
            cameras[i].update();
        }

        leftButton.update(dt);
        rightButton.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        Utils.clearScreen(GameColor.DARK_GRAY);
        sb.begin();
        {

            for (int i = 0; i < tileMaps.length; i++) {
                sb.setColor(1, 1, 1, 1);
                sb.setProjectionMatrix(cameras[i].combined);
                tileMaps[i].render(sb);
                tileMaps[i].renderTop(sb, null);
                infoBoxes[i].render(sb);
                levelTitles[i].render(sb);
                bestMoves[i].render(sb);
                playButtons[i].render(sb);
            }

            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(staticCam.combined);
            leftButton.render(sb);
            rightButton.render(sb);

            for (int i = placements.length - 1; i >= 0; i--) placements[i].render(sb);
            backButton.render(sb);
            refreshButton.render(sb);
        }
        sb.end();
    }
}
