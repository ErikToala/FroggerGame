package ClientFrogger.View;

import ClientFrogger.Main;
import ClientFrogger.Model.ThreadClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ControllerClient {
    @FXML
    private TextField txtIP;

    @FXML
    private TextField txtPort;

    @FXML
    private TextField txtName;

    @FXML
    private RadioButton rdBFrogGreen;

    @FXML
    private ToggleGroup tGFroggy;

    @FXML
    private RadioButton rdBFrogRed;

    @FXML
    private RadioButton rdBFrogBlue;

    @FXML
    private RadioButton rdBFrogRose;

    @FXML
    private RadioButton rdBFrogGray;

    @FXML
    private RadioButton rdBFrogYellow;

    @FXML
    private RadioButton rdBFrogPurple;

    private Socket socket;
    private DataOutputStream bufferOut = null;
    private Main main;

    @FXML
    void OnMouseClickedCancel(MouseEvent event) { main.getClientStage().close(); }

    @FXML
    void OnMouseClickedJoin(MouseEvent event) {
        try {
            socket = new Socket(txtIP.getText(),Integer.valueOf(txtPort.getText()));
            bufferOut = new DataOutputStream(socket.getOutputStream());
            bufferOut.flush();
            ThreadClient client = new ThreadClient(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image getColorFrog(){
        String file = "file:ClientFrogger/src/ClientFrogger/Resources/";
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
        return frog;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
