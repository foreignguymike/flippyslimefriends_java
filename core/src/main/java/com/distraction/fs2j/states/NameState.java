package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;

public class NameState extends GameState {

    private GameState previousState;

    private InfoBox infoBox;
    private TextFont nameFont;
    private String name;

    private float alpha;
    private OrthographicCamera staticCam;

    private ImageButton left;
    private ImageButton right;
    private float shiftTime;
    private float shiftInterval = 0.15f;

    private int selectionIndex = 0;
    private ImageButton[] selection;
    private int selectionPadding = 20;

    private TextButton deleteButton;
    private TextButton addButton;
    private TextButton saveButton;

    private float cameraDest = Constants.HEIGHT / 2f;

    public NameState(Context context, GameState previousState) {
        super(context);
        this.previousState = previousState;
        name = context.playerDataHandler.name;
        if (name == null) name = "";
        nameFont = new TextFont(context, name, true, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + 30);

        infoBox = new InfoBox(context, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, Constants.WIDTH / 2f, Constants.HEIGHT / 2f + 50);

        camera.position.y = Constants.HEIGHT * 2;
        camera.update();

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        left = new BreathingImage(context.getImage("shiftleft"), Constants.WIDTH / 2f - 15f, Constants.HEIGHT / 2f - 10, 5);
        right = new BreathingImage(context.getImage("shiftright"), Constants.WIDTH / 2f + 15f, Constants.HEIGHT / 2f - 10, 5);

        selection = new ImageButton[37];
        for (int i = 0; i < selection.length; i++) {
            selection[i] = new ImageButton(context.getImage("font1", i), Constants.WIDTH / 2f + i * selectionPadding, Constants.HEIGHT / 2f + 10);
        }
        updateSelectionPositions();

        deleteButton = new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), Constants.WIDTH / 2f - 60, Constants.HEIGHT / 2f - 50, 0);
        addButton = new TextButton(context.getImage("addicon"), context.getImage("iconbuttonbg"), Constants.WIDTH / 2f - 20, Constants.HEIGHT / 2f - 50, 0);
        saveButton = new TextButton(context.getImage("save"), context.getImage("buttonbgsmall"), Constants.WIDTH / 2f + 50, Constants.HEIGHT / 2f - 50, 0);
    }

    private void shiftSelection(int amount) {
        if (shiftTime < shiftInterval) return;
        int targetIndex = selectionIndex + amount;
        if (targetIndex < 0 || targetIndex >= selection.length) return;
        shiftTime = 0;
        selectionIndex = targetIndex;
        updateSelectionPositions();
    }

    private void updateSelectionPositions() {
        float sx = Constants.WIDTH / 2f - selectionIndex * selectionPadding;
        for (int i = 0; i < selection.length; i++) {
            selection[i].lerpTo(sx + i * selectionPadding, Constants.HEIGHT / 2f + 10, 10);
        }
    }

    private char getLetter() {
        if (selectionIndex < 10) return (char) (selectionIndex + '0');
        else if (selectionIndex < 36) return (char) (selectionIndex - 10 + 'A');
        else return ' ';
    }

    private void save() {
        ignoreInput = true;
        context.playerDataHandler.save(name);
        cameraDest = Constants.HEIGHT * 2f;
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            unprojectTouch();
            if (left.containsPoint(touchPoint)) shiftSelection(-1);
            else if (right.containsPoint(touchPoint)) shiftSelection(1);
        }
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (deleteButton.containsPoint(touchPoint)) {
                if (name.length() > 0) name = name.substring(0, name.length() - 1);
                nameFont.setText(name);
            } else if (addButton.containsPoint(touchPoint)) {
                if (name.length() < 10) name += getLetter();
                nameFont.setText(name);
            } else if (saveButton.containsPoint(touchPoint)) {
                save();
            }
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();

        shiftTime += dt;

        alpha += dt * 2;
        if (alpha > 0.4f) alpha = 0.4f;

        Utils.lerp(camera.position, Constants.WIDTH / 2f, cameraDest, 0f, 10f * dt);
        camera.update();
        if (ignoreInput && camera.position.y > Constants.HEIGHT * 1.8f) {
            previousState.ignoreInput = false;
            context.gsm.depth--;
            context.gsm.pop();
            return;
        }

        for (ImageButton it : selection) {
            it.alpha = 1f - Math.abs(it.pos.x - Constants.WIDTH / 2f) / 100f;
            it.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);
            sb.setColor(0, 0, 0, alpha);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, Constants.HEIGHT);

            sb.setProjectionMatrix(camera.combined);
            sb.setColor(1, 1, 1, 1);
            infoBox.render(sb);
            nameFont.render(sb);
            for (ImageButton it : selection) it.render(sb);
            left.render(sb);
            right.render(sb);
            deleteButton.render(sb);
            addButton.render(sb);
            saveButton.render(sb);

            sb.setColor(1, 1, 1, 1);
            sb.draw(pixel, Constants.WIDTH / 2f - 3, Constants.HEIGHT / 2f + 3, 6, 1);
        }
        sb.end();
    }
}
