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
import org.Games.Controller.AppController;
import org.Games.JavaFX.commun.ThemeConfig;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;

public class AppView extends VBox implements Observer {
    private Label titre;
    private VBox vBoxGomoku;
    private VBox vBoxTicTacToe;
    private VBox vBoxPower4;
    private AppController controller;

    public AppView(AppController controller) {
        super(10);
        this.controller = controller;

        initializeComponents();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vBoxTicTacToe,vBoxGomoku,vBoxPower4);

        this.getChildren().addAll(titre, hBox);

        ThemeConfig.applyDarkBackground(this);
    }

    private VBox createVBox(String path, String labelText, GameType gameType) {
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

        button.setOnAction(e -> controller.onGameSelected(gameType));
        button.setOnMousePressed(e -> button.setEffect(null));
        button.setOnMouseReleased(e -> button.setEffect(shadow));

        return vBox;
    }

    private void initializeComponents()  {
        vBoxTicTacToe = createVBox("/org/Games/JavaFX/assets/tictactoe.png", "TicTacToe", GameType.TICTACTOE);
        vBoxGomoku = createVBox("/org/Games/JavaFX/assets/gomoku.png", "Gomoku", GameType.GOMOKU);
        vBoxPower4 = createVBox("/org/Games/JavaFX/assets/power4.png", "Power4", GameType.POWER4);

        titre = new Label("Choisissez un jeu");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);
    }

    @Override
    public void updateState(GameState gameState) {
        if(gameState == GameState.CHECKSAVE) {
            Label question = new Label("Voulez vous charger la sauvegarde ?");
            question.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));
            question.setFont(Font.font("Arial", FontWeight.BOLD, 20.0));
            question.setAlignment(Pos.CENTER);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            hBox.getChildren().addAll(createButton("Oui"),createButton("Non"));

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            vBox.getChildren().addAll(question, hBox);

            this.getChildren().addAll(vBox);
        }
    }

    private Button createButton(String labelText) {
        Button button = new Button(labelText);
        button.setDefaultButton(true);
        button.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        ThemeConfig.applyButtonStyle(button);

        if(labelText.equals("Oui")) {
            button.setOnAction(e-> {controller.startGameWithSaveData(true);});
        } else {
            button.setOnAction(e-> {controller.startGameWithSaveData(false);});
        }
        return button;
    }
}
