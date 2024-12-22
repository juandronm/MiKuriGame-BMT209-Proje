package Functionality;

import InGameScreen.Card;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public interface InGameFunctionality {
    void pause(KeyEvent hareket);

    void continueGame();

    void gameStart();

    void cardToStage(Card card, double x, double y);

    void removeCardFromStage(ImageView cardImage);

    void checkWin();

    void attackPressed();
}
