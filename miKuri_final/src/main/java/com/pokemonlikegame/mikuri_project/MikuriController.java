package com.pokemonlikegame.mikuri_project;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
    private Button
            yesButton,
            noButton,
            newGameButton,
            creditsButton,
            exitGameButton,
            backMenuButton;

    @FXML
    private Slider volumeSlider;

    private MediaPlayer media;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        // Initialize background music
        try {
            String path = "src/main/resources/com/pokemonlikegame/mikuri_project/startMusic.mp3";
            Media ses = new Media(new File(path).toURI().toString());
            media = new MediaPlayer(ses);
            media.setAutoPlay(true);
        } catch (Exception e) {
            System.err.println("Error initializing media: " + e.getMessage());
        }

        // Check if volumeSlider is defined
        if (volumeSlider != null) {
            volumeSlider.setMin(0);
            volumeSlider.setMax(1);
            volumeSlider.setValue(media.getVolume());
            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                media.setVolume(newValue.doubleValue());
            });
        }
    }

    @FXML
    protected void onSlider() {
        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        volumeSlider.setValue(media.getVolume());

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            media.setVolume(newValue.doubleValue());
        });
    }

    @FXML
    protected void onNewGameButtonClick() {
        newGameButton.setVisible(false);
        exitGameButton.setVisible(false);
        creditsButton.setVisible(false);

        txt.setVisible(true);
        txt.setText("Your progress will gone.");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        yesButton.setOnAction(event -> {

            yesButton.setVisible(false);
            noButton.setVisible(false);
            txt.setVisible(false);
            try {
                gameDegisme();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            });

        noButton.setOnAction(event -> {
            txt.setText("");

            yesButton.setVisible(false);
            noButton.setVisible(false);
            txt.setVisible(false);

            newGameButton.setVisible(true);
            exitGameButton.setVisible(true);
        });
    }

    @FXML
    protected void onCreditsButtonClick() throws IOException {
        Stage window = (Stage) creditsButton.getScene().getWindow();

        if(window != null){
            Parent root = FXMLLoader.load
                    (Objects.requireNonNull(getClass().getResource("credits.fxml")));
            window.setScene(new Scene(root, 600, 450));
            window.setTitle("Mikuri - Menu");
        }
    }

    @FXML
    protected void onExitGameButtonClick() throws IOException{
        newGameButton.setDisable(true);
        exitGameButton.setDisable(true);
        creditsButton.setDisable(true);

        txt.setVisible(true);
        txt.setText("Are you sure?");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        yesButton.setOnAction(event -> {
            newGameButton.setVisible(false);
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

            newGameButton.setDisable(false);
            exitGameButton.setDisable(false);
            creditsButton.setDisable(false);
        });
    }

    @FXML
    public void menuBack() throws IOException {
        Stage window = (Stage) backMenuButton.getScene().getWindow();
        if(window != null){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
            window.setScene(new Scene(root, 600, 450));
            window.setTitle("Mikuri - Menu");
        }
    }

    public void gameDegisme() throws IOException {
        if(yesButton.getScene() != null){
            Stage window = (Stage) yesButton.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
                window.setScene(new Scene(root, 600, 450));
                window.setTitle("Mikuri - Menu");
            }
        }
    }
}