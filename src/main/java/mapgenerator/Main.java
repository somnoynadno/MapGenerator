package mapgenerator;

import static spark.Spark.*;

public class Main {
    public static int i;
    public static void main(String[] args) {
        staticFileLocation("/");

        get("/hello", (req, res) -> {
            System.out.println(i);
            return i;
        });

        i = 0;
        while (i < 100){
            System.out.println(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            i++;
        }
    }
}