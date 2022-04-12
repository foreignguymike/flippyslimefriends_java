package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.Background;
import com.distraction.fs2j.BreathingImage;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.TextButton;
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

    private Background bg;

    private TextureRegion pixel;

    private TextButton backButton;

    private ImageButton skinText;
    private ImageButton faceText;
    private ImageButton accessoriesText;

    private AccessoryIcon faceIcon;
    private AccessoryIcon skinIcon;
    private AccessoryIcon[] accessoryIcons;

    private int selectedAccessoryIndex = -1;
    private BreathingImage selectedBorder;
    private ImageButton shiftLeft;
    private ImageButton shiftRight;

    private Skin skin;
    private Face face;
    private AccessoryType[] accessoryTypes;

    private TileMap tileMap;
    private Player player;
    private List<Player> players;

    private TextButton saveButton;

    private OrthographicCamera staticCam;

    private ImageButton right;
    private ImageButton down;
    private ImageButton left;
    private ImageButton up;

    private TextureRegion star;
    private NumberFont starFont;

    private TileMap.TileListener emptyTileListener = tileMap -> {
    };
    private Player.MoveListener emptyMoveListener = new Player.MoveListener() {
        @Override
        public void onMoved() {

        }

        @Override
        public void onIllegal() {

        }
    };

    protected CustomizeState(Context context) {
        super(context);

        tileMap = new TileMap(context, emptyTileListener, Area.TUTORIAL, 4);
        player = new Player(context, tileMap, emptyMoveListener, 0, 0, false);
        players = new ArrayList<>();
        players.add(player);

        bg = new Background(context, context.getImage("slimebg"), GameColor.PEACH, GameColor.WHITE);
        pixel = context.getImage("pixel");
        backButton = new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);
        skinText = new ImageButton(context.getImage("skin"), Constants.WIDTH / 6, 245);
        faceText = new ImageButton(context.getImage("face"), 2 * Constants.WIDTH / 6, 245);
        accessoriesText = new ImageButton(context.getImage("accessories"), Constants.WIDTH / 4 - 20, 165);

        skinIcon = new AccessoryIcon(context, null, skinText.pos.x, skinText.pos.y - 33);
        skinIcon.setOffset(0, 4);
        faceIcon = new AccessoryIcon(context, null, faceText.pos.x, faceText.pos.y - 33);
        faceIcon.setOffset(-1, 6);
        accessoryTypes = new AccessoryType[10];
        accessoryIcons = new AccessoryIcon[10];
        int r = 2;
        int c = 5;
        int p = 5;
        int w = 30;
        int tw = w * c + p * (c - 1);
        float s = Constants.WIDTH / 4 - tw / 2f + w / 2f;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                int i = row * c + col;
                accessoryIcons[i] = new AccessoryIcon(context, null,
                        s + col * (w + p),
                        accessoriesText.pos.y - 33 - row * (w + p)
                );
                if (i < context.playerDataHandler.accessories.size()) {
                    setAccessory(context.playerDataHandler.accessories.get(i), i);
                }
            }
        }
        accessoryIcons[1].setStars(10);
        accessoryIcons[2].setStars(20);
        accessoryIcons[3].setStars(30);
        accessoryIcons[4].setStars(50);
        accessoryIcons[5].setStars(70);
        accessoryIcons[6].setStars(100);
        accessoryIcons[7].setStars(130);
        accessoryIcons[8].setStars(160);
        accessoryIcons[9].setStars(200);

        selectedBorder = new BreathingImage(context.getImage("levelselectedborder"), -100, -100, 0, 1f, 0.03f);
        shiftLeft = new ImageButton(context.getImage("shiftleft"), Constants.WIDTH / 4 - 15, accessoryIcons[accessoryIcons.length - 1].pos.y - 35, 10);
        shiftRight = new ImageButton(context.getImage("shiftright"), Constants.WIDTH / 4 + 15, accessoryIcons[accessoryIcons.length - 1].pos.y - 35, 10);

        saveButton = new TextButton(
                context.getImage("save"),
                context.getImage("buttonbg"),
                Constants.WIDTH / 4f,
                30,
                5f
        );

        camera.position.set(-100f, player.isop.y, 0f);
        camera.update();

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        int ax = 420;
        int ay = 60;
        int dist = 22;
        left = new ImageButton(context.getImage("upleftarrow"), ax - dist, ay + dist, 5f);
        up = new ImageButton(context.getImage("uprightarrow"), ax + dist, ay + dist, 5f);
        right = new ImageButton(context.getImage("downrightarrow"), ax + dist, ay - dist, 5f);
        down = new ImageButton(context.getImage("downleftarrow"), ax - dist, ay - dist, 5f);

        setSkin(context.playerDataHandler.skin);
        setFace(context.playerDataHandler.face);

        star = context.getImage("starunlock");
        starFont = new NumberFont(context, false, NumberFont.NumberSize.LARGE);
        starFont.setNum(context.scoreHandler.getNumStars());
        accessoriesText.setPosition(Constants.WIDTH / 4 - (19 + star.getRegionWidth() + starFont.getTotalWidth()) / 2f, accessoriesText.pos.y);
    }

    private void openSkinSelect() {
        ignoreInput = true;
        context.gsm.push(new SkinSelectState(context, this));
        context.gsm.depth++;
    }

    private void openFaceSelect() {
        ignoreInput = true;
        context.gsm.push(new FaceSelectState(context, this));
        context.gsm.depth++;
    }

    private void openAccessorySelect(int index) {
        setSelectedIndex(index);
        ignoreInput = true;
        context.gsm.push(new AccessorySelectState(context, this, index, accessoryTypes[index], accessoryTypes));
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
            accessoryIcons[index].setOffset(accessoryType.xoffset, accessoryType.yoffset);
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
    }

    private void save() {
        context.playerDataHandler.save(skin);
        context.playerDataHandler.save(face);
        List<AccessoryType> list = new ArrayList<>(Arrays.asList(accessoryTypes));
        list.removeAll(Collections.singleton(null));
        context.playerDataHandler.save(list);
        goBack();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.moveTile(0, 1);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.moveTile(0, -1);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.moveTile(-1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.moveTile(1, 0);

        if (Gdx.input.isTouched()) {
            unprojectTouch(staticCam);
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
            if (backButton.containsPoint(touchPoint)) goBack();
        } else {
            left.scale = 1f;
            right.scale = 1f;
            up.scale = 1f;
            down.scale = 1f;
            shiftLeft.scale = 1f;
            shiftRight.scale = 1f;
        }

        if (Gdx.input.justTouched()) {
            if (skinIcon.containsPoint(touchPoint)) openSkinSelect();
            if (faceIcon.containsPoint(touchPoint)) openFaceSelect();
            for (int i = 0; i < accessoryIcons.length; i++) {
                if (accessoryIcons[i].containsPoint(touchPoint) && !accessoryIcons[i].locked)
                    openAccessorySelect(i);
            }
            if (shiftLeft.scale == 0.75f) shiftAccessory(-1);
            if (shiftRight.scale == 0.75f) shiftAccessory(1);

            if (saveButton.containsPoint(touchPoint)) save();
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        bg.update(dt);
        player.update(dt);
        tileMap.update(dt);
        camera.position.set(Utils.lerp(camera.position, player.isop.x - 120, player.isop.y, 0f, 4f * dt));
        camera.update();
        selectedBorder.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1, 1, 1, 1);
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);
            bg.render(sb);

            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(camera.combined);
            tileMap.render(sb);
            tileMap.renderTop(sb, players);

            sb.setProjectionMatrix(staticCam.combined);
            sb.setColor(GameColor.BLACK);
            Utils.setAlpha(sb, 0.4f);
            sb.draw(pixel, 0, 0, Constants.WIDTH / 2, Constants.HEIGHT);
            Utils.setAlpha(sb, 1f);
            sb.setColor(GameColor.BLACK);
            sb.draw(pixel, Constants.WIDTH / 2, 0, 1, Constants.HEIGHT);

            sb.setColor(1, 1, 1, 1);
            Utils.drawCentered(sb, star, accessoriesText.pos.x + 80, accessoriesText.pos.y);
            starFont.render(sb, accessoriesText.pos.x + 95, accessoriesText.pos.y);

            backButton.render(sb);
            skinText.render(sb);
            faceText.render(sb);
            accessoriesText.render(sb);

            saveButton.render(sb);

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
