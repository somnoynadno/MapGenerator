package mapgenerator.models.animals;

import mapgenerator.models.Unit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Herbivore extends Animal {

    public Herbivore(){
        super();
        ID = 1;
        possibleTargetIDs = new HashSet<Integer>(Arrays.asList(10));
    }

    @Override
    public void searchForTarget(Vector<Unit> units){
        for (int i = 0; i < units.size(); i++){
            Unit unit = units.get(i);
            if (possibleTargetIDs.contains(unit.getID())
                    && Math.abs(unit.getX() - x) <= huntRadius
                    && Math.abs(unit.getY() - y) <= huntRadius
                    ){
                target = unit;
                break;
            }
        }
    }
}
