package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

public class Giraffe extends Herbivore {

    public Giraffe(){
        super();
        unitType = UnitType.GIRAFFE;

        huntRadius = 24;
    }
}
