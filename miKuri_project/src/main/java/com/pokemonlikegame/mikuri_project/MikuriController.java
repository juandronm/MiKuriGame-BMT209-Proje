package com.pokemonlikegame.mikuri_project;

import Functionality.MikuriMenu;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MikuriController implements Initializable, MikuriMenu {
    @FXML
    private Label txt;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button exitGameButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView miKuriTitle;
    @FXML
    private ImageView soundIcon;
    @FXML
    private AnchorPane credits;

    private MediaPlayer media;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        credits.setVisible(false);
        backMenuButton.setVisible(false);
        try {
            URL resource = getClass().getResource("/music/startMusic.mp3");
            assert resource != null;
            Media ses = new Media(resource.toString());
            media = new MediaPlayer(ses);
            media.setAutoPlay(true);
        } catch (Exception e) {
            System.err.println("Error initializing media: " + e.getMessage());
        }

        // Check if volumeSlider is defined
        if (media != null) {
            volumeSlider.setMin(0);
            volumeSlider.setMax(1);
            volumeSlider.setValue(media.getVolume());
            volumeSlider.valueProperty().addListener(( _, _, newValue) -> {
                media.setVolume(newValue.doubleValue());
            });
        }
    }

    @FXML
    @Override
    public void onSlider() {
        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        volumeSlider.setValue(media.getVolume());

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            media.setVolume(newValue.doubleValue());
        });
    }

    @FXML
    @Override
    public void onNewGameButtonClick() {
        newGameButton.setVisible(false);
        exitGameButton.setVisible(false);
        creditsButton.setVisible(false);
        volumeSlider.setVisible(false);
        miKuriTitle.setVisible(false);
        soundIcon.setVisible(false);


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

            soundIcon.setVisible(true);
            newGameButton.setVisible(true);
            exitGameButton.setVisible(true);
            creditsButton.setVisible(true);
            volumeSlider.setVisible(true);
            miKuriTitle.setVisible(true);

        });
    }

    @FXML
    @Override
    public void onExitGameButtonClick() {
        newGameButton.setDisable(true);
        exitGameButton.setDisable(true);
        creditsButton.setDisable(true);
        volumeSlider.setDisable(true);
        soundIcon.setVisible(false);
        miKuriTitle.setVisible(false);

        txt.setVisible(true);
        txt.setText("Are you sure?");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        yesButton.setOnAction(event -> {
            newGameButton.setVisible(false);
            exitGameButton.setVisible(false);
            creditsButton.setVisible(false);
            volumeSlider.setVisible(false);
            soundIcon.setVisible(false);

            yesButton.setDisable(true);
            noButton.setDisable(true);

            txt.setText("Exiting...");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                yesButton.setVisible(false);
                noButton.setVisible(false);
                txt.setVisible(false);
                System.exit(0);
            }); pause.play();
        });

        noButton.setOnAction( _ -> {
            txt.setText("");

            yesButton.setVisible(false);
            noButton.setVisible(false);
            txt.setVisible(false);

            newGameButton.setDisable(false);
            exitGameButton.setDisable(false);
            creditsButton.setDisable(false);
            volumeSlider.setDisable(false);
            miKuriTitle.setVisible(true);
            soundIcon.setVisible(true);
        });
    }
    @FXML
    public void menuBack() throws IOException {
        newGameButton.setVisible(true);
        exitGameButton.setVisible(true);
        creditsButton.setVisible(true);
        volumeSlider.setVisible(true);
        soundIcon.setVisible(true);
        credits.setVisible(false);
        backMenuButton.setVisible(false);
    }

    @FXML
    public void setCredits(){
        newGameButton.setVisible(false);
        exitGameButton.setVisible(false);
        creditsButton.setVisible(false);
        volumeSlider.setVisible(false);
        soundIcon.setVisible(false);
        credits.setVisible(true);
        backMenuButton.setVisible(true);
    }
    public void gameDegisme() throws IOException {
        if(yesButton.getScene() != null){
            Stage window = (Stage) yesButton.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuToGame.fxml")));
                window.setScene(new Scene(root, 600, 450));

                Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
                window.setX((screenLayout.getWidth() - 600) / 2);
                window.setY((screenLayout.getHeight() - 450) / 2);
            }
        }
    }
}