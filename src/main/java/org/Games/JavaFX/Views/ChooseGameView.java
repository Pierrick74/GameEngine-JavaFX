package org.Games.JavaFX.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.Games.Controller.Controller;
import org.Games.JavaFX.commun.ThemeConfig;
import org.Games.model.game.GameState;

public class ChooseGameView extends VBox {
    private Label titre;
    private VBox vBoxGomoku;
    private VBox vBoxTicTacToe;
    private VBox vBoxPower4;

    public ChooseGameView() {
        super(10);
        initializeComponents();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vBoxTicTacToe,vBoxGomoku,vBoxPower4);

        this.getChildren().addAll(titre, hBox);

        ThemeConfig.applyDarkBackground(this);
    }

    private VBox createVBox(String path, String labelText, GameState gameState) {
        Button button = new Button();
        Image image = new Image(path);
        ImageView picture = new ImageView(image);
        picture.setFitWidth(120);
        picture.setPreserveRatio(true);

        double imageHeight = 120 * (image.getHeight() / image.getWidth());
        Rectangle clip = new Rectangle(120, imageHeight);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        picture.setClip(clip);

        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));

        button.setGraphic(picture);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        button.setFocusTraversable(false);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(button, label);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.color(0, 0, 0, 0.5));
        button.setEffect(shadow);

        button.setOnAction(e -> Controller.getInstance().setGameState(gameState));
        button.setOnMousePressed(e -> button.setEffect(null));
        button.setOnMouseReleased(e -> button.setEffect(shadow));

        return vBox;
    }

    private void initializeComponents()  {
        vBoxTicTacToe = createVBox("/org/Games/JavaFX/assets/tictactoe.png", "TicTacToe", GameState.CREATETICTACTOE);
        vBoxGomoku = createVBox("/org/Games/JavaFX/assets/gomoku.png", "Gomoku", GameState.CREATEGOMOKU);
        vBoxPower4 = createVBox("/org/Games/JavaFX/assets/power4.png", "Power4", GameState.CREATEPOWER4);

        titre = new Label("Choisissez un jeu");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);
    }
}
