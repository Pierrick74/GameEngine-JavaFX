package org.Games.Controller;

import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.GameView;
import org.Games.JavaFX.Views.MenuHandler;
import org.Games.model.game.GameType;

public class ChoosePlayerController implements MenuHandler {
    private AppController appController;
    private GameType gameType;

    public ChoosePlayerController(AppController appController, GameType gameType) {
        this.appController = appController;
        this.gameType = gameType;
    }

    public void numberOfHumain(Integer number) {
        try {
            GameController gameController = new GameController(gameType, appController, number);
            GameView gameView = new GameView(gameController);
            gameController.registerView(gameView);

            StageRepository.getInstance().replaceScene(gameView, gameController);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onNewGame() {
        appController.backToGameSelection();
    }

    @Override
    public void onSaveGame() {

    }

    @Override
    public void onExit() {
        System.exit(0);
    }
}
