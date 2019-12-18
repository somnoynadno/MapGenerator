package mapgenerator.models;

import mapgenerator.models.tiles.GreenTile;
import mapgenerator.models.tiles.SteppeTile;
import mapgenerator.models.tiles.Tile;

import java.io.Serializable;
import java.util.Vector;

public class Map implements Serializable {
    private Integer width;
    private Integer height;
    private Vector<Vector<Tile>> tiles;

    public Map(Integer w, Integer h){
        width = w;
        height = h;

        setRandomTiles();
    }

    private void setRandomTiles(){
        tiles = new Vector<Vector<Tile>>();

        for (int i = 0; i < width; i++){
            Vector<Tile> r = new Vector<>();
            for (int j = 0; j < height; j++){
                double flip = Math.random();
                if (flip < 0.5){
                    SteppeTile t = new SteppeTile();
                    r.add(t);
                }
                else {
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
}
