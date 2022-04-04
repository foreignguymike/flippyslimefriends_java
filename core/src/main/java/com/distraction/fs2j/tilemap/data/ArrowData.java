package com.distraction.fs2j.tilemap.data;

public class ArrowData extends TileObjectData {

    public Direction direction;

    public ArrowData(int row, int col, Direction direction) {
        super(row, col);
        this.direction = direction;
    }

}
