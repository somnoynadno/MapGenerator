package mapgenerator.models;

import java.io.Serializable;

public class Game implements Serializable {
    private Map map;

    public Game(){
        map = new Map(100, 100);
    }

    public void run(){
        System.out.println("Game started");
        for (int seconds = 0; seconds != 1000; seconds++){
            System.out.println(seconds + " seconds");

            // TODO: do something

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
