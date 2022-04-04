package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.e;
import static com.distraction.fs2j.tilemap.data.GameData.g;

import java.util.Arrays;
import java.util.List;

class TutorialData {

    public List<MapData> data;

    public TutorialData() {
        data = Arrays.asList(
                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0
                        },
                        7,
                        Arrays.asList(new TilePoint(0, 0))
                ),
                new MapData(
                        3, 3,
                        new int[]{
                                e, e, 0,
                                0, 0, 0,
                                e, e, 0
                        },
                        6,
                        Arrays.asList(new TilePoint(1, 0))
                ),
                new MapData(
                        3, 3,
                        new int[]{
                                0, g, 0,
                                0, g, 0,
                                0, g, 0
                        },
                        7,
                        Arrays.asList(new TilePoint(0, 1))
                ),
                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0
                        },
                        6,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(2, 2))
                )
        );
    }

}
