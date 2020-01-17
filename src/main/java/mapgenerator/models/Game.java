package mapgenerator.models;

import mapgenerator.models.animals.*;
import mapgenerator.models.tiles.TileType;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Serializable {
    private Map map;
    private Integer animalNum;
    private Vector<Unit> units;

    public Game() {
        map = new Map(180, 180);
        animalNum = map.getHeight() * 4;
        units = new Vector<Unit>();
    }

    public void run() {
        spawnAnimals();
        System.out.println("Game started");
        for (int seconds = 0; seconds != 1000; seconds++) {
            System.out.println(seconds + " seconds");
            System.out.println(units.size() + " units left");

            for (int i = 0; i < units.size(); i++) {
                units.get(i).move(map, units);
            }

            spawnTree();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void spawnAnimals() {
        for (int i = 0; i < animalNum; i++) {
            double flip = Math.random();
            if (flip < 0.4) {
                Tree u = new Tree();
                giveCoordinates(u);
                units.add(u);
            } else if (flip < 0.54) {
                Rabbit u = new Rabbit();
                giveCoordinates(u);
                units.add(u);
            } else if (flip < 0.65) {
                Zebra u = new Zebra();
                giveCoordinates(u);
                units.add(u);
            } else if (flip < 0.76) {
                Giraffe u = new Giraffe();
                giveCoordinates(u);
                units.add(u);
            } else if (flip < 0.86) {
                Fox u = new Fox();
                giveCoordinates(u);
                units.add(u);
            } else if (flip < 0.92) {
                Puma u = new Puma();
                giveCoordinates(u);
                units.add(u);
            } else if (flip < 0.96) {
                Bear u = new Bear();
                giveCoordinates(u);
                units.add(u);
            } else {
                Human u = new Human();
                giveCoordinates(u);
                units.add(u);
            }
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

    public void spawnTree() {
        double flip = Math.random();
        if (flip < 0.7) {
            Tree u = new Tree();
            giveCoordinates(u);
            units.add(u);
        }
    }

    public Map getMap() {
        return map;
    }

    public Integer getAnimalNum() {
        return animalNum;
    }

    public Vector<Unit> getUnits() {
        return units;
    }
}
