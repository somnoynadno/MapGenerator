package mapgenerator.models.animals;

public class Human extends Animal {

    private Integer ID;

    public Human(){
        super();
        ID = 3;
    }

    @Override
    public Integer getID() {
        return ID;
    }
}
