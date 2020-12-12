package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.Player;
import ClientFrogger.Model.ThreadClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.DataInput;
import java.io.DataInputStream;
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

    private Socket socket;
    private DataOutputStream bufferOut = null;
    private Main main;

    @FXML
    void OnMouseClickedCancel(MouseEvent event) {
        //main.getClientStage().close();
        main.GameClientWindow();
    }

    @FXML
    void OnMouseClickedJoin(MouseEvent event) {
        if(checkFields()){
            try {
                socket = new Socket(txtIP.getText(),Integer.valueOf(txtPort.getText()));
                bufferOut = new DataOutputStream(socket.getOutputStream());
                ThreadClient client = new ThreadClient(socket);
                client.addObserver(this);
                new Thread(client).start();
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
            player += 0;
        }
        if(rdBFrogRed.isSelected()){
            player += 1;
        }
        if(rdBFrogBlue.isSelected()){
            player += 2;
        }
        if(rdBFrogRose.isSelected()){
            player += 3;
        }
        if(rdBFrogGray.isSelected()){
            player += 4;
        }
        if(rdBFrogYellow.isSelected()){
            player += 5;
        }
        if(rdBFrogPurple.isSelected()){
            player += 6;
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
            alert.setTitle("WARNNING");
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
        String status = (String) arg;
        if(status.equals("Joined")){

        }else if(status.equals("ColorSelected")){
            lbStatus.setText("Color ya elegido por otro jugador");
            lbStatus.setTextFill(Color.RED);
        }
    }
}
