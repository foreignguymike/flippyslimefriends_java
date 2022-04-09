package com.distraction.fs2j.tilemap.data;

public enum Direction {
    RIGHT,
    UP,
    LEFT,
    DOWN;

    public Direction opposite() {
        if (this == UP) return DOWN;
        if (this == DOWN) return UP;
        if (this == LEFT) return RIGHT;
        if (this == RIGHT) return LEFT;
        throw new IllegalStateException("Invalid direction " + this);
    }
}
