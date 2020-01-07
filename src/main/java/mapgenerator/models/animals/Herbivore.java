package mapgenerator.models.animals;

import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Herbivore extends Animal {

    public Herbivore() {
        super();
        unitType = UnitType.HERBIVORE;
        possibleTargets = new HashSet<UnitType>(Arrays.asList(UnitType.TREE));
    }

    @Override
    public void searchForTarget(Vector<Unit> units) {
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if (possibleTargets.contains(unit.getUnitType())
                    && Math.abs(unit.getX() - x) <= huntRadius
                    && Math.abs(unit.getY() - y) <= huntRadius
            ) {
                target = unit;
                break;
            }
        }
    }
}
