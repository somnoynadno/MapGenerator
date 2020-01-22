package mapgenerator.models.houses;

import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;

public class House extends Unit {

    protected Integer houseSize = 3;

    public House() {
        super();
        unitType = UnitType.HOUSE;
    }

    public House(int X, int Y) {
        super();
        unitType = UnitType.HOUSE;

        x = X;
        y = Y;
    }

    public boolean checkUnitInHouse(Unit unit) {
        boolean check = false;
        if (Math.abs(x - unit.getX()) <= houseSize) {
            if (Math.abs(y - unit.getY()) <= houseSize) {
                check = true;
            }
        }
        return check;
    }

    public Integer getHouseSize() {
        return houseSize;
    }
}
