package mapgenerator.models;

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
                double flip = Math.random();
                if (flip < (0.7 + (float) temperature / 100)) {
                    SteppeTile t = new SteppeTile();
                    r.add(t);
                } else {
                    GreenTile t = new GreenTile();
                    r.add(t);
                }
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
        int x = house.getX() - 1;
        int y = house.getY() - 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
