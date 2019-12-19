package mapgenerator.models.animals;

import mapgenerator.models.Map;
import mapgenerator.models.Unit;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Unit implements Serializable {

    protected Integer hunger;
    protected Integer ID;
    protected Animal target;
    protected final Integer huntRadius = 5;

    public Animal(){
        super();
        hunger = ThreadLocalRandom.current().nextInt(15, 40);
        ID = 0;
        target = null;
    }

    public void move(Map map, Vector<Animal> animals){
        hunger -= 1;
        checkForDeath(animals);

        boolean huntResult = false;
        if (hunger < 10) {
            huntResult = hunt(animals);
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
                        && tempY >= 0 && tempY < map.getHeight()){
                    x = tempX;
                    y = tempY;
                } // else just exit
            }
        }
    }

    protected boolean hunt(Vector<Animal> animals){
        if (target == null){
            searchForTarget(animals);
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
            hunger += 25;
            animals.remove(target);
            target = null;
            return false;
        }
        return true;
    }

    public void searchForTarget(Vector<Animal> animals){
        for (int i = 0; i < animals.size(); i++){
            Animal animal = animals.get(i);
            if (animal != this
                    && Math.abs(animal.getX() - x) <= huntRadius
                    && Math.abs(animal.getY() - y) <= huntRadius){
                target = animal;
                break;
            }
        }
    }

    public void checkForDeath(Vector<Animal> animals){
        if (hunger <= 0){
            System.out.println("Death of hunger on " + getX() + " " + getY());
            animals.remove(this);
            return;
        }
    }

    public Integer getHunger() {
        return hunger;
    }

    public Integer getID() {
        return ID;
    }
}

