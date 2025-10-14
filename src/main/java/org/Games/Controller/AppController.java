package org.Games.Controller;

import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.AppView;
import org.Games.JavaFX.Views.GameView;
import org.Games.JavaFX.Views.MenuHandler;
import org.Games.model.AppModel;
import org.Games.model.bd.GameSerialization;
import org.Games.model.bd.Persistence;
import org.Games.model.game.Game;
import org.Games.model.game.GameType;


public class AppController implements MenuHandler {
    private AppModel model;
    private Persistence dbRepository;

    public AppController(AppModel model) {
        this.model = model;
        this.dbRepository = new GameSerialization();
    }

    public void onGameSelected(GameType gameType) {
        model.setSelectedGameType(gameType);
        if(!model.isGameSaved(gameType)){
            launchGameWithOldGame(null);
        }
    }

    public void startGameWithSaveData(Boolean isloard) {
        if(!isloard){
            launchGameWithOldGame(null);
        } else {
            Game saveGame = dbRepository.getGame(model.getSelectedGameType());
            launchGameWithOldGame(saveGame);
        }
    }

    public void onQuitRequested() {
        model.setShouldQuit(true);
        System.exit(0);
    }

    private void launchGameWithOldGame(Game gameModel) {
        System.out.println("Lancement du jeu: ");

        try {
            GameController gameController;
            if(gameModel != null){
                gameController = new GameController(gameModel, this);
            } else {
                gameController = new GameController(model.getSelectedGameType(), this);
            }
            GameView gameView = new GameView(gameController);
            gameController.registerView(gameView);
            model.removeAllObserver();
            StageRepository.getInstance().replaceScene(gameView, gameController);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToGameSelection() {
        model.removeAllObserver();
        AppView appView = new AppView(this);
        registerView(appView);
        StageRepository.getInstance().replaceScene(appView, this);
    }

    @Override
    public void onNewGame() {
    }

    @Override
    public void onSaveGame() {

    }

    @Override
    public void onExit() {
        System.exit(0);
    }

    public void registerView(AppView view) {
        this.model.addObserver(view);
    }
}
