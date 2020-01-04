package mapgenerator.models;

public class House extends Unit {
    public House() {
        super();
        ID = 20;
    }

    public House(int X, int Y) {
        super();
        ID = 20;
        x = X;
        y = Y;
    }

    public boolean checkUnitInHouse(Unit unit) {
        boolean check = false;
        if (Math.abs(x - unit.x) <= 1) {
            if (Math.abs(y - unit.y) <= 1) {
                check = true;
            }
        }
        return check;
    }
}
