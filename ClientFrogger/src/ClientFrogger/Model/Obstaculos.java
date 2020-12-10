package ClientFrogger.Model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class Obstaculos implements Runnable{
    private ImageView obstacles[];
    private ImageView player;
    private AnimationTimer timer;
    boolean x = false;

    public Obstaculos(ImageView[] obstacles, ImageView player) {
        this.obstacles = obstacles;
        this.player = player;
    }

    public void moveSerpent(){

       if(!x && obstacles[0].getX()<=110){
            obstacles[0].setTranslateX(obstacles[0].getTranslateX()-5);
        }
        if(obstacles[0].getTranslateX()==-110)
            x = true;
        else if(obstacles[0].getTranslateX()==55)
            x=false;
        if(x && obstacles[0].getX()>=0){
            obstacles[0].setTranslateX(obstacles[0].getTranslateX()+5);
        }
        if(obstacles[0].getBoundsInParent().intersects(player.getBoundsInParent())){

            player.setLayoutY(600-38);
        }

    }

    @Override
    public void run() {
        //System.out.println(obstacle.getLayoutX());

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveSerpent();
            }
        };
        timer.start();
    }
}
