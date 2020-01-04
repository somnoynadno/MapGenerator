package mapgenerator.models;

import java.io.Serializable;
import java.util.Vector;

public class Unit implements Serializable {
    protected Integer x;
    protected Integer y;
    protected Integer ID;

    public Unit() {

    }

    public void move(Map map, Vector<Unit> units) {

    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getID() {
        return ID;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
