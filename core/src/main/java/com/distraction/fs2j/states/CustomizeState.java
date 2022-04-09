package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.AccessoryIcon;
import com.distraction.fs2j.Background;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.ImageButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.TileMap;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameColor;
import com.distraction.fs2j.tilemap.player.Player;

public class CustomizeState extends GameState {

    private Background bg;

    private TextureRegion pixel;
    private TextureRegion face;
    private TextureRegion skin;
    private TextureRegion accessories;

    private AccessoryIcon faceIcon;
    private AccessoryIcon skinIcon;
    private AccessoryIcon[] accessoryIcons;

    private TileMap tileMap;
    private Player player;

    private OrthographicCamera staticCam;

    private ImageButton right;
    private ImageButton down;
    private ImageButton left;
    private ImageButton up;

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

        bg = new Background(context, context.getImage("slimebg"), GameColor.PEACH, GameColor.WHITE);
        pixel = context.getImage("pixel");
        face = context.getImage("face");
        skin = context.getImage("skin");
        accessories = context.getImage("accessories");

        faceIcon = new AccessoryIcon(context, null, 58, 186);
        skinIcon = new AccessoryIcon(context, null, 156, 186);
        accessoryIcons = new AccessoryIcon[10];
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 5; col++) {
                accessoryIcons[row * 5 + col] = new AccessoryIcon(context, null, 24 + col * 40, 86 - row * 40);
            }
        }

        tileMap = new TileMap(context, emptyTileListener, Area.TUTORIAL, 4);
        player = new Player(context, tileMap, emptyMoveListener, 0, 0, false);

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
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new TitleState(context)));
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
        } else {
            left.scale = 1f;
            right.scale = 1f;
            up.scale = 1f;
            down.scale = 1f;
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        bg.update(dt);
        player.update(dt);
        camera.position.set(Utils.lerp(camera.position, player.isop.x - 120, player.isop.y, 0f, 4f * dt));
        camera.update();
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
            player.render(sb);

            sb.setProjectionMatrix(staticCam.combined);
            sb.setColor(GameColor.BLACK);
            Utils.setAlpha(sb, 0.4f);
            sb.draw(pixel, 0, 0, Constants.WIDTH / 2, Constants.HEIGHT);
            Utils.setAlpha(sb, 1f);
            sb.setColor(GameColor.BLACK);
            sb.draw(pixel, Constants.WIDTH / 2, 0, 1, Constants.HEIGHT);

            sb.setColor(1, 1, 1, 1);
            sb.draw(face, 50, 230);
            sb.draw(skin, 150, 230);
            sb.draw(accessories, 55, 130);

            faceIcon.render(sb);
            skinIcon.render(sb);
            for (AccessoryIcon it : accessoryIcons) it.render(sb);

            left.render(sb);
            up.render(sb);
            right.render(sb);
            down.render(sb);
        }
        sb.end();
    }
}
