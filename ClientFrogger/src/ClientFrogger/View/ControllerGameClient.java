package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Obstacles;
import ClientFrogger.Model.Player;
import ClientFrogger.Model.ThreadGameClient;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ControllerGameClient implements Observer, Initializable {
    @FXML private AnchorPane pane;
    @FXML private ImageView serpentYellow;
    @FXML private ImageView rightTrunk;
    @FXML private ImageView leftTrunk;
    @FXML private ImageView yellowCar2;
    @FXML private ImageView redCar2;
    @FXML private ImageView whiteCar2;
    @FXML private ImageView centerTrunk;
    @FXML private ImageView redCar1;
    @FXML private ImageView whiteCar1;
    @FXML private ImageView yellowCar1;

    private Socket socket;
    private DataOutputStream bufferout = null;
    private Thread threadGameClient;
    private Main main;
    private ImageView[] obstacles = new ImageView[10];
    private ImageView[] imgPlayers = new ImageView[2];
    private ObservableList<Player> players;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void update(Observable o, Object arg) {
        String st = (String)arg;
        String [] playerReceived = st.split(";");
        ImageView frog = (ImageView) pane.lookup("#"+playerReceived[0]);
        Platform.runLater(()->{
            if(playerReceived[1].equals("W")){
                frog.setLayoutY(frog.getLayoutY()-22);
            }
            if(playerReceived[1].equals("S")){
                frog.setLayoutY(frog.getLayoutY()+22);
            }
            if(playerReceived[1].equals("A")){
                frog.setLayoutX(frog.getLayoutX()-22);
            }
            if(playerReceived[1].equals("D")){
                frog.setLayoutX(frog.getLayoutX()+22);
            }
        });
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
    }

    public void eventos(KeyEvent event) throws IOException {
        String sendPlayer = "";
        ImageView frog = (ImageView) pane.lookup("#"+players.get(1).getName());
        sendPlayer += players.get(1).getName() + ";";
        switch (event.getCode()) {
            case W:
                if(frog.getLayoutY()>=20){
                    frog.setLayoutY(frog.getLayoutY()-22);
                    sendPlayer += "W";
                    bufferout.writeUTF(sendPlayer);
                }
                break;
            case S:
                if(frog.getLayoutY()<=560){
                    frog.setLayoutY(frog.getLayoutY()+22);
                    sendPlayer += "S";
                    bufferout.writeUTF(sendPlayer);
                }
                break;
            case A:
                if(frog.getLayoutX()>=10){
                    frog.setLayoutX(frog.getLayoutX()-22);
                    sendPlayer += "A";
                    bufferout.writeUTF(sendPlayer);
                }
                break;
            case D:
                if(frog.getLayoutX()<=360){
                    frog.setLayoutX(frog.getLayoutX()+22);
                    sendPlayer += "D";
                    bufferout.writeUTF(sendPlayer);
                }
                break;
            default:
                break;
        }
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        bufferout = new DataOutputStream(socket.getOutputStream());
        bufferout.flush();
        ThreadGameClient gameClient = new ThreadGameClient(socket);
        gameClient.addObserver(ControllerGameClient.this);
        threadGameClient = new Thread(gameClient);
        threadGameClient.start();
    }

    public void setPlayers(ObservableList<Player> players) {
        this.players = players;
        setImgPlayers();
    }

    public void setImgPlayers() {
        ImageView imgPlayer;
        String file = "file:ClientFrogger/src/ClientFrogger/Resources/";
        for(int i = 0;i<players.size();i++){
            imgPlayer = new ImageView();
            imgPlayer.setImage(new Image(file+players.get(i).getColor()+".png"));
            imgPlayer.setId(players.get(i).getName());
            imgPlayer.setFitHeight(32);
            imgPlayer.setFitWidth(32);
            imgPlayer.setLayoutX(375);
            imgPlayer.setLayoutY(570);
            pane.getChildren().add(imgPlayer);
        }
        imgPlayers[0] = (ImageView) pane.lookup("#"+players.get(0).getName());
        imgPlayers[1] = (ImageView) pane.lookup("#"+players.get(1).getName());
        Obstacles hilo = new Obstacles(obstacles, imgPlayers);
        Thread thread = new Thread(hilo);
        thread.start();
    }
}
