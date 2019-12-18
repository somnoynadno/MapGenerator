package mapgenerator.models.animals;

import mapgenerator.models.Map;
import mapgenerator.models.Unit;

import javax.naming.spi.StateFactory;
import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Unit implements Serializable {

    private Integer hunger;
    private Integer ID;
    private Animal target;
    private final Integer huntRadius = 5;

    public Animal(){
        super();
        hunger = ThreadLocalRandom.current().nextInt(15, 40);
        ID = 0;
        target = null;
    }

    public void move(Map map, Vector<Animal> animals){
        hunger -= 1;

        // death
        if (hunger <= 0){
            System.out.println("Death of hunger on " + getX() + " " + getY());
            animals.remove(this);
            return;
        }

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
                int x = getX();
                int y = getY();

                switch (direction){
                    case 1:
                        x += 1;
                    case 2:
                        x -= 1;
                    case 3:
                        y += 1;
                    case 4:
                        y -= 1;
                }
                // check constraints
                if (x >= 0 && x < map.getWidth() && y >= 0 && y < map.getHeight()){
                    setX(x);
                    setY(y);
                } // else just exit
            }
        }
    }

    private boolean hunt(Vector<Animal> animals){
        Integer x = getX();
        Integer y = getY();

        if (target == null){
            // search for target
            for (int i = 0; i < animals.size(); i++){
                Animal animal = animals.get(i);
                if (animal != this
                        && Math.abs(animal.getX() - x) <= huntRadius
                        && Math.abs(animal.getY() - y) <= huntRadius){
                    target = animal;
                    break;
                }
            }
            return false;
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

        setX(x);
        setY(y);

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

    public Integer getHunger() {
        return hunger;
    }

    public Integer getID() {
        return ID;
    }
}

