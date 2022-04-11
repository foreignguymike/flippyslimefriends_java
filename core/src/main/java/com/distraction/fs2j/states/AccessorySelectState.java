package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.AccessoryType;

class AccessorySelectState extends GameState {

    private static final float MAX_ALPHA = 0.5f;

    private CustomizeState customizeState;
    private int accessoryIndex;
    private AccessoryType[] alreadySelected;

    private TextureRegion pixel;

    private float alpha = 0f;
    private InfoBox infoBox;

    private float cameraDest;
    private AccessoryIcon[] accessoryIcons;
    private AccessoryType[] accessoryTypes;

    private TextButton xbutton;

    protected AccessorySelectState(Context context, CustomizeState customizeState, int accessoryIndex, AccessoryType[] alreadySelected) {
        super(context);
        this.customizeState = customizeState;
        this.accessoryIndex = accessoryIndex;
        this.alreadySelected = alreadySelected;
        this.accessoryTypes = AccessoryType.values();

        pixel = context.getImage("pixel");

        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
        cameraDest = Constants.HEIGHT / 2f;

        accessoryIcons = new AccessoryIcon[accessoryTypes.length];
        int r = 2;
        int c = 4;
        int p = 5;
        int w = 30;
        int tw = w * c + p * (c - 1);
        int th = w * r + p * (r - 1);
        float s = Constants.WIDTH / 2 - tw / 2f + w / 2f;
        float sy = Constants.HEIGHT / 2 + th / 2f - w / 2f;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                System.out.println("length: " + accessoryIcons.length);
                System.out.println("current i " + i);
                if (i == accessoryIcons.length) break;
                accessoryIcons[i] = new AccessoryIcon(context, accessoryTypes[i].getSprites(context)[0],
                        s + col * (w + p),
                        sy - row * (w + p) + 10
                );
                accessoryIcons[i].setOffset(accessoryTypes[i].xoffset, accessoryTypes[i].yoffset);
                if (Utils.contains(alreadySelected, accessoryTypes[i])) {
                    accessoryIcons[i].disabled = true;
                }
            }
        }
        xbutton = new TextButton(context.getImage("xicon"), context.getImage("iconbuttonbg"), Constants.WIDTH / 2, sy - th);
        infoBox = new InfoBox(context, Constants.WIDTH / 2, Constants.HEIGHT / 2, tw + 50, th + 100);
    }

    private void goBack() {
        ignoreInput = true;
        cameraDest = Constants.HEIGHT * 2f;
    }

    private void select(AccessoryType accessoryType) {
        customizeState.setAccessory(accessoryType, accessoryIndex);
        goBack();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            for (int i = 0; i < accessoryIcons.length; i++) {
                if (accessoryIcons[i].containsPoint(touchPoint) && !accessoryIcons[i].disabled) {
                    select(accessoryTypes[i]);
                }
            }
            if (xbutton.containsPoint(touchPoint)) select(null);
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        Utils.lerp(camera.position, Constants.WIDTH / 2f, cameraDest, 0f, 10f * dt);
        camera.update();
        if (ignoreInput && camera.position.y > Constants.HEIGHT * 1.8f) {
            customizeState.ignoreInput = false;
            context.gsm.depth--;
            context.gsm.pop();
        }

        if (ignoreInput) alpha -= 2 * dt;
        else alpha += 2 * dt;
        if (alpha < 0) alpha = 0;
        if (alpha > MAX_ALPHA) alpha = MAX_ALPHA;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setColor(0, 0, 0, alpha);
            sb.draw(pixel, 0, 0, Constants.WIDTH, Constants.HEIGHT);

            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(camera.combined);
            infoBox.render(sb);

            for (AccessoryIcon it : accessoryIcons) it.render(sb);
            sb.setColor(1, 1, 1, 1);
            xbutton.render(sb);
        }
        sb.end();
    }

}
