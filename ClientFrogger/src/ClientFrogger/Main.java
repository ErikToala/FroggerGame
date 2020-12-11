package ClientFrogger;

import ClientFrogger.View.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage helpStage;
    private Stage inicioStage;
    private Stage OnePlayerStage;
    private Stage twoPlayerStage;
    private Stage serverStage;
    private Stage privateGameStage;
    private Stage clientStage;
    private Stage onlineStage;


    @Override
    public void start(Stage inicioStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/InicioFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerInicio ControllerInicio = loader.getController();
            ControllerInicio.setMain(this);
            Scene scene = new Scene(root);
            inicioStage.setTitle("Inicio");
            inicioStage.setResizable(false);
            inicioStage.setScene(scene);
            inicioStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void helpWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/HelpFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerHelp controllerHelp = loader.getController();
            controllerHelp.setMain(this);
            Stage helpStage = new Stage();
            Scene scene = new Scene(root);
            this.helpStage = helpStage;
            helpStage.setResizable(false);
            helpStage.setScene(scene);
            helpStage.setTitle("Help");
            helpStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void OnePlayerWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/OnePlayerFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerOnePlayer controllerOnePlayer = loader.getController();
            controllerOnePlayer.setMain(this);
            Stage OnePlayerStage = new Stage();
            Scene scene = new Scene(root);
            this.OnePlayerStage = OnePlayerStage;
            OnePlayerStage.setResizable(false);
            scene.setOnKeyPressed(event ->{
                controllerOnePlayer.eventos(event);
            });
            OnePlayerStage.setScene(scene);
            OnePlayerStage.setTitle("Frogger One Player");
            OnePlayerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void TwoPlayerWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TwoPlayerFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerTwoPlayer controllerTwoPlayer = loader.getController();
            controllerTwoPlayer.setMain(this);
            Stage twoPlayerStage = new Stage();
            Scene scene = new Scene(root);
            this.twoPlayerStage = twoPlayerStage;
            twoPlayerStage.setResizable(false);
            scene.setOnKeyPressed(event ->{
                controllerTwoPlayer.eventos(event);
            });
            twoPlayerStage.setScene(scene);
            twoPlayerStage.setTitle("Frogger Two Player");
            twoPlayerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void ServerWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/ServerFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerServer controllerServer = loader.getController();
            controllerServer.setMain(this);
            Stage serverStage = new Stage();
            Scene scene = new Scene(root);
            this.serverStage = serverStage;
            serverStage.setResizable(false);
            serverStage.setScene(scene);
            serverStage.setTitle("Login Server");
            serverStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void PrivateGameWindows(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/PartidaPrivadaFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerPartidaPrivada controllerPartidaPrivada = loader.getController();
            controllerPartidaPrivada.setMain(this);
            Stage privateGameStage = new Stage();
            Scene scene = new Scene(root);
            this.privateGameStage = privateGameStage;
            privateGameStage.setResizable(false);
            privateGameStage.setScene(scene);
            privateGameStage.setTitle("Private Game");
            privateGameStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void ClientWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/ClientFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerClient controllerClient = loader.getController();
            controllerClient.setMain(this);
            Stage clientStage = new Stage();
            Scene scene = new Scene(root);
            this.clientStage = clientStage;
            clientStage.setResizable(false);
            clientStage.setScene(scene);
            clientStage.setTitle("Client Game");
            clientStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void OnlineWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/OnlineFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerOnline controllerOnline = loader.getController();
            controllerOnline.setMain(this);
            Stage onlineStage = new Stage();
            Scene scene = new Scene(root);
            this.onlineStage = onlineStage;
            onlineStage.setResizable(false);
            onlineStage.setScene(scene);
            onlineStage.setTitle("Online Game");
            onlineStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getHelpStage() {
        return helpStage;
    }

    public Stage getInicioStage() {
        return inicioStage;
    }

    public Stage getTwoPlayerStage() {
        return twoPlayerStage;
    }

    public Stage getOnePlayerStage() {
        return OnePlayerStage;
    }

    public Stage getServerStage() {
        return serverStage;
    }

    public Stage getClientStage() { return clientStage; }

    public Stage getOnlineStage() { return onlineStage; }

    public Stage getPrivateGameStage() { return privateGameStage; }

    public static void main(String[] args) {
        launch(args);
    }
}
