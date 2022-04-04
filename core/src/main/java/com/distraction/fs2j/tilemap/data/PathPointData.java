package com.distraction.fs2j.tilemap.data;

public class PathPointData {
    public int row;
    public int col;
    public float time;

    public PathPointData(int row, int col) {
        this(row, col, 0f);
    }

    public PathPointData(int row, int col, float time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
