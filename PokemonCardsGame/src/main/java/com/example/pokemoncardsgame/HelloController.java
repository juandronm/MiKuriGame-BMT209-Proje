package com.example.pokemoncardsgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label welcomeText;
    @FXML
    private Label tryText;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label labelProgress;
    @FXML
    private Button pressButtonTry;
    @FXML
    ImageView menuImage;


    double progress;
    private Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //progressBar.setStyle("-fx-accent: #00ff00");
    }

    @FXML
    public void menuyeDegisme() throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
            Stage window = (Stage) pressButtonTry.getScene().getWindow();
            window.setScene(new Scene(root, 700, 400));
    }

    @FXML
    public void automaticIncrease() throws InterruptedException {
        progressBar.setStyle("-fx-accent: #00ff00");
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                event -> {
                    if (progress < 1.0){
                        progress += 0.1;
                        progressBar.setProgress(progress);
                    } else {
                        ((Timeline) event.getSource()).stop();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    @FXML
    protected void onHelloButtonClick() {
        //tryText.setGraphic(welcomeText);
        tryText.setText("Welcome to JavaFX Application!");
    }
}