package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Obstacles;
import ClientFrogger.Model.ObstaclesTwoPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTwoPlayer implements Initializable {

    @FXML private AnchorPane pane;
    @FXML private ImageView serpentYellow;
    @FXML private ImageView rightTrunk;
    @FXML private ImageView leftTrunk;
    @FXML private ImageView frogImg;
    @FXML private ImageView frogImg2;
    @FXML private ImageView yellowCar1;
    @FXML private ImageView redCar1;
    @FXML private ImageView whiteCar1;
    @FXML private ImageView yellowCar2;
    @FXML private ImageView redCar2;
    @FXML private ImageView whiteCar2;
    @FXML private ImageView centerTrunk;

    private ImageView[] obstacles = new ImageView[10];
    private Main main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obstacles[0]=serpentYellow;
        obstacles[1]=leftTrunk;
        obstacles[2]=rightTrunk;
        obstacles[3]=yellowCar1;
        obstacles[4]=redCar1;
        obstacles[5]=whiteCar1;
        obstacles[6]=whiteCar2;
        obstacles[7]=yellowCar2;
        obstacles[8]=redCar2;
        obstacles[9]=centerTrunk;
        ObstaclesTwoPlayer hilo = new ObstaclesTwoPlayer(obstacles, frogImg, frogImg2 );
        Thread thread = new Thread(hilo);
        thread.start();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void eventos(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if(frogImg2.getLayoutY()>=20){
                    frogImg2.setLayoutY(frogImg2.getLayoutY()-22);
                }
                break;
            case S:
                if(frogImg2.getLayoutY()<=560){
                    frogImg2.setLayoutY(frogImg2.getLayoutY()+22);
                }
                break;
            case A:
                if(frogImg2.getLayoutX()>=10){
                    frogImg2.setLayoutX(frogImg2.getLayoutX()-22);
                }
                break;
            case D:
                if(frogImg2.getLayoutX()<=360){
                    frogImg2.setLayoutX(frogImg2.getLayoutX()+22);
                }
                break;
            case UP:
                if(frogImg.getLayoutY()>=20){
                    frogImg.setLayoutY(frogImg.getLayoutY()-22);
                }
                break;
            case DOWN:
                if(frogImg.getLayoutY()<=560){
                    frogImg.setLayoutY(frogImg.getLayoutY()+22);
                }
                break;
            case LEFT:
                if(frogImg.getLayoutX()>=10){
                    frogImg.setLayoutX(frogImg.getLayoutX()-22);
                }
                break;
            case RIGHT:
                if(frogImg.getLayoutX()<=360){
                    frogImg.setLayoutX(frogImg.getLayoutX()+22);
                }
                break;
            default:
                break;
        }
    }

}
