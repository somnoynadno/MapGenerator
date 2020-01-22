package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

public class Rabbit extends Herbivore {

    public Rabbit(){
        stepSleepProbability = 0.1;
        huntRadius = 8;
        unitType = UnitType.RABBIT;
        nutritionalValue = 15;
        possibleTargets = new HashSet<>(Arrays.asList(UnitType.GRASS, UnitType.WHEAT));
    }
}
