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

    private static final float DT_THRESHOLD = 0.3f;

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
        float dt = Gdx.graphics.getDeltaTime();
        // fix dt for html game running background, just ignore
        if (dt > DT_THRESHOLD) return;
        gsm.update(dt);
        gsm.render(sb);
    }

    @Override
    public void dispose() {
        sb.dispose();
        context.assets.dispose();
    }
}