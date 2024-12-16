package com.pokemonlikegame.mikuri_project;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InGameController extends MikuriController implements Initializable {
    @FXML
    private ImageView
            environment;
    @FXML
    private Button
            inGameToMenu,
            continueGame,
            exitFromInGame,
            evetButton,
            hayirButton;
    @FXML
    private Label pauseMenu;
    @FXML
    private AnchorPane anchorPane;

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        environment.fitWidthProperty().bind(anchorPane.widthProperty());
        environment.fitHeightProperty().bind(anchorPane.heightProperty());

        anchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        anchorPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        anchorPane.setOnKeyPressed(this::pause);
        anchorPane.requestFocus();
    }

    @FXML
    void pause(KeyEvent hareket) {
        if(hareket.getCode() == KeyCode.ESCAPE){
            System.out.println("ESCAPE");
            environment.setVisible(false);
            evetButton.setVisible(false);
            hayirButton.setVisible(false);
        }
    }
    @FXML
    public void menuBack() throws IOException {
        Stage window = (Stage) inGameToMenu.getScene().getWindow();
        if(window != null){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mikuri-StaLoaScreen.fxml")));
            window.setScene(new Scene(root, 600, 450));
            window.setTitle("Mikuri - Menu");

            Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
            window.setX((screenLayout.getWidth() - 600) / 2);
            window.setY((screenLayout.getHeight() - 450) / 2);
        }
    }
    @FXML
    protected void onExitGameButtonClick() {
        inGameToMenu.setVisible(false);
        continueGame.setVisible(false);
        exitFromInGame.setVisible(false);

        pauseMenu.setText("Are you sure?");

        evetButton.setVisible(true);
        hayirButton.setVisible(true);

        evetButton.setOnAction(event -> {

            evetButton.setDisable(true);
            hayirButton.setDisable(true);
            pauseMenu.setText("Exiting...");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                evetButton.setVisible(false);
                hayirButton.setVisible(false);
                pauseMenu.setVisible(false);
                System.exit(0);
            }); pause.play();
        });

        hayirButton.setOnAction(event -> {
            pauseMenu.setText("Game paused");

            evetButton.setVisible(false);
            hayirButton.setVisible(false);


            inGameToMenu.setVisible(true);
            continueGame.setVisible(true);
            exitFromInGame.setVisible(true);
        });
    }
    @FXML
    protected void continueGame() {
        environment.setVisible(true);
    }
}