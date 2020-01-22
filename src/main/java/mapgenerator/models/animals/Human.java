package mapgenerator.models.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mapgenerator.models.houses.House;
import mapgenerator.models.Map;
import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Human extends Animal {

    @JsonIgnore
    private House house;

    public Human() {
        super();
        house = null;
        partner = null;
        unitType = UnitType.HUMAN;
        possibleTargets = new HashSet<>(Arrays.asList(
                UnitType.TREE,
                UnitType.FOX,
                UnitType.BEAR,
                UnitType.GIRAFFE,
                UnitType.PUMA,
                UnitType.ZEBRA,
                UnitType.RABBIT
        ));
    }

    @Override
    public void searchForTarget(Vector<Unit> units) {
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if (possibleTargets.contains(unit.getUnitType())
                    && unit != this
                    && Math.abs(unit.getX() - x) <= huntRadius
                    && Math.abs(unit.getY() - y) <= huntRadius) {
                target = unit;
                break;
            }
        }
    }

    @Override
    public void move(Map map, Vector<Unit> units) {
        checkForDeath(units);
        tryBuildHouse(map);
        tryMakeChild(units);
        tryHuntAndMove(map, units);
    }

    @Override
    protected void tryHuntAndMove(Map map, Vector<Unit> units) {
        boolean huntResult = false;
        if (hunger < 80) {
            huntResult = hunt(units);
        }
        if (!huntResult) {
            double flip = Math.random();
            if (flip < 0.3) {
                if (partner == null && house != null)
                    searchForPartner(units);
            } else {
                goToRandomDirection(map);
            }
        }
    }

    private void tryBuildHouse(Map map) {
        if (house == null) {
            double flip = Math.random();
            if (flip < (float) yearsAlive / 2000) {
                boolean freeSpace = true;
                for (House house : map.getHouses()) {
                    if (house.checkUnitInHouse(this)) {
                        freeSpace = false;
                        break;
                    }
                }
                if (freeSpace) {
                    // build house
                    House h = new House(x, y);
                    map.addHouse(h);
                    house = h;

                    System.out.println("House built on " + x + " " + y);
                }
            }
        }
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

}
