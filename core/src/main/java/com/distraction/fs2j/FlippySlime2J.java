package com.distraction.fs2j;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.states.GSM;
import com.distraction.fs2j.states.TitleState;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class FlippySlime2J extends ApplicationAdapter {

    private Context context;
    private SpriteBatch sb;
    private GSM gsm;

    @Override
    public void create() {
        context = new Context();
        gsm = context.gsm;
        sb = new SpriteBatch();
        gsm.push(new TitleState(context));
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F8)) Utils.takeScreenshot();
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(sb);
    }

    @Override
    public void dispose() {
        sb.dispose();
        context.assets.dispose();
    }
}