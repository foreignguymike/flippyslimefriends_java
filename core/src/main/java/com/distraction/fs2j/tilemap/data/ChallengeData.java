package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChallengeData {

    public List<MapData> data;

    public ChallengeData() {
        data = Arrays.asList(
                new MapData(
                        6, 6,
                        new int[]{
                                e, 0, 0, e, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                e, 0, 0, e, 0, 0
                        },
                        0,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(3, 4, Direction.RIGHT),
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(2, 4, Direction.RIGHT),
                                new ArrowData(2, 2, Direction.RIGHT),
                                new TeleportData(0, 1, 5, 5),
                                new TeleportData(5, 5, 0, 1),
                                new IceData(2, 0),
                                new SuperJumpData(2, 1),
                                new IceData(2, 3),
                                new IceData(3, 0),
                                new SuperJumpData(3, 1),
                                new IceData(3, 3),
                                new TeleportData(5, 1, 0, 5),
                                new TeleportData(0, 5, 5, 1)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        7, 11,
                        new int[]{
                                e, e, 0, 0, 0, 0, 0, e, e, e, e,
                                e, 0, 0, 0, 0, 0, 0, 0, 0, 0, e,
                                e, 0, 0, 0, 0, 0, 0, 0, e, e, e,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, e,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, e,
                                e, e, 0, 0, 0, 0, 0, 0, 0, e, e
                        },
                        0,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(2, 6),
                                new IceData(3, 0),
                                new IceData(3, 2),
                                new IceData(3, 6),
                                new IceData(4, 0),
                                new IceData(4, 2),
                                new IceData(4, 3),
                                new IceData(4, 7),
                                new IceData(5, 0),
                                new IceData(5, 1),
                                new IceData(5, 6),
                                new IceData(5, 7),
                                new IceData(5, 8),
                                new IceData(5, 9)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        9, 6,
                        new int[]{
                                e, 0, 0, 0, 0, e,
                                0, 0, 0, 0, 0, 0,
                                0, 0, e, e, 0, 0,
                                e, e, e, 0, 0, 0,
                                e, e, 0, 0, 0, e,
                                e, e, e, 0, 0, 0,
                                0, 0, e, e, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0, e
                        },
                        0,
                        Arrays.asList(new TilePoint(0, 0)),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        false
                )
        );
    }

}
