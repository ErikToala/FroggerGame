package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ControllerGameServer {
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView serpentYellow;

    @FXML
    private ImageView rightTrunk;

    @FXML
    private ImageView leftTrunk;

    @FXML
    private ImageView frogImg;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
}
