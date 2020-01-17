package mapgenerator.models.herbs;

import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;

public abstract class Herb extends Unit {
    public Herb() {
        super();
        unitType = UnitType.HERB;
        nutritionalValue = 10;
    }
}
