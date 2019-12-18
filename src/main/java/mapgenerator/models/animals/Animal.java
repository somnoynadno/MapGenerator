package mapgenerator.models.animals;

import mapgenerator.models.Unit;

import java.io.Serializable;

public abstract class Animal extends Unit implements Serializable {

    private Integer hunger;

    public Animal(){
        hunger = 10;
    }

    public void hunt(){

    }
}
