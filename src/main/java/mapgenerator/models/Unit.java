package mapgenerator.models;

import java.io.Serializable;

public class Unit implements Serializable {
    private Integer x;
    private Integer y;

    public Unit(){

    }

    public void move(Map map){

    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
