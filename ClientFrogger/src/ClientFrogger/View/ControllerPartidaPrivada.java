package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class ControllerPartidaPrivada {
    private Main main;

    @FXML
    void OnMouseClickedEnterGame(MouseEvent event) {
    }

    @FXML
    void OnMouseClikedCreateGame(MouseEvent event) {
        main.ServerWindows();
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
