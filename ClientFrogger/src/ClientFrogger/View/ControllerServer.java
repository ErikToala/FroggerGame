package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Player;
import ClientFrogger.Model.ThreadServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class ControllerServer implements Observer {
    private static ServerSocket servidor;
    private Socket socket;
    private DataOutputStream bufferout = null;
    private Main main;
    private String [] playerReceived;
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private int nPlayers = -1;

    @FXML private RadioButton rdBFrogGreen;
    @FXML private ToggleGroup tGFroggy;
    @FXML private RadioButton rdBFrogRed;
    @FXML private RadioButton rdBFrogBlue;
    @FXML private RadioButton rdBFrogRose;
    @FXML private RadioButton rdBFrogGray;
    @FXML private RadioButton rdBFrogYellow;
    @FXML private RadioButton rdBFrogPurple;
    @FXML private Button btnStart;
    @FXML private TextField txtPort;
    @FXML private TextField txtName;
    @FXML private TextField txtCode;
    @FXML private TableView<Player> tablePlayers;
    @FXML private TableColumn<Player, Integer> columnId;
    @FXML private TableColumn<Player, String> columnName;
    @FXML private TableColumn<Player, String> columnColor;


    @FXML
    void OnMouseClickedCancel(MouseEvent event) throws Exception {
        //main.getServerStage().close();
        //main.start(main.getMenuStage());
        main.GameServerWindow();
    }

    @FXML
    void OnMouseClickedCreateGame(MouseEvent event) {
        if(checkField()){
            try {
                txtCode.setVisible(true);
                txtCode.setStyle("-fx-text-fill: Green; -fx-font-size: 15;");
                txtCode.setText(getIp());
                servidor = new ServerSocket(Integer.valueOf(txtPort.getText()));
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!servidor.isClosed()) {
                            try {
                                System.out.println("Servidor corriendo");
                                socket = servidor.accept();
                                nPlayers++;
                                bufferout = new DataOutputStream(socket.getOutputStream());
                                bufferout.flush();
                                System.out.println("Servidor en escucha");
                                ThreadServer server = new ThreadServer(socket);
                                server.addObserver(ControllerServer.this);
                                new Thread(server).start();

                            } catch (IOException e) {}
                        }

                    }
                });
                hilo.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            btnStart.setDisable(false);
        }
    }

    @FXML
    void OnMouseClickedStart(MouseEvent event) {

    }

    @Override
    public void update(Observable o, Object arg) {
        playerReceived = (String[]) arg;
        Player player = new Player(nPlayers,playerReceived[0],getColorFrog());
        players.add(player);
        fillTable();
    }



    private String getColorFrog(){
        /*String file = "file:ClientFrogger/src/ClientFrogger/Resources/";
        Image frog = null;
        if(rdBFrogGreen.isSelected()){
            frog = new Image(file+"frog.png");
        }
        if(rdBFrogRed.isSelected()){
            frog = new Image(file+"ranaRoja.png");
        }
        if(rdBFrogBlue.isSelected()){
            frog = new Image(file+"ranaAzul.png");
        }
        if(rdBFrogRose.isSelected()){
            frog = new Image(file+"ranaRosa.png");
        }
        if(rdBFrogGray.isSelected()){
            frog = new Image(file+"ranaGris.png");
        }
        if(rdBFrogYellow.isSelected()){
            frog = new Image(file+"ranaAmarilla.png");
        }
        if(rdBFrogPurple.isSelected()){
            frog = new Image(file+"ranaMorada.png");
        }
        return frog;*/
        String color = "";
        if(playerReceived[1].equals("0")){
            color = "Normal";
        }
        if(playerReceived[1].equals("1")){
            color = "Rojo";
        }
        if(playerReceived[1].equals("2")){
            color = "Azul";
        }
        if(playerReceived[1].equals("3")){
            color = "Rosa";
        }
        if(playerReceived[1].equals("4")){
            color = "Gris";
        }
        if(playerReceived[1].equals("5")){
            color = "Amarillo";
        }
        if(playerReceived[1].equals("6")){
            color = "Morado";
        }
        return color;
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


}
