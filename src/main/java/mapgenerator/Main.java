package mapgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import mapgenerator.models.Game;
import mapgenerator.models.animals.Animal;

import java.util.Vector;

import static spark.Spark.*;

public class Main {
    public static Game game;

    public static void main(String[] args) {
        staticFileLocation("/");

        get("/api/v1/map/", (req, res) -> {
            ObjectMapper ow = new ObjectMapper();
            res.header("Content-Type", "application/json");
            String json = ow.writeValueAsString(game.getMap());

            return json;
        });

        get("/api/v1/tiles/", (req, res) -> {
            ObjectMapper ow = new ObjectMapper();
            res.header("Content-Type", "application/json");
            String json = ow.writeValueAsString(game.getMap().getTiles());

            return json;
        });

        get("/api/v1/animals/", (req, res) -> {
            ObjectMapper ow = new ObjectMapper();
            res.header("Content-Type", "application/json");
            String json = ow.writeValueAsString(game.getAnimals());

            return json;
        });

        get("/api/v1/info/", (req, res) -> {
            Integer x = Integer.parseInt(req.queryMap().value("x"));
            Integer y = Integer.parseInt(req.queryMap().value("y"));

            res.header("Content-Type", "application/json");
            ObjectMapper ow = new ObjectMapper();

            Vector<Animal> animals = game.getAnimals();
            for (int i = 0; i < animals.size(); i++) {
                if (x == animals.get(i).getX() && y == animals.get(i).getY()){
                    return ow.writeValueAsString(animals.get(i));
                }
            }
            return ow.writeValueAsString("No animal on position");
        });

        game = new Game();
        game.run();
    }
}