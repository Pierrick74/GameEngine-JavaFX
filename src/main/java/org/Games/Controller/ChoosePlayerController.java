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
        if (appController == null) {
            throw new IllegalArgumentException("appController ne peut pas être null");
        }

        if (gameType == null) {
            throw new IllegalArgumentException("gameType ne peut pas être null");
        }

        if (model == null) {
            throw new IllegalArgumentException("model ne peut pas être null");
        }

        this.appController = appController;
        this.gameType = gameType;
        this.model = model;
    }

    public void numberOfHumain(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("Le nombre de joueurs ne peut pas être null");
        }

        if (number < 0 || number > 2) {
            throw new IllegalArgumentException(
                    "Le nombre de joueurs  doit être entre 0 et 2"
            );
        }

        GameController gameController = new GameController(gameType, appController, number);
        GameView gameView = new GameView(gameController);
        gameController.registerView(gameView);
        gameController.registerView(ConsoleView.getInstance());
        ConsoleView.getInstance().setGameController(gameController);

       gameController.displayInput();

        StageRepository.getInstance().replaceScene(gameView, gameController);
    }
    public void keyPressed(String keyCode) {
        if(keyCode == null || keyCode.isEmpty()) {
            model.setGameState(INVALIDINPUT);
            return;
        }

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
        if (observer == null) {
            throw new IllegalArgumentException("GameState ne peut pas être null");
        }
        model.addObserver(observer);
    }

    public void setGameState(GameState gameState) {
        if (gameState == null) {
            throw new IllegalArgumentException("GameState ne peut pas être null");
        }

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
