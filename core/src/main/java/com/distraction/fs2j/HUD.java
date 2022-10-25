package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HUD {

    public static final float HEIGHT = 48f;

    private final Context context;
    private final ButtonListener buttonListener;
    private final List<Player> players;
    private final Area area;

    private final Vector3 touchPoint = new Vector3();

    public boolean hideInfo = false;
    public int currentPlayer = 0;

    private final OrthographicCamera topCam;
    private final OrthographicCamera bottomCam;

    private final Map<ButtonListener.ButtonType, ImageButton> bottomButtons;
    private final Map<ButtonListener.ButtonType, IconButton> topButtons;
    private final IconButton switchButton;
    private final IconButton bubbleDropButton;
    private final AudioButton audioButton;
    private final TextFont[] labels;

    private int goal;
    private int best;
    private int moves;

    public HUD(Context context, int level, ButtonListener buttonListener, List<Player> players, Area area) {
        this.context = context;
        this.buttonListener = buttonListener;
        this.players = players;
        this.area = area;

        topCam = new OrthographicCamera();
        topCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        bottomCam = new OrthographicCamera();
        bottomCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        bottomButtons = new HashMap<>();
        // for arrow button placement
        Vector2 a = new Vector2(60, 60);
        float dist = 22f;
        bottomButtons.put(ButtonListener.ButtonType.LEFT, new ImageButton(context.getImage("upleftarrow"), a.x - dist, a.y + dist, 5f));
        bottomButtons.put(ButtonListener.ButtonType.UP, new ImageButton(context.getImage("uprightarrow"), a.x + dist, a.y + dist, 5f));
        bottomButtons.put(ButtonListener.ButtonType.RIGHT, new ImageButton(context.getImage("downrightarrow"), a.x + dist, a.y - dist, 5f));
        bottomButtons.put(ButtonListener.ButtonType.DOWN, new ImageButton(context.getImage("downleftarrow"), a.x - dist, a.y - dist, 5f));
        topButtons = new HashMap<>();
        topButtons.put(ButtonListener.ButtonType.BACK,
                new IconButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - HEIGHT / 2f, 5f));
        topButtons.put(ButtonListener.ButtonType.RESTART,
                new IconButton(context.getImage("restarticon"), context.getImage("iconbuttonbg"), 65f, Constants.HEIGHT - HEIGHT / 2f, 5f));
        switchButton = new IconButton(context.getImage("switchicon"), context.getImage("iconbuttonbg"), Constants.WIDTH - 30f, 34f, 5f);
        bubbleDropButton = new IconButton(context.getImage("bubbledropicon"), context.getImage("iconbuttonbg"), Constants.WIDTH - 30f, 34f, 5f);
        audioButton = new AudioButton(context, context.audioHandler.getAudioState(), 105f, Constants.HEIGHT - HEIGHT / 2f, 5f);

        labels = new TextFont[]{
                new TextFont(context, TextFont.FontType.NORMAL2, "goal 0", false, Constants.WIDTH - 70f, Constants.HEIGHT - 14f),
                new TextFont(context, TextFont.FontType.NORMAL2, "best 0", false, Constants.WIDTH - 72f, Constants.HEIGHT - 34f + (area == Area.CHALLENGE ? 20 : 0)),
                new TextFont(context, TextFont.FontType.NORMAL2, "moves 0", false, Constants.WIDTH - 81f, Constants.HEIGHT - 54f + (area == Area.CHALLENGE ? 20 : 0)),
                new TextFont(context, TextFont.FontType.BIG, (area == Area.CHALLENGE ? "challenge " : "level ") + (level + 1), true, Constants.WIDTH / 2f, Constants.HEIGHT - HEIGHT / 2f),
        };

        if (players.size() > 1) {
            bubbleDropButton.setPosition(Constants.WIDTH - 30f, 80f);
        }
    }

    public void setGoal(int goal) {
        this.goal = goal;
        labels[0].setText("goal " + goal);
    }

    public void setBest(int best) {
        this.best = best;
        if (best == 0) labels[1].setText("best -");
        else labels[1].setText("best " + best);
    }

    public void incrementMoves() {
        moves++;
        labels[2].setText("moves " + moves);
    }

    public int getGoal() {
        return goal;
    }

    public int getBest() {
        return best;
    }

    public int getMoves() {
        return moves;
    }

    private void updateVisibility(float dt) {
        if (hideInfo) {
            Utils.lerp(topCam.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f - HEIGHT * 2, 0f, 4f * dt);
            Utils.lerp(bottomCam.position, Constants.WIDTH / 2f, 300f, 0f, 4f * dt);
        } else {
            Utils.lerp(topCam.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f, 4f * dt);
            Utils.lerp(bottomCam.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f, 4f * dt);
        }
        topCam.update();
        bottomCam.update();
    }

    public void handleInput() {
        for (IconButton it : topButtons.values()) it.scale = 1f;
        for (ImageButton it : bottomButtons.values()) it.scale = 1f;
        switchButton.scale = 1f;
        bubbleDropButton.scale = 1f;
        audioButton.scale = 1f;
        if (Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            topCam.unproject(touchPoint);
            for (Map.Entry<ButtonListener.ButtonType, IconButton> it : topButtons.entrySet()) {
                ButtonListener.ButtonType key = it.getKey();
                IconButton value = it.getValue();
                if (value.containsPoint(touchPoint)) {
                    buttonListener.onButtonPressed(key);
                    value.scale = 0.75f;
                } else {
                    value.scale = 1f;
                }
            }
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            bottomCam.unproject(touchPoint);
            for (Map.Entry<ButtonListener.ButtonType, ImageButton> it : bottomButtons.entrySet()) {
                ButtonListener.ButtonType key = it.getKey();
                ImageButton value = it.getValue();
                if (value.containsPoint(touchPoint)) {
                    buttonListener.onButtonPressed(key);
                    value.scale = 0.75f;
                } else {
                    value.scale = 1f;
                }
            }
            if (switchButton.containsPoint(touchPoint)) switchButton.scale = 0.75f;
            if (bubbleDropButton.containsPoint(touchPoint)) bubbleDropButton.scale = 0.75f;
            if (audioButton.containsPoint(touchPoint)) audioButton.scale = 0.75f;
        }
        if (Gdx.input.justTouched()) {
            if (switchButton.scale < 1f) {
                buttonListener.onButtonPressed(ButtonListener.ButtonType.SWITCH);
            }
            if (bubbleDropButton.scale < 1f && players.get(currentPlayer).canDrop) {
                buttonListener.onButtonPressed(ButtonListener.ButtonType.BUBBLE_DROP);
            }
            if (audioButton.scale < 1f) {
                audioButton.setState(context.audioHandler.nextAudioState());
            }
        }
    }

    public void update(float dt) {
        updateVisibility(dt);
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(topCam.combined);
        sb.setColor(1, 1, 1, 1);
        for (int i = area == Area.CHALLENGE ? 1 : 0; i < labels.length; i++) labels[i].render(sb);
        for (ImageButton it : topButtons.values()) it.render(sb);
        audioButton.render(sb);

        sb.setProjectionMatrix(bottomCam.combined);
        for (ImageButton it : bottomButtons.values()) it.render(sb);
        if (players.size() > 1) switchButton.render(sb);
        if (players.get(currentPlayer).bubbling) bubbleDropButton.render(sb);
    }

}

