package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class ControllerServer {
    final static ObservableList<String> listClient = FXCollections.observableArrayList();
    private static ServerSocket servidor;
    private Socket socket;
    private DataOutputStream bufferout = null;
    private Main main;

    @FXML private RadioButton rdBFrogGreen;
    @FXML private ToggleGroup tGFroggy;
    @FXML private RadioButton rdBFrogRed;
    @FXML private RadioButton rdBFrogBlue;
    @FXML private RadioButton rdBFrogRose;
    @FXML private RadioButton rdBFrogGray;
    @FXML private RadioButton rdBFrogYellow;
    @FXML private RadioButton rdBFrogPurple;
    @FXML private TextField txtIP;
    @FXML private TextField txtPort;
    @FXML private TextField txtName;
    @FXML private TextField txtCode;

    @FXML
    void OnMouseClickedCancel(MouseEvent event) {
        main.getServerStage().close();

    }

    @FXML
    void OnMouseClickedPlay(MouseEvent event) {
        try {
            txtCode.setVisible(true);
            txtCode.setStyle("-fx-text-fill: Green; -fx-font-size: 15;");
            txtCode.setText(getIp());
        } catch (Exception e) {
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

    public void setMain(Main main) {
        this.main = main;
    }
}
