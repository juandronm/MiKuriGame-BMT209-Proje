package InGameScreen;

import com.pokemonlikegame.mikuri_project.MikuriController;
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
            attackButton,
            contPlaying,
            finishGame;
    @FXML
    private Label pauseMenu;
    @FXML
    private Label contentWindow;
    @FXML
    private AnchorPane anchorPane;
    private static InGameController instance;

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage primaryStage;
    private static final ArrayList<ImageView> characterImages = new ArrayList<>();

    private Attacker attacker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        environment.fitWidthProperty().bind(anchorPane.widthProperty());
        environment.fitHeightProperty().bind(anchorPane.heightProperty());
        contentWindow.setVisible(false);
        attackButton.setVisible(false);
        contPlaying.setVisible(false);
        finishGame.setVisible(false);

        attacker = new Attacker(this);
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

            for(ImageView character : characterImages){
                character.setVisible(false);
            }
            environment.setVisible(false);
            evetButton.setVisible(false);
            hayirButton.setVisible(false);
            contentWindow.setVisible(false);
            attackButton.setVisible(false);
        }
    }
    @FXML
    @Override
    public void menuBack() throws IOException {
        Stage window = (Stage) inGameToMenu.getScene().getWindow();
        if(window != null){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pokemonlikegame/mikuri_project/mikuri-StaLoaScreen.fxml")));
            window.setScene(new Scene(root, 600, 450));
            window.setTitle("Mikuri - Menu");

            Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
            window.setX((screenLayout.getWidth() - 600) / 2);
            window.setY((screenLayout.getHeight() - 450) / 2);
        }
    }

    @FXML
    @Override
    public void onExitGameButtonClick() {
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
    public void continueGame() {
        environment.setVisible(true);
        contentWindow.setVisible(false);
        attackButton.setVisible(false);
        for(ImageView character : characterImages){
            character.setVisible(true);
        }
    }

    String [][] characterData = {
            {"Charizard", "120", "Fire", "90"},
            {"Blaziken", "117", "Fire", "82"},
            {"Camerupt", "143", "Fire", "84"},
            {"Blastoise","173", "Water", "83"},
            {"Greninja", "122", "Water", "87"},
            {"Swampert", "106", "Water", "78"},
            {"Lapras", "156", "Ice", "88"},
            {"Mamoswine", "133","Ice", "77"},
            {"Glalie", "125", "Ice", "74"},
            {"Pikachu", "60", "Electric", "110"},
            {"Jolteon", "101", "Electric", "82"},
            {"Magneton", "150", "Electric", "68"},
            {"Venusaur", "150", "Eco", "67"},
            {"Geodude", "120", "Eco", "80"},
            {"Bulbazaur", "150", "Eco", "70"},
            {"Scyther", "90", "Fly", "90"},
            {"Pidgey", "70", "Fly", "80"},
            {"Gyarados", "110", "Fly", "85"},
    };

    //@FXML
    protected void gameStart(){

        int anchorHeight = 700;

        double cellWidth = 106;
        double cellHeight = 86;

        double padding = 10;

        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 8, padding + 30, cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 22, padding + 1 * (cellHeight + 5), cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 8, padding + 2 * (cellHeight + 5), cellWidth, cellHeight);

        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 8, anchorHeight - 3 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 22, anchorHeight - 2 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 8, anchorHeight - 1 * (cellHeight + 5) - padding - 30, cellWidth, cellHeight);

        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 71, padding + 30, cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 58, padding + 1 * (cellHeight + 5), cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 71, padding + 2 * (cellHeight + 5), cellWidth, cellHeight);

        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 71, anchorHeight - 3 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 58, anchorHeight - 2 * (cellHeight + 5) - padding, cellWidth, cellHeight);
        cardToStage(new Card(characterData[(int) (Math.random() * (characterData.length - 1)) + 1]), padding * 71, anchorHeight - 1 * (cellHeight + 5) - padding - 30, cellWidth, cellHeight);


}

    private boolean attackMode = false;
    private Card selectedCard = null;
    private final Map<ImageView, Card> cardImageMap = new HashMap<>();

    private static final List<ImageView> blueTeamImages = new ArrayList<>();
    private static final List<ImageView> redTeamImages = new ArrayList<>();

    public static void updateContentWindow(String text){
        if (instance != null && instance.contentWindow != null){
            javafx.application.Platform.runLater(() -> {
                instance.contentWindow.setVisible(true);
                instance.contentWindow.setText(text);
            });
        }
    }

    public static List<ImageView> getBlueTeamImages(){
        return blueTeamImages;
    }
    public static List<ImageView> getRedTeamImages(){
        return redTeamImages;
    }

    public Card getCardForImageView(ImageView imageView) {
        return cardImageMap.get(imageView);
    }

    protected void cardToStage(Card card, double x, double y, double width, double height){
        try{
            String path = "/types/" + card.getName().toLowerCase() +".png";
            ImageView character = new ImageView();
            character.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(path))));
            character.setFitWidth(106);
            character.setFitHeight(86);
            character.setPreserveRatio(false);

            character.setLayoutX(x);
            character.setLayoutY(y);

            if (isOnBluePart(y)){
                blueTeamImages.add(character);
                character.setOnMousePressed(event -> {
                    if(event.isPrimaryButtonDown()){
                        selectedCard = card;
                        contentWindow.setVisible(true);
                        attackButton.setVisible(true);

                        contentWindow.setText(card.getName() + "\nHP: " + card.getHp() + "\nType: " + card.getType() + "\nAttacks: " + card.getAttacks());
                    }
                });
            }else{
                redTeamImages.add(character);
                character.setOnMousePressed(event -> {
                    contentWindow.setVisible(true);
                    attackButton.setVisible(false);
                    contentWindow.setText(card.getName() + "\nHP: " + card.getHp() + "\nType: " + card.getType() + "\nAttacks: " + card.getAttacks());

                    if(event.isPrimaryButtonDown() && attackMode && selectedCard != null){
                        attack(card, character);
                        contentWindow.setVisible(true);
                        //contentWindow.setText("Attacker: " + selectedCard.getName() + "\nTarget: "+ card.getName());

                        attackMode = false;
                        selectedCard = null;
                        attackButton.setVisible(false);
                    }
                });
            }

            cardImageMap.put(character, card);
            anchorPane.getChildren().add(character);
            characterImages.add(character);
       }catch (NullPointerException e){
           System.err.println("Image " + card.getName() + " not found!!!!");
        }
    }

    private void attack(Card target, ImageView targetImage){
        if (selectedCard == null){
            System.out.println("No attacker selected");
            return;
        }

        int damage = selectedCard.getAttacks();
        if(cardImageMap.containsKey(targetImage)){
            Card targetCard = cardImageMap.get(targetImage);
            targetCard.setHp(targetCard.getHp() - damage);
        }

        if(target.getHp() <= 0){
            target.setHp(0);
            contentWindow.setText(target.getName() + " has fainted!!!");

            removeCardFromStage(targetImage);
            checkWin();
        } else {
            contentWindow.setText(selectedCard.getName() + " attacked\n" + target.getName() + "\nfor " + damage + " of damage!");
        }

        if(!redTeamImages.isEmpty()){
            PauseTransition transition = new PauseTransition(Duration.seconds(3));
            transition.setOnFinished(event -> {
                Attacker.enemyAttack();
            });
            transition.play();
        }
    }

    void removeCardFromStage(ImageView cardImage){
        Card character = cardImageMap.get(cardImage);
        if (character != null){
            anchorPane.getChildren().remove(cardImage);
            characterImages.remove(cardImage);
            cardImageMap.remove(cardImage);
        }
        if(redTeamImages.contains(cardImage)){
            redTeamImages.remove(cardImage);
        }

        if(blueTeamImages.contains(cardImage)){
            blueTeamImages.remove(cardImage);
        }
    }

    private boolean isOnBluePart(double y){
        return (y >= 400);
    }

    void checkWin(){
        //System.out.println("red team: " + redTeamImages.size());
        //System.out.println("blue team: " + blueTeamImages.size());
        if(redTeamImages.isEmpty()){
            contentWindow.setVisible(true);
            contentWindow.setText("Congratulations!!! - You win!!!");
            disableGame();
            PauseTransition transition = new PauseTransition(Duration.seconds(3));
            transition.setOnFinished(event -> {
                javafx.application.Platform.runLater(() -> {
                    for (ImageView character : characterImages){
                        character.setVisible(false);
                    }
                    environment.setVisible(false);
                    evetButton.setVisible(false);
                    hayirButton.setVisible(false);
                    contentWindow.setVisible(false);
                    attackButton.setVisible(false);
                    evetButton.setVisible(false);
                    hayirButton.setVisible(false);
                    exitFromInGame.setVisible(false);
                    inGameToMenu.setVisible(false);
                    continueGame.setVisible(false);
                    environment.setVisible(false);
                    contPlaying.setVisible(true);
                    finishGame.setVisible(true);

                    pauseMenu.setText("Do you want to play again?");
                });
            });
            transition.play();
        } else if(blueTeamImages.isEmpty()){
            contentWindow.setVisible(true);
            contentWindow.setText("You've lost!!!");
            disableGame();
            PauseTransition transition = new PauseTransition(Duration.seconds(3));
            transition.setOnFinished(event -> {
                javafx.application.Platform.runLater(() -> {
                    for (ImageView character : characterImages){
                        character.setVisible(false);
                    }
                    environment.setVisible(false);
                    evetButton.setVisible(false);
                    hayirButton.setVisible(false);
                    contentWindow.setVisible(false);
                    attackButton.setVisible(false);
                    evetButton.setVisible(false);
                    hayirButton.setVisible(false);
                    exitFromInGame.setVisible(false);
                    inGameToMenu.setVisible(false);
                    continueGame.setVisible(false);
                    environment.setVisible(false);
                    contPlaying.setVisible(true);
                    finishGame.setVisible(true);

                    pauseMenu.setText("Do you want to play again?");
                });
            });
            transition.play();
        }
    }
    private void disableGame(){
        attackButton.setDisable(true);
        //anchorPane.setDisable(true);
        for (ImageView character : characterImages){
            character.setDisable(true);
            character.setOpacity(0.5);
        }

        contentWindow.setDisable(false);
        contentWindow.setVisible(true);
    }

    @FXML
    protected void attackPressed(){
        if(selectedCard != null){
            attackButton.setVisible(false);
            attackMode = true;
            contentWindow.setText("Attack!!!\nSelect a pokemon from the red side");
        }else{
            System.out.println("Select a pokemon from your side first");
        }
    }
    @FXML
    public void gameDegisme() throws IOException {
        if(contPlaying.getScene() != null){
            Stage window = (Stage) contPlaying.getScene().getWindow();
            if(window != null){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pokemonlikegame/mikuri_project/menuToGame.fxml")));
                window.setScene(new Scene(root, 600, 450));
                window.setTitle("Mikuri - Menu");

                Rectangle2D screenLayout = Screen.getPrimary().getVisualBounds();
                window.setX((screenLayout.getWidth() - 600) / 2);
                window.setY((screenLayout.getHeight() - 450) / 2);
            }
        }
    }
}