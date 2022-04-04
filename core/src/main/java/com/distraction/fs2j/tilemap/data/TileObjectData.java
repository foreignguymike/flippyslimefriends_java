package com.distraction.fs2j.tilemap.data;

public abstract class TileObjectData {
    public int row;
    public int col;

    protected TileObjectData(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
