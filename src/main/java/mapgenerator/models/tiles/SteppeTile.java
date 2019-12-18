package mapgenerator.models.tiles;

public class SteppeTile extends Tile {

    private Integer ID;

    public SteppeTile(){
        super();
        ID = 1;
    }

    @Override
    public Integer getID() {
        return ID;
    }
}
