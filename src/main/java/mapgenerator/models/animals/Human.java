package mapgenerator.models.animals;

import mapgenerator.models.House;
import mapgenerator.models.Map;
import mapgenerator.models.Unit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Human extends Animal {

    private House house;

    public Human() {
        super();
        ID = 3;
        house = null;
        possibleTargetIDs = new HashSet<Integer>(Arrays.asList(1, 2, 10));
    }

    @Override
    public void searchForTarget(Vector<Unit> units) {
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if (possibleTargetIDs.contains(unit.getID())
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
        tryHuntAndMove(map, units);
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
                }
            }
        }
    }

    public House getHouse() {
        return house;
    }
}
