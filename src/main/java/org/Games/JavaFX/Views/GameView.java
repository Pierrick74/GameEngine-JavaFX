package org.Games.JavaFX.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.Games.Controller.GameController;
import org.Games.JavaFX.commun.ThemeConfig;
import org.Games.model.game.GameState;
import org.Games.observer.Observer;

import java.io.Serializable;


public class GameView extends VBox implements Observer, Serializable {
        private Label titre;
        private GameController controller;
        private GridPane board;

    public GameView(GameController controller) {
        super(10);
        this.controller = controller;

        initializeComponents();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(board);
        this.getChildren().addAll(titre, hBox);

        ThemeConfig.applyDarkBackground(this);
    }

    private void initializeComponents()  {
        titre = new Label(controller.getGameName());
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GREEN));
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);

        board = new GridPane();
        board.setGridLinesVisible(true);
        board.setHgap(2);
        board.setVgap(2);

        int rows = controller.getRowCount();    // 3 pour TicTacToe, 15 pour Gomoku, etc.
        int cols = controller.getColumnCount();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button cell = createCell(i, j);
                board.add(cell, j, i);
            }
        }
    }

    private Button createCell(int i, int j) {
        String path = controller.getRepresentation(i,j);
        Button button = new Button();
        int size = controller.getButtonSize();

        Image image = new Image(path);
        ImageView picture = new ImageView(image);
        picture.setFitWidth(size);
        picture.setPreserveRatio(true);
        button.setGraphic(picture);

        button.setMinSize(size, size);
        button.setMaxSize(size, size);
        button.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        button.setOnAction(e -> {controller.onCellClicked(i, j);});
        return button;
    }

    @Override
    public void updateState(GameState gameState) {
        updateBoard();
        if  (gameState == GameState.FINISHED) {
            showWinner();
        }
    }

    private void showWinner() {
        titre.setText(controller.getWinner());
        titre.setTextFill(Color.GOLD);
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 60.0));

        // Désactiver toutes les cases
        for (var node : board.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }

        Button replayButton = new Button("Rejouer");
        replayButton.setOnAction(e -> controller.restartNewGame());
        this.getChildren().add(replayButton);

    }

        private void updateBoard() {
        int rows = controller.getRowCount();
        int cols = controller.getColumnCount();
        int size = controller.getButtonSize();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button cell = null;
                for (var node : board.getChildren()) {
                    if (node instanceof Button &&
                        GridPane.getRowIndex(node) == i &&
                        GridPane.getColumnIndex(node) == j) {
                        cell = (Button) node;
                        break;
                    }
                }

                if (cell != null) {
                    String path = controller.getRepresentation(i, j);
                    Image image = new Image(path);
                    ImageView picture = new ImageView(image);
                    picture.setFitWidth(size);
                    picture.setPreserveRatio(true);
                    cell.setGraphic(picture);
                }
            }
        }
    }

}
