package mapgenerator.models.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mapgenerator.models.Map;
import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;
import mapgenerator.models.tiles.TileType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Unit implements Serializable {

    protected Integer hunger;
    @JsonIgnore
    protected Unit target;
    @JsonIgnore
    protected Integer huntRadius = 18;
    @JsonIgnore
    protected int yearsAlive;
    @JsonIgnore
    protected Set<UnitType> possibleTargets;

    @JsonIgnore
    protected int minDefaultHunger = 60;
    @JsonIgnore
    protected int maxDefaultHunger = 120;
    @JsonIgnore
    protected int minHungerForHunt = 40;

    @JsonIgnore
    protected int ms = 1; // move speed
    @JsonIgnore
    protected double stepSleepProbability = 0.3;

    public Animal() {
        super();
        unitType = UnitType.ANIMAL;
        yearsAlive = 0;
        hunger = ThreadLocalRandom.current().nextInt(minDefaultHunger, maxDefaultHunger);
        target = null;
        possibleTargets = new HashSet<UnitType>(Arrays.asList(UnitType.ANIMAL));
    }

    public void move(Map map, Vector<Unit> units) {
        checkForDeath(units);
        tryHuntAndMove(map, units);
    }

    protected boolean hunt(Vector<Unit> units) {
        if (target == null) {
            searchForTarget(units);
            if (target == null) return false;
        }
        // hunt
        if (target.getX() > x) {
            x += ms;
        }
        if (target.getY() > y) {
            y += ms;
        }
        if (target.getX() < x) {
            x -= ms;
        }
        if (target.getY() < y) {
            y -= ms;
        }

        // kill
        if (x.equals(target.getX()) && y.equals(target.getY())) {
            System.out.println("Kill on " + x + " " + y + "!");
            hunger += target.getNutritionalValue();
            units.remove(target);
            target = null;
            return false;
        }
        return true;
    }

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

    public void checkForDeath(Vector<Unit> units) {
        hunger -= 1;
        yearsAlive += 1;
        if (hunger <= 0) {
            System.out.println("Death of hunger on " + getX() + " " + getY());
            units.remove(this);
            return;
        }
    }

    protected void tryHuntAndMove(Map map, Vector<Unit> units) {
        boolean huntResult = false;
        if (hunger < minHungerForHunt) {
            huntResult = hunt(units);
        }
        if (!huntResult) {
            double flip = Math.random();
            if (flip < stepSleepProbability) {
                // just sleep
            } else {
                goToRandomDirection(map);
            }
        }
    }

    protected void goToRandomDirection(Map map) {
        int direction = ThreadLocalRandom.current().nextInt(1, 5);
        int tempX = x;
        int tempY = y;

        switch (direction) {
            case 1:
                tempX += ms;
            case 2:
                tempX -= ms;
            case 3:
                tempY += ms;
            case 4:
                tempY -= ms;
        }
        // check constraints
        if (tempX >= 0 && tempX < map.getWidth()
                && tempY >= 0 && tempY < map.getHeight()) {   // check for borders
            if (map.getTiles().get(y).get(x).getTileType() != TileType.WATER) { // check for water
                x = tempX;
                y = tempY;
            }
        } // else just exit
    }

    public Integer getHunger() {
        return hunger;
    }
}

