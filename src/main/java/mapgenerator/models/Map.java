package mapgenerator.models;

import mapgenerator.models.houses.House;
import mapgenerator.models.tiles.*;

import java.io.Serializable;
import java.util.Vector;

public class Map implements Serializable {
    private Integer width;
    private Integer height;
    final private Integer temperature;
    private Vector<Vector<Tile>> tiles;
    private Vector<House> houses;

    public Map(Integer w, Integer h) {
        width = w;
        height = h;
        temperature = 18;
        houses = new Vector<>();
        setRandomTiles();
    }

    private void setRandomTiles() {
        tiles = new Vector<Vector<Tile>>();

        for (int i = 0; i < width; i++) {
            Vector<Tile> r = new Vector<>();
            for (int j = 0; j < height; j++) {
                SteppeTile t = new SteppeTile();
                r.add(t);
            }
            tiles.add(r);
        }
    }

    public Vector<Vector<Tile>> getTiles() {
        return tiles;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Vector<House> getHouses() {
        return houses;
    }

    public void addHouse(House house) {
        int x = house.getX() - house.getHouseSize()/2;
        int y = house.getY() - house.getHouseSize()/2;

        for (int i = 0; i < house.getHouseSize(); i++) {
            for (int j = 0; j < house.getHouseSize(); j++) {
                try {
                    Tile t = new StoneTile();
                    tiles.get(y + j).set(x + i, t);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }

        houses.add(house);
    }
}
