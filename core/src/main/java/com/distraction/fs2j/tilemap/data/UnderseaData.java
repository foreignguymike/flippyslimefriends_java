package com.distraction.fs2j.tilemap.data;

import static com.distraction.fs2j.tilemap.data.GameData.b;
import static com.distraction.fs2j.tilemap.data.GameData.e;
import static com.distraction.fs2j.tilemap.data.GameData.g;

import java.util.Arrays;
import java.util.List;

public class UnderseaData {

    public List<MapData> data;

    public UnderseaData() {
        data = Arrays.asList(

                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, 0,
                                e, e, e,
                                0, 0, 0
                        },
                        5,
                        Arrays.asList(new TilePoint(0, 0)),
                        Arrays.asList(new BubbleData(0, 2))
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.UP),
                                new ArrowData(1, 1, Direction.DOWN)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                e, e, 0,
                                e, 0, 0,
                                0, 0, 0,
                                0, 0, e,
                                0, e, e
                        },
                        9,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 0, Direction.UP),
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(1, 2, Direction.UP),
                                new BubbleData(0, 2),
                                new BubbleData(1, 1),
                                new BubbleData(2, 0)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0,
                                0, e, 0,
                                0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, e, 0, 0,
                                0, 0, e, 0, 0,
                                0, e, e, 0, 0,
                                e, e, 0, 0, e
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new BubbleData(1, 3)
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
                        16,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.DOWN),
                                new ArrowData(1, 1, Direction.DOWN)
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
                        16,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.DOWN),
                                new ArrowData(2, 0, Direction.DOWN),
                                new ArrowData(1, 2, Direction.UP),
                                new ArrowData(1, 0, Direction.UP)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, 0, e,
                                0, 0, 0, e,
                                0, 0, e, 0,
                                e, 0, 0, 0,
                                e, e, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(2, 1)),
                        Arrays.asList(
                                new BubbleData(1, 2)
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
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(2, 0, Direction.UP),
                                new SuperJumpData(2, 1)
                        )
                ),
                new MapData(
                        3, 3,
                        new int[]{
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new BubbleData(0, 2)
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, 0,
                                0, e, e,
                                0, 0, 0,
                                0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(2, 0, Direction.UP),
                                new BubbleData(0, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new StopPathPointData(1, 2)
                                )
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, e, e,
                                0, 0, 0,
                                0, 0, 0,
                                0, e, e
                        },
                        8,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 0, Direction.UP),
                                new ArrowData(0, 0, Direction.DOWN),
                                new SuperJumpData(0, 0),
                                new SuperJumpData(3, 0)
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new BubbleData(0, 1),
                                new SuperJumpData(0, 2),
                                new SuperJumpData(1, 1),
                                new BubbleData(2, 1),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        4, 3,
                        new int[]{
                                0, 0, e,
                                0, 0, e,
                                0, 0, e,
                                0, 0, 0
                        },
                        9,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new SuperJumpData(1, 0),
                                new SuperJumpData(1, 1),
                                new SuperJumpData(2, 0),
                                new SuperJumpData(2, 1),
                                new BubbleData(3, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 2),
                                        new StopPathPointData(0, 2)
                                )
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, e, e,
                                0, 0, 0, 0, 0,
                                e, e, 0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.RIGHT),
                                new ArrowData(1, 3, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new ArrowData(0, 1, Direction.LEFT),
                                new BubbleData(0, 0),
                                new BubbleData(2, 4)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e,
                                0, 0, 0, 0
                        },
                        14,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.LEFT),
                                new ArrowData(3, 0, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.UP),
                                new ArrowData(1, 1, Direction.DOWN),
                                new SuperJumpData(1, 0),
                                new SuperJumpData(1, 3)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, e, e, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, e, e, 0
                        },
                        10,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 0),
                                        new StopPathPointData(0, 3)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 3),
                                        new StopPathPointData(3, 0)
                                )
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new SuperJumpData(0, 1),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, e, e,
                                0, 0, e, e,
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new IceData(2, 1),
                                new IceData(2, 2),
                                new IceData(3, 1),
                                new IceData(3, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(0, 1),
                                        new PathPointData(0, 2),
                                        new StopPathPointData(1, 2),
                                        new PathPointData(1, 3),
                                        new StopPathPointData(2, 3),
                                        new PathPointData(0, 3),
                                        new PathPointData(0, 1)
                                )
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
                        16,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT)
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
                        18,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.UP),
                                new ArrowData(1, 1, Direction.DOWN),
                                new SuperJumpData(1, 1),
                                new SuperJumpData(2, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.UP),
                                new ArrowData(2, 3, Direction.LEFT),
                                new SuperJumpData(1, 1)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, b, 0, 0,
                                0, 0, 0, e
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, e, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, e
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 1),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(1, 0),
                                        new PathPointData(0, 0),
                                        new StopPathPointData(0, 1),
                                        new PathPointData(0, 0)
                                )
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
                        16,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(1, 3, Direction.UP),
                                new ArrowData(0, 2, Direction.RIGHT),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new BubbleData(0, 3),
                                new IceData(1, 2),
                                new IceData(2, 1)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, e,
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                e, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, g, 0, 0,
                                e, 0, 0, 0, e
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.LEFT),
                                new ArrowData(1, 1, Direction.UP),
                                new ArrowData(0, 2, Direction.RIGHT),
                                new BubbleData(1, 2)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, e,
                                e, 0, 0, 0,
                                0, 0, 0, e,
                                e, 0, 0, e
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new IceData(1, 2),
                                new IceData(2, 1)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(2, 0),
                                        new StopPathPointData(1, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(1, 3),
                                        new StopPathPointData(2, 3)
                                )
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 3, Direction.LEFT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 2)
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
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new ArrowData(0, 2, Direction.LEFT),
                                new IceData(1, 0),
                                new IceData(1, 3),
                                new IceData(2, 0),
                                new IceData(2, 3)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(0, 1), new StopPathPointData(0, 0)),
                                Arrays.asList(new StopPathPointData(3, 2), new StopPathPointData(3, 3))
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                e, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                e, 0, 0, 0
                        },
                        14,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
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
                        5, 5,
                        new int[]{
                                e, 0, 0, e, e,
                                0, 0, 0, 0, e,
                                0, 0, e, 0, 0,
                                0, 0, 0, 0, e,
                                e, 0, 0, e, e
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new BubbleData(2, 4)
                        )
                ),
                new MapData(
                        4, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, e,
                                e, 0, 0, 0, e
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(1, 3, Direction.DOWN),
                                new ArrowData(1, 1, Direction.DOWN),
                                new IceData(0, 1),
                                new IceData(0, 3),
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 2),
                                new IceData(3, 2)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, e, 0, e,
                                e, 0, 0, 0, 0,
                                e, 0, 0, 0, e,
                                0, 0, 0, 0, e,
                                e, 0, e, e, e
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.UP),
                                new ArrowData(2, 3, Direction.LEFT),
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(3, 0), new StopPathPointData(1, 0)),
                                Arrays.asList(new StopPathPointData(0, 3), new StopPathPointData(0, 1)),
                                Arrays.asList(new StopPathPointData(4, 1), new StopPathPointData(4, 3)),
                                Arrays.asList(new StopPathPointData(1, 4), new StopPathPointData(3, 4))
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                e, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, e
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 1, Direction.LEFT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 1, Direction.LEFT)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, 0, e, e,
                                e, 0, 0, 0, e,
                                0, 0, g, 0, 0,
                                e, 0, 0, 0, e,
                                e, e, 0, e, e
                        },
                        19,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.DOWN),
                                new ArrowData(2, 1, Direction.DOWN),
                                new ArrowData(1, 2, Direction.RIGHT),
                                new ArrowData(3, 2, Direction.RIGHT),
                                new BubbleData(2, 2)
                        )
                ),
                new MapData(
                        5, 3,
                        new int[]{
                                e, 0, 0,
                                0, 0, 0,
                                0, e, 0,
                                0, e, 0,
                                e, 0, 0
                        },
                        11,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 0, Direction.UP),
                                new ArrowData(1, 0, Direction.DOWN),
                                new IceData(1, 2),
                                new SuperJumpData(2, 2),
                                new IceData(3, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(1, 1), new StopPathPointData(3, 1))
                        )
                ),
                new MapData(
                        3, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        12,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.LEFT),
                                new ArrowData(2, 1, Direction.LEFT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new ArrowData(1, 1, Direction.LEFT),
                                new ArrowData(0, 2, Direction.LEFT),
                                new ArrowData(0, 1, Direction.LEFT)
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, 0, e,
                                0, 0, 0, 0, 0,
                                e, 0, 0, 0, 0
                        },
                        13,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(0, 3, Direction.DOWN),
                                new ArrowData(0, 2, Direction.LEFT),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3)
                        )
                ),
                new MapData(
                        5, 4,
                        new int[]{
                                e, 0, 0, e,
                                e, 0, 0, e,
                                e, 0, 0, e,
                                0, 0, 0, 0,
                                e, 0, 0, e
                        },
                        14,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.UP),
                                new ArrowData(3, 1, Direction.UP),
                                new ArrowData(1, 2, Direction.DOWN),
                                new ArrowData(1, 1, Direction.DOWN),
                                new IceData(2, 1),
                                new IceData(2, 2)
                        ),
                        Arrays.asList(
                                Arrays.asList(
                                        new StopPathPointData(3, 0),
                                        new StopPathPointData(2, 0),
                                        new StopPathPointData(1, 0),
                                        new StopPathPointData(2, 0)
                                ),
                                Arrays.asList(
                                        new StopPathPointData(3, 3),
                                        new StopPathPointData(2, 3),
                                        new StopPathPointData(1, 3),
                                        new StopPathPointData(2, 3)
                                )
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, 0,
                                0, 0, 0, 0,
                                0, 0, e, 0,
                                0, 0, 0, 0
                        },
                        15,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 2, Direction.RIGHT),
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(1, 2, Direction.LEFT),
                                new ArrowData(1, 0, Direction.DOWN),
                                new ArrowData(0, 1, Direction.RIGHT),
                                new IceData(1, 1),
                                new SuperJumpData(1, 2),
                                new SuperJumpData(2, 1)
                        )
                ),
                new MapData(
                        4, 4,
                        new int[]{
                                0, 0, 0, e,
                                0, 0, 0, e,
                                0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        16,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(2, 0, Direction.RIGHT),
                                new ArrowData(1, 0, Direction.UP),
                                new ArrowData(0, 2, Direction.LEFT),
                                new IceData(1, 1),
                                new SuperJumpData(3, 1)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                0, e, e, e, e,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                e, e, e, e, 0
                        },
                        17,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.RIGHT),
                                new ArrowData(3, 0, Direction.UP),
                                new ArrowData(2, 2, Direction.RIGHT),
                                new ArrowData(1, 4, Direction.DOWN),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new IceData(2, 1),
                                new IceData(2, 3)
                        ),
                        Arrays.asList(
                                Arrays.asList(new StopPathPointData(0, 0), new StopPathPointData(0, 4)),
                                Arrays.asList(new StopPathPointData(4, 4), new StopPathPointData(4, 0))
                        )
                ),
                new MapData(
                        3, 5,
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0
                        },
                        19,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(2, 3, Direction.UP),
                                new ArrowData(2, 1, Direction.UP),
                                new ArrowData(0, 3, Direction.DOWN),
                                new ArrowData(0, 1, Direction.DOWN),
                                new IceData(1, 1),
                                new IceData(1, 2),
                                new IceData(1, 3)
                        )
                ),
                new MapData(
                        5, 5,
                        new int[]{
                                e, e, e, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, e, e, e
                        },
                        21,
                        Arrays.asList(new TilePoint(0, 0), new TilePoint(1, 0)),
                        true,
                        Arrays.asList(
                                new ArrowData(3, 3, Direction.LEFT),
                                new ArrowData(3, 1, Direction.RIGHT),
                                new ArrowData(3, 0, Direction.DOWN),
                                new ArrowData(2, 4, Direction.UP),
                                new ArrowData(2, 3, Direction.LEFT),
                                new ArrowData(2, 1, Direction.RIGHT),
                                new ArrowData(2, 0, Direction.DOWN),
                                new ArrowData(1, 4, Direction.UP),
                                new ArrowData(1, 3, Direction.LEFT),
                                new ArrowData(1, 1, Direction.RIGHT),
                                new IceData(2, 2)
                        )
                )
        );
    }

}
