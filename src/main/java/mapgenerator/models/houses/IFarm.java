package mapgenerator.models.houses;

import mapgenerator.models.Map;
import mapgenerator.models.Unit;

import java.util.Vector;

public interface IFarm {

    void produce(Map map, Vector<Unit> units);

}
