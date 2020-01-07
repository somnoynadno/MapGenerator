package mapgenerator.models.tiles;

import java.io.Serializable;

public abstract class Tile implements Serializable {

    protected TileType tileType;

    public Tile() {
        tileType = TileType.TILE;
    }

    public TileType getTileType() {
        return tileType;
    }
}
