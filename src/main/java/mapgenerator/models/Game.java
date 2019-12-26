package mapgenerator.models;

import mapgenerator.models.animals.Animal;
import mapgenerator.models.animals.Herbivore;
import mapgenerator.models.animals.Human;
import mapgenerator.models.animals.Predator;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Serializable {
    private Map map;
    private Integer animalNum;
    private Vector<Unit> units;

    public Game(){
        map = new Map(100, 100);
        animalNum = map.getHeight()*3;
        units = new Vector<Unit>();
    }

    public void run(){
        spawnAnimals();
        System.out.println("Game started");
        for (int seconds = 0; seconds != 1000; seconds++){
            System.out.println(seconds + " seconds");
            System.out.println(units.size() + " animals left");

            for (int i = 0; i < units.size(); i++){
                units.get(i).move(map, units);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void spawnAnimals(){
        for (int i = 0; i < animalNum; i++){
            double flip = Math.random();
            if (flip < 0.6){
                Herbivore a = new Herbivore();
                giveCoordinates(a);
                units.add(a);
            }
            else if (flip < 0.8) {
                Predator a = new Predator();
                giveCoordinates(a);
                units.add(a);
            }
            else {
                Human a = new Human();
                giveCoordinates(a);
                units.add(a);
            }
        }
    }

    private void giveCoordinates(Animal animal){
        boolean flag = true;
        while (flag) {
            int x = ThreadLocalRandom.current().nextInt(0, map.getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, map.getHeight());

            // check for water tile
            if (map.getTiles().get(y).get(x).getID() == 3) continue;

            boolean overlap = false;
            for (Unit a : units) {
                if (x == a.getX() && y == a.getY()) {
                    overlap = true;
                    break;
                }
            }

            if (!overlap){
                animal.setX(x);
                animal.setY(y);
                flag = false;
            }
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
