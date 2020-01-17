package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

public class Rabbit extends Herbivore {

    public Rabbit(){
        stepSleepProbability = 0.1;
        huntRadius = 14;
        unitType = UnitType.RABBIT;
    }
}
