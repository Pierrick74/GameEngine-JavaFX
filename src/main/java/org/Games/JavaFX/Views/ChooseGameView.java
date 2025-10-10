package org.Games.JavaFX.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.Games.Controller.Controller;
import org.Games.JavaFX.commun.ThemeConfig;
import org.Games.model.game.GameState;

public class ChooseGameView extends VBox {
    private Label titre;
    private Button button1;
    private Button button2;
    private Button button3;


    public ChooseGameView() {
        super(10);
        initializeComponents();
        setupActions();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(button1,button2,button3);

        this.getChildren().addAll(titre, hBox);

        ThemeConfig.applyDarkBackground(this);
    }

    private void initializeComponents()  {
        button1 = new Button("TicTacToe");
        button1.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        button1.setMinSize(150, 50);
        button1.setPrefSize(150, 50);
        button1.setMaxSize(150, 50);
        ThemeConfig.applyButtonStyle(button1);
        button1.setMouseTransparent(false);
        button1.setFocusTraversable(false);

        button2 = new Button("Gomoku");
        button2.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        button2.setMinSize(150, 50);
        button2.setPrefSize(150, 50);
        button2.setMaxSize(150, 50);
        ThemeConfig.applyButtonStyle(button2);
        button2.setMouseTransparent(false);
        button2.setFocusTraversable(false);

        button3 = new Button("Power4");
        button3.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        button3.setMinSize(150, 50);
        button3.setPrefSize(150, 50);
        button3.setMaxSize(150, 50);
        ThemeConfig.applyButtonStyle(button3);
        button3.setMouseTransparent(false);
        button3.setFocusTraversable(false);


        titre = new Label("Choisissez un jeu");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));

        titre.setFont(Font.font("Arial", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);
    }

    private void setupActions() {
        // Action sur le bouton - maintenant dans une méthode
        button1.setOnAction(e -> Controller.getInstance().setGameState(GameState.CREATETICTACTOE));
    }
}
