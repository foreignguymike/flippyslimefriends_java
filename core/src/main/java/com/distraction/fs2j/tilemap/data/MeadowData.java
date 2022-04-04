package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.e;
import static com.distraction.fs2j.tilemap.data.GameData.g;

import java.util.Arrays;
import java.util.List;

class MeadowData {
    public List<MapData> data;

    public MeadowData() {
        data = Arrays.asList(
                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, e,
                                0, 0, 0,
                                e, 0, 0
                        },
                        6,
                        Arrays.asList(new TilePoint(1, 0))
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, e, 0,
                                e, 0, 0, 0
                        },
                        8,
                        Arrays.asList(new TilePoint(2, 1))
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                e, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(1, 1))
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, e, e,
                                0, 0, 0, 0,
                                e, e, 0, 0
                        },
                        11,
                        Arrays.asList(new TilePoint(1, 2))
                ),
                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, e, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(1, 0))
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                e, 0, 0,
                                e, 0, 0,
                                0, 0, e,
                                0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(2, 1))
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, e, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, e, 0
                        },
                        19,
                        Arrays.asList(new TilePoint(2, 1))
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 3)),
                        Arrays.asList(
                                new ArrowData(0, 0, Direction.RIGHT)
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(1, 1, Direction.UP),
                                new ArrowData(2, 1, Direction.UP)
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
                        Arrays.asList(new TilePoint(1, 1)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(0, 1, Direction.LEFT)
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(3, 1)),
                        Arrays.asList(
                                new ArrowData(1, 0, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.UP)
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
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(0, 2, Direction.RIGHT),
                                new ArrowData(0, 1, Direction.LEFT),
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(3, 1, Direction.LEFT)
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
                        Arrays.asList(new TilePoint(3, 0)),
                        Arrays.asList(
                                new ArrowData(0, 2, Direction.DOWN),
                                new ArrowData(1, 2, Direction.DOWN),
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(3, 1, Direction.UP)
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
                        Arrays.asList(new TilePoint(3, 0)),
                        Arrays.asList(
                                new ArrowData(0, 0, Direction.DOWN),
                                new ArrowData(0, 3, Direction.DOWN),
                                new ArrowData(1, 0, Direction.RIGHT),
                                new ArrowData(2, 0, Direction.RIGHT),
                                new ArrowData(1, 3, Direction.LEFT),
                                new ArrowData(2, 3, Direction.LEFT)
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
                        23,
                        Arrays.asList(new TilePoint(3, 0)),
                        Arrays.asList(
                                new ArrowData(1, 1, Direction.DOWN),
                                new ArrowData(3, 1, Direction.DOWN),
                                new ArrowData(1, 2, Direction.UP),
                                new ArrowData(3, 2, Direction.UP)
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, e, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new SuperJumpData(0, 2),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                0, 0, e,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                e, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(2, 1)),
                        Arrays.asList(
                                new SuperJumpData(2, 0),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, e, e, e,
                                0, 0, e, 0, 0,
                                0, 0, e, 0, 0,
                                0, 0, e, 0, 0,
                                e, e, e, 0, e
                        },
                        17,
                        Arrays.asList(new TilePoint(1, 0)),
                        Arrays.asList(
                                new SuperJumpData(1, 3),
                                new SuperJumpData(3, 1)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        }, 16,
                        Arrays.asList(new TilePoint(4, 1)),
                        Arrays.asList(
                                new ArrowData(1, 1, Direction.RIGHT),
                                new ArrowData(3, 1, Direction.LEFT)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0,
                                0, 0, e,
                                0, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(2, 1)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.UP),
                                new SuperJumpData(1, 0)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, g, 0, 0,
                                0, 0, g, 0, 0,
                                e, 0, 0, 0, e
                        },
                        19,
                        Arrays.asList(new TilePoint(1, 0)),
                        Arrays.asList(
                                new SuperJumpData(1, 3),
                                new SuperJumpData(2, 1),
                                new ArrowData(0, 3, Direction.DOWN),
                                new ArrowData(3, 1, Direction.UP)
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
                        24,
                        Arrays.asList(new TilePoint(4, 2)),
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, 0, 0,
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e,
                                0, 0, 0, e
                        }, 19,
                        Arrays.asList(new TilePoint(2, 0)),
                        Arrays.asList(
                                new ArrowData(1, 1, Direction.UP),
                                new ArrowData(1, 3, Direction.UP),
                                new ArrowData(3, 0, Direction.DOWN),
                                new ArrowData(3, 2, Direction.DOWN)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0
                        },
                        21,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(3, 4, Direction.UP),
                                new ArrowData(3, 0, Direction.UP),
                                new SuperJumpData(3, 0),
                                new SuperJumpData(3, 4)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        }, 28,
                        Arrays.asList(new TilePoint(4, 2)),
                        Arrays.asList(
                                new SuperJumpData(1, 1),
                                new SuperJumpData(1, 3),
                                new SuperJumpData(3, 2)
                        )
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0
                        }, 27,
                        Arrays.asList(new TilePoint(3, 5)),
                        Arrays.asList(
                                new SuperJumpData(0, 4),
                                new SuperJumpData(1, 3),
                                new SuperJumpData(2, 2),
                                new SuperJumpData(3, 1)
                        )
                ),
                new MapData(
                        6, 8,
                        new int[]{
                                e, e, e, e, 0, 0, e, e,
                                e, e, 0, e, 0, 0, 0, e,
                                e, 0, 0, e, 0, 0, 0, 0,
                                0, 0, 0, e, 0, 0, 0, e,
                                e, 0, 0, e, 0, 0, e, e,
                                e, e, 0, e, e, e, e, e
                        }, 30,
                        Arrays.asList(new TilePoint(5, 2)),
                        Arrays.asList(
                                new SuperJumpData(3, 2),
                                new SuperJumpData(2, 4)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, e, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, e, 0, 0,
                                e, 0, 0, 0, e
                        }, 28,
                        Arrays.asList(new TilePoint(2, 4)),
                        Arrays.asList(
                                new SuperJumpData(2, 2),
                                new ArrowData(0, 2, Direction.RIGHT),
                                new ArrowData(4, 2, Direction.RIGHT)
                        )
                ),
                new MapData(
                        8, 5,
                        new int[]{
                                e, e, e, 0, e,
                                e, e, e, 0, 0,
                                e, e, e, 0, e,
                                e, e, e, 0, e,
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, e,
                                0, 0, 0, 0, e,
                                e, 0, 0, e, e
                        },
                        23,
                        Arrays.asList(new TilePoint(6, 0)),
                        Arrays.asList(
                                new ArrowData(5, 3, Direction.UP),
                                new ArrowData(4, 3, Direction.DOWN),
                                new SuperJumpData(4, 3),
                                new SuperJumpData(5, 3)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, e, 0, 0,
                                0, 0, 0, 0, 0,
                                0, e, 0, e, 0,
                                0, 0, 0, 0, 0,
                                0, 0, e, e, e
                        }, 15,
                        Arrays.asList(new TilePoint(4, 0), new TilePoint(0, 4)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP)
                        )
                ),
                new MapData(
                        2, 7,
                        new int[]{
                                0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 3), new TilePoint(1, 3)),
                        Arrays.asList(
                                new ArrowData(1, 5, Direction.RIGHT),
                                new ArrowData(1, 4, Direction.LEFT),
                                new ArrowData(0, 2, Direction.RIGHT),
                                new ArrowData(0, 1, Direction.LEFT)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, 0,
                                0, 0, e, 0, e,
                                0, 0, 0, 0, 0
                        }, 15,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(2, 1)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.LEFT),
                                new ArrowData(1, 2, Direction.RIGHT)
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 3), new TilePoint(2, 3)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(1, 0, Direction.UP)
                        )
                ),
                new MapData(
                        4, 6,
                        new int[]{
                                e, e, 0, 0, 0, 0,
                                e, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, e,
                                0, 0, 0, 0, e, e
                        },
                        18,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(0, 5)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.UP),
                                new ArrowData(2, 3, Direction.UP)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, e, e, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        23,
                        Arrays.asList(new TilePoint(2, 0)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new SuperJumpData(1, 1),
                                new SuperJumpData(1, 2),
                                new SuperJumpData(3, 1),
                                new SuperJumpData(3, 2)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, 0, 0, e, e,
                                0, e, 0, 0, e,
                                0, 0, 0, 0, 0,
                                e, 0, 0, e, 0,
                                e, e, 0, 0, 0
                        },
                        26,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.DOWN),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        5, 6,
                        new int[]{
                                0, 0, 0, e, e, e,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, e, e, e
                        },
                        25,
                        Arrays.asList(new TilePoint(0, 2)),
                        Arrays.asList(
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 2),
                                new SuperJumpData(3, 2)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, e, e, e, 0,
                                0, 0, 0, 0, 0,
                                0, e, e, e, 0,
                                0, 0, 0, 0, 0
                        },
                        23,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(0, 4)),
                        Arrays.asList(
                                new SuperJumpData(0, 2),
                                new SuperJumpData(2, 2),
                                new SuperJumpData(4, 2)
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
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(1, 2, Direction.RIGHT)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, e, 0, 0,
                                0, e, e, e, 0,
                                0, 0, e, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        29,
                        Arrays.asList(new TilePoint(0, 2)),
                        Arrays.asList(
                                new SuperJumpData(1, 1),
                                new SuperJumpData(3, 3)
                        )
                ),
                new MapData(
                        6, 5,
                        new int[]{
                                e, e, 0, 0, 0,
                                e, e, 0, e, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, e, 0, e, e,
                                0, 0, 0, e, e
                        },
                        27,
                        Arrays.asList(new TilePoint(0, 2)),
                        Arrays.asList(
                                new ArrowData(4, 2, Direction.UP),
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 1, Direction.RIGHT)
                        )
                ),
                new MapData(
                        7, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, e, 0, e, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e,
                                e, e, 0, e, e
                        },
                        21,
                        Arrays.asList(new TilePoint(0, 2), new TilePoint(6, 2)),
                        Arrays.asList(
                                new ArrowData(4, 1, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.LEFT),
                                new SuperJumpData(3, 2)
                        )
                ),
                new MapData(
                        5, 6,
                        new int[]{
                                e, e, 0, 0, 0, e,
                                e, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, e
                        },
                        28,
                        Arrays.asList(new TilePoint(4, 0)),
                        Arrays.asList(
                                new ArrowData(4, 4, Direction.UP),
                                new ArrowData(0, 4, Direction.DOWN),
                                new SuperJumpData(2, 2),
                                new SuperJumpData(3, 3)
                        )
                ),
                new MapData(
                        6, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, e, 0, e, 0,
                                0, e, 0, 0, 0,
                                0, e, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        37,
                        Arrays.asList(new TilePoint(5, 1)),
                        Arrays.asList(
                                new ArrowData(5, 2, Direction.LEFT),
                                new ArrowData(4, 2, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.DOWN),
                                new SuperJumpData(3, 3)
                        )
                ),
                new MapData(
                        7, 7,
                        new int[]{
                                e, e, 0, 0, 0, e, e,
                                e, 0, 0, 0, 0, e, e,
                                0, 0, 0, 0, 0, e, e,
                                e, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, e,
                                e, e, 0, 0, 0, 0, 0,
                                e, e, e, 0, e, e, e
                        },
                        33,
                        Arrays.asList(new TilePoint(6, 3)),
                        Arrays.asList(
                                new ArrowData(5, 6, Direction.UP),
                                new ArrowData(4, 0, Direction.RIGHT),
                                new ArrowData(3, 6, Direction.LEFT),
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 0, Direction.DOWN),
                                new SuperJumpData(2, 0),
                                new SuperJumpData(5, 6)
                        )
                )
        );
    }
}
