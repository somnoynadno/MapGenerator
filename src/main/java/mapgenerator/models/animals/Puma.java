package mapgenerator.models.animals;

import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;

public class Puma extends Predator {

    public Puma(){
        unitType = UnitType.PUMA;
        ms = 2;
        possibleTargets = new HashSet<>(Arrays.asList(
                UnitType.ZEBRA,
                UnitType.RABBIT,
                UnitType.FOX,
                UnitType.GIRAFFE
        ));

        nutritionalValue = 50;
    }
}
