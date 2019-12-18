package mapgenerator.models.animals;

public class Predator extends Animal {

    private Integer ID;

    public Predator(){
        super();
        ID = 2;
    }

    @Override
    public Integer getID() {
        return ID;
    }
}
