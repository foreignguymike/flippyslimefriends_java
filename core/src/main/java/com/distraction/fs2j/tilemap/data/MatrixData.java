package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.e;
import static com.distraction.fs2j.tilemap.data.GameData.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixData {

    public List<MapData> data;

    public MatrixData() {
        data = Arrays.asList(
                new MapData(
                        3, 3,
                        new int[]{
                                0, e, 0,
                                0, e, 0,
                                0, e, 0
                        },
                        5,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new TeleportData(2, 0, 0, 2),
                                new TeleportData(0, 2, 2, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, e, 0, e, 0,
                                0, 0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(1, 0)),
                        Arrays.asList(
                                new TeleportData(0, 1, 1, 4),
                                new TeleportData(1, 4, 0, 1)
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        11,
                        Arrays.asList(new TilePoint(2, 0)),
                        Arrays.asList(
                                new ArrowData(1, 3, Direction.UP),
                                new ArrowData(1, 2, Direction.DOWN),
                                new ArrowData(1, 1, Direction.DOWN),
                                new ArrowData(1, 0, Direction.UP),
                                new TeleportData(2, 3, 0, 0),
                                new TeleportData(0, 0, 2, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                e, 0, 0, 0,
                                0, 0, 0, e,
                                0, 0, 0, e
                        },
                        15,
                        Arrays.asList(new TilePoint(3, 0)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(1, 1, Direction.DOWN),
                                new TeleportData(1, 3, 3, 2),
                                new TeleportData(3, 2, 1, 3)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(1, 3)),
                        Arrays.asList(
                                new SuperJumpData(1, 1),
                                new SuperJumpData(1, 2),
                                new TeleportData(2, 0, 0, 3),
                                new TeleportData(0, 3, 2, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        11,
                        Arrays.asList(new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.LEFT),
                                new IceData(0, 1),
                                new IceData(2, 1),
                                new TeleportData(2, 3, 0, 0),
                                new TeleportData(0, 0, 2, 3)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                e, 0, 0,
                                e, 0, 0,
                                e, 0, 0,
                                0, 0, 0
                        },
                        8,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(1, 1, Direction.UP),
                                new TeleportData(3, 2, 0, 2),
                                new TeleportData(0, 2, 3, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(3, 0), new StopPathPointData(0, 0))
                        ),
                        false
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(2, 0)),
                        Arrays.asList(
                                new ArrowData(0, 2, Direction.RIGHT),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new TeleportData(2, 2, 1, 1),
                                new TeleportData(1, 1, 2, 2),
                                new IceData(2, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                e, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(3, 2)),
                        Arrays.asList(
                                new ArrowData(1, 1, Direction.LEFT),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new TeleportData(2, 0, 1, 2),
                                new TeleportData(1, 2, 2, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        17,
                        Arrays.asList(new TilePoint(2, 3)),
                        Arrays.asList(
                                new TeleportData(0, 1, 3, 3),
                                new TeleportData(3, 3, 0, 1),
                                new TeleportData(0, 2, 3, 0),
                                new TeleportData(3, 0, 0, 2),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 2),
                                new TeleportData(2, 3, 0, 2),
                                new TeleportData(0, 2, 2, 3)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        1, 5,
                        new int[]{
                                0, 0, 0, 0, 0
                        },
                        8,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new FinishTileData(0, 2)
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, e,
                                e, 0, 0, 0,
                                0, 0, 0, e
                        },
                        16,
                        Arrays.asList(new TilePoint(1, 2)),
                        Arrays.asList(
                                new FinishTileData(1, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(3, 2)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new FinishTileData(0, 2),
                                new FinishTileData(3, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        2, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(0, 4)),
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.LEFT),
                                new ArrowData(0, 2, Direction.RIGHT),
                                new FinishTileData(0, 0),
                                new FinishTileData(1, 4)
                        )
                ),
                new MapData(
                        5, 2,
                        new int[]{
                                e, 0,
                                0, 0,
                                0, 0,
                                0, 0,
                                0, e
                        },
                        6,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(3, 1)),
                        Arrays.asList(
                                new FinishTileData(0, 1),
                                new SuperJumpData(2, 0),
                                new SuperJumpData(2, 1),
                                new FinishTileData(4, 0)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(0, 1), new StopPathPointData(0, 0)),
                                Arrays.asList(new StopPathPointData(4, 0), new StopPathPointData(4, 1))
                        ),
                        false
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                e, e, 0, 0, e, e,
                                e, 0, 0, 0, 0, e,
                                e, 0, 0, 0, 0, e,
                                e, e, e, e, e, e
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        Arrays.asList(
                                new FinishTileData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new FinishTileData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 2),
                                        new PathPointData(0, 0),
                                        new PathPointData(3, 0),
                                        new StopPathPointData(3, 2),
                                        new PathPointData(3, 0),
                                        new PathPointData(0, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(0, 3),
                                        new PathPointData(0, 5),
                                        new PathPointData(3, 5),
                                        new StopPathPointData(3, 3),
                                        new PathPointData(3, 5),
                                        new PathPointData(0, 5)
                                )
                        ),
                        true
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, e, e,
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(2, 3)),
                        Arrays.asList(
                                new FinishTileData(1, 2),
                                new FinishTileData(3, 0),
                                new TeleportData(3, 3, 0, 1),
                                new TeleportData(0, 1, 3, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, e, 0,
                                0, e, 0, 0,
                                0, 0, 0, 0
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new FinishTileData(3, 3),
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(0, 2, Direction.LEFT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 1),
                                        new StopPathPointData(1, 2)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(2, 1)
                                )
                        )
                ),
                new MapData(
                        1, 6,
                        new int[]{
                                0, 0, g, e, 0, 0
                        },
                        8,
                        Arrays.asList(new TilePoint(0, 1), new TilePoint(0, 4)),
                        Arrays.asList(
                                new FinishTileData(0, 1),
                                new SuperJumpData(0, 2),
                                new FinishTileData(0, 4),
                                new ArrowData(0, 0, Direction.RIGHT),
                                new ArrowData(0, 5, Direction.LEFT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 2),
                                        new StopPathPointData(0, 3)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, e, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, e, e
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.LEFT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new TeleportData(2, 0, 2, 3),
                                new TeleportData(2, 3, 2, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, 0,
                                e, e, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new TeleportData(1, 0, 0, 3),
                                new TeleportData(0, 3, 1, 0),
                                new FinishTileData(1, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, g, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(3, 1)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.UP),
                                new FinishTileData(1, 1)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, e,
                                e, 0, 0, 0,
                                e, 0, 0, 0,
                                e, e, e, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new FinishTileData(1, 1),
                                new TeleportData(3, 3, 0, 0),
                                new TeleportData(0, 0, 3, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, e, e, e,
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                e, e, e, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new FinishTileData(1, 1),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new PathPointData(0, 3),
                                        new StopPathPointData(1, 3),
                                        new PathPointData(0, 3)
                                ),
                                Arrays.asList(new StopPathPointData(3, 3), new StopPathPointData(3, 0))
                        ),
                        true
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, e, 0, e,
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e
                        },
                        20,
                        Arrays.asList(new TilePoint(3, 2)),
                        Arrays.asList(
                                new TeleportData(0, 1, 2, 4),
                                new TeleportData(2, 4, 0, 1),
                                new TeleportData(2, 0, 0, 3),
                                new TeleportData(0, 3, 2, 0),
                                new SuperJumpData(2, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(2, 0, Direction.UP),
                                new FinishTileData(2, 1),
                                new TeleportData(3, 0, 1, 2),
                                new TeleportData(1, 2, 3, 0)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, e, e,
                                0, 0, 0, 0, e,
                                e, 0, 0, 0, 0,
                                0, e, 0, 0, 0,
                                e, 0, e, 0, e
                        },
                        17,
                        Arrays.asList(new TilePoint(1, 3)),
                        Arrays.asList(
                                new ArrowData(4, 3, Direction.LEFT),
                                new ArrowData(4, 1, Direction.UP),
                                new ArrowData(3, 0, Direction.RIGHT),
                                new ArrowData(1, 0, Direction.DOWN),
                                new SuperJumpData(1, 0),
                                new SuperJumpData(2, 2),
                                new SuperJumpData(3, 0),
                                new SuperJumpData(4, 1),
                                new SuperJumpData(4, 3),
                                new TeleportData(0, 2, 2, 4),
                                new TeleportData(2, 4, 0, 2)
                        )
                ),
                new MapData(
                        6, 4,
                        new int[]{
                                e, e, e, e,
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e,
                                e, e, e, e
                        },
                        14,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(3, 3)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.DOWN),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new FinishTileData(1, 3),
                                new IceData(2, 2),
                                new IceData(3, 1),
                                new FinishTileData(4, 0)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 3),
                                        new PathPointData(0, 3),
                                        new PathPointData(0, 0),
                                        new StopPathPointData(1, 0),
                                        new PathPointData(0, 0),
                                        new PathPointData(0, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(4, 0),
                                        new PathPointData(5, 0),
                                        new PathPointData(5, 3),
                                        new StopPathPointData(4, 3),
                                        new PathPointData(5, 3),
                                        new PathPointData(5, 0)
                                )
                        ),
                        false
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, e, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, e, 0, 0, e
                        },
                        12,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(2, 4)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new TeleportData(0, 1, 3, 3),
                                new TeleportData(3, 3, 0, 1),
                                new TeleportData(2, 0, 1, 4),
                                new TeleportData(1, 4, 2, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, e, e, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e,
                                e, 0, 0, e
                        },
                        12,
                        Arrays.asList(new TilePoint(2, 0)),
                        Arrays.asList(
                                new TeleportData(0, 3, 2, 1),
                                new TeleportData(2, 1, 0, 3),
                                new IceData(1, 1),
                                new FinishTileData(1, 2),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(0, 0), new StopPathPointData(0, 2)),
                                Arrays.asList(new StopPathPointData(1, 3), new StopPathPointData(3, 3))
                        ),
                        false
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(1, 1, Direction.UP),
                                new FinishTileData(0, 1),
                                new FinishTileData(3, 1)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, e, 0, 0,
                                0, 0, e, 0,
                                0, 0, 0, 0
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new TeleportData(0, 3, 3, 0),
                                new TeleportData(3, 0, 0, 3),
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 1),
                                new FinishTileData(3, 3)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, e, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, e, e
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new TeleportData(0, 2, 3, 1),
                                new TeleportData(3, 1, 0, 2),
                                new FinishTileData(1, 3),
                                new FinishTileData(2, 3)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(1, 3)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new IceData(0, 2),
                                new FinishTileData(1, 0),
                                new IceData(1, 1),
                                new FinishTileData(1, 3),
                                new IceData(2, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 6,
                        new int[]{
                                e, e, 0, 0, e, e,
                                0, 0, 0, 0, 0, 0,
                                0, 0, e, e, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(1, 3, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new TeleportData(1, 0, 1, 5),
                                new TeleportData(1, 5, 1, 0),
                                new FinishTileData(2, 0),
                                new FinishTileData(2, 5)
                        ),
                        new ArrayList<>(),
                        true
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, 0
                        },
                        24,
                        Arrays.asList(new TilePoint(3, 3)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.DOWN),
                                new IceData(1, 1),
                                new TeleportData(1, 3, 0, 2),
                                new TeleportData(0, 2, 1, 3),
                                new TeleportData(2, 0, 3, 1),
                                new TeleportData(3, 1, 2, 0),
                                new FinishTileData(2, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e,
                                e, e, 0, e, e
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 1), new TilePoint(2, 3)),
                        Arrays.asList(
                                new FinishTileData(0, 2),
                                new TeleportData(1, 0, 1, 4),
                                new TeleportData(1, 4, 1, 0),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new FinishTileData(3, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e
                        },
                        20,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(2, 4)),
                        Arrays.asList(
                                new ArrowData(1, 3, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new SuperJumpData(2, 2),
                                new TeleportData(3, 2, 0, 2),
                                new TeleportData(0, 2, 3, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(1, 3)),
                        Arrays.asList(
                                new ArrowData(0, 3, Direction.LEFT),
                                new ArrowData(0, 0, Direction.RIGHT),
                                new FinishTileData(0, 1),
                                new FinishTileData(0, 2),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(2, 2, Direction.LEFT)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                g, 0, 0, g,
                                0, 0, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 3), new TilePoint(2, 3)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.LEFT),
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(0, 2, Direction.LEFT),
                                new ArrowData(0, 1, Direction.LEFT),
                                new FinishTileData(0, 0),
                                new TeleportData(1, 0, 1, 3),
                                new TeleportData(1, 3, 1, 0),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new FinishTileData(2, 0)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                e, e, 0, 0, 0, e,
                                e, 0, 0, 0, 0, e,
                                e, 0, 0, 0, 0, e,
                                e, 0, 0, 0, e, e
                        },
                        17,
                        Arrays.asList(new TilePoint(3, 1)),
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.UP),
                                new FinishTileData(1, 3),
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 1),
                                        new PathPointData(3, 0),
                                        new PathPointData(0, 0),
                                        new StopPathPointData(0, 1),
                                        new PathPointData(0, 0),
                                        new PathPointData(3, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(0, 4),
                                        new PathPointData(0, 5),
                                        new PathPointData(3, 5),
                                        new StopPathPointData(3, 4),
                                        new PathPointData(3, 5),
                                        new PathPointData(0, 5)
                                )
                        ),
                        false
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                g, e, e, 0
                        },
                        20,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(0, 3)),
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.LEFT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new FinishTileData(0, 3),
                                new FinishTileData(2, 0),
                                new SuperJumpData(2, 1),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 0),
                                        new StopPathPointData(3, 1),
                                        new StopPathPointData(3, 2))
                        ),
                        false
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        26,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(2, 3)),
                        Arrays.asList(
                                new TeleportData(0, 1, 4, 1),
                                new TeleportData(4, 1, 0, 1),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new FinishTileData(2, 1),
                                new FinishTileData(2, 2),
                                new IceData(3, 1),
                                new IceData(3, 2),
                                new TeleportData(4, 2, 0, 2),
                                new TeleportData(0, 2, 4, 2)
                        ),
                        new ArrayList<>(),
                        false
                ),
                new MapData(
                        3, 6,
                        new int[]{
                                e, 0, e, e, e, e,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0
                        },
                        26,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 3, Direction.RIGHT),
                                new TeleportData(1, 0, 2, 5),
                                new TeleportData(2, 5, 1, 0),
                                new TeleportData(1, 2, 2, 3),
                                new TeleportData(2, 3, 1, 2),
                                new FinishTileData(2, 0)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(0, 1), new StopPathPointData(0, 4))
                        ),
                        false
                )
        );
    }

}
