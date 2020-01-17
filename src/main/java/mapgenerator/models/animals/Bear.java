package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

public class Bear extends Predator {

    public Bear() {
        super();
        unitType = UnitType.BEAR;
        possibleTargets = new HashSet<>(Arrays.asList(
                UnitType.FOX,
                UnitType.ZEBRA,
                UnitType.PUMA,
                UnitType.GIRAFFE
        ));

        huntRadius = 22;
        hungerIncreaseValue = 45;
    }
}
