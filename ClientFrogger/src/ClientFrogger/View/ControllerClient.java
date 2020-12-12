package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Player;
import ClientFrogger.Model.ThreadClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ControllerClient implements Observer{
    @FXML private TextField txtIP;
    @FXML private TextField txtPort;
    @FXML private TextField txtName;
    @FXML private RadioButton rdBFrogGreen;
    @FXML private ToggleGroup tGFroggy;
    @FXML private RadioButton rdBFrogRed;
    @FXML private RadioButton rdBFrogBlue;
    @FXML private RadioButton rdBFrogRose;
    @FXML private RadioButton rdBFrogGray;
    @FXML private RadioButton rdBFrogYellow;
    @FXML private RadioButton rdBFrogPurple;
    @FXML private Label lbStatus;
    @FXML private Button btnJoin;

    private Socket socket;
    private DataOutputStream bufferOut = null;
    private Thread hiloCliente;
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private Main main;

    @FXML
    void OnMouseClickedCancel(MouseEvent event) throws Exception {
        main.getClientStage().close();
        main.start(main.getMenuStage());
    }

    @FXML
    void OnMouseClickedJoin(MouseEvent event) {
        if(checkFields()){
            try {
                socket = new Socket(txtIP.getText(),Integer.valueOf(txtPort.getText()));
                bufferOut = new DataOutputStream(socket.getOutputStream());
                ThreadClient client = new ThreadClient(socket);
                client.addObserver(this);
                hiloCliente = new Thread(client);
                hiloCliente.start();
                bufferOut.writeUTF(getPlayer());
                bufferOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getPlayer(){
        String player = "";
        player += txtName.getText() + ";";
        if(rdBFrogGreen.isSelected()){
            player += "Normal";
        }
        if(rdBFrogRed.isSelected()){
            player += "Rojo";
        }
        if(rdBFrogBlue.isSelected()){
            player += "Azul";
        }
        if(rdBFrogRose.isSelected()){
            player += "Rosa";
        }
        if(rdBFrogGray.isSelected()){
            player += "Gris";
        }
        if(rdBFrogYellow.isSelected()){
            player += "Amarillo";
        }
        if(rdBFrogPurple.isSelected()){
            player += "Morado";
        }
        return player;
    }

    private boolean checkFields(){
        String mensaje = "";
        if(txtIP.getText().isEmpty()){
            mensaje += "Server Address\n";
        }
        if(txtPort.getText().isEmpty()){
            mensaje += "Port\n";
        }
        if(txtName.getText().isEmpty()){
            mensaje += "Player Name\n";
        }
        if(!mensaje.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText("Empty Fields");
            alert.setContentText(mensaje);
            alert.show();
            return false;
        }
        if(!port(txtPort.getText()) || !name(txtName.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText("Wrong values");
            alert.setContentText("Enter valid data");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public boolean port(String port){
        return port.matches("[0-9]{0,6}");
    }

    public boolean name(String name){
        return name.matches("[A-z]{0,10}");
    }

    @Override
    public void update(Observable o, Object arg) {
        String [] status = (String[]) arg;

        if(status[0].equals("ConnectedServer")){
            Player player = new Player(Integer.valueOf(status[1]),status[2],status[3]);
            players.add(player);
        }
        if(status[0].equals("Joined")){
            Player player = new Player(Integer.valueOf(status[1]),status[2],status[3]);
            players.add(player);
            lbStatus.setVisible(true);
            btnJoin.setDisable(true);
        }
        if(status[0].equals("Started")){ Platform.runLater(()->main.GameClientWindow(socket, players)); }
        if(status[0].equals("ColorSelected")){
            Platform.runLater(()-> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                alert.setHeaderText("Color de rana ya seleccionado");
                alert.setContentText("Selecciona otro color de rana");
                alert.showAndWait();
                }
            );
            closeClientSocket();
        }
        if(status[0].equals("ServerClosed")){
            Platform.runLater(()-> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("WARNING");
                        alert.setHeaderText("Server cerrado");
                        alert.setContentText("Cree un servidor o únase a un servidor encendido");
                        alert.showAndWait();
                        lbStatus.setVisible(false);
                        btnJoin.setDisable(false);
                    }
            );
            closeClientSocket();

        }
    }

    private void closeClientSocket(){
        hiloCliente.stop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
