package org.Games.JavaFX.commun;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class ThemeConfig {
    // Couleurs
    public static final String TEXT_LIGHT = "#264653";
    public static final String TEXT = "#e8e8e8";
    public static final String BUTTON_YELLOW = "#06b6d4";

    // Styles CSS
    public static final String DARK_BACKGROUND_STYLE =
            "-fx-background-color: #0a1428;" +
                    "-fx-alignment: center; ";

    public static final String PLAYER_INFORMATION_STYLE =
            "-fx-background-color: #8B7355;" +
                    "-fx-background-radius: 10;" +
                    "-fx-alignment: center;" +
                    "-fx-effect: dropshadow(three-pass-box, #000000, 10, 0, 5, 5);";

    public static final String BUTTON_STYLE =
            "-fx-background-color: " + BUTTON_YELLOW + ";" +
                    "-fx-text-fill:  " + TEXT + ";" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-color: " + TEXT + ";" +
                    "-fx-border-width: 2;";

    public static final String INPUT_STYLE =
            "-fx-border-color: " + TEXT + ";" +
                    "-fx-border-width: 2;";

    // Méthodes utilitaires
    public static void applyDarkBackground(Region node) {
        node.setStyle(DARK_BACKGROUND_STYLE);
    }

    public static void applyPlayerTheme(Region node) {
        node.setStyle(PLAYER_INFORMATION_STYLE);
    }

    public static void applyButtonStyle(Button button) {
        String fullStyle = BUTTON_STYLE +
                "-fx-background-insets: 0;" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;";

        button.setStyle(fullStyle);

        // Forcer le même style pour tous les états (hover, pressed, focused)
        button.setOnMouseEntered(e -> button.setStyle(fullStyle));
        button.setOnMouseExited(e -> button.setStyle(fullStyle));
        button.setOnMousePressed(e -> button.setStyle(fullStyle));
        button.setOnMouseReleased(e -> button.setStyle(fullStyle));
        button.focusedProperty().addListener((obs, oldVal, newVal) -> button.setStyle(fullStyle));
    }
}

