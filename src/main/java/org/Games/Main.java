package org.Games;

import javafx.application.Platform;
import javafx.stage.StageStyle;
import org.Games.Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.MainView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        primaryStage.initStyle(StageStyle.DECORATED);
        StageRepository.getInstance().setStage(primaryStage);

        // Lancer le jeu dans un thread séparé
        Thread gameThread = new Thread(() -> {
            Controller controlleur = Controller.getInstance();
            try {
                controlleur.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        gameThread.setDaemon(true); // Se termine avec l'application
        gameThread.start();
    }
}