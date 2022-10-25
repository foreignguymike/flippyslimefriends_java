package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.e;
import static com.distraction.fs2j.tilemap.data.GameData.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChallengeFinalData {

    public List<MapData> data;

    public ChallengeFinalData() {
        data = Arrays.asList(
                new MapData(
                        5, 5,
                        new int[] {
                                0, 0, 0, e, e,
                                0, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0,
                                e, e, 0, 0, 0
                        },
                        0,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(1, 3, Direction.DOWN),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new SuperJumpData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 2),
                                new TeleportData(4, 4, 0, 0),
                                new TeleportData(0, 0, 4, 4)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        6, 6,
                        new int[] {
                                e, e, e, 0, 0, 0,
                                e, e, 0, 0, 0, 0,
                                e, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, e,
                                0, 0, 0, 0, e, e,
                                0, 0, 0, e, e, e
                        },
                        0,
                        Arrays.asList(new TilePoint(3, 3), new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(4, 3, Direction.UP),
                                new ArrowData(3, 4, Direction.LEFT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.DOWN),
                                new SuperJumpData(2, 3),
                                new SuperJumpData(3, 2),
                                new FinishTileData(4, 0),
                                new FinishTileData(1, 5)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        5, 8,
                        new int[] {
                                0, g, 0, e, e, 0, 0, 0,
                                0, g, 0, e, e, 0, 0, 0,
                                e, e, e, 0, e, e, e, 0,
                                0, g, 0, e, e, 0, 0, 0,
                                0, g, 0, e, e, 0, 0, 0
                        },
                        0,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new ArrowData(3, 7, Direction.DOWN),
                                //new ArrowData(3, 6, Direction.DOWN),
                                //new ArrowData(3, 5, Direction.DOWN),
                                new ArrowData(1, 7, Direction.UP),
                                //new ArrowData(1, 6, Direction.UP),
                                //new ArrowData(1, 5, Direction.UP),
                                new IceData(0, 1),
                                new IceData(1, 1),
                                new IceData(3, 1),
                                new IceData(4, 1),
                                new TeleportData(4, 2, 0, 7),
                                new TeleportData(0, 7, 4, 2),
                                new TeleportData(4, 7, 0, 2),
                                new TeleportData(0, 2, 4, 7)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(2, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 7),
                                        new StopPathPointData(2, 4)
                                )
                        ),
                        false
                ),
                new MapData(
                        7, 7,
                        new int[] {
                                0, 0, 0, 0, 0, 0, 0,
                                0, e, e, 0, e, e, 0,
                                0, e, 0, 0, 0, e, 0,
                                0, 0, 0, 0, 0, 0, 0,
                                0, e, 0, 0, 0, e, 0,
                                0, e, e, 0, e, e, 0,
                                0, 0, 0, 0, 0, 0, 0
                        },
                        0,
                        Arrays.asList(new TilePoint(2, 3), new TilePoint(3, 3), new TilePoint(4, 3)),
                        Arrays.asList(
                                new ArrowData(6, 3, Direction.UP),
                                new ArrowData(4, 3, Direction.UP),
                                new ArrowData(3, 6, Direction.LEFT),
                                new ArrowData(3, 4, Direction.LEFT),
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(3, 0, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(0, 3, Direction.DOWN),
                                new FinishTileData(3, 3),
                                new FinishTileData(4, 4),
                                new FinishTileData(6, 6)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        8, 9,
                        new int[] {
                                e, 0, 0, e, e, e, e, 0, 0,
                                0, 0, 0, e, e, e, 0, 0, 0,
                                0, 0, 0, e, e, e, 0, 0, e,
                                e, e, e, e, e, e, e, e, e,
                                0, 0, 0, 0, e, e, e, e, e,
                                0, 0, 0, e, e, 0, 0, 0, e,
                                e, 0, 0, e, e, 0, 0, 0, e,
                                e, e, e, e, e, 0, 0, e, e
                        },
                        0,
                        Arrays.asList(new TilePoint(4, 5)),
                        Arrays.asList(
                                new TeleportData(0, 8, 1, 1),
                                new TeleportData(1, 1, 0, 8),
                                new SuperJumpData(4, 1),
                                new FinishTileData(4, 3),
                                new IceData(6, 6)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(4, 3, 1f),
                                        new StopPathPointData(4, 5, 1f),
                                        new StopPathPointData(2, 5, 1f),
                                        new StopPathPointData(2, 3, 1f),
                                        new PathPointData(4, 3)
                                )
                        ),
                        true
                )
        );
    }

}
