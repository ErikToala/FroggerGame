package ClientFrogger.View;

import ClientFrogger.Main;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerOnePlayer implements Initializable {

    private Main main;

    @FXML
    private Pane pane;

    public ImageView getImgCarro() {
        return ImgCarro;
    }

    @FXML
    private ImageView ImgCarro;

    private AnimationTimer timer;

    private List<Node> serpientes = new ArrayList<>();
    private Node rana;

    private Parent crearJuego() {

        rana = ranaInicio();

        pane.getChildren().add(rana);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                actualizar();
            }
        };
        timer.start();

        return pane;
    }

    private Node ranaInicio() {
        Image frog = new Image("file:src:/ClientFrogger/Resources/frog.png");
        ImageView imgFrog = new ImageView();
        imgFrog.setImage(frog);
        imgFrog.setFitHeight(38);
        imgFrog.setFitWidth(38);
        imgFrog.setTranslateY(600-39);
        imgFrog.setTranslateX(361);

        return imgFrog;
    }


    private Node esquivarSerpiente() {
        Image serpent = new Image("file:src:/ClientFrogger/Resources/serpiente.png");
        ImageView imgSerpent = new ImageView();
        imgSerpent.setImage(serpent);
        imgSerpent.setFitHeight(40);
        imgSerpent.setFitWidth(40);
        imgSerpent.setTranslateY((int)(Math.random() * 14) * 40);


        pane.getChildren().add(imgSerpent);
        return imgSerpent;
    }

    private void actualizar() {
        for (Node serpiente : serpientes)
            serpiente.setTranslateX(serpiente.getTranslateX() + Math.random() * 10);

        if (Math.random() < 0.2) {
            serpientes.add(esquivarSerpiente());
        }

        checarEstado();
    }

    private void checarEstado() {
        for (Node serpiente : serpientes) {
            if (serpiente.getBoundsInParent().intersects(rana.getBoundsInParent())) {
                rana.setTranslateX(0);
                rana.setTranslateY(600 - 39);
                return;
            }
        }

        if (rana.getTranslateY() <= 0) {
            timer.stop();


            HBox hBox = new HBox();
            hBox.setTranslateX(300);
            hBox.setTranslateY(250);
            pane.getChildren().add(hBox);

            Label label = new Label("FELICIDADES HAS GANADO");
            label.setScaleX(3);
            label.setScaleY(3);
            hBox.getChildren().add(label);


        }

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        crearJuego();
    }

    public void eventos(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                rana.setTranslateY(rana.getTranslateY() - 40);
                break;
            case S:
                rana.setTranslateY(rana.getTranslateY() + 40);
                break;
            case A:
                rana.setTranslateX(rana.getTranslateX() - 40);
                break;
            case D:
                rana.setTranslateX(rana.getTranslateX() + 40);
                break;
            default:
                break;
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
