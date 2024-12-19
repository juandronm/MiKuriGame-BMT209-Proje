package com.pokemonlikegame.mikuri_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuToGameBarController extends StartController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    public void menuyeDegisme() throws IOException {
        if(progressBar.getScene() != null){
            Stage window = (Stage) progressBar.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
                window.setScene(new Scene(root, 900, 700));
                window.setTitle("Mikuri - Menu");
                //window.setMaximized(true);

                Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
                window.setX((screenLayout.getWidth() - 900) / 2);
                window.setY((screenLayout.getHeight() - 700) / 2);
            }
        }
    }

}