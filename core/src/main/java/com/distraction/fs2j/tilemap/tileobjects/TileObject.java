package com.distraction.fs2j.tilemap.tileobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.distraction.fs2j.Context;
import com.distraction.fs2j.tilemap.Tile;
import com.distraction.fs2j.tilemap.TileMap;

public abstract class TileObject {

    public Context context;
    public TileMap tileMap;

    public Vector3 p = new Vector3();
    public Vector3 isop = new Vector3();
    public Vector3 pdest = new Vector3();
    public float speed = 0;

    public Tile currentTile = null;
    public int row;
    public int col;

    public float width = 0f;
    public float height = 0f;

    public boolean remove = false;

    protected TileObject(Context context, TileMap tileMap) {
        this.context = context;
        this.tileMap = tileMap;
    }

    public void setPositionFromTile(int row, int col) {
        this.row = row;
        this.col = col;
        tileMap.toPosition(row, col, p);
    }

    public void setPosition(float x, float y) {
        p.x = x;
        p.y = y;
    }

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

}
