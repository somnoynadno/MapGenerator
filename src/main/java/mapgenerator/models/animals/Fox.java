package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

public class Fox extends Predator {

    public Fox(){
        super();
        unitType = UnitType.FOX;
        possibleTargets = new HashSet<>(Arrays.asList(
                UnitType.ZEBRA,
                UnitType.RABBIT
        ));
    }
}
