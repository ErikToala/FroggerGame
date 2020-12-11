package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControllerMenu {
    private Main main;

    @FXML
    void OnMouseClickedHelp(MouseEvent event) {
        main.getMenuStage().close();
        main.helpWindow();

    }

    @FXML
    void OnMouseClickedOnePlayer(MouseEvent event) {
        main.OnePlayerWindow();
        main.getMenuStage().close();
    }

    @FXML
    void OnMouseClickedTwoPlayer(MouseEvent event) {
        main.TwoPlayerWindow();
        main.getMenuStage().close();
    }

    @FXML
    void OnMouseClickOnline(MouseEvent event) {
        main.getMenuStage().close();
        main.OnlineOptionsWindow();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
