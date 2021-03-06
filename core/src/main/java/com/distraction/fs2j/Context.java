package com.distraction.fs2j;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.gj.GameJoltClient;
import com.distraction.fs2j.states.GSM;
import com.distraction.fs2j.tilemap.data.Area;
import com.distraction.fs2j.tilemap.data.GameData;
import com.distraction.fs2j.tilemap.data.MapData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.golfgl.gdxgamesvcs.leaderboard.ILeaderBoardEntry;

public class Context {

    private static final String ATLAS_NAME = "fs2.atlas";

    public AssetManager assets;
    public GSM gsm;
    public GameData gameData;
    public ScoreHandler scoreHandler;
    public PlayerDataHandler playerDataHandler;
    public GameJoltClient client;
    public AudioHandler audioHandler;

    public Context() {
        assets = new AssetManager();
        assets.load(ATLAS_NAME, TextureAtlas.class);
        assets.finishLoading();

        gsm = new GSM();

        gameData = new GameData(this);
        // sanity check
        for (Map.Entry<Area, List<MapData>> entry : gameData.mapData.entrySet()) {
            Area area = entry.getKey();
            List<MapData> levels = entry.getValue();
            for (int index = 0; index < levels.size(); index++) {
                if (levels.get(index).goal == 0 && area != Area.CHALLENGE) {
                    throw new IllegalStateException("level " + area + "-" + (index + 1) + "has no goal");
                }
            }
        }

        scoreHandler = new ScoreHandler(this);
        playerDataHandler = new PlayerDataHandler(this);

        audioHandler = new AudioHandler();
    }

    public TextureRegion getImage(String key) {
        TextureRegion region = assets.get(ATLAS_NAME, TextureAtlas.class).findRegion(key);
        if (region == null) throw new IllegalStateException("image " + key + " not found");
        return region;
    }

    public TextureRegion getImage(String key, int index) {
        TextureRegion region = assets.get(ATLAS_NAME, TextureAtlas.class).findRegion(key, index);
        if (region == null)
            throw new IllegalStateException("image " + key + "_" + index + " not found");
        return region;
    }

    public void fetchLeaderboards(SimpleCallback callback) {
        client.fetchLeaderboardEntries("BETA_1", 7, false, leaderBoard -> {
            playerDataHandler.leaderboards.clear();
            List<ILeaderBoardEntry> entries = new ArrayList<>();
            for (ILeaderBoardEntry it : leaderBoard) entries.add(it);
            playerDataHandler.leaderboards.add(entries);
            System.out.println("added leaderboard entries for beta 1");
            callback.callback();
        });
    }

}
