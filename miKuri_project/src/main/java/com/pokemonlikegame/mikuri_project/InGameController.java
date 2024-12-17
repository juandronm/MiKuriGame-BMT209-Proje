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
import javafx.scene.image.Image;
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
import java.util.ArrayList;
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
            hayirButton,
            attackButton;
    @FXML
    private Label pauseMenu,
                    contentWindow;
    @FXML
    private AnchorPane anchorPane;

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        environment.fitWidthProperty().bind(anchorPane.widthProperty());
        environment.fitHeightProperty().bind(anchorPane.heightProperty());
        contentWindow.setVisible(false);
        attackButton.setVisible(false);

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

        gameStart();
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
    //@FXML
    protected void gameStart(){
        ArrayList<Card> card = new ArrayList<>();

        //card.add(new Card("Pikachu", 60, "Electric", 50));
        card.add(new Card("Charizard", 120, "Fire", 90));
        card.add(new Card("Blaziken", 117, "Fire", 82));
        card.add(new Card("Camerupt", 143, "Fire", 84));
        card.add(new Card("Blastoise", 173, "Water", 83));
        card.add(new Card("Greninja", 122, "Water", 87));
        card.add(new Card("Swampert", 106, "Water", 78));
        card.add(new Card("Lapras", 156, "Ice", 88));
        card.add(new Card("Mamoswine", 133,"Ice", 77));
        card.add(new Card("Glalie", 125, "Ice", 74));

        //card.add(new Card("Venusaur", 150, "Grass", 87));

        System.out.println("Cards:");
        for(Card c : card){
           System.out.println(c);
        }

        //PLACE THE POKEMONS ON THE STAGE AT THIS PART OF THE CODE

//        int columns = 9;
//        int rows = 9;
//
//        double cellWidth = anchorPane.getWidth() / columns;
//        double cellHeight = anchorPane.getHeight() / rows;
        int anchorWidth = 900;
        int anchorHeight = 700;

        double cellWidth = 106;
        double cellHeight = 86;

        double padding = 10;

        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 8, padding + 30, cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 22, padding + 1 * (cellHeight + 5), cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 8, padding + 2 * (cellHeight + 5), cellWidth, cellHeight);

        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 8, anchorHeight - 3 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 22, anchorHeight - 2 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 8, anchorHeight - 1 * (cellHeight + 5) - padding - 30, cellWidth, cellHeight);

        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 71, padding + 30, cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 58, padding + 1 * (cellHeight + 5), cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 71, padding + 2 * (cellHeight + 5), cellWidth, cellHeight);

        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 71, anchorHeight - 3 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 58, anchorHeight - 2 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(card.get((int) (Math.random() * 6) + 1), padding * 71, anchorHeight - 1 * (cellHeight + 5) - padding - 30, cellWidth, cellHeight);

//        for (int i = 0; i < 4; i++) { // 4 cards on the top-left
//            double x = padding;
//            double y = (i + 1) * cellHeight; // Start placing from the 2nd row
//            cardToStage(card.get(i), x, y, cellWidth, cellHeight);
//        }
//
//        // Bottom-left vertical column
//        for (int i = 4; i < 7; i++) { // 3 cards on bottom-left
//            double x = padding;
//            double y = (rows - 4 + (i - 4)) * cellHeight; // Start from 6th row
//            cardToStage(card.get(i), x, y, cellWidth, cellHeight);
//        }
//
//        // Bottom-right single Pokémon
//        double x = (columns - 2) * cellWidth; // 2nd column from the right
//        double y = (rows - 3) * cellHeight;  // Start from 7th row
//        cardToStage(card.get(7), x, y, cellWidth, cellHeight);
//        for (int i = 0; i < card.size() / 2; i++){
//            double x = (i % columns) * cellWidth + 5;
//            double y = 1 * cellHeight + 5;
//            cardToStage(card.get(i), x, y, cellWidth, cellHeight);
//        }
//        for (int i = card.size() / 2; i < card.size(); i++){
//            double x = ((i - (double) card.size() / 2) % columns) * cellWidth + 5;
//            double y = (rows - 2) * cellHeight + 5;
//            cardToStage(card.get(i), x, y, cellWidth, cellHeight);
//        }
}

    protected void cardToStage(Card card, double x, double y, double width, double height){
        try{
            String path = "/com/pokemonlikegame/mikuri_project/"+ card.getName().toLowerCase() +".png";
            ImageView character = new ImageView();
            character.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(path))));
            character.setFitWidth(106);
            character.setFitHeight(86);
            character.setPreserveRatio(false);

            character.setLayoutX(x);
            character.setLayoutY(y);

            character.setOnMousePressed(event -> {
                if(event.isPrimaryButtonDown()){
                    contentWindow.setVisible(true);
                    attackButton.setVisible(true);
                    contentWindow.setText(card.getName() + "\nHP: " + card.getHp() + "\nType: " + card.getType());
                    System.out.println(card.getName() + ". HP: " + card.getHp() + ". Type: " + card.getType());
                }
            });

            anchorPane.getChildren().add(character);
       }catch (NullPointerException e){
           System.err.println("Image " + card.getName() + " not found!!!!");
        }
    }
}