package mapgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mapgenerator.models.animals.*;
import mapgenerator.models.herbs.Grass;
import mapgenerator.models.herbs.Tree;
import mapgenerator.models.houses.House;
import mapgenerator.models.tiles.TileType;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Serializable {
    private Map map;
    private Integer defaultUnitsNum;
    private Vector<Unit> units;

    @JsonIgnore
    private int populationControlThreshold = 1400;

    public Game() {
        map = new Map(180, 180);
        defaultUnitsNum = map.getHeight() * 4;
        units = new Vector<>();
    }

    public void run() {
        for (int i = 0; i < defaultUnitsNum; i++) {
            spawnRandomUnit();
        }
        addExtraGreen();

        System.out.println("Game started");
        for (int seconds = 0; seconds != 1000; seconds++) {
            System.out.println(seconds + " seconds");
            System.out.println(units.size() + " units left");

            for (int i = 0; i < units.size(); i++) {
                units.get(i).move(map, units);
            }

            for (House house : map.getHouses()){
                house.move(map, units);
            }

            // population control
            if (units.size() < populationControlThreshold) {
                spawnRandomUnit();
                spawnHerb();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void spawnRandomUnit() {
        double flip = Math.random();
        if (flip < 0.3) {
            Grass u = new Grass();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.43) {
            Tree u = new Tree();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.62) {
            Rabbit u = new Rabbit();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.72) {
            Zebra u = new Zebra();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.83) {
            Giraffe u = new Giraffe();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.91) {
            Fox u = new Fox();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.97) {
            Puma u = new Puma();
            giveCoordinates(u);
            units.add(u);
        } else if (flip < 0.99) {
            Bear u = new Bear();
            giveCoordinates(u);
            units.add(u);
        } else {
            Human u = new Human();
            giveCoordinates(u);
            units.add(u);
        }
    }

    private void addExtraGreen() {
        for (int i = 0; i < defaultUnitsNum *2; i++) {
            Grass u = new Grass();
            giveCoordinates(u);
            units.add(u);
        }
    }

    private void giveCoordinates(Unit unit) {
        boolean flag = true;
        while (flag) {
            int x = ThreadLocalRandom.current().nextInt(0, map.getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, map.getHeight());

            // check for water tile
            if (map.getTiles().get(y).get(x).getTileType() == TileType.WATER) continue;

            boolean overlap = false;
            for (Unit a : units) {
                if (x == a.getX() && y == a.getY()) {
                    overlap = true;
                    break;
                }
            }

            if (!overlap) {
                unit.setX(x);
                unit.setY(y);
                flag = false;
            }
        }
    }

    public void spawnHerb() {
        double flip = Math.random();
        // depends on temperature
        if ((double)map.getTemperature()/100 < flip) {
            flip = Math.random();
            if (flip < 0.3) {
                Tree u = new Tree();
                giveCoordinates(u);
                units.add(u);
            } else {
                Grass u = new Grass();
                giveCoordinates(u);
                units.add(u);
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public Integer getDefaultUnitsNum() {
        return defaultUnitsNum;
    }

    public Vector<Unit> getUnits() {
        return units;
    }
}
