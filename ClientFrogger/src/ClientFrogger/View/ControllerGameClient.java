package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Obstacles;
import ClientFrogger.Model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ControllerGameClient implements Observer, Initializable {
    @FXML private AnchorPane pane;
    @FXML private ImageView serpentYellow;
    @FXML private ImageView rightTrunk;
    @FXML private ImageView leftTrunk;
    @FXML private ImageView frogImg;
    @FXML private ImageView yellowCar2;
    @FXML private ImageView redCar2;
    @FXML private ImageView whiteCar2;
    @FXML private ImageView centerTrunk;
    @FXML private ImageView redCar1;
    @FXML private ImageView whiteCar1;
    @FXML private ImageView yellowCar1;

    private Socket socket;
    private DataOutputStream bufferout = null;

    private Main main;
    private ImageView[] obstacles = new ImageView[10];
    private ObservableList<Player> players;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

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
        Obstacles hilo = new Obstacles(obstacles, frogImg);
        Thread thread = new Thread(hilo);
        thread.start();
    }

    public void eventos(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if(frogImg.getLayoutY()>=20){
                    frogImg.setLayoutY(frogImg.getLayoutY()-22);

                }
                break;
            case S:
                if(frogImg.getLayoutY()<=560){
                    frogImg.setLayoutY(frogImg.getLayoutY()+22);
                }
                break;
            case A:
                if(frogImg.getLayoutX()>=10){
                    frogImg.setLayoutX(frogImg.getLayoutX()-22);
                }
                break;
            case D:
                if(frogImg.getLayoutX()<=360){
                    frogImg.setLayoutX(frogImg.getLayoutX()+22);
                }
                break;
            default:
                break;
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setPlayers(ObservableList<Player> players) {
        this.players = players;
        pane.getChildren().addAll(getImgPlayers());
    }

    public ArrayList<ImageView> getImgPlayers() {
        ArrayList<ImageView> playersImg = null;
        ImageView imgPlayer = null;
        String file = "file:ClientFrogger/src/ClientFrogger/Resources/";
        for(int i = 0;i<players.size();i++){
            imgPlayer.setImage(new Image(file+players.get(i).getColor()));
            playersImg.add(imgPlayer);
        }
        return playersImg;
    }
}
