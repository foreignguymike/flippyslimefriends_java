package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.Skin;

public class SkinSelectState extends GameState {

    private static final float MAX_ALPHA = 0.5f;

    private final CustomizeState customizeState;

    private final TextureRegion pixel;
    private final BreathingImage selectedBorder;

    private float alpha = 0f;
    private final InfoBox infoBox;

    private float cameraDest;
    private final AccessoryIcon[] skinIcons;

    private final Skin[] skins = Skin.values();

    private final ImageButton diamond;
    private final TextFont diamondFont;

    protected SkinSelectState(Context context, CustomizeState customizeState, Skin replacing) {
        super(context);
        this.customizeState = customizeState;
        selectedBorder = new BreathingImage(context.getImage("levelselectedborder"), -100, -100, 0, 1f, 0.03f);

        int numDiamonds = context.scoreHandler.getNumDiamonds();

        pixel = context.getImage("pixel");

        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
        cameraDest = Constants.HEIGHT / 2f;

        skinIcons = new AccessoryIcon[skins.length];
        int r = (int) (9 * 0.363241579);
        int c = (int) Math.ceil(16 * 0.363241579);
        while (r * c < skinIcons.length) c++;
        while ((r - 1) * c > skinIcons.length) r--;
        int p = 5;
        int w = 30;
        int tw = w * c + p * (c - 1);
        int th = w * r + p * (r - 1);
        float s = Constants.WIDTH / 2f - tw / 2f + w / 2f;
        float sy = Constants.HEIGHT / 2f + th / 2f - w / 2f - 10;
        for (int row = 0; row < r; row++) {
            if (row * c >= skinIcons.length) break;
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                if (i == skinIcons.length) break;
                float x = s + col * (w + p);
                float y = sy - row * (w + p);
                skinIcons[i] = new AccessoryIcon(context, skins[i],
                        x,
                        y,
                        -1,
                        numDiamonds
                );
                skinIcons[i].setOffset(0, 4);
                if (skins[i] == replacing) {
                    selectedBorder.setPosition(x, y);
                }
            }
        }

        infoBox = new InfoBox(context, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, tw + 40, th + 55);
        diamond = new ImageButton(context.getImage("diamondunlock"));
        diamondFont = new TextFont(context, TextFont.FontType.BIG, Integer.toString(numDiamonds), false, 0, 0);
        diamond.setPosition((Constants.WIDTH - diamondFont.getTotalWidth()) / 2f - 3, infoBox.pos.y + infoBox.height / 2 - 20);
        diamondFont.setPosition(diamond.pos.x + diamond.width / 2 + 6, diamond.pos.y - 8);
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
                if (skinIcons[i].containsPoint(touchPoint) && skinIcons[i].enabled && !skinIcons[i].locked) {
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
        selectedBorder.update(dt);
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
            selectedBorder.render(sb);
            diamond.render(sb);
            diamondFont.render(sb);
        }
        sb.end();
    }
}
