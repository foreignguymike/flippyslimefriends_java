package com.distraction.fs2j;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.gj.GameJoltClient;
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

    public FlippySlime2J() {
    }

    @Override
    public void create() {
        context = new Context();
        gsm = context.gsm;
        sb = new SpriteBatch();
        gsm.push(new TitleState(context));

        GameJoltClient client = new GameJoltClient();
        client.setGjScoreTableMapper(id -> {
            if (id.equals("BETA_1")) return Constants.BETA_1_ID;
            return -1;
        });
        client.initialize(Constants.APP_ID, Constants.API_KEY);
        client.setGuestName(context.playerDataHandler.name);
        context.client = client;
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