package com.distraction.fs2j.tilemap.data;

public class StopPathPointData extends PathPointData {
    public StopPathPointData(int row, int col) {
        super(row, col, 2f);
    }
    public StopPathPointData(int row, int col, float time) {
        super(row, col, time);
    }
}
