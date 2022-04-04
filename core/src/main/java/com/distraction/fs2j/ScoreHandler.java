package com.distraction.fs2j;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.distraction.fs2j.tilemap.data.Area;

import java.util.HashMap;
import java.util.Map;

public class ScoreHandler {

    public Map<Area, int[]> scores;

    public ScoreHandler(Context context) {
        scores = new HashMap<>();
        for (Area area : Area.values()) {
            if (context.gameData == null) throw new IllegalStateException("game data null");
            if (context.gameData.getMapData(area) == null)
                throw new IllegalStateException("map data for " + area + " null");
            scores.put(area, new int[context.gameData.getMapData(area).size()]);
        }
    }

    private Preferences getPreferences(Area area) {
        return Gdx.app.getPreferences(area.text + "scores");
    }

    public void load() {
        for (Area area : Area.values()) {
            Preferences prefs = getPreferences(area);
            int[] areaScores = getScores(area);
            for (int i = 0; i < areaScores.length; i++) {
                if (!prefs.contains(Integer.toString(i))) {
                    prefs.putInteger(Integer.toString(i), 0);
                    prefs.flush();
                }
                areaScores[i] = prefs.getInteger(Integer.toString(i), 0);
            }
        }
    }

    public int[] getScores(Area area) {
        return scores.get(area);
    }

    public void saveScore(Area area, int level, int score) {
        Preferences prefs = getPreferences(area);
        prefs.putInteger(Integer.toString(level), score);
        prefs.flush();
        getScores(area)[level] = score;
    }

}
