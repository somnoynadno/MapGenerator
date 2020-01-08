package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

public class Herbivore extends Animal {

    public Herbivore() {
        super();
        unitType = UnitType.HERBIVORE;
        possibleTargets = new HashSet<UnitType>(Arrays.asList(UnitType.TREE));
    }
}
