package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControllerMenu {
    private Main main;

    @FXML
    void OnMouseClickedHelp(MouseEvent event) {
        main.helpWindow();
        //main.getMenuStage().close();
    }

    @FXML
    void OnMouseClickedOnePlayer(MouseEvent event) {
        main.OnePlayerWindow();
        //main.getMenuStage().close();
    }

    @FXML
    void OnMouseClickedTwoPlayer(MouseEvent event) {
        main.TwoPlayerWindow();
        //main.getMenuStage().close();
    }

    @FXML
    void OnMouseClickOnline(MouseEvent event) {
        main.OnlineOptionsWindow();
        //main.getMenuStage().close();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
