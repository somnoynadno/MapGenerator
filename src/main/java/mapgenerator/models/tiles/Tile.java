package mapgenerator.models.tiles;

import java.io.Serializable;

public abstract class Tile implements Serializable {

    protected Integer ID;

    public Tile() {
        ID = 0;
    }

    public Integer getID() {
        return ID;
    }
}
