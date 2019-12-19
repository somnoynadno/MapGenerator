package mapgenerator.models.animals;

import mapgenerator.models.Map;
import mapgenerator.models.tiles.Tile;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Herbivore extends Animal {

    private Tile target;
    private Integer targetX;
    private Integer targetY;

    public Herbivore(){
        super();
        ID = 1;
    }

    @Override
    public void move(Map map, Vector<Animal> animals){
        hunger -= 1;
        checkForDeath(animals);

        boolean huntResult = false;
        if (hunger < 18) {
            huntResult = hunt(map);
        }
        if (!huntResult) {
            double flip = Math.random();
            if (flip < 0.1){
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

    protected boolean hunt(Map map){
        if (target == null){
            searchForTarget(map);
            if (target == null) return false;
        }

        // go
        if (targetX > x){
            x += 1;
        }
        if (targetY > y){
            y += 1;
        }
        if (targetX < x){
            x -= 1;
        }
        if (targetY < y){
            y -= 1;
        }

        // eat
        if (x.equals(targetX) && y.equals(targetY)){
            System.out.println("Eat on " + x + " " + y + "!");
            hunger += 12;

            map.eatGrass(targetX, targetY);

            target = null;
            targetX = null;
            targetY = null;

            return false;
        }

        return true;
    }

    public void searchForTarget(Map map){
        Vector<Vector<Tile>> tiles = map.getTiles();
        for (int j = y - huntRadius; j > 0 && j < map.getHeight() && j < y + huntRadius; j++){
            for (int i = x - huntRadius; i > 0 && i < map.getWidth() && i < x + huntRadius; i++) {
                Tile tile = tiles.get(j).get(i);
                if (tile.getID() == 2){
                    target = tile;
                    targetX = i;
                    targetY = j;
                    return;
                }
            }
        }
    }
}
