package com.distraction.fs2j.tilemap.data;

public class TeleportData extends TileObjectData {
    public int destRow;
    public int destCol;

    public TeleportData(int row, int col, int destRow, int destCol) {
        super(row, col);
        this.destRow = destRow;
        this.destCol = destCol;
    }
}
