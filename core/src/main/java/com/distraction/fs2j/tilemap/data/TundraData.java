package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.b;
import static com.distraction.fs2j.tilemap.data.GameData.e;
import static com.distraction.fs2j.tilemap.data.GameData.g;

import java.util.Arrays;
import java.util.List;

public class TundraData {

    public List<MapData> data;

    public TundraData() {
        data = Arrays.asList(
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        11,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        19,
                        Arrays.asList(new TilePoint(3, 3)),
                        Arrays.asList(
                                new ArrowData(1, 0, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.LEFT),
                                new IceData(1, 1),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        15,
                        Arrays.asList(new TilePoint(2, 3)),
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 1)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                e, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                e, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new SuperJumpData(2, 1),
                                new IceData(3, 1)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        19,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(3, 0, Direction.RIGHT),
                                new ArrowData(0, 3, Direction.LEFT),
                                new ArrowData(0, 2, Direction.LEFT),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0
                        },
                        28,
                        Arrays.asList(new TilePoint(3, 4)),
                        Arrays.asList(
                                new IceData(0, 2),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                g, g, g, g,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                g, g, g, g
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(0, 1),
                                new IceData(1, 0),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 3),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, e, 0,
                                0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(1, 1)),
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.UP),
                                new IceData(1, 3),
                                new IceData(2, 3),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                e, e, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        23,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new SuperJumpData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new SuperJumpData(2, 2),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, e, 0, 0, e,
                                e, g, 0, 0, 0,
                                0, 0, 0, g, e,
                                e, 0, 0, e, e
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 2)),
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, e, 0, 0
                        },
                        22,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                0, e, e, e,
                                0, 0, 0, 0,
                                0, 0, 0, e,
                                0, 0, 0, e,
                                e, e, 0, e
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(4, 2)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(2, 1),
                                new IceData(3, 1)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, e, 0, 0,
                                e, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, e,
                                0, 0, e, e, e
                        },
                        19,
                        Arrays.asList(new TilePoint(4, 1), new TilePoint(0, 3)),
                        Arrays.asList(
                                new IceData(2, 1),
                                new SuperJumpData(2, 2),
                                new IceData(2, 2),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                e, 0, e
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(0, 2)),
                        Arrays.asList(
                                new IceData(0, 1),
                                new IceData(1, 1),
                                new IceData(2, 1)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0,
                                e, 0, 0, 0, e
                        },
                        21,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(3, 4)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 2),
                                new IceData(3, 2),
                                new IceData(3, 3)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        23,
                        Arrays.asList(new TilePoint(3, 0)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.DOWN),
                                new ArrowData(1, 2, Direction.UP),
                                new IceData(1, 1),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, e, e, b, e,
                                0, 0, 0, 0, e,
                                0, e, e, 0, e,
                                0, 0, 0, 0, b,
                                b, e, e, e, e
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(1, 0),
                                new IceData(2, 0),
                                new IceData(3, 0),
                                new IceData(3, 1),
                                new IceData(3, 2),
                                new IceData(3, 3),
                                new IceData(2, 3),
                                new IceData(1, 3),
                                new IceData(1, 2)
                        )
                ),
                new MapData(
                        6, 5,
                        new int[]{
                                e, e, 0, 0, 0,
                                e, e, 0, b, 0,
                                e, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, e, e
                        },
                        23,
                        Arrays.asList(new TilePoint(3, 1)),
                        Arrays.asList(
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new SuperJumpData(3, 2),
                                new IceData(3, 3),
                                new IceData(4, 2)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e
                        },
                        28,
                        Arrays.asList(new TilePoint(0, 2)),
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, g, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, g, e
                        },
                        26,
                        Arrays.asList(new TilePoint(0, 3)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                g, 0, 0, g,
                                0, 0, 0, 0,
                                0, b, b, 0,
                                0, 0, 0, 0,
                                g, 0, 0, g
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 0),
                                new IceData(2, 3),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, e, 0, e,
                                0, 0, 0, b,
                                0, 0, 0, b,
                                0, 0, 0, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(1, 1)),
                        Arrays.asList(
                                new IceData(0, 2),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        )
                ),


                new MapData(
                        3, 5,
                        new int[]{
                                e, e, 0, e, e,
                                0, 0, 0, 0, 0,
                                e, e, 0, e, e
                        },
                        7,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(1, 4)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, e, e, 0,
                                0, 0, 0, 0,
                                e, 0, 0, 0,
                                e, 0, 0, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(0, 3)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                g, 0, 0, g,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                g, 0, 0, g
                        },
                        14,
                        Arrays.asList(new TilePoint(1, 2), new TilePoint(0, 0)),
                        Arrays.asList(
                                new IceData(0, 1),
                                new IceData(0, 2),
                                new IceData(1, 0),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 0),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e
                        },
                        14,
                        Arrays.asList(new TilePoint(0, 2), new TilePoint(0, 3)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new IceData(1, 2),
                                new IceData(2, 1)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        14,
                        Arrays.asList(new TilePoint(0, 1), new TilePoint(0, 2)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        7, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, b, 0,
                                0, g, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        17,
                        Arrays.asList(new TilePoint(6, 0), new TilePoint(6, 2)),
                        Arrays.asList(
                                new IceData(1, 0),
                                new IceData(2, 1),
                                new IceData(4, 2),
                                new IceData(5, 0),
                                new IceData(5, 1)
                        )
                ),
                new MapData(
                        7, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(5, 2)),
                        Arrays.asList(
                                new ArrowData(4, 1, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.LEFT),
                                new IceData(2, 2),
                                new IceData(4, 0)
                        )
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
                        18,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(2, 3)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                e, e, e, e, 0, e,
                                0, 0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0, e,
                                e, 0, 0, e, e, e
                        },
                        26,
                        Arrays.asList(new TilePoint(1, 2)),
                        Arrays.asList(
                                new IceData(1, 4),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        22,
                        Arrays.asList(new TilePoint(4, 1), new TilePoint(4, 2)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                e, e, 0, 0, e, e,
                                e, 0, 0, 0, 0, e,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0
                        },
                        24,
                        Arrays.asList(new TilePoint(0, 2), new TilePoint(0, 3)),
                        Arrays.asList(
                                new ArrowData(3, 4, Direction.LEFT),
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(2, 4)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, b, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, b, 0, 0,
                                e, 0, 0, 0, e
                        },
                        21,
                        Arrays.asList(new TilePoint(0, 3), new TilePoint(4, 1)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 1),
                                new IceData(3, 3)
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 2), new TilePoint(2, 2)),
                        Arrays.asList(
                                new IceData(0, 1),
                                new IceData(0, 3),
                                new IceData(1, 1),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                0, b, 0, 0,
                                0, 0, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(1, 3)),
                        Arrays.asList(
                                new IceData(1, 0),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 0),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 1)
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, b, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, b, 0, 0
                        },
                        21,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(2, 4)),
                        Arrays.asList(
                                new IceData(0, 1),
                                new IceData(0, 3),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 3)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                e, 0, b, 0, 0,
                                g, 0, 0, 0, 0,
                                g, 0, 0, 0, e,
                                e, 0, 0, e, e
                        },
                        21,
                        Arrays.asList(new TilePoint(2, 4)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.UP),
                                new IceData(1, 3),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(3, 1)
                        )
                ),
                new MapData(
                        6, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        26,
                        Arrays.asList(new TilePoint(0, 2), new TilePoint(5, 1)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.UP),
                                new ArrowData(3, 0, Direction.UP),
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 2, Direction.DOWN),
                                new IceData(2, 0),
                                new IceData(2, 1),
                                new IceData(3, 2),
                                new IceData(3, 3)
                        )
                ),
                new MapData(
                        6, 6,
                        new int[]{
                                0, 0, 0, e, e, e,
                                0, 0, 0, g, e, e,
                                0, 0, 0, 0, 0, e,
                                e, 0, 0, 0, 0, 0,
                                e, e, g, 0, 0, 0,
                                e, e, e, 0, 0, 0
                        },
                        31,
                        Arrays.asList(new TilePoint(2, 3), new TilePoint(3, 2)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new SuperJumpData(2, 1),
                                new IceData(2, 2),
                                new IceData(3, 3),
                                new SuperJumpData(3, 4),
                                new IceData(4, 4)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, g, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, b, 0, 0,
                                0, g, 0, g, 0,
                                e, 0, 0, 0, e
                        },
                        27,
                        Arrays.asList(new TilePoint(4, 2)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(2, 3),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                e, 0, 0, 0, 0, e,
                                0, 0, b, b, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0, e
                        },
                        22,
                        Arrays.asList(new TilePoint(0, 4), new TilePoint(1, 5)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 4),
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(2, 3),
                                new IceData(2, 4)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, g, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e
                        },
                        20,
                        Arrays.asList(new TilePoint(4, 1), new TilePoint(4, 3)),
                        Arrays.asList(
                                new ArrowData(4, 2, Direction.UP),
                                new ArrowData(2, 4, Direction.LEFT),
                                new ArrowData(2, 0, Direction.RIGHT),
                                new ArrowData(0, 2, Direction.DOWN),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 3),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        6, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        26,
                        Arrays.asList(new TilePoint(0, 1), new TilePoint(0, 2)),
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.LEFT),
                                new ArrowData(3, 0, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.LEFT),
                                new ArrowData(2, 0, Direction.RIGHT),
                                new IceData(1, 0),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3),
                                new IceData(2, 1),
                                new IceData(3, 2),
                                new IceData(4, 0),
                                new IceData(4, 1),
                                new IceData(4, 2),
                                new IceData(4, 3)
                        )
                )
        );
    }

}
