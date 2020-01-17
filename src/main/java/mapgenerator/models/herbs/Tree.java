package mapgenerator.models.herbs;

import mapgenerator.models.UnitType;

public class Tree extends Herb {
    public Tree() {
        super();
        unitType = UnitType.TREE;
        nutritionalValue = 40;
    }
}
