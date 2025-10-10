package org.Games.JavaFX.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.Games.Controller.Controller;
import org.Games.JavaFX.commun.ThemeConfig;
import org.Games.model.game.GameState;

public class MainView extends VBox {
    private Label bienvenue;
    private Label titre;
    private Button button;
    private ImageView imageView;

    public MainView() {
        super(10);
        initializeComponents();
        setupActions();

        this.getChildren().addAll(imageView,bienvenue, titre,button);

        ThemeConfig.applyDarkBackground(this);
    }

    private void initializeComponents()  {
        button = new Button("Choisir un jeu");
        button.setDefaultButton(true);
        button.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        ThemeConfig.applyButtonStyle(button);

        bienvenue = new Label("Bienvenue dans");
        bienvenue.setFont(Font.font("Almendra", FontWeight.BOLD, 30.0));
        bienvenue.setAlignment(Pos.CENTER);
        bienvenue.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        titre = new Label("Campus Games");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));

        titre.setFont(Font.font("Arial", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);

        // Initialiser imageView à null pour éviter NullPointerException
        imageView = new ImageView();
        imageView.setVisible(false);

        /*
        Image image = new Image("fr/pierrickviret/javaquest/javafx/assets/home.jpg");
        imageView.setImage(image);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
         */
    }

    private void setupActions() {
        // Action sur le bouton - maintenant dans une méthode
        button.setOnAction(e -> Controller.getInstance().setGameState(GameState.ASKFORCHOSEGAME));
    }
}
