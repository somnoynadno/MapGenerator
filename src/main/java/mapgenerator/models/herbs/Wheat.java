package mapgenerator.models.herbs;

import mapgenerator.models.UnitType;

public class Wheat extends Herb {
    public Wheat() {
        super();
        unitType = UnitType.WHEAT;
        nutritionalValue = 20;
    }
}
