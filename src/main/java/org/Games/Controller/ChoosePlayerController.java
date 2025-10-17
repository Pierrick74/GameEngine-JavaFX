package org.Games.Controller;

import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.GameView;
import org.Games.JavaFX.Views.MenuHandler;
import org.Games.Vue.ConsoleView;
import org.Games.model.ChoosePlayerModel;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;

import static org.Games.model.game.GameState.INVALIDINPUT;

public class ChoosePlayerController implements MenuHandler {
    private AppController appController;
    private GameType gameType;
    private ChoosePlayerModel model;

    public ChoosePlayerController(AppController appController, GameType gameType, ChoosePlayerModel model) {
        this.appController = appController;
        this.gameType = gameType;
        this.model = model;
    }

    public void numberOfHumain(Integer number) {
        try {
            GameController gameController = new GameController(gameType, appController, number);
            GameView gameView = new GameView(gameController);
            gameController.registerView(gameView);
            gameController.registerView(ConsoleView.getInstance());
            ConsoleView.getInstance().setGameController(gameController);

            gameController.setGameState(GameState.DISPLAYONLYBOARD);

            StageRepository.getInstance().replaceScene(gameView, gameController);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void keyPressed(String keyCode) {
        switch (keyCode) {
            case "0":
                numberOfHumain(0);
                break;
            case "1":
                numberOfHumain(1);
                break;
            case "2":
                numberOfHumain(2);
                break;
            default:
                model.setGameState(INVALIDINPUT);
                break;
        }
    }


    public void registerView(Observer observer) {
        model.addObserver(observer);
    }

    public void setGameState(GameState gameState) {
        model.setGameState(gameState);
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
