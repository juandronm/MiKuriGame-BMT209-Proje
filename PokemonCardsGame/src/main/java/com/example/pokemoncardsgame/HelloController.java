package com.example.pokemoncardsgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

import java.net.URL;
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

    double progress;

    @FXML
    private void clickhere() { welcomeText.setText("Hello hello hello!");}

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        progressBar.setStyle("-fx-accent: #00ff00");
    }

    @FXML
    public void automaticIncrease() throws InterruptedException {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                event -> {
                    if (progress < 1.0){
                        progress += 0.01;
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