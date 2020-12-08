module ServerFrogger {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.logging;

    opens ServerFrogger.View to javafx.fxml;

    exports ServerFrogger;
}