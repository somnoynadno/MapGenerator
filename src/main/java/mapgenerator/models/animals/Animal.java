package mapgenerator.models.animals;

import mapgenerator.models.Map;
import mapgenerator.models.Unit;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Unit implements Serializable {

    protected Integer hunger;
    protected Unit target;
    protected final Integer huntRadius = 18;
    protected Set<Integer> possibleTargetIDs;

    public Animal(){
        super();
        ID = 0;
        hunger = ThreadLocalRandom.current().nextInt(30, 70);
        target = null;
        possibleTargetIDs = new HashSet<Integer>(Arrays.asList(10));
    }

    public void move(Map map, Vector<Unit> units){
        hunger -= 1;
        checkForDeath(units);

        boolean huntResult = false;
        if (hunger < 40) {
            huntResult = hunt(units);
        }
        if (!huntResult) {
            double flip = Math.random();
            if (flip < 0.3){
                return; // just sleep
            } else {
                int direction = ThreadLocalRandom.current().nextInt(1, 5);
                int tempX = x;
                int tempY = y;

                switch (direction){
                    case 1:
                        tempX += 1;
                    case 2:
                        tempX -= 1;
                    case 3:
                        tempY += 1;
                    case 4:
                        tempY -= 1;
                }
                // check constraints
                if (tempX >= 0 && tempX < map.getWidth()
                        && tempY >= 0 && tempY < map.getHeight()){   // check for borders
                    if (map.getTiles().get(y).get(x).getID() != 3) { // check for water
                        x = tempX;
                        y = tempY;
                    }
                } // else just exit
            }
        }
    }

    protected boolean hunt(Vector<Unit> units){
        if (target == null){
            searchForTarget(units);
            if (target == null) return false;
        }
        // hunt
        if (target.getX() > x){
            x += 1;
        }
        if (target.getY() > y){
            y += 1;
        }
        if (target.getX() < x){
            x -= 1;
        }
        if (target.getY() < y){
            y -= 1;
        }

        // kill
        if (x.equals(target.getX()) && y.equals(target.getY())){
            System.out.println("Kill on " + x + " " + y + "!");
            hunger += 35;
            units.remove(target);
            target = null;
            return false;
        }
        return true;
    }

    public void searchForTarget(Vector<Unit> units){
        for (int i = 0; i < units.size(); i++){
            Unit unit = units.get(i);
            if (possibleTargetIDs.contains(unit.getID())
                    && unit != this
                    && Math.abs(unit.getX() - x) <= huntRadius
                    && Math.abs(unit.getY() - y) <= huntRadius){
                target = unit;
                break;
            }
        }
    }

    public void checkForDeath(Vector<Unit> units){
        if (hunger <= 0){
            System.out.println("Death of hunger on " + getX() + " " + getY());
            units.remove(this);
            return;
        }
    }

    public Integer getHunger() {
        return hunger;
    }
}

