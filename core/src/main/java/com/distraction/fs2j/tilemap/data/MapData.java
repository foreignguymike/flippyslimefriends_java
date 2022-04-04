package com.distraction.fs2j.tilemap.data;

import java.util.ArrayList;
import java.util.List;

public class MapData {
    public int numRows;
    public int numCols;
    public int[] map;
    public int goal;
    public List<TilePoint> playerPositions;
    public List<TileObjectData> objects;
    public List<List<PathPointData>> paths;
    public boolean startBubble;

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions) {
        this(numRows, numCols, map, goal, playerPositions, new ArrayList<>(), new ArrayList<>(), false);
    }

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions,
            List<TileObjectData> objects) {
        this(numRows, numCols, map, goal, playerPositions, objects, new ArrayList<>(), false);
    }

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions,
            boolean startBubble) {
        this(numRows, numCols, map, goal, playerPositions, new ArrayList<>(), new ArrayList<>(), startBubble);
    }

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions,
            boolean startBubble,
            List<TileObjectData> objects) {
        this(numRows, numCols, map, goal, playerPositions, objects, new ArrayList<>(), startBubble);
    }

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions,
            boolean startBubble,
            List<TileObjectData> objects,
            List<List<PathPointData>> paths) {
        this(numRows, numCols, map, goal, playerPositions, objects, paths, startBubble);
    }

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions,
            List<TileObjectData> objects,
            List<List<PathPointData>> paths) {
        this(numRows, numCols, map, goal, playerPositions, objects, paths, false);
    }

    public MapData(
            int numRows,
            int numCols,
            int[] map,
            int goal,
            List<TilePoint> playerPositions,
            List<TileObjectData> objects,
            List<List<PathPointData>> paths,
            boolean startBubble) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.map = map;
        this.goal = goal;
        this.playerPositions = playerPositions;
        this.objects = objects;
        this.paths = paths;
        this.startBubble = startBubble;
    }
}
