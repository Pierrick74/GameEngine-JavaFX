package org.Games;


import javafx.stage.StageStyle;
import org.Games.Controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.AppView;
import org.Games.model.AppModel;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.initStyle(StageStyle.DECORATED);
        StageRepository.getInstance().setStage(primaryStage);

        AppModel appModel = new AppModel();
        AppController appController = new AppController(appModel);

        AppView appView = new AppView(appController);

        appController.registerView(appView);
        StageRepository.getInstance().replaceScene(appView, appController);
    }
}