package com.pokemonlikegame.mikuri_project;

import Functionality.MikuriMenuFunctionality;
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
import javafx.scene.control.TextArea;
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

public class MikuriController implements Initializable, MikuriMenuFunctionality {
    @FXML
    private Label txt;
    @FXML
    private TextArea victoriesText;
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
    private Button victoriesButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView miKuriTitle;
    @FXML
    private ImageView soundIcon;
    @FXML
    private AnchorPane credits;
    @FXML
    private AnchorPane victoryWindow;

    public static MediaPlayer media;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        credits.setVisible(false);
        backMenuButton.setVisible(false);
        victoryWindow.setVisible(false);

        //ses dosyasının çalışmasını dener
        try {
            URL resource = getClass().getResource("/music/startMusic.mp3");
            assert resource != null;
            Media ses = new Media(resource.toString());
            media = new MediaPlayer(ses);
            media.setCycleCount(MediaPlayer.INDEFINITE);
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

        InGameScreen.SaveInFile.setController(this);
        InGameScreen.SaveInFile.printInfo();
    }

    //ses kaydırıcısını ses ile senkronize eder
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
    //ögeleri gizler
    public void hide(){
        newGameButton.setVisible(false);
        exitGameButton.setVisible(false);
        creditsButton.setVisible(false);
        volumeSlider.setVisible(false);
        miKuriTitle.setVisible(false);
        soundIcon.setVisible(false);
        victoriesButton.setVisible(false);
    }
    //ögeleri gösterir
    public void show(){
        soundIcon.setVisible(true);
        newGameButton.setVisible(true);
        exitGameButton.setVisible(true);
        creditsButton.setVisible(true);
        volumeSlider.setVisible(true);
        miKuriTitle.setVisible(true);
        victoriesButton.setVisible(true);
    }
    //yeni oyun başlatır
    @FXML
    @Override
    public void onNewGameButtonClick() {
        hide();
        try {
            gameDegisme();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    //ana menüden oyuna geçmemizi sağlar
    public void gameDegisme() throws IOException {
        if(yesButton.getScene() != null){
            Stage window = (Stage) newGameButton.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuToGame.fxml")));
                window.setScene(new Scene(root, 600, 450));

                Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
                window.setX((screenLayout.getWidth() - 600) / 2);
                window.setY((screenLayout.getHeight() - 450) / 2);
            }
        }
    }

    //oyundan çıkmak isteyip istemediğimizi sorar
    @FXML
    @Override
    public void onExitGameButtonClick() {
        hide();

        //EMIN MISIN? (evet/hayir)
        txt.setVisible(true);
        txt.setText("Are you sure?");

        yesButton.setVisible(true);
        noButton.setVisible(true);

        //oyundan çıkar
        yesButton.setOnAction(event -> {
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

        //ana menüye döner
        noButton.setOnAction( _ -> {
            txt.setText("");

            yesButton.setVisible(false);
            noButton.setVisible(false);
            txt.setVisible(false);

            show();
        });
    }

    //yapımcıları gösterme
    @FXML
    public void setCredits(){
        hide();
        miKuriTitle.setVisible(true);
        credits.setVisible(true);
        backMenuButton.setVisible(true);
    }

    //ana ekrana dönme
    @FXML
    public void menuBack() throws IOException {
        show();
        credits.setVisible(false);
        backMenuButton.setVisible(false);
        victoryWindow.setVisible(false);
    }

    public void updateVictoriesText(String content){
        victoriesText.setText(content);
    }
    @FXML
    public void victoriesYaz(){
        newGameButton.setVisible(false);
        exitGameButton.setVisible(false);
        creditsButton.setVisible(false);
        victoriesButton.setVisible(false);
        volumeSlider.setVisible(false);
        soundIcon.setVisible(false);
        victoryWindow.setVisible(true);
        backMenuButton.setVisible(true);
    }
}