package mapgenerator.models.animals;

import mapgenerator.models.Map;
import mapgenerator.models.Unit;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Unit implements Serializable {

    private Integer hunger;
    private Integer ID;

    public Animal(){
        super();
        hunger = 20;
        ID = 0;
    }

    public void move(Map map){
        // TODO: add hunt
        // hunger -= 1;

        if (hunger < 10){
            hunt();
        } else {
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

    private void hunt(){

    }

    public Integer getHunger() {
        return hunger;
    }

    public Integer getID() {
        return ID;
    }
}
