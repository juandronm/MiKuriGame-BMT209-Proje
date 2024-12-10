module com.pokemonlikegame.mikuri_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.pokemonlikegame.mikuri_project to javafx.fxml;
    exports com.pokemonlikegame.mikuri_project;
}