package mapgenerator.models.animals;

import java.util.Vector;

public class Predator extends Animal {

    public Predator(){
        super();
        ID = 2;
    }

    @Override
    public void searchForTarget(Vector<Animal> animals){
        for (int i = 0; i < animals.size(); i++){
            Animal animal = animals.get(i);
            if (animal != this
                    && Math.abs(animal.getX() - x) <= huntRadius
                    && Math.abs(animal.getY() - y) <= huntRadius
                    && animal.getID() != 2 && animal.getID() != 3){
                target = animal;
                break;
            }
        }
    }
}
