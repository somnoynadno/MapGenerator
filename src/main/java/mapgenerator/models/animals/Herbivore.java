package mapgenerator.models.animals;

public class Herbivore extends Animal {

    private Integer ID;

    public Herbivore(){
        super();
        ID = 1;
    }

    @Override
    public Integer getID() {
        return ID;
    }
}
