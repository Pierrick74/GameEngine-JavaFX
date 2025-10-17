package org.Games.JavaFX;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.Games.JavaFX.Views.MenuHandler;

public class StageRepository {
    private static StageRepository instance;
    private Stage stage;
    private MenuHandler menuHandler;

    // Dimensions communes
    private static final double SCENE_WIDTH = 1200;
    private static final double SCENE_HEIGHT = 800;
    private static final Color BACKGROUND_COLOR = Color.web("#E8E4DC"); // Couleur claire pour correspondre au thème

    private StageRepository() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static StageRepository getInstance() {
        return instance==null?instance=new StageRepository():instance;
    }

    public void replaceScene(javafx.scene.Parent scene, MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
        Scene newScene = createScene(scene);

        if (scene instanceof KeyHandler) {
            newScene.setOnKeyPressed(((KeyHandler) scene)::onKeyPressed);
        }

        stage.setScene(newScene);
        stage.show();

        scene.requestFocus();
    }

    public Scene createScene(javafx.scene.Parent root) {
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(createMenuBar());
        mainPane.setCenter(root);
        return new Scene(mainPane, SCENE_WIDTH, SCENE_HEIGHT, BACKGROUND_COLOR);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-font-size: 16px; -fx-pref-height: 35px;");

        Menu gameMenu = new Menu("Menu");
        gameMenu.setStyle("-fx-font-size: 16px;");

        MenuItem newGame = new MenuItem("Nouvelle partie");
        newGame.setOnAction(e -> {
            if (menuHandler != null) {
                menuHandler.onNewGame();
            }
        });

        MenuItem saveGame = new MenuItem("Sauvegarder");
        saveGame.setOnAction(e -> {
            if (menuHandler != null) {
                menuHandler.onSaveGame();
            }
        });

        MenuItem exitGame = new MenuItem("Quitter");
        exitGame.setOnAction(e -> {
            if (menuHandler != null) {
                menuHandler.onExit();
            }
        });

        String itemStyle = "-fx-font-size: 14px; -fx-pref-height: 30px;";
        newGame.setStyle(itemStyle);
        saveGame.setStyle(itemStyle);
        exitGame.setStyle(itemStyle);

        gameMenu.getItems().addAll(newGame, saveGame, exitGame);
        menuBar.getMenus().add(gameMenu);

        return menuBar;
    }
}
