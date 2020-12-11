package ClientFrogger.Model;

import javafx.scene.image.Image;

public class Player {
    private int id;
    private String name;
    //private Image color;
    private String color;


    public Player(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    /*public Image getColor() {
        return color;
    }*/
}
