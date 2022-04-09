package com.distraction.fs2j.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;

public class TransitionState extends GameState {

    private GameState nextState;
    private int numPop;

    protected float duration = 0.5f;
    protected float time = 0f;
    protected boolean next = false;

    public TransitionState(Context context, GameState nextState) {
        this(context, nextState, 1);
    }

    public TransitionState(Context context, GameState nextState, int numPop) {
        super(context);
        this.nextState = nextState;
        this.numPop = numPop;

        context.gsm.depth++;
    }

    @Override
    public void update(float dt) {
        time += dt;
        if (!next && time > duration / 2) {
            next = true;
            nextState.ignoreInput = true;
            for (int i = 0; i < numPop; i++) context.gsm.pop();
            context.gsm.depth -= numPop - 1;
            context.gsm.replace(nextState);
            context.gsm.push(this);
        }
        if (time > duration) {
            context.gsm.depth--;
            context.gsm.pop();
            nextState.ignoreInput = false;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        float interp = time / duration;
        float perc = interp < 0.5f ? interp * 2 : 1f - (time - duration / 2) / duration * 2;
        Color c = sb.getColor();
        sb.setColor(Color.BLACK);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        {
            sb.draw(pixel, 0, Constants.HEIGHT, Constants.WIDTH, -perc * Constants.HEIGHT / 2);
            sb.draw(pixel, 0, 0, Constants.WIDTH, perc * Constants.HEIGHT / 2);
        }
        sb.end();
        sb.setColor(c);
    }
}
