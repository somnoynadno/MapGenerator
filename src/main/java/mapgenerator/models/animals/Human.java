package mapgenerator.models.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mapgenerator.models.House;
import mapgenerator.models.Map;
import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Human extends Animal {

    // TODO: add go home method
    @JsonIgnore
    private House house;
    @JsonIgnore
    private Human partner;
    // TODO: add child
    @JsonIgnore
    private Human child;

    @JsonIgnore
    private int partnerSearchRadius = 10;

    public Human() {
        super();
        house = null;
        partner = null;
        unitType = UnitType.HUMAN;
        hunger = ThreadLocalRandom.current().nextInt(80, 100);
        possibleTargets = new HashSet<>(Arrays.asList(
                UnitType.TREE,
                UnitType.HERBIVORE,
                UnitType.PREDATOR
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
                }
            }
        }
    }

    protected void searchForPartner(Vector<Unit> units) {
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if (unit.getUnitType() == UnitType.HUMAN
                    && unit != this
                    && Math.abs(unit.getX() - x) <= partnerSearchRadius
                    && Math.abs(unit.getY() - y) <= partnerSearchRadius) {
                if (((Human) unit).getPartner() == null) {
                    partner = (Human) unit;
                    if (partner.getHouse() == null) {
                        partner.setHouse(house);
                    }
                    partner.setPartner(this);
                    break;
                }
            }
        }
    }

    public House getHouse() {
        return house;
    }

    public Human getPartner() {
        return partner;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setPartner(Human partner) {
        this.partner = partner;
    }
}
