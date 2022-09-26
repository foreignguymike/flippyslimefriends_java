package com.distraction.fs2j.tilemap.data;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.fs2j.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameData {

    public static final int e = -1;
    public static final int b = 5;
    public static final int g = 100;

    public Context context;

    public Map<Area, List<MapData>> mapData;

    private final Map<Integer, TextureRegion> tileset;

    public GameData(Context context) {
        this.context = context;

        mapData = new HashMap<>();
        mapData.put(Area.MEADOW, new MeadowData().data);
        mapData.put(Area.TUNDRA, new TundraData().data);
        mapData.put(Area.RUINS, new RuinsData().data);
        mapData.put(Area.UNDERSEA, new UnderseaData().data);
        mapData.put(Area.MATRIX, new MatrixData().data);
        mapData.put(Area.CHALLENGE, new ChallengeData().data);

        tileset = new HashMap<>();
        tileset.put(0, context.getImage("tileoff"));
        tileset.put(1, context.getImage("tileon"));
        tileset.put(b, context.getImage("tileblocked"));
        tileset.put(g, context.getImage("tilegrayfloor"));
    }

    public List<MapData> getAllMapData() {
        List<MapData> list = new ArrayList<>();
        list.addAll(mapData.get(Area.MEADOW));
        list.addAll(mapData.get(Area.TUNDRA));
        list.addAll(mapData.get(Area.RUINS));
        list.addAll(mapData.get(Area.UNDERSEA));
        list.addAll(mapData.get(Area.MATRIX));
        return list;
    }

    public List<MapData> getMapData(Area area) {
        return mapData.get(area);
    }

    public TextureRegion getTile(int index) {
        return tileset.get(index);
    }

}

