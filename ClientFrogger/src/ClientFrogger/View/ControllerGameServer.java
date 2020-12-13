package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ControllerGameServer implements Observer, Initializable {
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

    private Main main;
    private static ServerSocket servidor;
    private Socket socket;
    private ObservableList<Player> players;
    private ImageView[] obstacles = new ImageView[10];
    private DataOutputStream bufferout = null;
    private Thread threadServer;
    private Thread gameServer;
    //private Image frogImg = new Image("file:ClientFrogger/src/ClientFrogger/Resources/Normal.png");

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obstacles[0]=serpentYellow;
        obstacles[1]=leftTrunk;
        obstacles[2]=rightTrunk;
        obstacles[3]=yellowCar1;
        obstacles[4]=redCar1;
        obstacles[5]= whiteCar1;
        obstacles[6]=whiteCar2;
        obstacles[7]= yellowCar2;
        obstacles[8]= redCar2;
        obstacles[9]=centerTrunk;
        Obstacles hilo = new Obstacles(obstacles, frogImg);
        Thread thread = new Thread(hilo);
        thread.start();

    }

    public void eventos(KeyEvent event) throws IOException {
        String sendPlayer = "";
        ImageView frogSocket = (ImageView) pane.lookup("#"+players.get(0).getName());
        sendPlayer += players.get(0).getName() +";";
        switch (event.getCode()) {
            case W:
                if(frogSocket.getLayoutY()>=20){
                    frogSocket.setLayoutY(frogSocket.getLayoutY()-22);
                    sendPlayer += "W";
                    bufferout.writeUTF(sendPlayer);

                }
                break;
            case S:
                if(frogSocket.getLayoutY()<=560){
                    frogSocket.setLayoutY(frogSocket.getLayoutY()+22);
                    sendPlayer += "S";
                    bufferout.writeUTF(sendPlayer);
                }
                break;
            case A:
                if(frogSocket.getLayoutX()>=10){
                    frogSocket.setLayoutX(frogSocket.getLayoutX()-22);
                    sendPlayer += "A";
                    bufferout.writeUTF(sendPlayer);
                }
                break;
            case D:
                if(frogSocket.getLayoutX()<=360){
                    frogSocket.setLayoutX(frogSocket.getLayoutX()+22);
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
        ThreadGameServer threadGameServer = new ThreadGameServer(socket);
        threadGameServer.addObserver(ControllerGameServer.this);
        threadServer = new Thread(threadGameServer);
        threadServer.start();
    }

    public void setPlayers(ObservableList<Player> players) {
        this.players = players;
        setImgPlayers();
        //System.out.println(pane.lookup("#"+players.get(0).getName()));
        //System.out.println(pane.lookup("#"+players.get(1).getName()));
    }

    public void setImgPlayers() {
        ImageView imgPlayer;
        String file = "file:ClientFrogger/src/ClientFrogger/Resources/";
        for(int i = 0;i<players.size();i++){
            imgPlayer = new ImageView();
            System.out.println(players.get(i).getColor());
            imgPlayer.setImage(new Image(file+players.get(i).getColor()+".png"));
            imgPlayer.setId(players.get(i).getName());
            imgPlayer.setFitHeight(32);
            imgPlayer.setFitWidth(32);
            imgPlayer.setTranslateY(570);
            imgPlayer.setTranslateX(375-(i*50));
            pane.getChildren().add(imgPlayer);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String valor = ((String) arg);
        String [] playerReceived = valor.split(";");
        ImageView frog2 = (ImageView) pane.lookup("#"+playerReceived[0]);
        Platform.runLater(()->{
            if(playerReceived[1].equals("W")){
                frog2.setLayoutY(frog2.getLayoutY()-22);
            }
            if(playerReceived[1].equals("S")){
                frog2.setLayoutY(frog2.getLayoutY()+22);
            }
            if(playerReceived[1].equals("A")){
                frog2.setLayoutX(frog2.getLayoutX()-22);

            }
            if(playerReceived[1].equals("D")){
                frog2.setLayoutX(frog2.getLayoutX()+22);
            }
        });

    }
}
