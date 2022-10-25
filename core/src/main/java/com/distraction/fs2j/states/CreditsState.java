package com.distraction.fs2j.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.GameBackground;
import com.distraction.fs2j.IconButton;
import com.distraction.fs2j.TextFont;
import com.distraction.fs2j.tilemap.data.Area;

import java.util.ArrayList;
import java.util.List;

public class CreditsState extends GameState {

    private final GameBackground bg;

    private final IconButton backButton;

    private final List<TextFont> texts;

    public CreditsState(Context context) {
        super(context);

        bg = new GameBackground(context, Area.MEADOW);

        backButton = new IconButton(context.getImage("backicon"), context.getImage("iconbuttonbg"), 25f, Constants.HEIGHT - 25, 5f);

        texts = new ArrayList<>();
        texts.add(new TextFont(context, TextFont.FontType.NORMAL2, "programming", false, 30, Constants.HEIGHT - 70));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL2, "artwork", false, 30, Constants.HEIGHT - 85));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL2, "music", false, 30, Constants.HEIGHT - 100));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL, "mike s", false, 150, Constants.HEIGHT - 70));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL2, "powered by", false, 30, Constants.HEIGHT - 140));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL, "libgdx", false, 150, Constants.HEIGHT - 140));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL2, "daw", false, 30, Constants.HEIGHT - 180));
        texts.add(new TextFont(context, TextFont.FontType.NORMAL, "lmms", false, 150, Constants.HEIGHT - 180));
    }

    private void back() {
        ignoreInput = true;
        context.gsm.push(new TransitionState(context, new TitleState(context)));
        context.audioHandler.playSound("select", 0.3f);
        context.audioHandler.stopMusic();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            unprojectTouch();
            if (backButton.containsPoint(touchPoint)) back();
        }
    }

    @Override
    public void update(float dt) {
        if (!ignoreInput) handleInput();
        bg.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        bg.render(sb);
        backButton.render(sb);
        for (int i = 0; i < texts.size(); i++) {
            texts.get(i).render(sb);
        }
        sb.end();
    }
}
