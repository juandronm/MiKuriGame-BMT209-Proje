package com.pokemonlikegame.mikuri_project;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MikuriController {
    @FXML
    private Label txt;
    @FXML
    private Button
            continueButton,
            newGameButton,
            optionsButton,
            exitGameButton,
            noButton,
            yesButton;
    @FXML
    private ImageView mikuriTextImage;
    @FXML
    private ProgressBar loadingProgressBar;
    private MikuriApplication app;

    public void setApp(MikuriApplication app) {
        this.app = app;
    }

    @FXML
    protected void onContinueButtonClick() {
        continueButton.setVisible(false);
        newGameButton.setVisible(false);
        optionsButton.setVisible(false);
        exitGameButton.setVisible(false);

    }

    @FXML
    protected void onNewGameButtonClick() {
        continueButton.setVisible(false);
        newGameButton.setVisible(false);
        optionsButton.setVisible(false);
        exitGameButton.setVisible(false);
        mikuriTextImage.setVisible(false);

//        txt.setVisible(true);
//        txt.setText("Your progress will be erased.\nAre you sure?");
//
//        yesButton.setVisible(true);
//        noButton.setVisible(true);
//
//        yesButton.setOnAction(event -> {
//            txt.setText("Creating New Game...");
//
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(e -> {
//                yesButton.setVisible(false);
//                noButton.setVisible(false);
//                txt.setVisible(false);
//                onLoadingProgress();
//            }); pause.play();
//        });
//
//        noButton.setOnAction(event -> {
//            txt.setText("");
//
//            yesButton.setVisible(false);
//            noButton.setVisible(false);
//            txt.setVisible(false);
//
//            continueButton.setVisible(true);
//            newGameButton.setVisible(true);
//            optionsButton.setVisible(true);
//            exitGameButton.setVisible(true);
//            mikuriTextImage.setVisible(true);
//        });

        txt.setVisible(true);
        txt.setText("Creating New Game...");
        loadingProgressBar.setVisible(true);

        Timeline timeline = new Timeline();
        loadingProgressBar.setProgress(0);

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(50), event -> {
                    double progress = loadingProgressBar.getProgress();
                    loadingProgressBar.setProgress(progress + 0.01);
                })
        );

        timeline.setCycleCount(100);
        timeline.setOnFinished(event -> {
            loadingProgressBar.setVisible(false);
            txt.setText("Game Loaded!");
            app.update();
        });

        timeline.play();
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

        txt.setVisible(true);
        txt.setText("Are you sure?");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        yesButton.setOnAction(event -> {
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
        });
    }

    @FXML
    protected void onLoadingProgress() {
        loadingProgressBar.setVisible(true);
        System.exit(0);
    }
}