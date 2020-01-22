package mapgenerator.models.houses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mapgenerator.models.Map;
import mapgenerator.models.Unit;
import mapgenerator.models.UnitType;
import mapgenerator.models.herbs.Wheat;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Farm extends House implements IFarm {

    @JsonIgnore
    private final int cooldown = 10;
    @JsonIgnore
    private int i = 0;

    public Farm() {
        super();
        unitType = UnitType.FARM;
        houseSize = 5;
    }

    public Farm(int X, int Y) {
        super();
        unitType = UnitType.FARM;
        houseSize = 5;

        x = X;
        y = Y;
    }

    @Override
    public void move(Map map, Vector<Unit> units) {
        if (i < cooldown) {
            i++;
        } else {
            i = 0;
            produce(map, units);
        }
    }

    @Override
    public void produce(Map map, Vector<Unit> units) {
        int x0 = ThreadLocalRandom.current().nextInt(x-1, x+1);
        int y0 = ThreadLocalRandom.current().nextInt(y-1, y+1);

        if (x0 < map.getWidth() && x0 >= 0 && y0 < map.getHeight() && y0 >= 0) {
            for (Unit unit: units) {
                if (unit.getX() == x0  && unit.getY() == y0) {
                    return;
                }
            }
            Wheat wheat = new Wheat();
            wheat.setX(x0);
            wheat.setY(y0);

            units.add(wheat);

            System.out.println("Farm produced wheat");
        }
    }
}
