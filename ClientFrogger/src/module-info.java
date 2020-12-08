module ClientFrogger {

    requires javafx.fxml;
    requires javafx.controls;

    opens ClientFrogger.Model to javafx.base;
    opens ClientFrogger.View to javafx.fxml;

    exports ClientFrogger;
}