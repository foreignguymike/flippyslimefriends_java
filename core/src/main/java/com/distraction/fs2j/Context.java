package com.distraction.fs2j;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.states.GSM;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameData;
import com.distraction.fs2j.tilemap.data.MapData;

import java.util.List;
import java.util.Map;

public class Context {

    public AssetManager assets;
    public GSM gsm;
    public GameData gameData;
    public ScoreHandler scoreHandler;

    public Context() {
        assets = new AssetManager();
        assets.load("fs2.atlas", TextureAtlas.class);
        assets.finishLoading();

        gsm = new GSM();

        gameData = new GameData(this);
        // sanity check
        for (Map.Entry<Area, List<MapData>> entry : gameData.mapData.entrySet()) {
            Area area = entry.getKey();
            List<MapData> levels = entry.getValue();
            for (int index = 0; index < levels.size(); index++) {
                if (levels.get(index).goal == 0) {
                    throw new IllegalStateException("level " + area + "-" + (index + 1) + "has no goal");
                }
            }
        }

        scoreHandler = new ScoreHandler(this);
        scoreHandler.load();
    }

    public TextureRegion getImage(String key) {
        TextureRegion region = assets.get("fs2.atlas", TextureAtlas.class).findRegion(key);
        if (region == null) throw new IllegalStateException("image " + key + " not found");
        return region;
    }

    public TextureRegion getImage(String key, int index) {
        TextureRegion region = assets.get("fs2.atlas", TextureAtlas.class).findRegion(key, index);
        if (region == null)
            throw new IllegalStateException("image " + key + "_" + index + " not found");
        return region;
    }

    public TextureAtlas getAtlas() {
        return assets.get("fs2.atlas", TextureAtlas.class);
    }

}
