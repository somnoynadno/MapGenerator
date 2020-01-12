package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

abstract public class Predator extends Animal {

    public Predator() {
        super();
        unitType = UnitType.PREDATOR;
        possibleTargets = new HashSet<>(Arrays.asList(UnitType.HERBIVORE));
    }
}
