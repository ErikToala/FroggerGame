package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Obstacles;
import ClientFrogger.Model.Player;
import ClientFrogger.Model.ThreadGameServer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerGameServer implements Observer, Initializable {
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
    @FXML private Label lbPlayer1;
    @FXML private Label lbPlayer2;

    private Main main;
    private Socket socket;
    private ObservableList<Player> players;
    private ImageView[] obstacles = new ImageView[10];
    private ImageView[] imgPlayers = new ImageView[2];
    private DataOutputStream bufferout = null;
    private Thread threadServer;
    private int nWinPlayer1 = 0;
    private int nWinPlayer2 = 0;
    private String nGames;


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
    }

    public void eventos(KeyEvent event) throws IOException {
        String sendPlayer = "";
        ImageView frogSocket = (ImageView) pane.lookup("#"+players.get(0).getColor());
        sendPlayer += players.get(0).getColor() +";";
        switch (event.getCode()) {
            case W:
                if(frogSocket.getLayoutY()>=20){
                    frogSocket.setLayoutY(frogSocket.getLayoutY()-22);
                    sendPlayer += "W";
                    if(frogSocket.getLayoutY()<=21){
                        nWinPlayer1++;
                        lbPlayer1.setText(players.get(0).getName()+" "+nWinPlayer1);
                        lbPlayer2.setText(players.get(1).getName()+" "+nWinPlayer2);
                        imgPlayers[0].setLayoutX(375);
                        imgPlayers[0].setLayoutY(570);
                        imgPlayers[1].setLayoutX(375);
                        imgPlayers[1].setLayoutY(570);
                        sendPlayer += ";Win";
                        bufferout.writeUTF(sendPlayer);
                        if(String.valueOf(nWinPlayer1).equals(nGames)){
                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("WINNER");
                            alert.setHeaderText("Congratulations "+players.get(0).getName()+" you've won");
                            alert.setContentText("Do you want to continue playing?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                nWinPlayer1 = 0;
                                nWinPlayer2 = 0;
                                lbPlayer1.setText(players.get(0).getName()+" "+nWinPlayer1);
                                lbPlayer2.setText(players.get(1).getName()+" "+nWinPlayer2);
                            }else{
                                sendPlayer = "FIN";
                                bufferout.writeUTF(sendPlayer);
                                main.getGameServerStage().close();
                                try {
                                    main.start(main.getMenuStage());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else{
                        sendPlayer += ";Playing;Proceso";
                        bufferout.writeUTF(sendPlayer);
                    }
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

    public void setPlayers(ObservableList<Player> players, String nGames) {
        this.players = players;
        this.nGames = nGames;
        setImgPlayers();
    }

    public void setImgPlayers() {
        ImageView imgPlayer;
        String file = "file:ClientFrogger/src/ClientFrogger/Resources/";
        for(int i = 0;i<players.size();i++){
            imgPlayer = new ImageView();
            imgPlayer.setImage(new Image(file+players.get(i).getColor()+".png"));
            imgPlayer.setId(players.get(i).getColor());
            imgPlayer.setFitHeight(32);
            imgPlayer.setFitWidth(32);
            imgPlayer.setLayoutX(375);
            imgPlayer.setLayoutY(570);
            pane.getChildren().add(imgPlayer);
        }
        imgPlayers[0] = (ImageView) pane.lookup("#"+players.get(0).getColor());
        imgPlayers[1] = (ImageView) pane.lookup("#"+players.get(1).getColor());
        lbPlayer1.setText(players.get(0).getName()+" "+nWinPlayer1);
        lbPlayer2.setText(players.get(1).getName()+" "+nWinPlayer2);
        Obstacles hilo = new Obstacles(obstacles, imgPlayers);
        Thread thread = new Thread(hilo);
        thread.start();

    }

    @Override
    public void update(Observable o, Object arg) {
        String valor = (String) arg;
        if(valor.equals("FIN")){
            try {
                bufferout.writeUTF("FIN");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->{
                main.getGameServerStage().close();
                try {
                    main.start(main.getMenuStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }else{
            String [] playerReceived = valor.split(";");
            ImageView frog2 = (ImageView) pane.lookup("#"+playerReceived[0]);
            Platform.runLater(()->{
                if(playerReceived[1].equals("W")){
                    frog2.setLayoutY(frog2.getLayoutY()-22);
                    if(playerReceived[2].equals("Win")){
                        nWinPlayer2++;
                        Platform.runLater(()->{
                            lbPlayer1.setText(players.get(0).getName()+" "+nWinPlayer1);
                            lbPlayer2.setText(players.get(1).getName()+" "+nWinPlayer2);
                            imgPlayers[0].setLayoutX(375);
                            imgPlayers[0].setLayoutY(570);
                            imgPlayers[1].setLayoutX(375);
                            imgPlayers[1].setLayoutY(570);
                        });
                        if(String.valueOf(nWinPlayer2).equals(nGames)){
                            Alert alert = new Alert (Alert.AlertType.INFORMATION);
                            alert.setTitle("LOSER");
                            alert.setHeaderText("Waiting answer of "+ players.get(1).getName());
                            alert.showAndWait();
                            nWinPlayer1=0;
                            nWinPlayer2=0;
                            lbPlayer1.setText(players.get(0).getName()+" "+nWinPlayer1);
                            lbPlayer2.setText(players.get(1).getName()+" "+nWinPlayer2);
                        }
                    }
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
}
