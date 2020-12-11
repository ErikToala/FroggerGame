package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class ControllerOnlineOptions {
    private Main main;

    @FXML
    void OnMouseClickedEnterGame(MouseEvent event) {
        main.ClientWindow();
        //main.getOnlineStage().close();
    }

    @FXML
    void OnMouseClikedCreateGame(MouseEvent event) {
        main.ServerWindow();
        //main.getMenuStage().close();
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
