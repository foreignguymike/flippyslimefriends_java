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
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Face;
import com.distraction.fs2j.tilemap.player.Player;
import com.distraction.fs2j.tilemap.player.Skin;

public class ChallengeState extends GameState {

    private static final int PAGE_WIDTH = Constants.WIDTH * 2;

    private TextButton backButton;

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
        super(context);

        backButton = new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);

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
            levelTitles[i] = new NumberLabel(context, context.getImage("leveltitle"),new Vector2(-30, -Constants.HEIGHT + Constants.HEIGHT / 8f + 20), i + 1, NumberFont.NumberSize.LARGE);
            bestMoves[i] = new NumberLabel(context, context.getImage("best"), new Vector2(-30, -Constants.HEIGHT + Constants.HEIGHT / 8f));
            playButtons[i] = new TextButton(context.getImage("play"), context.getImage("buttonbgsmall"), 90, -Constants.HEIGHT + Constants.HEIGHT / 8f + 13, 5);
            cameras[i] = new OrthographicCamera();
            cameras[i].setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
            cameras[i].position.x = 150 + i * PAGE_WIDTH;
            cameras[i].position.y = -Constants.HEIGHT / 2f;
            cameras[i].update();
        }
        changeLevel(0);
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new TitleState(context)));
    }

    // todo test method
    private Player randomPlayer() {
        Player player = new Player(context, null, null, 0, 0, false);
        int numAcc = MathUtils.random(1, 3);
        int[] custom = new int[2 + numAcc];
        custom[0] = MathUtils.random(Skin.values().length - 1);
        custom[1] = MathUtils.random(Face.values().length - 1);
        for (int i = 2; i < custom.length; i++)
            custom[i] = MathUtils.random(AccessoryType.values().length - 1);
        player.playerRenderer.setCustomization(custom);
        return player;
    }

    private void changeLevel(int amount) {
        if (level + amount < 0 || level + amount >= tileMaps.length) return;
        level += amount;
        placements[0].setScore(randomPlayer(), MathUtils.random(20, 25));
        placements[1].setScore(randomPlayer(), MathUtils.random(26, 30));
        placements[2].setScore(randomPlayer(), MathUtils.random(31, 35));
        placements[3].setScore(MathUtils.random(36, 45));
        placements[4].setScore(MathUtils.random(46, 55));
        placements[5].setScore(0);
        placements[6].setScore(0);
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch(staticCam);
            if (backButton.containsPoint(touchPoint)) goBack();
            if (leftButton.containsPoint(touchPoint)) changeLevel(-1);
            if (rightButton.containsPoint(touchPoint)) changeLevel(1);
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
        }
        sb.end();
    }
}
