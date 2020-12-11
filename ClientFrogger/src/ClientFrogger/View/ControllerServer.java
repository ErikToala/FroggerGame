package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControllerServer {
    private Main main;

    @FXML
    void OnMouseClickedCancel(MouseEvent event) {
        main.getServerStage().close();

    }

    @FXML
    void OnMouseClickedPlay(MouseEvent event) {

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
