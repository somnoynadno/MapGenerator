package mapgenerator.models;

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
        animalNum = map.getHeight()*4;
        units = new Vector<Unit>();
    }

    public void run(){
        spawnAnimals();
        System.out.println("Game started");
        for (int seconds = 0; seconds != 1000; seconds++){
            System.out.println(seconds + " seconds");
            System.out.println(units.size() + " units left");

            for (int i = 0; i < units.size(); i++){
                units.get(i).move(map, units);
            }

            spawnTree();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void spawnAnimals(){
        for (int i = 0; i < animalNum; i++){
            double flip = Math.random();
            if (flip < 0.3){
                Tree u = new Tree();
                giveCoordinates(u);
                units.add(u);
            }
            else if (flip < 0.6){
                Herbivore u = new Herbivore();
                giveCoordinates(u);
                units.add(u);
            }
            else if (flip < 0.8) {
                Predator u = new Predator();
                giveCoordinates(u);
                units.add(u);
            }
            else {
                Human u = new Human();
                giveCoordinates(u);
                units.add(u);
            }
        }
    }

    private void giveCoordinates(Unit unit){
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
                unit.setX(x);
                unit.setY(y);
                flag = false;
            }
        }
    }

    public void spawnTree(){
        double flip = Math.random();
        if (flip < 0.7){
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
