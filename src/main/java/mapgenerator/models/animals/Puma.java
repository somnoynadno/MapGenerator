package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

public class Puma extends Predator {

    public Puma(){
        unitType = UnitType.PUMA;
        possibleTargets = new HashSet<>(Arrays.asList(
                UnitType.ZEBRA,
                UnitType.RABBIT,
                UnitType.FOX,
                UnitType.GIRAFFE
        ));
    }
}
