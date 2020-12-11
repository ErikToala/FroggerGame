package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerHelp {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void OnMouseClikedInicio(MouseEvent event) throws Exception {
        main.getHelpStage().close();
        main.start(main.getMenuStage());
    }
}
