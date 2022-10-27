package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.AudioButton;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.GameBackground;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.MyViewport;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.AccessoryType;
import com.distraction.fs2j.tilemap.player.Face;
import com.distraction.fs2j.tilemap.player.Player;
import com.distraction.fs2j.tilemap.player.Skin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomizeState extends GameState {

    private final boolean preview;

    private final GameBackground bg;

    private final TextureRegion pixel;

    private final IconButton backButton;
    private final AudioButton audioButton;

    private final TextFont skinText;
    private final TextFont faceText;
    private final TextFont accessoriesText;

    private final int numDiamonds;

    private final AccessoryIcon faceIcon;
    private final AccessoryIcon skinIcon;
    private final AccessoryIcon[] accessoryIcons;

    private int selectedAccessoryIndex = -1;
    private final BreathingImage selectedBorder;
    private final ImageButton shiftLeft;
    private final ImageButton shiftRight;

    private Skin skin;
    private Face face;
    private final AccessoryType[] accessoryTypes;

    private final TileMap tileMap;
    private final Player player;
    private final List<Player> players;

    private final TextButton saveButton;

    private final Viewport staticViewport;

    private final ImageButton right;
    private final ImageButton down;
    private final ImageButton left;
    private final ImageButton up;

    private final TextureRegion star;
    private final TextFont starFont;

    protected CustomizeState(Context context, boolean preview) {
        super(context);

        this.preview = preview;

        int numStars;
        if (preview) {
            numStars = 1000;
            numDiamonds = 1000;
        } else {
            numStars = context.scoreHandler.getNumStars();
            numDiamonds = context.scoreHandler.getNumDiamonds();
        }

        tileMap = new TileMap(
                context,
                tileMap -> {
                },
                Area.MEADOW,
                4
        );
        player = new Player(
                context,
                tileMap,
                new Player.MoveListener() {
                    @Override
                    public void onMoved(boolean on) {
                        context.audioHandler.playSound(on ? "activate" : "deactivate");
                    }

                    @Override
                    public void onIllegal() {

                    }
                },
                0,
                0,
                false
        );
        players = new ArrayList<>();
        players.add(player);

        bg = new GameBackground(context, context.getImage("slimebg"), GameColor.PEACH, GameColor.WHITE);
        pixel = context.getImage("pixel");
        backButton = new IconButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);
        audioButton = new AudioButton(context, context.audioHandler.getAudioState(), 65f, Constants.HEIGHT - 25f, 5f);
        skinText = new TextFont(context, TextFont.FontType.BIG, "skin", true, 4f * Constants.WIDTH / 6, 237);
        faceText = new TextFont(context, TextFont.FontType.BIG, "face", true, 5f * Constants.WIDTH / 6, 237);
        accessoriesText = new TextFont(context, TextFont.FontType.BIG, "accessories", true, 3f * Constants.WIDTH / 4 - 20, 157);

        skinIcon = new AccessoryIcon(context, null, skinText.x, skinText.y - 33, numStars, numDiamonds);
        skinIcon.setOffset(0, 4);
        faceIcon = new AccessoryIcon(context, null, faceText.x, faceText.y - 33, numStars, numDiamonds);
        faceIcon.setOffset(-1, 6);
        accessoryTypes = new AccessoryType[10];
        accessoryIcons = new AccessoryIcon[10];
        int r = 2;
        int c = 5;
        int p = 5;
        int w = 30;
        int tw = w * c + p * (c - 1);
        float s = 3f * Constants.WIDTH / 4 - tw / 2f + w / 2f;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                accessoryIcons[i] = new AccessoryIcon(context, null,
                        s + col * (w + p),
                        accessoriesText.y - 33 - row * (w + p),
                        numStars,
                        numDiamonds
                );
                if (i < context.playerDataHandler.accessories.size()) {
                    setAccessory(context.playerDataHandler.accessories.get(i), i);
                }
            }
        }
        accessoryIcons[1].setRequiredStars(10);
        accessoryIcons[2].setRequiredStars(20);
        accessoryIcons[3].setRequiredStars(30);
        accessoryIcons[4].setRequiredStars(50);
        accessoryIcons[5].setRequiredStars(70);
        accessoryIcons[6].setRequiredStars(100);
        accessoryIcons[7].setRequiredStars(130);
        accessoryIcons[8].setRequiredStars(160);
        accessoryIcons[9].setRequiredStars(200);

        selectedBorder = new BreathingImage(context.getImage("levelselectedborder"), -100, -100, 0, 1f, 0.03f);
        shiftLeft = new ImageButton(context.getImage("shiftleft"), 3f * Constants.WIDTH / 4 - 15, accessoryIcons[accessoryIcons.length - 1].pos.y - 35, 5);
        shiftRight = new ImageButton(context.getImage("shiftright"), 3f * Constants.WIDTH / 4 + 15, accessoryIcons[accessoryIcons.length - 1].pos.y - 35, 5);

        saveButton = new TextButton(
                context,
                "save",
                context.getImage("buttonbg"),
                3 * Constants.WIDTH / 4f,
                25,
                5f
        );

        camera.position.set(-100f, player.isop.y, 0f);
        camera.update();

        staticViewport = new MyViewport(Constants.WIDTH, Constants.HEIGHT);

        int ax = 60;
        int ay = 60;
        int dist = 22;
        left = new ImageButton(context.getImage("upleftarrow"), ax - dist, ay + dist, 5f);
        up = new ImageButton(context.getImage("uprightarrow"), ax + dist, ay + dist, 5f);
        right = new ImageButton(context.getImage("downrightarrow"), ax + dist, ay - dist, 5f);
        down = new ImageButton(context.getImage("downleftarrow"), ax - dist, ay - dist, 5f);

        setSkin(context.playerDataHandler.skin);
        setFace(context.playerDataHandler.face);

        star = context.getImage("starunlock");
        starFont = new TextFont(context, TextFont.FontType.BIG, Integer.toString(numStars), false, accessoriesText.x + 85, accessoriesText.y);
        accessoriesText.setPosition(3f * Constants.WIDTH / 4 - (19 + star.getRegionWidth() + starFont.getTotalWidth()) / 2f, accessoriesText.y);
    }

    private void openSkinSelect() {
        ignoreInput = true;
        context.gsm.push(new SkinSelectState(context, this, skin, numDiamonds));
        context.gsm.depth++;
    }

    private void openFaceSelect() {
        ignoreInput = true;
        context.gsm.push(new FaceSelectState(context, this, face, numDiamonds));
        context.gsm.depth++;
    }

    private void openAccessorySelect(int index) {
        setSelectedIndex(index);
        ignoreInput = true;
        context.gsm.push(new AccessorySelectState(context, this, index, accessoryTypes[index], accessoryTypes, numDiamonds));
        context.gsm.depth++;
    }

    private void setSelectedIndex(int index) {
        selectedAccessoryIndex = index;
        if (index == -1) selectedBorder.setPosition(-100f, -100f);
        else selectedBorder.setPosition(accessoryIcons[index].pos.x, accessoryIcons[index].pos.y);
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
        skinIcon.setType(skin);
        player.playerRenderer.setSkin(skin);
    }

    public void setFace(Face face) {
        this.face = face;
        faceIcon.setType(face);
        player.playerRenderer.setFace(face);
    }

    public void setAccessory(AccessoryType accessoryType, int index) {
        accessoryTypes[index] = accessoryType;
        accessoryIcons[index].setType(accessoryType);
        if (accessoryType != null) {
            accessoryIcons[index].setOffset(accessoryType.iconx, accessoryType.icony);
        } else {
            setSelectedIndex(-1);
        }
        player.playerRenderer.setAccessories(new ArrayList<>(Arrays.asList(accessoryTypes)));
    }

    private void shiftAccessory(int amount) {
        if (selectedAccessoryIndex == -1) return;
        int targetIndex = selectedAccessoryIndex + amount;
        if (targetIndex < 0 || targetIndex >= accessoryIcons.length) return;
        if (accessoryIcons[targetIndex].locked) return;

        AccessoryType temp = accessoryTypes[selectedAccessoryIndex];
        setAccessory(accessoryTypes[targetIndex], selectedAccessoryIndex);
        setAccessory(temp, targetIndex);
        setSelectedIndex(targetIndex);
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new TitleState(context)));
        context.audioHandler.playSound("select", 0.3f);
    }

    private void save() {
        if (!preview) {
            context.playerDataHandler.save(skin);
            context.playerDataHandler.save(face);
            List<AccessoryType> list = new ArrayList<>(Arrays.asList(accessoryTypes));
            list.removeAll(Collections.singleton(null));
            context.playerDataHandler.save(list);
            context.audioHandler.playSound("complete", 0.3f);
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.moveTile(0, 1);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.moveTile(0, -1);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.moveTile(-1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.moveTile(1, 0);

        if (Gdx.input.isTouched()) {
            unprojectTouch(staticViewport);
            if (left.containsPoint(touchPoint)) {
                player.moveTile(0, -1);
                left.scale = 0.75f;
            } else left.scale = 1f;
            if (up.containsPoint(touchPoint)) {
                player.moveTile(-1, 0);
                up.scale = 0.75f;
            } else up.scale = 1f;
            if (down.containsPoint(touchPoint)) {
                player.moveTile(1, 0);
                down.scale = 0.75f;
            } else down.scale = 1f;
            if (right.containsPoint(touchPoint)) {
                player.moveTile(0, 1);
                right.scale = 0.75f;
            } else right.scale = 1f;
            if (shiftLeft.containsPoint(touchPoint)) shiftLeft.scale = 0.75f;
            else shiftLeft.scale = 1f;
            if (shiftRight.containsPoint(touchPoint)) shiftRight.scale = 0.75f;
            else shiftRight.scale = 1f;
            if (audioButton.containsPoint(touchPoint)) audioButton.scale = 0.75f;
            else audioButton.scale = 1f;
            if (saveButton.containsPoint(touchPoint)) saveButton.enabled = false;
        } else {
            left.scale = 1f;
            right.scale = 1f;
            up.scale = 1f;
            down.scale = 1f;
            shiftLeft.scale = 1f;
            shiftRight.scale = 1f;
            audioButton.scale = 1f;
            saveButton.enabled = true;
        }

        if (Gdx.input.justTouched()) {
            if (skinIcon.containsPoint(touchPoint)) openSkinSelect();
            if (faceIcon.containsPoint(touchPoint)) openFaceSelect();
            for (int i = 0; i < accessoryIcons.length; i++) {
                if (accessoryIcons[i].containsPoint(touchPoint) && !accessoryIcons[i].locked)
                    openAccessorySelect(i);
            }
            if (shiftLeft.scale < 1) shiftAccessory(-1);
            if (shiftRight.scale < 1) shiftAccessory(1);
            if (audioButton.scale < 1)
                audioButton.setState(context.audioHandler.nextAudioState());
            if (backButton.containsPoint(touchPoint)) goBack();
            if (saveButton.containsPoint(touchPoint)) save();
        }
    }

    @Override
    public void resize(int w, int h) {
        super.resize(w, h);
        staticViewport.update(w, h);
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        bg.update(dt);
        player.update(dt);
        tileMap.update(dt);
        camera.position.set(Utils.lerp(camera.position, player.isop.x + 120, player.isop.y, 0f, 4f * dt));
        camera.update();
        selectedBorder.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(staticViewport.getCamera().combined);
            bg.render(sb);

            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(camera.combined);
            tileMap.render(sb);
            tileMap.renderTop(sb, players);

            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(staticViewport.getCamera().combined);
            sb.setColor(GameColor.BLACK);
            Utils.setAlpha(sb, 0.4f);
            sb.draw(pixel, Constants.WIDTH / 2f, 0, Constants.WIDTH / 2f, Constants.HEIGHT);
            Utils.setAlpha(sb, 1f);
            sb.setColor(GameColor.BLACK);
            sb.draw(pixel, Constants.WIDTH / 2f, 0, 1, Constants.HEIGHT);

            sb.setColor(1, 1, 1, 1);
            Utils.drawCentered(sb, star, accessoriesText.x + 80, accessoriesText.y);
            starFont.render(sb);

            backButton.render(sb);
            audioButton.render(sb);
            skinText.render(sb);
            faceText.render(sb);
            accessoriesText.render(sb);

            if (!preview) saveButton.render(sb);

            skinIcon.render(sb);
            faceIcon.render(sb);
            for (AccessoryIcon it : accessoryIcons) it.render(sb);
            selectedBorder.render(sb);
            shiftLeft.render(sb);
            shiftRight.render(sb);

            left.render(sb);
            up.render(sb);
            right.render(sb);
            down.render(sb);
        }
        sb.end();
    }
}
