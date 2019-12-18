package mapgenerator.models.animals;

import mapgenerator.models.Unit;

import java.io.Serializable;

public abstract class Animal extends Unit implements Serializable {

    private Integer hunger;
    private Integer ID;

    public Animal(){
        super();
        hunger = 10;
        ID = 0;
    }

    public void hunt(){

    }

    public Integer getHunger() {
        return hunger;
    }

    public Integer getID() {
        return ID;
    }
}
