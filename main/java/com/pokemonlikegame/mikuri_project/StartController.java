package com.pokemonlikegame.mikuri_project;

import Functionality.TransitionControllerFunctionality;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController extends TransitionControllerFunctionality implements Initializable {

    @FXML
    private Label count;
    @FXML
    private ProgressBar progressBar;
    private double progress;

    //ilk çalıştırılacak ögeleri çalıştırır
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        try {
            automaticIncrease();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //yükleme ekranının çalıştırmasını sağlar
    @FXML
    @Override
    public void automaticIncrease() throws InterruptedException{
        progressBar.setStyle("-fx-accent:  #ff77cc");

        //yükleme barını ayarlar
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(4),
                _ -> {
                    if (progress < 1.0){
                        progress += 0.002;
                        count.setText((int) (progress * 100) + "%");
                        progressBar.setProgress(progress);
                    } else {
                        //bar dolduğunda çalışır
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

    //yükleme ekranından ana menüye geçişini sağlar
    @FXML
    @Override
    public void menuyeDegisme() throws IOException {
        if(progressBar.getScene() != null){
            //yükleme ekranını window nesnesine atar
            Stage window = (Stage) progressBar.getScene().getWindow();
            if(window != null){
                //window nesnesindeki sahneyi yeni sahneye atama yapılır
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mikuri-StaLoaScreen.fxml")));
                window.setScene(new Scene(root, 600, 450));

                //ayarlama yapma
                Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
                window.setX((screenLayout.getWidth() - 600) / 2);
                window.setY((screenLayout.getHeight() - 450) / 2);
            }
        }
    }
}