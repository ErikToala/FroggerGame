package ClientFrogger.Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Obstacles implements Runnable{
    private ImageView obstacles[];
    private ImageView players[];
    private ImageView playerOne;
    private ImageView playerTwo;

    public Obstacles(ImageView[] obstacles, ImageView[] players) {
        this.obstacles = obstacles;
        this.players = players;
        playerOne = players[0];
        playerTwo = players[1];
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
                new KeyFrame(Duration.millis(1100), new KeyValue(obstacles[3].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[4].translateXProperty(),400)),
                new KeyFrame(Duration.millis(1100), new KeyValue(obstacles[4].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[5].translateXProperty(),400)),
                new KeyFrame(Duration.millis(1100), new KeyValue(obstacles[5].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[6].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(1100), new KeyValue(obstacles[6].translateXProperty(),400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[7].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(1100), new KeyValue(obstacles[7].translateXProperty(),400)),
                new KeyFrame(Duration.millis(0), new KeyValue(obstacles[8].translateXProperty(),-400)),
                new KeyFrame(Duration.millis(1100), new KeyValue(obstacles[8].translateXProperty(),400))

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
                new KeyFrame(Duration.millis(5), ae -> {
                    if(obstacles[0].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[1].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[2].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[3].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[4].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[5].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[6].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[7].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[8].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[9].getBoundsInParent().intersects(playerOne.getBoundsInParent())){
                        playerOne.setLayoutY(600-29);
                    }
                    if(obstacles[0].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[1].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[2].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[3].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[4].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[5].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[6].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[7].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[8].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                    if(obstacles[9].getBoundsInParent().intersects(playerTwo.getBoundsInParent())){
                        playerTwo.setLayoutY(600-29);
                    }
                })
        );

        intersections.setCycleCount(Timeline.INDEFINITE);
        intersections.play();

    }
}
