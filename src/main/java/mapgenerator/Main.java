package mapgenerator;

import mapgenerator.models.Game;

import static spark.Spark.*;

public class Main {
    public static int i;
    public static void main(String[] args) {
        staticFileLocation("/");

        get("/hello", (req, res) -> {
            System.out.println(i);
            return i;
        });

        Game game = new Game();
        game.run();
    }
}