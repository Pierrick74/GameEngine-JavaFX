package org.Games.JavaFX.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.Games.Controller.ChoosePlayerController;
import org.Games.JavaFX.KeyHandler;
import org.Games.JavaFX.commun.ThemeConfig;


public class ChoosePlayerView extends VBox implements KeyHandler {
    private Label titre;
    private HBox selectionPlayer;
    private ChoosePlayerController controller;

    public ChoosePlayerView(ChoosePlayerController controller) {
        super(10);
        this.controller = controller;

        initializeComponents();

        this.getChildren().addAll(titre, selectionPlayer);
        ThemeConfig.applyDarkBackground(this);
    }

    private void initializeComponents()  {
        titre = new Label("Combien de joueur ?");
        titre.setTextFill(Color.web(ThemeConfig.TEXT));
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);

        selectionPlayer = new HBox();
        selectionPlayer.setAlignment(Pos.CENTER);
        selectionPlayer.setSpacing(10);
        selectionPlayer.getChildren().addAll(createButton("2 joueurs", 2), createButton("1 joueur VS IA", 1), createButton("2 IA", 0));

    }

    private Button createButton(String labelText, Integer number) {
        Button button = new Button(labelText);
        button.setDefaultButton(true);
        button.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        ThemeConfig.applyButtonStyle(button);
        button.setOnAction(e -> {controller.numberOfHumain(number);});
        return button;
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        controller.keyPressed(event.getText());
    }
}
