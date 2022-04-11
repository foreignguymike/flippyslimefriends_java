package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.Skin;

public class SkinSelectState extends GameState {

    private static final float MAX_ALPHA = 0.5f;

    private CustomizeState customizeState;

    private TextureRegion pixel;

    private float alpha = 0f;
    private InfoBox infoBox;

    private float cameraDest;
    private AccessoryIcon[] skinIcons;

    private Skin[] skins = Skin.values();

    protected SkinSelectState(Context context, CustomizeState customizeState) {
        super(context);
        this.customizeState = customizeState;

        pixel = context.getImage("pixel");

        infoBox = new InfoBox(context, Constants.WIDTH / 2, Constants.HEIGHT / 2, 200, 100);

        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
        cameraDest = Constants.HEIGHT / 2f;

        skinIcons = new AccessoryIcon[skins.length];
        int r = 2;
        int c = 5;
        int p = 5;
        int w = 30;
        int tw = w * c + p * (c - 1);
        int th = w * r + p * (r - 1);
        float s = Constants.WIDTH / 2 - tw / 2f + w / 2f;
        float sy = Constants.HEIGHT / 2 + th / 2f - w / 2f;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                if (i == skinIcons.length) break;
                skinIcons[i] = new AccessoryIcon(context, skins[i].getSprites(context)[0],
                        s + col * (w + p),
                        sy - row * (w + p)
                );
                skinIcons[i].setOffset(0, 4);
            }
        }
    }

    private void goBack() {
        ignoreInput = true;
        cameraDest = Constants.HEIGHT * 2f;
    }

    private void select(Skin skin) {
        customizeState.setSkin(skin);
        goBack();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            for (int i = 0; i < skinIcons.length; i++) {
                if (skinIcons[i].containsPoint(touchPoint)) {
                    select(skins[i]);
                }
            }
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

            for (AccessoryIcon it : skinIcons) it.render(sb);
        }
        sb.end();
    }
}
