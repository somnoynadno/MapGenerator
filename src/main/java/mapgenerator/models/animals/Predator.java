package mapgenerator.models.animals;

import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Predator extends Animal {

    public Predator() {
        super();
        unitType = UnitType.PREDATOR;
        possibleTargets = new HashSet<UnitType>(Arrays.asList(UnitType.HERBIVORE));
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
}
