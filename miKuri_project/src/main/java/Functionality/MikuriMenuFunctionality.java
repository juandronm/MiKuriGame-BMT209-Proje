package Functionality;

import java.io.IOException;

public interface MikuriMenuFunctionality {
    void onSlider();

    void onNewGameButtonClick();

    void onExitGameButtonClick();

    void menuBack() throws IOException;

    void setCredits();

    void gameDegisme() throws IOException;
}
