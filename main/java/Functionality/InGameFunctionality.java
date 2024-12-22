package Functionality;

import javafx.scene.input.KeyEvent;

public interface InGameFunctionality {
    void pause(KeyEvent hareket);

    void continueGame();

    void gameStart();

    void attackPressed();
}
