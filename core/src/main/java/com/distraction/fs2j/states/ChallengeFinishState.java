package com.distraction.fs2j.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.distraction.fs2j.Constants;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.InfoBox;
import com.distraction.fs2j.NumberFont;
import com.distraction.fs2j.NumberLabel;
import com.distraction.fs2j.Utils;

public class ChallengeFinishState extends GameState {

    private TextureRegion pixel;

    private float alpha;
    private OrthographicCamera staticCam;

    private InfoBox infoBox;
    private InfoBox border;

    private NumberLabel title;
    private NumberLabel movesLabel;
    private NumberLabel bestLabel;

    public ChallengeFinishState(Context context, int level, int moves, int best) {
        super(context);

        pixel = context.getImage("pixel");

        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        infoBox = new InfoBox(
                context,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f,
                2f * Constants.WIDTH / 4f,
                4f * Constants.HEIGHT / 5f + 10
        );
        border = new InfoBox(
                context,
                Constants.WIDTH / 2f,
                Constants.HEIGHT / 2f,
                2f * Constants.WIDTH / 4f,
                0
        );

        camera.position.y = Constants.HEIGHT * 2;
        camera.update();

        title = new NumberLabel(
                context,
                context.getImage("leveltitle"),
                new Vector2(Constants.WIDTH / 2f, Constants.HEIGHT / 2f + infoBox.height / 2f - 20),
                level + 1,
                NumberFont.NumberSize.LARGE
        );
        bestLabel = new NumberLabel(
                context,
                context.getImage("best"),
                new Vector2(Constants.WIDTH / 2f + 40f, Constants.HEIGHT / 2f + infoBox.height / 2 - 64f),
                best
        );
        movesLabel = new NumberLabel(
                context,
                context.getImage("moves"),
                new Vector2(Constants.WIDTH / 2f - 60f, Constants.HEIGHT / 2f + infoBox.height / 2 - 64f),
                moves
        );

        context.gsm.depth++;
    }

    @Override
    public void update(float dt) {
        alpha += dt * 2f;
        if (alpha > 0.4f) alpha = 0.4f;

        Utils.lerp(camera.position, Constants.WIDTH / 2f, Constants.HEIGHT / 2f, 0f, 10f * dt);
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        {
            sb.setProjectionMatrix(staticCam.combined);
            sb.setColor(0, 0, 0, alpha);
            sb.draw(pixel, 0f, 0f, Constants.WIDTH, Constants.HEIGHT);

            sb.setProjectionMatrix(camera.combined);
            sb.setColor(1, 1, 1, 1);
            infoBox.render(sb);
            border.render(sb);
            title.render(sb);
            movesLabel.render(sb);
            bestLabel.render(sb);

            if (context.scoreHandler.authenticated) {
                System.out.println("auth");
                // todo create name submit
            } else {
                sb.setColor(0, 0, 0, 0.7f);
                sb.draw(pixel, Constants.WIDTH / 2f - infoBox.width / 2, Constants.HEIGHT / 2f - infoBox.height / 2, infoBox.width, infoBox.height / 2);
            }
        }
        sb.end();
    }
}
