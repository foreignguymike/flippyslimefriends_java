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

    private Context context;
    private ButtonListener buttonListener;
    private List<Player> players;
    private Area area;

    private Vector3 touchPoint = new Vector3();

    public boolean hideInfo = false;
    public int currentPlayer = 0;

    private OrthographicCamera topCam;
    private OrthographicCamera bottomCam;

    // for arrow button placement
    private Vector2 a = new Vector2(60, 60);
    private float dist = 22f;
    private Map<ButtonListener.ButtonType, ImageButton> bottomButtons;
    private Map<ButtonListener.ButtonType, TextButton> topButtons;
    private TextButton switchButton;
    private TextButton bubbleDropButton;
    private TextButton audioButton;
    private NumberLabel[] labels;

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
        bottomButtons.put(ButtonListener.ButtonType.LEFT, new ImageButton(context.getImage("upleftarrow"), a.x - dist, a.y + dist, 5f));
        bottomButtons.put(ButtonListener.ButtonType.UP, new ImageButton(context.getImage("uprightarrow"), a.x + dist, a.y + dist, 5f));
        bottomButtons.put(ButtonListener.ButtonType.RIGHT, new ImageButton(context.getImage("downrightarrow"), a.x + dist, a.y - dist, 5f));
        bottomButtons.put(ButtonListener.ButtonType.DOWN, new ImageButton(context.getImage("downleftarrow"), a.x - dist, a.y - dist, 5f));
        topButtons = new HashMap<>();
        topButtons.put(ButtonListener.ButtonType.BACK,
                new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - HEIGHT / 2f, 5f));
        topButtons.put(ButtonListener.ButtonType.RESTART,
                new TextButton(context.getImage("restarticon"), context.getImage("iconbuttonbg"), 65f, Constants.HEIGHT - HEIGHT / 2f, 5f));
        switchButton = new TextButton(context.getImage("switch"), context.getImage("buttonbg"), Constants.WIDTH / 2f, 30f, 5f);
        bubbleDropButton = new TextButton(context.getImage("bubbledropicon"), context.getImage("iconbuttonbg"), Constants.WIDTH - 25f, 25f, 5f);
        audioButton = new TextButton(context.getImage("audioicon"), context.getImage("iconbuttonbg"), 105f, Constants.HEIGHT - HEIGHT / 2f, 5f);
        audioButton.enabled = !context.audioHandler.isMuted();

        labels = new NumberLabel[]{
                new NumberLabel(
                        context,
                        context.getImage("goal"),
                        new Vector2(Constants.WIDTH - 50f, Constants.HEIGHT - 15f)
                ),
                new NumberLabel(
                        context,
                        context.getImage("best"),
                        new Vector2(Constants.WIDTH - 50f, Constants.HEIGHT - 35f + (area == Area.CHALLENGE ? 12 : 0))
                ),
                new NumberLabel(
                        context,
                        context.getImage("moves"),
                        new Vector2(Constants.WIDTH - 130f, Constants.HEIGHT - 35f + (area == Area.CHALLENGE ? 12 : 0)),
                        0
                ),
                new NumberLabel(
                        context,
                        context.getImage("leveltitle"),
                        new Vector2(170f, Constants.HEIGHT - HEIGHT / 2f),
                        level + 1,
                        NumberFont.NumberSize.LARGE
                )
        };
    }

    public void setGoal(int goal) {
        labels[0].font.setNum(goal);
    }

    public void setBest(int best) {
        if (best == 0) labels[1].font.setNum(-1);
        else labels[1].font.setNum(best);
    }

    public void setMoves(int moves) {
        labels[2].font.setNum(moves);
    }

    public int getGoal() {
        return labels[0].font.num;
    }

    public int getBest() {
        return labels[1].font.num;
    }

    public int getMoves() {
        return labels[2].font.num;
    }

    public void incrementMoves() {
        labels[2].font.setNum(labels[2].font.num + 1);
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
        for (TextButton it : topButtons.values()) it.scale = 1f;
        for (ImageButton it : bottomButtons.values()) it.scale = 1f;
        switchButton.scale = 1f;
        bubbleDropButton.scale = 1f;
        audioButton.scale = 1f;
        if (Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            topCam.unproject(touchPoint);
            for (Map.Entry<ButtonListener.ButtonType, TextButton> it : topButtons.entrySet()) {
                ButtonListener.ButtonType key = it.getKey();
                TextButton value = it.getValue();
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
                audioButton.enabled = !context.audioHandler.toggleMute();
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

