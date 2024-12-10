package com.pokemonlikegame.mikuri_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuToGameBarController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    double progress;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        try {
            automaticIncrease();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void menuyeDegisme() throws IOException {
        if(progressBar.getScene() != null){
            Stage window = (Stage) progressBar.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mikuri-InGameScreen.fxml")));
                window.setScene(new Scene(root, 600, 450));
                window.setTitle("Mikuri - Menu");
            }
        }
    }

    @FXML
    public void automaticIncrease() throws InterruptedException {
        progressBar.setStyle("-fx-accent: #00ff00");
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                event -> {
                    if (progress < 1.0){
                        progress += 0.01;
                        progressBar.setProgress(progress);
                    } else {
                        try {
                            menuyeDegisme();
                            //((Timeline) event.getSource()).stop();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}