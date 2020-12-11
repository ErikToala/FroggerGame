package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Obstacles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTwoPlayer implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView serpentYellow;

    @FXML
    private ImageView rightTrunk;

    @FXML
    private ImageView leftTrunk;

    @FXML
    private ImageView frogImg;

    private ImageView[] obstacles = new ImageView[10];
    private Main main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obstacles[0]=serpentYellow;
        obstacles[1]=leftTrunk;
        obstacles[2]=rightTrunk;
        Obstacles hilo = new Obstacles(obstacles, frogImg);
        Thread thread = new Thread(hilo);
        thread.start();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void eventos(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if(frogImg.getLayoutY()>=20){
                    frogImg.setLayoutY(frogImg.getLayoutY()-30);
                }
                break;
            case S:
                if(frogImg.getLayoutY()<=560){
                    frogImg.setLayoutY(frogImg.getLayoutY()+30);
                }
                break;
            case A:
                if(frogImg.getLayoutX()>=10){
                    frogImg.setLayoutX(frogImg.getLayoutX()-30);
                }
                break;
            case D:
                if(frogImg.getLayoutX()<=360){
                    frogImg.setLayoutX(frogImg.getLayoutX()+30);
                }
                break;
            default:
                break;
        }
    }

}
