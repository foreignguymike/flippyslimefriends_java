package com.distraction.fs2j.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.data.GameColor;

class CheckeredTransitionState extends TransitionState {

    private float s = Constants.WIDTH / 16;

    public CheckeredTransitionState(Context context, GameState nextState) {
        super(context, nextState);
    }

    public CheckeredTransitionState(Context context, GameState nextState, int numPop) {
        super(context, nextState, numPop);
    }

    {
        duration = 1.3f;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(GameColor.BLACK);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 16; col++) {
                    float size;
                    float ttime = time - ((9 - row + col) / 40f) * (duration / 2);
                    if (time < duration / 2) size = s * (ttime / (duration / 6));
                    else size = s - s * ((ttime - duration / 2) / (duration / 6));
                    size = MathUtils.clamp(size, 0, s);
                    sb.draw(pixel, s / 2 + s * col - size / 2, s / 2 + s * row - size / 2, size, size);
                }
            }
        }
        sb.end();
    }
}
