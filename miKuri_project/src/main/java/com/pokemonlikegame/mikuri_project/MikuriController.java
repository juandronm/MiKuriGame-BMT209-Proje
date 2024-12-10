package com.pokemonlikegame.mikuri_project;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MikuriController implements Initializable {
    @FXML
    private Label txt;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button exitGameButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Button creditsButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        String path = "src/main/resources/com/pokemonlikegame/mikuri_project/startMusic.mp3";
        Media ses = new Media(new File(path).toURI().toString());
        MediaPlayer media = new MediaPlayer(ses);
        media.setAutoPlay(true);
    }

    @FXML
    protected void onContinueButtonClick() {
        continueButton.setVisible(false);
        newGameButton.setVisible(false);
        optionsButton.setVisible(false);
        exitGameButton.setVisible(false);
        creditsButton.setVisible(false);

    }

    @FXML
    protected void onNewGameButtonClick() {
        continueButton.setVisible(false);
        newGameButton.setVisible(false);
        optionsButton.setVisible(false);
        exitGameButton.setVisible(false);

        txt.setVisible(true);
        txt.setText("Your progress will gone.");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        yesButton.setOnAction(event -> {
            txt.setText("Creating New Game...");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                yesButton.setVisible(false);
                noButton.setVisible(false);
                txt.setVisible(false);
                try {
                    gameDegisme();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                //System.exit(0);
            }); pause.play();
        });

        noButton.setOnAction(event -> {
            txt.setText("");

            yesButton.setVisible(false);
            noButton.setVisible(false);
            txt.setVisible(false);

            continueButton.setVisible(true);
            newGameButton.setVisible(true);
            optionsButton.setVisible(true);
            exitGameButton.setVisible(true);
        });
    }

    @FXML
    protected void onOptionsButtonClick() {
        continueButton.setDisable(true);
        newGameButton.setDisable(true);
        optionsButton.setDisable(true);
        exitGameButton.setDisable(true);

    }

    @FXML
    protected void onExitGameButtonClick() {
        continueButton.setDisable(true);
        newGameButton.setDisable(true);
        optionsButton.setDisable(true);
        exitGameButton.setDisable(true);
        creditsButton.setDisable(true);

        txt.setVisible(true);
        txt.setText("Are you sure?");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        yesButton.setOnAction(event -> {
            continueButton.setVisible(false);
            newGameButton.setVisible(false);
            optionsButton.setVisible(false);
            exitGameButton.setVisible(false);
            creditsButton.setVisible(false);
            txt.setText("Exiting...");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                    yesButton.setVisible(false);
                    noButton.setVisible(false);
                    txt.setVisible(false);
                    System.exit(0);
            }); pause.play();
        });

        noButton.setOnAction(event -> {
            txt.setText("");

            yesButton.setVisible(false);
            noButton.setVisible(false);
            txt.setVisible(false);

            continueButton.setDisable(false);
            newGameButton.setDisable(false);
            optionsButton.setDisable(false);
            exitGameButton.setDisable(false);
            creditsButton.setDisable(false);
        });
    }
    @FXML
    public void menuBack() throws IOException {
            Stage window = (Stage) backMenuButton.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mikuri-StaLoaScreen.fxml")));
                window.setScene(new Scene(root, 600, 450));
                window.setTitle("Mikuri - Menu");
            }
    }

    @FXML
    public void setCredits() throws IOException {
        Stage window = (Stage) creditsButton.getScene().getWindow();
        if(window != null){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("credits.fxml")));
            window.setScene(new Scene(root, 600, 450));
            window.setTitle("Mikuri - Menu");
        }
    }
    public void gameDegisme() throws IOException {
        if(yesButton.getScene() != null){
            Stage window = (Stage) yesButton.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuToGame.fxml")));
                window.setScene(new Scene(root, 600, 450));
                window.setTitle("Mikuri - Menu");
            }
        }
    }
}