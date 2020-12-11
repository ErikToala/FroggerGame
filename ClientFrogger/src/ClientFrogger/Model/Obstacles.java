package ClientFrogger.Model;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Obstacles implements Runnable{
    private ImageView obstacles[];
    private ImageView player;

    public Obstacles(ImageView[] obstacles, ImageView player) {
        this.obstacles = obstacles;
        this.player = player;
    }

    @Override
    public void run() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[0].translateXProperty(),55)),
                new KeyFrame(Duration.millis(600), new KeyValue(obstacles[0].translateXProperty(),-110)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[1].translateXProperty(),0)),
                new KeyFrame(Duration.millis(600), new KeyValue(obstacles[1].translateXProperty(),-50)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[2].translateXProperty(),0)),
                new KeyFrame(Duration.millis(600), new KeyValue(obstacles[2].translateXProperty(),50))
        );

        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline carMovement = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[3].translateXProperty(),400)),
                new KeyFrame(Duration.millis(3000), new KeyValue(obstacles[3].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[4].translateXProperty(),400)),
                new KeyFrame(Duration.millis(3000), new KeyValue(obstacles[4].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[5].translateXProperty(),400)),
                new KeyFrame(Duration.millis(3000), new KeyValue(obstacles[5].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[6].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(3000), new KeyValue(obstacles[6].translateXProperty(),400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[7].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(3000), new KeyValue(obstacles[7].translateXProperty(),400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[8].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(3000), new KeyValue(obstacles[8].translateXProperty(),400))

        );

        carMovement.setCycleCount(Timeline.INDEFINITE);
        carMovement.play();

        Timeline moveTrunk = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(obstacles[9].translateXProperty(),80)),
                    new KeyFrame(Duration.millis(500), new KeyValue(obstacles[9].translateXProperty(),-80))
        );

        moveTrunk.setAutoReverse(true);
        moveTrunk.setCycleCount(Timeline.INDEFINITE);
        moveTrunk.play();


        Timeline intersections = new Timeline(
                new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        /*if(obstacles[0].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[1].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[2].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }*/
                        if(obstacles[3].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[4].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[5].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[6].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[7].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[8].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                        if(obstacles[9].getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.setLayoutY(600-38);
                        }
                    }
                })
        );

        intersections.setCycleCount(Timeline.INDEFINITE);
        intersections.play();

    }
}
