package org.Games.Controller;

import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.AppView;
import org.Games.JavaFX.Views.GameView;
import org.Games.model.AppModel;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;

public class AppController implements Observer {
    private AppModel model;

    public AppController(AppModel model) {
        this.model = model;
        this.model.addObserver(this);
    }

    public void onGameSelected(GameType gameType) {
        model.setSelectedGameType(gameType);
    }

    public void onQuitRequested() {
        model.setShouldQuit(true);
    }

    @Override
    public void updateState(GameState gameState) {
        if (model.shouldLaunchGame()) {
            launchGame(model.getSelectedGameType());
            model.resetLaunchFlag(); // Reset le flag pour éviter de relancer
        }

        if (model.shouldQuit()) {
            //TODO penser a mettre la sauvegarde
            System.exit(0);
        }
    }

    private void launchGame(GameType gameType) {
        System.out.println("Lancement du jeu: " + gameType);

        try {
            GameController gameController = new GameController(gameType, this);
            GameView gameView = new GameView(gameController);
            gameController.registerView(gameView);
            StageRepository.getInstance().replaceScene(gameView);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToGameSelection() {
        AppView appView = new AppView(this);
        StageRepository.getInstance().replaceScene(appView);
    }
}
