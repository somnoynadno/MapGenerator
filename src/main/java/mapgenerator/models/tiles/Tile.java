package mapgenerator.models.tiles;

import java.io.Serializable;

public abstract class Tile implements Serializable {

    private Integer ID;

    public Tile(){
        ID = 0;
    }

    public Integer getID() {
        return ID;
    }
}
