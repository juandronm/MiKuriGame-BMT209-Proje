package com.example.pokemoncardsgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button credits;
    @FXML
    private Button play;
    @FXML
    private Button exitGame;
    @FXML
    private VBox scenePane;

    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void setPlay() throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("in_game.fxml")));
            Stage window = (Stage) play.getScene().getWindow();
            window.setScene(new Scene(root, 700, 400));
            window.setTitle("Pokemon Cards Game");
    }

    @FXML
    public void setExit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to log out!!!");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            if(scenePane != null) {
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
            }else{
                System.out.println("System is null");
            }
        }
    }

    public void setCredits() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("credits.fxml")));
        Stage window = (Stage) play.getScene().getWindow();
        window.setScene(new Scene(root, 700, 400));
        window.setTitle("Pokemon Cards Game - Credits");
    }
}
