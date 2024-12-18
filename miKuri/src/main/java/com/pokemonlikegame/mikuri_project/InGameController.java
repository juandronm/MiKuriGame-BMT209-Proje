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
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private Label
            pauseMenu,
            contentWindow;
    @FXML
    private AnchorPane anchorPane;

    private Map<Card, ImageView> cardImageMap = new HashMap<>();

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage primaryStage;

    private ArrayList<Card> playerTeam = new ArrayList<>();
    private ArrayList<Card> opponentTeam = new ArrayList<>();

    private boolean isPlayerTurn = true;
    private Card selectedPlayerCard = null;

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
    public void pause(KeyEvent hareket) {
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
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

    protected void gameStart(){
        // Making allCards decks
        ArrayList<Card> allCards = new ArrayList<>();

        allCards.add(new Card("Charizard", 120, "Fire", 90));
        allCards.add(new Card("Blaziken", 117, "Fire", 82));
        allCards.add(new Card("Camerupt", 143, "Fire", 84));

        allCards.add(new Card("Blastoise", 173, "Water", 83));
        allCards.add(new Card("Greninja", 122, "Water", 87));
        allCards.add(new Card("Swampert", 106, "Water", 78));

        allCards.add(new Card("Lapras", 156, "Ice", 88));
        allCards.add(new Card("Mamoswine", 133, "Ice", 77));
        allCards.add(new Card("Glalie", 125, "Ice", 74));

        allCards.add(new Card("Pikachu", 60, "Electric", 110));
        allCards.add(new Card("Jolteon", 101, "Electric", 82));
        allCards.add(new Card("Magneton", 150, "Electric", 68));

        allCards.add(new Card("Venusaur", 150, "Eco", 67));
        allCards.add(new Card("Geodude", 120, "Eco", 80));
        allCards.add(new Card("Bulbazaur", 150, "Eco", 70));

        allCards.add(new Card("Scyther", 90, "Fly", 90));
        allCards.add(new Card("Pidgey", 70, "Fly", 80));
        allCards.add(new Card("Gyarados", 110, "Fly", 85));

        // Split cards into teams
        for (int i = 0; i < 4; i++) {
            opponentTeam.add(allCards.get(i)); // First 4 are the opponent's
        }
        for (int i = 4; i < 8; i++) {
            playerTeam.add(allCards.get(i)); // Next 4 are the player's
        }

        // Setting the cards in the arena
        int anchorWidth = 900;
        int anchorHeight = 700;

        double padding = 10, cellWidth = 100, cellHeight = 80;

        // Using a valid random index between 0 and 17, and adding the boolean parameter
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 8, padding + 30, cellWidth, cellHeight, true);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 22, padding + 1 * (cellHeight + 5), cellWidth, cellHeight, true);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 8, padding + 2 * (cellHeight + 5), cellWidth, cellHeight, true);

        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 8, anchorHeight - 3 * (cellHeight + 5) - padding, cellWidth, cellHeight, false);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 22, anchorHeight - 2 * (cellHeight + 5) - padding, cellWidth, cellHeight, false);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 8, anchorHeight - 1 * (cellHeight + 5) - padding - 30, cellWidth, cellHeight, false);

        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 71, padding + 30, cellWidth, cellHeight, true);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 58, padding + 1 * (cellHeight + 5), cellWidth, cellHeight, true);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 71, padding + 2 * (cellHeight + 5), cellWidth, cellHeight, true);

        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 71, anchorHeight - 3 * (cellHeight + 5) - padding, cellWidth, cellHeight, false);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 58, anchorHeight - 2 * (cellHeight + 5) - padding, cellWidth, cellHeight, false);
        cardToStage(allCards.get((int) (Math.random() * 18)), padding * 71, anchorHeight - 1 * (cellHeight + 5) - padding - 30, cellWidth, cellHeight, false);
    }

    protected void cardToStage(Card card, double x, double y, double width, double height, boolean isOpponent){
        try{
            String path = "/com/pokemonlikegame/mikuri_project/photos/"+ card.getName().toLowerCase() +".png";
            ImageView character = new ImageView();
            character.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(path))));
            character.setFitWidth(106);
            character.setFitHeight(86);
            character.setPreserveRatio(false);

            character.setLayoutX(x);
            character.setLayoutY(y);

            // Store the mapping between card and ImageView
            cardImageMap.put(card, character);

            character.setOnMousePressed(event -> {
                if (isPlayerTurn) {
                    if (!isOpponent) {
                        selectPlayerPokemon(card);
                    } else if (selectedPlayerCard != null) {
                        attackOpponentPokemon(selectedPlayerCard, card);
                    }
                }
            });

            anchorPane.getChildren().add(character);
        }
        catch (NullPointerException e){
            System.err.println("Image " + card.getName() + " not found!!!!");
        }
    }

    // Modified removeCardFromStage method
    private void removeCardFromStage(Card card) {
        ImageView imageToRemove = cardImageMap.get(card);
        if (imageToRemove != null) {
            anchorPane.getChildren().remove(imageToRemove);
            cardImageMap.remove(card);
        }

        // Remove the card from its team
        playerTeam.remove(card);
        opponentTeam.remove(card);

        // Check for game end conditions
        if (playerTeam.isEmpty()) {
            contentWindow.setText("Game Over! Opponent Wins!");
            contentWindow.setVisible(true);
        } else if (opponentTeam.isEmpty()) {
            contentWindow.setText("Congratulations! You Win!");
            contentWindow.setVisible(true);
        }
    }

    // Player selects their Pokémon
    private void selectPlayerPokemon(Card card) {
        selectedPlayerCard = card;
        contentWindow.setVisible(true);
        contentWindow.setText("Selected: " + card.getName() + "\nHP: " + card.getHp() + "\nType: " + card.getType());
        System.out.println("Player selected: " + card.getName());
    }

    // Player attacks an opponent Pokémon
    private void attackOpponentPokemon(Card playerCard, Card opponentCard) {
        int damage = playerCard.getAttackPower();
        opponentCard.takeDamage(damage);

        System.out.println(playerCard.getName() + " attacked " + opponentCard.getName() + " for " + damage + " damage!");
        contentWindow.setText(opponentCard.getName() + " HP: " + opponentCard.getHp());

        if (!opponentCard.isAlive()) {
            System.out.println(opponentCard.getName() + " has been defeated!");
            removeCardFromStage(opponentCard);
        }

        endPlayerTurn();
    }

    // Opponent attacks the player's Pokémon
    private void opponentTurn() {
        isPlayerTurn = false;

        Card opponentCard = opponentTeam.get((int) (Math.random() * opponentTeam.size()));
        Card playerCard = playerTeam.get((int) (Math.random() * playerTeam.size()));

        int damage = opponentCard.getAttackPower();
        playerCard.takeDamage(damage);

        System.out.println("Opponent's " + opponentCard.getName() + " attacked " + playerCard.getName() + " for " + damage + " damage!");

        if (!playerCard.isAlive()) {
            System.out.println(playerCard.getName() + " has been defeated!");
            removeCardFromStage(playerCard);
        }

        endOpponentTurn();
    }

    // End player turn and begin opponent turn
    private void endPlayerTurn() {
        selectedPlayerCard = null;
        contentWindow.setVisible(false);
        isPlayerTurn = false;

        // Delay to simulate opponent thinking
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> opponentTurn());
        pause.play();
    }

    // End opponent turn and begin player turn
    private void endOpponentTurn() {
        isPlayerTurn = true;
    }

}