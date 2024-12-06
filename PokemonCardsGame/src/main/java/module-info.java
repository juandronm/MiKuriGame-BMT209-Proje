module com.example.pokemoncardsgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.pokemoncardsgame to javafx.fxml;
    exports com.example.pokemoncardsgame;
}