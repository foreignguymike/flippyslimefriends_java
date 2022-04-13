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
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.player.AccessoryType;

class AccessorySelectState extends GameState {

    private static final float MAX_ALPHA = 0.5f;

    private CustomizeState customizeState;
    private int accessoryIndex;
    private AccessoryType replacing;
    private AccessoryType[] alreadySelected;

    private TextureRegion pixel;
    private BreathingImage selectedBorder;

    private float alpha = 0f;
    private InfoBox infoBox;

    private float cameraDest;
    private AccessoryIcon[] accessoryIcons;
    private AccessoryType[] accessoryTypes;

    private TextButton xbutton;

    private ImageButton diamond;
    private NumberFont diamondFont;

    protected AccessorySelectState(Context context, CustomizeState customizeState, int accessoryIndex, AccessoryType replacing, AccessoryType[] alreadySelected) {
        super(context);
        this.customizeState = customizeState;
        this.accessoryIndex = accessoryIndex;
        this.replacing = replacing;
        this.alreadySelected = alreadySelected;
        this.accessoryTypes = AccessoryType.values();
        selectedBorder = new BreathingImage(context.getImage("levelselectedborder"), -100, -100, 0, 1f, 0.03f);

        pixel = context.getImage("pixel");

        camera.position.y = 2f * Constants.HEIGHT;
        camera.update();
        cameraDest = Constants.HEIGHT / 2f;

        accessoryIcons = new AccessoryIcon[accessoryTypes.length];

        int r = (int) (9 * 0.363241579);
        int c = (int) Math.ceil(16 * 0.363241579);
        while (r * c < accessoryIcons.length) c++;
        while ((r - 1) * c > accessoryIcons.length) r--;
        int p = 5;
        int w = 30;
        int tw = w * c + p * (c - 1);
        int th = w * r + p * (r - 1);
        float s = Constants.WIDTH / 2 - tw / 2f + w / 2f;
        float sy = Constants.HEIGHT / 2 + th / 2f - w / 2f + 8;
        for (int row = 0; row < r; row++) {
            if (row * c >= accessoryIcons.length) break;
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                if (i == accessoryIcons.length) break;
                float x = s + col * (w + p);
                float y = sy - row * (w + p);
                accessoryIcons[i] = new AccessoryIcon(context, accessoryTypes[i], x, y);
                accessoryIcons[i].setOffset(accessoryTypes[i].xoffset, accessoryTypes[i].yoffset);
                if (accessoryTypes[i] != replacing && Utils.contains(alreadySelected, accessoryTypes[i])) {
                    accessoryIcons[i].disabled = true;
                }
                if (accessoryTypes[i] == replacing) {
                    selectedBorder.setPosition(x, y);
                }
            }
        }

        infoBox = new InfoBox(context, Constants.WIDTH / 2, Constants.HEIGHT / 2, tw + 40, th + 90);
        xbutton = new TextButton(context.getImage("xicon"), context.getImage("iconbuttonbg"), Constants.WIDTH / 2, infoBox.pos.y - infoBox.height / 2 + 30);
        diamond = new ImageButton(context.getImage("diamondunlock"));
        diamondFont = new NumberFont(context, false, NumberFont.NumberSize.LARGE);
        diamondFont.setNum(context.scoreHandler.getNumDiamonds());
        diamond.setPosition((Constants.WIDTH - diamondFont.getTotalWidth()) / 2f - 3, infoBox.pos.y + infoBox.height / 2 - 20);
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
                if (accessoryIcons[i].containsPoint(touchPoint) && !accessoryIcons[i].disabled && !accessoryIcons[i].locked) {
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

            for (AccessoryIcon it : accessoryIcons) it.render(sb);
            sb.setColor(1, 1, 1, 1);
            xbutton.render(sb);
            selectedBorder.render(sb);

            diamond.render(sb);
            diamondFont.render(sb, diamond.pos.x + diamond.width / 2 + 6, diamond.pos.y);
        }
        sb.end();
    }

}
