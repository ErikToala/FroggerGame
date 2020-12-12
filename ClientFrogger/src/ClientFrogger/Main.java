package ClientFrogger;

import ClientFrogger.Model.Player;
import ClientFrogger.View.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.Socket;

public class Main extends Application {

    private Stage helpStage;
    private Stage menuStage;
    private Stage OnePlayerStage;
    private Stage twoPlayerStage;
    private Stage serverStage;
    private Stage onlineOptionsStage;
    private Stage clientStage;
    private Stage gameClientStage;
    private Stage gameServerStage;
    private ControllerGameClient controllerGameClient;
    private ControllerGameServer controllerGameServer;


    @Override
    public void start(Stage menuStage) throws Exception{
        this.menuStage = menuStage;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/menuFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerMenu ControllerInicio = loader.getController();
            ControllerInicio.setMain(this);
            Scene scene = new Scene(root);
            menuStage.setTitle("Inicio");
            menuStage.setResizable(false);
            menuStage.setScene(scene);
            menuStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void helpWindow() {
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

    public void OnlineOptionsWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/OnlineOptionsFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerOnlineOptions controllerOnlineOptions = loader.getController();
            controllerOnlineOptions.setMain(this);
            Stage onlineOptionsStage = new Stage();
            Scene scene = new Scene(root);
            this.onlineOptionsStage = onlineOptionsStage;
            onlineOptionsStage.setResizable(false);
            onlineOptionsStage.setScene(scene);
            onlineOptionsStage.setTitle("Private Game");
            onlineOptionsStage.show();
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
            serverStage.setTitle("Configuration Server");
            serverStage.show();
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
            clientStage.setTitle("Join the Game");
            clientStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void GameServerWindow(Socket socket, ObservableList<Player> players){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/GameServerFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerGameServer controllerGameServer2 = loader.getController();
            this.controllerGameServer = controllerGameServer2;
            controllerGameServer.setMain(this);
            controllerGameServer.setSocket(socket);
            controllerGameServer.setPlayers(players);
            Stage gameServerStage = new Stage();
            Scene scene = new Scene(root);
            this.gameServerStage = gameServerStage;
            gameServerStage.setResizable(false);
            scene.setOnKeyPressed(event ->{
                controllerGameServer.eventos(event);
            });
            gameServerStage.setScene(scene);
            gameServerStage.setTitle("Server Game");
            gameServerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void GameClientWindow(Socket socket, ObservableList<Player> players){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/GameClientFrogger.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ControllerGameClient controllerGameClient2 = loader.getController();
            this.controllerGameClient = controllerGameClient2;
            controllerGameClient.setMain(this);
            controllerGameClient.setSocket(socket);
            controllerGameClient.setPlayers(players);
            Stage gameClientStage = new Stage();
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event ->{
                controllerGameClient.eventos(event);
            });
            this.gameClientStage = gameClientStage;
            gameClientStage.setResizable(false);
            gameClientStage.setScene(scene);
            gameClientStage.setTitle("Client Game");
            gameClientStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getHelpStage() {
        return helpStage;
    }

    public Stage getMenuStage() { return menuStage; }

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

    public Stage getOnlineOptionsStage() {
        return onlineOptionsStage;
    }

    public ControllerGameClient getControllerGameClient() {
        return controllerGameClient;
    }

    public ControllerGameServer getControllerGameServer() {
        return controllerGameServer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
