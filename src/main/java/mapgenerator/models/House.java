package mapgenerator.models;

public class House extends Unit {
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
        if (Math.abs(x - unit.x) <= 3) {
            if (Math.abs(y - unit.y) <= 3) {
                check = true;
            }
        }
        return check;
    }
}
