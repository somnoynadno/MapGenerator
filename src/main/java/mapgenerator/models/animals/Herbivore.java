package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

abstract public class Herbivore extends Animal {

    public Herbivore() {
        super();
        unitType = UnitType.HERBIVORE;
        possibleTargets = new HashSet<>(Arrays.asList(UnitType.TREE));
    }
}
