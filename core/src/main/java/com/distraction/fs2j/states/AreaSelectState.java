package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;

import java.util.ArrayList;
import java.util.List;

public class AreaSelectState extends GameState {

    private final float width = Constants.WIDTH / 5f;
    private final IconButton backButton;
    private final IconButton audioButton;
    private final List<ImageButton> areaButtons;
    private final BreathingImage rightArrow;
    private final BreathingImage leftArrow;
    private final Color color;
    private int currentIndex;

    public AreaSelectState(Context context) {
        this(context, 0);
    }

    public AreaSelectState(Context context, int currentIndex) {
        super(context);
        this.currentIndex = currentIndex;
        backButton = new IconButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25f, 5f);
        audioButton = new IconButton(context.getImage("audioicon"), context.getImage("iconbuttonbg"), 65f, Constants.HEIGHT - 25f, 5f);
        audioButton.enabled = !context.audioHandler.isMuted();

        areaButtons = new ArrayList<>();
        List<Area> areaList = Area.getNormalAreas();
        for (int i = 0; i < areaList.size(); i++) {
            Area area = areaList.get(i);
            ImageButton button = new ImageButton(context.getImage(area.text));
            button.setPosition(Constants.WIDTH / 2f + i * width, Constants.HEIGHT / 2f);
            areaButtons.add(button);
        }

        rightArrow = new BreathingImage(context.getImage("areaselectarrow"), Constants.WIDTH - 50, Constants.HEIGHT / 2f, 20);
        leftArrow = new BreathingImage(context.getImage("areaselectarrow"), 50, Constants.HEIGHT / 2f, 20);
        leftArrow.flipped = true;

        color = Area.values()[currentIndex].colorCopy();

        moveAreaButtons(false);
    }

    private void goToLevelSelect() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new LevelSelectState(context, Area.values()[currentIndex])));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new TitleState(context)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void moveAreaButtons() {
        moveAreaButtons(true);
    }

    private void moveAreaButtons(boolean lerp) {
        for (int i = 0; i < areaButtons.size(); i++) {
            if (lerp) {
                areaButtons.get(i).lerpTo(Constants.WIDTH / 2f + i * width - currentIndex * width, Constants.HEIGHT / 2f);
            } else {
                areaButtons.get(i).setPosition(Constants.WIDTH / 2f + i * width - currentIndex * width, Constants.HEIGHT / 2f);
            }
        }
    }

    private void moveLeft() {
        if (currentIndex > 0) {
            currentIndex--;
            moveAreaButtons();
        }
    }

    private void moveRight() {
        if (currentIndex < areaButtons.size() - 1) {
            currentIndex++;
            moveAreaButtons();
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) moveRight();
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) moveLeft();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) goToLevelSelect();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (leftArrow.containsPoint(touchPoint)) moveLeft();
            else if (rightArrow.containsPoint(touchPoint)) moveRight();
            else if (areaButtons.get(currentIndex).containsPoint(touchPoint)) goToLevelSelect();
            if (backButton.containsPoint(touchPoint)) goBack();
            if (audioButton.containsPoint(touchPoint)) {
                audioButton.enabled = !context.audioHandler.toggleMute();
            }
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) {
            handleInput();
        }
        for (ImageButton it : areaButtons) {
            it.scale = 1f / (1f + Math.abs(Constants.WIDTH / 2f - it.pos.x) / 100f);
            it.alpha = Math.max(0f, (1f - Math.abs(Constants.WIDTH / 2f - it.pos.x) / 300f));
            it.update(dt);
        }
        leftArrow.update(dt);
        rightArrow.update(dt);
        Color c = Area.values()[currentIndex].color;
        color.lerp(c.r, c.g, c.b, c.a, 4 * dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        Utils.clearScreen(color);
        sb.begin();
        {
            sb.setProjectionMatrix(camera.combined);

            sb.setColor(GameColor.MIDNIGHT_BLUE);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, 60f);
            sb.draw(pixel, 0f, Constants.HEIGHT - 60f, Constants.WIDTH, 60f);
            sb.setColor(1, 1, 1, 1);
            sb.draw(pixel, 0f, 56f, Constants.WIDTH, 1f);
            sb.draw(pixel, 0f, Constants.HEIGHT - 58f, Constants.WIDTH, 1f);

            backButton.render(sb);
            audioButton.render(sb);

            for (int i = 0; i < currentIndex; i++) {
                areaButtons.get(i).render(sb);
            }
            for (int i = areaButtons.size() - 1; i >= currentIndex; i--) {
                areaButtons.get(i).render(sb);
            }
            leftArrow.render(sb);
            rightArrow.render(sb);
        }
        sb.end();
    }
}
