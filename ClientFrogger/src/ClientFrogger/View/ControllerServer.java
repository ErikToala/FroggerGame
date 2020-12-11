package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;

public class ControllerServer {
    final static ObservableList<String> listClient = FXCollections.observableArrayList();
    private static ServerSocket servidor;
    private Socket socket;
    private DataOutputStream bufferout = null;
    private Main main;

    @FXML private ListView<String> client;

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

    @FXML
    void OnMouseClickedCancel(MouseEvent event) throws Exception {
        main.getServerStage().close();
        main.start(main.getMenuStage());
    }

    @FXML
    void OnMouseClickedCreateGame(MouseEvent event) {
        if(checkField()){
            if(port(txtPort.getText()) != true || name(txtName.getText()) != true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNNING");
                alert.setHeaderText("Wrong values");
                alert.setContentText("Enter valid data");
                alert.showAndWait();
            } else{
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
                                    bufferout = new DataOutputStream(socket.getOutputStream());
                                    bufferout.flush();
                                    System.out.println("Servidor en escucha");
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
    }

    @FXML
    void OnMouseClickedStart(MouseEvent event) {

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

    private void openServerSocket(){
        TextInputDialog modal = new TextInputDialog();
        modal.setTitle("ServerSocket");
        Optional<String> result = modal.showAndWait();
        if (result.isPresent()){
            try {
                servidor = new ServerSocket(Integer.valueOf(result.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        return true;
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
