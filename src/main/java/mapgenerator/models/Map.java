package mapgenerator.models;

import mapgenerator.models.tiles.GreenTile;
import mapgenerator.models.tiles.SteppeTile;
import mapgenerator.models.tiles.Tile;
import mapgenerator.models.tiles.WaterTile;

import java.io.Serializable;
import java.util.Vector;

public class Map implements Serializable {
    private Integer width;
    private Integer height;
    final private Integer temperature;
    private Vector<Vector<Tile>> tiles;

    public Map(Integer w, Integer h){
        width = w;
        height = h;
        temperature = 18;
        setRandomTiles();
    }

    private void setRandomTiles(){
        tiles = new Vector<Vector<Tile>>();

        for (int i = 0; i < width; i++){
            Vector<Tile> r = new Vector<>();
            for (int j = 0; j < height; j++){
                double flip = Math.random();
                if (flip < (0.5 + (float) temperature/100)){
                    SteppeTile t = new SteppeTile();
                    r.add(t);
                }
                else if (flip < (0.8 + (float) temperature/100)){
                    GreenTile t = new GreenTile();
                    r.add(t);
                } else {
                    WaterTile t = new WaterTile();
                    r.add(t);
                }
            }
            tiles.add(r);
        }
    }

    public void eatGrass(int x, int y){
        tiles.get(y).set(x, new SteppeTile());
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
}
