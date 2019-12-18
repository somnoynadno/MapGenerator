package mapgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import mapgenerator.models.Game;

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

        game = new Game();
        game.run();
    }
}