package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.Placement;
import com.distraction.fs2j.TextButton;
import com.distraction.fs2j.Utils;
import com.distraction.fs2j.tilemap.data.GameColor;

public class ChallengeState extends GameState {

    private TextButton backButton;

    private OrthographicCamera staticCam;

    private Placement[] placements;

    public ChallengeState(Context context) {
        super(context);

        backButton = new TextButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        placements = new Placement[7];
        for (int i = 0; i < placements.length; i++) placements[i] = new Placement(context, i + 1);
        for (Placement it : placements) System.out.println(it.rank + ", " + it.x + ", " + it.y);
    }

    private void goBack() {
        ignoreInput = true;
        context.gsm.push(new CheckeredTransitionState(context, new TitleState(context)));
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch(staticCam);
            if (backButton.containsPoint(touchPoint)) goBack();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) goBack();
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();

        for (Placement it : placements) it.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        Utils.clearScreen(GameColor.DARK_GRAY);
        sb.begin();
        {
            sb.setColor(1, 1, 1, 1);
            sb.setProjectionMatrix(staticCam.combined);
            backButton.render(sb);

            for (Placement it : placements) it.render(sb);
        }
        sb.end();
    }
}
