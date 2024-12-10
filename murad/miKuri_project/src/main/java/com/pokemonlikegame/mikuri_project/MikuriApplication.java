package com.pokemonlikegame.mikuri_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class MikuriApplication extends Application {

    private Stage startStage;
    private Stage inGameStage;

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        startStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader
                (MikuriApplication.class.getResource("mikuri-StaLoaScreen.fxml"));
        AnchorPane startRoot = fxmlLoader.load();

        MikuriController controller = fxmlLoader.getController();
        controller.setApp(this);

        String introMusic = "src/main/resources/com/pokemonlikegame/mikuri_project/startMusic.mp3";

        Media media = new Media(new File(introMusic).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        startRoot.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        startRoot.setOnMouseDragged(event -> {
            startStage.setX(event.getScreenX() - xOffset);
            startStage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(startRoot);
        startStage.initStyle(StageStyle.UNDECORATED);
        startStage.setScene(scene);
        startStage.show();
    }

    public void update() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader
                    (MikuriApplication.class.getResource("mikuri-inGameScreen.fxml"));
            AnchorPane
                    inGameRoot = fxmlLoader.load();

            if (inGameStage == null) {
                inGameStage = new Stage();
            }

            Scene newScene = new Scene(inGameRoot);
            inGameStage.setFullScreen(true);
            inGameStage.initStyle(StageStyle.UNDECORATED);
            inGameStage.setScene(newScene);
            inGameStage.show();

            startStage.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}