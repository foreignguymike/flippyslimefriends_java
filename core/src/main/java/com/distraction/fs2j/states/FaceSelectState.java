package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.Face;

public class FaceSelectState extends GameState {

    private static final float MAX_ALPHA = 0.5f;

    private CustomizeState customizeState;

    private TextureRegion pixel;

    private float alpha = 0f;
    private InfoBox infoBox;

    private float cameraDest;
    private AccessoryIcon[] faceIcons;

    private Face[] faces = Face.values();

    private ImageButton diamond;
    private NumberFont diamondFont;

    protected FaceSelectState(Context context, CustomizeState customizeState, int numDiamonds) {
        super(context);
        this.customizeState = customizeState;

        pixel = context.getImage("pixel");

        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
        cameraDest = Constants.HEIGHT / 2f;

        faceIcons = new AccessoryIcon[faces.length];
        int r = (int) (9 * 0.363241579);
        int c = (int) Math.ceil(16 * 0.363241579);
        while (r * c < faceIcons.length) c++;
        while ((r - 1) * c > faceIcons.length) r--;
        int p = 5;
        int w = 30 * Constants.SCALE;
        int tw = w * c + p * (c - 1);
        int th = w * r + p * (r - 1);
        float s = Constants.WIDTH / 2 - tw / 2f + w / 2f;
        float sy = Constants.HEIGHT / 2 + th / 2f - w / 2f - 10 * Constants.SCALE;
        for (int row = 0; row < r; row++) {
            if (row * c >= faceIcons.length) break;
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                if (i == faceIcons.length) break;
                faceIcons[i] = new AccessoryIcon(context, faces[i],
                        s + col * (w + p),
                        sy - row * (w + p),
                        -1,
                        numDiamonds
                );
                faceIcons[i].setOffset(-1 * Constants.SCALE, 6 * Constants.SCALE);
            }
        }

        infoBox = new InfoBox(context, Constants.WIDTH / 2, Constants.HEIGHT / 2, tw + 40 * Constants.SCALE, th + 55 * Constants.SCALE);
        diamond = new ImageButton(context.getImage("diamondunlock"));
        diamondFont = new NumberFont(context, false, NumberFont.NumberSize.LARGE);
        diamondFont.setNum(numDiamonds);
        diamond.setPosition((Constants.WIDTH - diamondFont.getTotalWidth()) / 2f - 3 * Constants.SCALE, infoBox.pos.y + infoBox.height / 2 - 20 * Constants.SCALE);
    }

    private void goBack() {
        ignoreInput = true;
        cameraDest = Constants.HEIGHT * 2f;
    }

    private void select(Face face) {
        customizeState.setFace(face);
        goBack();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            for (int i = 0; i < faceIcons.length; i++) {
                if (faceIcons[i].containsPoint(touchPoint) && !faceIcons[i].disabled && !faceIcons[i].locked) {
                    select(faces[i]);
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

            for (AccessoryIcon it : faceIcons) it.render(sb);
            diamond.render(sb);
            diamondFont.render(sb, diamond.pos.x + diamond.width / 2 + 6 * Constants.SCALE, diamond.pos.y);
        }
        sb.end();
    }
}
