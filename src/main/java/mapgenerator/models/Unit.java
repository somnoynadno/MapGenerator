package mapgenerator.models;

import java.io.Serializable;
import java.util.Vector;

public class Unit implements Serializable {
    protected Integer x;
    protected Integer y;
    protected UnitType unitType;

    public Unit() {
        unitType = UnitType.UNIT;
    }

    public void move(Map map, Vector<Unit> units) {

    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
