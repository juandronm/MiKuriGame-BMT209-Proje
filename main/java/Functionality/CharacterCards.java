package Functionality;

import Cards.Card;
import javafx.scene.image.ImageView;

public interface CharacterCards {
    void cardToStage(Card card, double x, double y);

    void removeCardFromStage(ImageView cardImage);

    void checkWin();
}
