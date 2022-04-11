package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;

import java.util.ArrayList;
import java.util.List;

public class AreaSelectState extends GameState {

    private float width = Constants.WIDTH / 5;
    private TextButton backButton;
    private List<ImageButton> areaButtons;
    private BreathingImage rightArrow;
    private BreathingImage leftArrow;
    private Color color;
    private int currentIndex = 0;

    public AreaSelectState(Context context) {
        this(context, 0);
    }

    public AreaSelectState(Context context, int currentIndex) {
        super(context);
        this.currentIndex = currentIndex;
        backButton = new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25f,  5f);

        areaButtons = new ArrayList<>();
        for (int i = 0; i < Area.values().length; i++) {
            Area area = Area.values()[i];
            ImageButton button = new ImageButton(context.getImage(area.text));
            button.setPosition(Constants.WIDTH / 2 + i * width, Constants.HEIGHT / 2);
            areaButtons.add(button);
        }

        rightArrow = new BreathingImage(context.getImage("areaselectarrow"), Constants.WIDTH - 50, Constants.HEIGHT / 2, 20);
        leftArrow = new BreathingImage(context.getImage("areaselectarrow"), 50, Constants.HEIGHT / 2, 20);
        leftArrow.flipped = true;

        color = Area.values()[currentIndex].colorCopy();

        moveAreaButtons(false);
    }

    private void goToLevelSelect() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new LevelSelectState(context, Area.values()[currentIndex])));
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new TitleState(context)));
    }

    private void moveAreaButtons() {
        moveAreaButtons(true);
    }

    private void moveAreaButtons(boolean lerp) {
        for (int i = 0; i < areaButtons.size(); i++) {
            if (lerp) {
                areaButtons.get(i).lerpTo(Constants.WIDTH / 2 + i * width - currentIndex * width, Constants.HEIGHT / 2);
            } else {
                areaButtons.get(i).setPosition(Constants.WIDTH / 2 + i * width - currentIndex * width, Constants.HEIGHT / 2);
            }
        }
    }

    private void moveLeft() {
        if (currentIndex > 0) currentIndex--;
        moveAreaButtons();
    }

    private void moveRight() {
        if (currentIndex < areaButtons.size() - 1) currentIndex++;
        moveAreaButtons();
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
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) {
            handleInput();
        }
        for (ImageButton it : areaButtons) {
            it.scale = 1f / (1f + Math.abs(Constants.WIDTH / 2 - it.pos.x) / 100f);
            it.alpha = Math.max(0f, (1f - Math.abs(Constants.WIDTH / 2 - it.pos.x) / 300f));
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
