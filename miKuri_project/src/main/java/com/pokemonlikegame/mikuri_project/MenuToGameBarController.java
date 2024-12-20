package com.pokemonlikegame.mikuri_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuToGameBarController extends StartController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    public void menuyeDegisme() throws IOException {
        if(progressBar.getScene() != null){
            Stage window = (Stage) progressBar.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mikuri-InGameScreen.fxml")));
                window.setScene(new Scene(root, 900, 700));

                Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
                window.setX((screenLayout.getWidth() - 900) / 2);
                window.setY((screenLayout.getHeight() - 700) / 2);
            }
        }
    }

}