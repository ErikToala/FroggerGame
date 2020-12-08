package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControllerInicio {
    private Main main;

    @FXML
    void OnMouseClickedHelp(MouseEvent event) {
        main.helpWindows();
    }

    @FXML
    void OnMouseClickedOnePlayer(MouseEvent event) {
        main.OnePlayerWindow();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
