package mapgenerator.models.tiles;

public class GreenTile extends Tile {

    private Integer ID;

    public GreenTile(){
        super();
        ID = 2;
    }

    @Override
    public Integer getID() {
        return ID;
    }
}
