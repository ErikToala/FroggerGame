package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Player;
import ClientFrogger.Model.ThreadServer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class ControllerServer implements Observer {
    private static ServerSocket servidor;
    private Socket socket;
    private DataOutputStream bufferout = null;
    private Main main;
    private String [] playerReceived;
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private int nPlayers = 0;
    private String [] colores = {"Normal","Rojo","Azul","Rosa","Gris","Amarillo","Morado"};
    private Thread hiloServer;
    private Thread hiloCommunication;

    @FXML private RadioButton rdBFrogGreen;
    @FXML private ToggleGroup tGFroggy;
    @FXML private RadioButton rdBFrogRed;
    @FXML private RadioButton rdBFrogBlue;
    @FXML private RadioButton rdBFrogRose;
    @FXML private RadioButton rdBFrogGray;
    @FXML private RadioButton rdBFrogYellow;
    @FXML private RadioButton rdBFrogPurple;
    @FXML private Button btnStart;
    @FXML private Button btnServer;
    @FXML private TextField txtPort;
    @FXML private TextField txtName;
    @FXML private TextField txtCode;
    @FXML private TableView<Player> tablePlayers;
    @FXML private TableColumn<Player, Integer> columnId;
    @FXML private TableColumn<Player, String> columnName;
    @FXML private TableColumn<Player, String> columnColor;


    @FXML
    void OnMouseClickedCancel(MouseEvent event) throws Exception {
        //closeServerSocket();
        main.getServerStage().close();
        main.start(main.getMenuStage());
    }

    @FXML
    void OnMouseClickedCreateGame(MouseEvent event) {
        if(btnServer.getText().equals("Create Game")){
            if(checkField()){
                Player player;
                try {
                    txtCode.setVisible(true);
                    txtCode.setStyle("-fx-text-fill: Green; -fx-font-size: 15;");
                    txtCode.setText(getIp());
                    servidor = new ServerSocket(Integer.valueOf(txtPort.getText()));
                    btnServer.setText("Close Server");
                    player = new Player(nPlayers,txtName.getText(),getColorFrog());
                    players.add(player);
                    fillTable();
                    hiloCommunication = new Thread(() -> {
                        while (!servidor.isClosed()) {
                            try {
                                socket = servidor.accept();
                                nPlayers++;
                                bufferout = new DataOutputStream(socket.getOutputStream());
                                bufferout.flush();
                                ThreadServer server = new ThreadServer(socket);
                                server.addObserver(ControllerServer.this);
                                hiloServer = new Thread(server);
                                hiloServer.start();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String sendPlayer = "";
                            sendPlayer += "Joined"+ ";";
                            sendPlayer += player.getId() + ";";
                            sendPlayer += player.getName() + ";";
                            sendPlayer += player.getColor();
                            try {
                                bufferout.writeUTF(sendPlayer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    });

                    hiloCommunication.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                btnStart.setDisable(false);
            }
        }else{
            String status ="ServerClosed;0";
            try {
                bufferout.writeUTF(status);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        btnServer.setStyle("-fx-background-color: red;");
        btnServer.setTextFill(Color.WHITE);
    }

    @FXML
    void OnMouseClickedStart(MouseEvent event) throws IOException {
        String status = "ServerClosed;1";
        bufferout.writeUTF(status);
    }


    private boolean checkColorPlayer(){
        for(int i=0;i<players.size();i++){
            if(players.get(i).getColor().equals(playerReceived[1])){
                return false;
            }
        }
        return true;
    }

    public String getColorFrog(){
        String color = "";
        if(rdBFrogGreen.isSelected()){
            color = "Normal";
        }
        if(rdBFrogRed.isSelected()){
            color = "Rojo";
        }
        if(rdBFrogBlue.isSelected()){
            color = "Azul";
        }
        if(rdBFrogRose.isSelected()){
            color = "Rosa";
        }
        if(rdBFrogGray.isSelected()){
            color = "Gris";
        }
        if(rdBFrogYellow.isSelected()){
            color = "Amarillo";
        }
        if(rdBFrogPurple.isSelected()){
            color = "Morado";
        }
        return color;
    }

    private void closeServerSocket(){ //------------------------------------------- CERRAR SOCKET
        nPlayers = 0;
        try {
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnServer.setStyle("-fx-background-color: GREEN;");

    }

    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean checkField(){
        String mensaje = "";
        if(txtPort.getText().isEmpty()){
            mensaje += "Port\n";
        }
        if(txtName.getText().isEmpty()){
            mensaje += "Name\n";
        }
        if(!mensaje.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Empty Fields");
            alert.setContentText(mensaje);
            alert.showAndWait();
            return false;
        }
        if(!port(txtPort.getText()) || !name(txtName.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Wrong values");
            alert.setContentText("Enter valid data");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void fillTable(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        tablePlayers.setItems(players);
    }

    public boolean port(String port){
        return port.matches("[0-9]{0,6}");
    }

    public boolean name(String name){
        return name.matches("[A-z]{0,10}");
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void update(Observable o, Object arg) {
        String sendPlayer = "";
        String valor = (String) arg;
        playerReceived = valor.split(";");
        if(playerReceived[0].equals("ServerClosed")){
            if(playerReceived[1].equals(String.valueOf(1))){
                Platform.runLater(()->{
                        main.GameServerWindow(socket,players);
                        main.getServerStage().close();
                });
            }else if(playerReceived[1].equals(String.valueOf(0))){
                closeServerSocket();
                players.clear();
                Platform.runLater(()->{
                    btnServer.setText("Create Game");
                    btnStart.setDisable(true);
                    txtCode.setVisible(false);
                });

            }

        } else{
            if(checkColorPlayer()){
                Player player = new Player(nPlayers,playerReceived[0],playerReceived[1]);
                players.add(player);
                fillTable();
                try {
                    sendPlayer += "Joined"+ ";";
                    sendPlayer += player.getId() + ";";
                    sendPlayer += player.getName() + ";";
                    sendPlayer += player.getColor();
                    bufferout.writeUTF(sendPlayer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                nPlayers--;
                try {
                    sendPlayer = "ColorSelected";
                    bufferout.writeUTF(sendPlayer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            bufferout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
