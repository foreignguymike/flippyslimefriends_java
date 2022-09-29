package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuinsData {

    public List<MapData> data;

    public RuinsData() {
        data = Arrays.asList(

                new MapData(
                        3, 3,
                        new int[]{
                                0, e, e,
                                0, e, 0,
                                0, e, 0
                        },
                        4,
                        Arrays.asList(new TilePoint(2, 0)),
                        new ArrayList<>(),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new StopPathPointData(0, 2)
                                )
                        )
                ),
                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, e,
                                0, 0, 0,
                                e, 0, 0
                        },
                        6,
                        Arrays.asList(new TilePoint(0, 0)),
                        new ArrayList<>(),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new StopPathPointData(2, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(0, 2)
                                )
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                e, e, e,
                                0, 0, 0,
                                0, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(3, 2)),
                        new ArrayList<>(),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 1),
                                        new StopPathPointData(1, 1)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, e, e, e,
                                e, e, e, 0,
                                0, 0, 0, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new SuperJumpData(1, 0),
                                new SuperJumpData(2, 3)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new StopPathPointData(1, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(2, 2)
                                )
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, e, e, 0,
                                0, 0, 0, 0, 0,
                                0, e, e, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0)),
                        new ArrayList<>(),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 1),
                                        new StopPathPointData(0, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(2, 1)
                                )
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, 0, 0, 0, 0,
                                e, e, 0, 0, e,
                                0, 0, 0, 0, e,
                                e, e, 0, 0, e
                        },
                        11,
                        Arrays.asList(new TilePoint(2, 3)),
                        new ArrayList<>(),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 4),
                                        new StopPathPointData(3, 4)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(1, 0)
                                )
                        )
                ),
                new MapData(
                        6, 5,
                        new int[]{
                                e, e, e, e, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, e, 0, 0, 0,
                                e, e, 0, 0, 0
                        },
                        14,
                        Arrays.asList(new TilePoint(4, 3)),
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.DOWN),
                                new ArrowData(3, 2, Direction.UP)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 1),
                                        new PathPointData(3, 0),
                                        new PathPointData(0, 0),
                                        new PathPointData(0, 4),
                                        new StopPathPointData(3, 4),
                                        new PathPointData(0, 4),
                                        new PathPointData(0, 0),
                                        new PathPointData(3, 0)
                                )
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, e, e,
                                0, e, 0, 0, 0,
                                0, 0, 0, e, 0,
                                e, e, 0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(2, 0)),
                        Arrays.asList(
                                new ArrowData(1, 3, Direction.LEFT),
                                new ArrowData(0, 1, Direction.RIGHT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(1, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 3),
                                        new StopPathPointData(2, 3)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, e, e, e, e
                        },
                        11,
                        Arrays.asList(new TilePoint(1, 2)),
                        Arrays.asList(
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 2),
                                        new PathPointData(0, 4),
                                        new StopPathPointData(2, 4),
                                        new PathPointData(4, 4),
                                        new StopPathPointData(4, 2),
                                        new PathPointData(4, 0),
                                        new StopPathPointData(2, 0),
                                        new PathPointData(0, 0),
                                        new PathPointData(0, 2)
                                )
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, 0, 0,
                                e, 0, 0, e,
                                e, 0, 0, e,
                                e, 0, 0, e,
                                0, 0, 0, e
                        },
                        11,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(4, 0, Direction.RIGHT),
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(0, 3, Direction.LEFT),
                                new SuperJumpData(0, 3),
                                new SuperJumpData(4, 0)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 3),
                                        new StopPathPointData(1, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(4, 0),
                                        new StopPathPointData(3, 0)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, e, e, e,
                                e, 0, e, 0, e,
                                e, e, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, e, e, e, e
                        },
                        8,
                        Arrays.asList(new TilePoint(3, 1)),
                        new ArrayList<>(),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 2),
                                        new PathPointData(4, 2),
                                        new PathPointData(4, 0),
                                        new PathPointData(2, 0),
                                        new StopPathPointData(2, 1),
                                        new PathPointData(2, 0),
                                        new PathPointData(4, 0),
                                        new PathPointData(4, 2),
                                        new PathPointData(3, 2)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 3),
                                        new PathPointData(2, 4),
                                        new PathPointData(0, 4),
                                        new PathPointData(0, 2),
                                        new StopPathPointData(1, 2),
                                        new PathPointData(0, 2),
                                        new PathPointData(0, 4),
                                        new PathPointData(2, 4),
                                        new PathPointData(2, 3)
                                )
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, e, 0, 0,
                                0, 0, 0, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.LEFT),
                                new ArrowData(2, 0, Direction.RIGHT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(1, 1)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, e, e,
                                0, 0, 0,
                                0, e, e,
                                0, 0, e
                        },
                        8,
                        Arrays.asList(new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(2, 0, Direction.DOWN),
                                new ArrowData(1, 1, Direction.RIGHT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new StopPathPointData(0, 2)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 1),
                                        new PathPointData(2, 1),
                                        new StopPathPointData(2, 2),
                                        new PathPointData(3, 2),
                                        new PathPointData(3, 1)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                e, 0, 0,
                                0, e, 0,
                                0, e, 0,
                                0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.DOWN)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 0),
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(1, 1),
                                        new PathPointData(2, 1)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                e, 0, 0,
                                0, 0, 0,
                                e, 0, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(3, 2)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(1, 2, Direction.DOWN)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new StopPathPointData(1, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 0),
                                        new StopPathPointData(3, 0)
                                )
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, e, 0, 0, e,
                                0, e, 0, e, e,
                                0, 0, 0, 0, 0
                        },
                        11,
                        Arrays.asList(new TilePoint(3, 2)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 0),
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(1, 1),
                                        new PathPointData(2, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(1, 3),
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(2, 4),
                                        new PathPointData(2, 3)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, e, e, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, e, e
                        },
                        13,
                        Arrays.asList(new TilePoint(2, 3)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.LEFT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 1),
                                        new StopPathPointData(3, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(0, 3),
                                        new StopPathPointData(0, 1)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, e, e, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e
                        },
                        14,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(0, 3)),
                        Arrays.asList(
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(1, 1),
                                        new PathPointData(1, 2)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, e, e, e, e
                        },
                        12,
                        Arrays.asList(new TilePoint(3, 1), new TilePoint(3, 3)),
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 1, Direction.DOWN),
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 2),
                                        new PathPointData(0, 4),
                                        new PathPointData(4, 4),
                                        new StopPathPointData(4, 2),
                                        new PathPointData(4, 0),
                                        new PathPointData(0, 0),
                                        new PathPointData(0, 2)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, e, e,
                                0, e, e, e,
                                0, 0, 0, 0,
                                e, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new PathPointData(1, 2),
                                        new StopPathPointData(1, 1),
                                        new PathPointData(1, 2)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, e, e, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, e, e,
                                e, e, e, e, e
                        },
                        11,
                        Arrays.asList(new TilePoint(3, 1)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.LEFT),
                                new ArrowData(2, 2, Direction.LEFT),
                                new ArrowData(1, 2, Direction.LEFT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 1),
                                        new PathPointData(0, 1),
                                        new PathPointData(0, 4),
                                        new PathPointData(3, 4),
                                        new StopPathPointData(3, 3),
                                        new PathPointData(4, 3),
                                        new PathPointData(4, 0),
                                        new PathPointData(1, 0),
                                        new PathPointData(1, 1)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(3, 1), new TilePoint(0, 1)),
                        Arrays.asList(
                                new ArrowData(0, 2, Direction.DOWN),
                                new ArrowData(0, 0, Direction.DOWN),
                                new SuperJumpData(1, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 1),
                                        new StopPathPointData(2, 1)
                                )
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, e, e, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, e, e, e
                        },
                        14,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(2, 4)),
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(1, 3, Direction.UP),
                                new ArrowData(1, 1, Direction.UP),
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 1),
                                        new StopPathPointData(3, 2),
                                        new StopPathPointData(3, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(0, 3),
                                        new StopPathPointData(0, 2),
                                        new StopPathPointData(0, 1)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, e, 0, 0,
                                0, 0, e, 0,
                                0, e, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.LEFT),
                                new ArrowData(0, 3, Direction.LEFT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new StopPathPointData(1, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(2, 2)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 0),
                                        new StopPathPointData(3, 1)
                                )
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                e, 0, e,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                e, 0, e
                        },
                        18,
                        Arrays.asList(new TilePoint(1, 0)),
                        Arrays.asList(
                                new ArrowData(4, 1, Direction.UP),
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(2, 0, Direction.UP),
                                new ArrowData(0, 1, Direction.DOWN),
                                new SuperJumpData(0, 1),
                                new SuperJumpData(4, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 1),
                                        new StopPathPointData(0, 2),
                                        new StopPathPointData(0, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(4, 1),
                                        new StopPathPointData(4, 2),
                                        new StopPathPointData(4, 0)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e,
                                e, e, e, e, e
                        },
                        8,
                        Arrays.asList(new TilePoint(1, 1), new TilePoint(1, 3)),
                        Arrays.asList(
                                new SuperJumpData(2, 2),
                                new ArrowData(2, 3, Direction.LEFT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.DOWN)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 2),
                                        new PathPointData(0, 4),
                                        new StopPathPointData(2, 4),
                                        new PathPointData(4, 4),
                                        new StopPathPointData(4, 2),
                                        new PathPointData(4, 0),
                                        new StopPathPointData(2, 0),
                                        new PathPointData(0, 0),
                                        new PathPointData(0, 2)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                e, 0, e,
                                0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(3, 2)),
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(0, 2, Direction.LEFT),
                                new ArrowData(0, 0, Direction.RIGHT),
                                new SuperJumpData(2, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(2, 0)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, e, e, 0,
                                0, e, 0, 0,
                                0, 0, 0, e
                        },
                        18,
                        Arrays.asList(new TilePoint(3, 0)),
                        Arrays.asList(
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(1, 1),
                                        new StopPathPointData(2, 1),
                                        new PathPointData(2, 2)
                                )
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, e, e, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(1, 2)),
                        Arrays.asList(
                                new ArrowData(1, 3, Direction.LEFT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new SuperJumpData(0, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(2, 3),
                                        new PathPointData(2, 2)
                                )
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, e, e, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, e, e, e
                        },
                        19,
                        Arrays.asList(new TilePoint(2, 3)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new SuperJumpData(1, 1),
                                new SuperJumpData(3, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 3),
                                        new StopPathPointData(0, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(4, 0),
                                        new StopPathPointData(4, 3)
                                )
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, e, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(1, 0), new TilePoint(1, 3)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.LEFT),
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new ArrowData(0, 2, Direction.LEFT),
                                new ArrowData(0, 1, Direction.LEFT),
                                new SuperJumpData(1, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 1),
                                        new StopPathPointData(1, 2)
                                )
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, e, e, 0,
                                0, 0, 0, 0,
                                0, e, e, 0,
                                0, 0, 0, 0
                        },
                        18,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(0, 3)),
                        Arrays.asList(
                                new ArrowData(4, 2, Direction.RIGHT),
                                new ArrowData(4, 1, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(2, 0, Direction.UP),
                                new ArrowData(0, 2, Direction.RIGHT),
                                new ArrowData(0, 1, Direction.RIGHT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(1, 1),
                                        new StopPathPointData(3, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(3, 2)
                                )
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, 0, e,
                                e, 0, 0, e,
                                e, 0, 0, e,
                                e, 0, 0, 0,
                                0, 0, 0, e
                        },
                        17,
                        Arrays.asList(new TilePoint(2, 1)),
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(2, 2),
                                new IceData(3, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(4, 0),
                                        new StopPathPointData(2, 0),
                                        new StopPathPointData(0, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 3),
                                        new StopPathPointData(1, 3)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, e, 0, 0,
                                0, e, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        14,
                        Arrays.asList(new TilePoint(2, 2), new TilePoint(3, 3)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(1, 3, Direction.UP),
                                new ArrowData(1, 2, Direction.DOWN),
                                new IceData(1, 0)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new PathPointData(1, 1),
                                        new StopPathPointData(0, 1),
                                        new PathPointData(1, 1)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, e, e,
                                0, 0, 0,
                                0, 0, 0,
                                e, e, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(1, 2)),
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(1, 0, Direction.DOWN),
                                new IceData(1, 1),
                                new IceData(2, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new StopPathPointData(0, 2)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 2),
                                        new StopPathPointData(3, 0)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, e,
                                e, e, e,
                                0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 2), new TilePoint(3, 0)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.LEFT),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new SuperJumpData(1, 0)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(2, 0),
                                        new PathPointData(1, 0)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, e,
                                0, 0, 0, e,
                                0, e, 0, 0,
                                0, 0, 0, e
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 1)),
                        Arrays.asList(
                                new SuperJumpData(1, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(1, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(1, 1),
                                        new StopPathPointData(2, 1)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, e, e, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(3, 3)),
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(2, 0, Direction.UP),
                                new ArrowData(1, 3, Direction.UP),
                                new ArrowData(1, 0, Direction.UP),
                                new IceData(0, 1),
                                new IceData(0, 2),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(1, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(1, 2)
                                )
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, e, 0, e, 0,
                                e, 0, 0, 0, e
                        },
                        17,
                        Arrays.asList(new TilePoint(2, 2)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(1, 3, Direction.LEFT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new SuperJumpData(0, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(2, 1)
                                )
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, e, e, 0,
                                0, e, 0, 0,
                                0, e, 0, 0,
                                0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(4, 3)),
                        Arrays.asList(
                                new ArrowData(4, 0, Direction.RIGHT),
                                new ArrowData(0, 3, Direction.LEFT),
                                new SuperJumpData(3, 2),
                                new SuperJumpData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 2),
                                        new StopPathPointData(3, 1),
                                        new StopPathPointData(2, 1),
                                        new PathPointData(3, 1)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(1, 1),
                                        new PathPointData(1, 2)
                                )
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                0, 0, e, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(3, 4)),
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(3, 2, Direction.RIGHT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(2, 2)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                0, 0, e, 0, 0,
                                e, 0, e, 0, e,
                                e, e, 0, e, e
                        },
                        13,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(2, 4)),
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.RIGHT)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(3, 2),
                                        new StopPathPointData(2, 2)
                                )
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                0, e, e, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, e, e, e
                        },
                        12,
                        Arrays.asList(new TilePoint(3, 0), new TilePoint(1, 3)),
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new SuperJumpData(2, 0),
                                new SuperJumpData(2, 3)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new StopPathPointData(0, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(4, 0),
                                        new StopPathPointData(4, 3)
                                )
                        )
                ),
                new MapData(
                        6, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                e, 0, e, e,
                                e, e, e, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        22,
                        Arrays.asList(new TilePoint(0, 1), new TilePoint(0, 2)),
                        Arrays.asList(
                                new ArrowData(4, 2, Direction.LEFT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new SuperJumpData(2, 1),
                                new SuperJumpData(3, 3)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 1),
                                        new StopPathPointData(2, 2)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 3),
                                        new StopPathPointData(3, 0)
                                )
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, e, e, e, 0,
                                0, 0, 0, 0, 0,
                                0, 0, e, 0, 0,
                                0, 0, e, 0, 0,
                                0, e, e, e, 0
                        },
                        31,
                        Arrays.asList(new TilePoint(2, 0), new TilePoint(2, 4)),
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.UP),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.DOWN),
                                new SuperJumpData(1, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 2),
                                        new StopPathPointData(2, 2),
                                        new StopPathPointData(3, 2)
                                )
                        )
                )

        );
    }
}
