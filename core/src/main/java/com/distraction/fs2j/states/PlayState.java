package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.Background;
import com.distraction.fs2j.ButtonListener;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.HUD;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.TilePoint;
import com.distraction.fs2j.tilemap.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class PlayState extends GameState implements TileMap.TileListener, Player.MoveListener, ButtonListener {

    private Area area;
    private int level;

    private TileMap tileMap;
    private List<Player> players;
    private int playerIndex;
    private Player player;
    private List<Player> sortedPlayers;

    private Background bg;
    private OrthographicCamera bgCam;

    private HUD hud;
    private Vector2 cameraOffset = new Vector2(0, 0);

    private Comparator<Player> playerComp = (Comparator<Player>) (p1, p2) -> (int) p2.isop.y - (int) p1.isop.y;

    public PlayState(Context context, Area area, int level) {
        super(context);
        this.area = area;
        this.level = level;

        tileMap = new TileMap(context, this, area, level);
        players = new ArrayList<>();
        for (TilePoint it : tileMap.mapData.playerPositions) {
            Player player = new Player(context, tileMap, this, it.row, it.col, tileMap.startBubble);
            players.add(player);
            player.players = players;
        }
        setPlayerIndex(0);
        sortedPlayers = new ArrayList<>(players);

        bg = new Background(context, area);
        bgCam = new OrthographicCamera();
        bgCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        hud = new HUD(context, level, this, players);

        camera.position.set(-100f, player.isop.y + cameraOffset.y, 0f);
        camera.update();

        hud.setGoal(tileMap.mapData.goal);

        int[] scores = context.scoreHandler.scores.get(area);
        if (scores != null) hud.setBest(scores[level]);

        if (players.size() > 1) player.showSelected(true);
    }

    private void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
        player = players.get(playerIndex);
    }

    @Override
    public void onMoved() {
        hud.incrementMoves();
    }

    @Override
    public void onTileToggled(TileMap tileMap) {
        if (tileMap.isFinished(players) && !ignoreInput) {
            ignoreInput = true;
            hud.hideInfo = true;
            if (hud.getBest() < 0 || hud.getMoves() < hud.getBest()) {
                context.scoreHandler.saveScore(area, level, hud.getMoves());
                hud.setBest(hud.getMoves());
            }
            context.gsm.push(
                    new LevelFinishState(
                            context,
                            area,
                            level,
                            hud.getMoves(),
                            hud.getBest(),
                            hud.getGoal()
                    )
            );
        }
    }

    @Override
    public void onIllegal() {
        if (!tileMap.isFinished(players) && !ignoreInput) {
            ignoreInput = true;
            context.gsm.push(new TransitionState(context, new PlayState(context, area, level)));
        }
    }

    private void back() {
        if (!ignoreInput) {
            ignoreInput = true;
            context.gsm.push(new CheckeredTransitionState(context, new LevelSelectState(context, area, level)));
        }
    }

    private void switchPlayer() {
        if (playerIndex < players.size() - 1) setPlayerIndex(playerIndex + 1);
        else setPlayerIndex(0);
        hud.currentPlayer = playerIndex;
        if (players.size() > 1) {
            for (int i = 0; i < players.size(); i++) {
                players.get(i).showSelected(i == playerIndex);
            }
        }
    }

    @Override
    public void onButtonPressed(ButtonType type) {
        if (type == ButtonListener.ButtonType.UP) player.moveTile(-1, 0);
        if (type == ButtonListener.ButtonType.LEFT) player.moveTile(0, -1);
        if (type == ButtonListener.ButtonType.DOWN) player.moveTile(1, 0);
        if (type == ButtonListener.ButtonType.RIGHT) player.moveTile(0, 1);
        if (type == ButtonListener.ButtonType.BUBBLE_DROP) player.dropBubble();
        if (type == ButtonListener.ButtonType.SWITCH) switchPlayer();
        if (type == ButtonListener.ButtonType.RESTART) onIllegal();
        if (type == ButtonListener.ButtonType.BACK) back();
    }

    private void handleInput() {
        unprojectTouch();
        hud.handleInput();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.moveTile(0, 1);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.moveTile(0, -1);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.moveTile(-1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.moveTile(1, 0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) onIllegal();
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) switchPlayer();
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) player.dropBubble();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) back();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();

        for (Player it : players) it.update(dt);
        Collections.sort(sortedPlayers, playerComp);

        camera.position.set(
                Utils.lerp(camera.position,
                        player.isop.x + cameraOffset.x,
                        player.isop.y + cameraOffset.y,
                        0f,
                        4f * dt
                )
        );
        camera.update();

        bg.update(dt);
        tileMap.update(dt);
        hud.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(bgCam.combined);
            bg.render(sb);

            sb.setProjectionMatrix(camera.combined);
            tileMap.render(sb);
            tileMap.renderTop(sb, sortedPlayers);

            hud.render(sb);
        }
        sb.end();
    }
}
