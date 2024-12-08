package com.pokemonlikegame.mikuri_project;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class MikuriController {
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
                System.exit(0);
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
}