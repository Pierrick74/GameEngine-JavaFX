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

    /**
     * Change type of game selected in the model
     * if this type have save game, do nothing and wait for intruction
     * if have, launch game with a new model
     * @param gameType type of game user want
     */
    public void onGameSelected(GameType gameType) {
        model.setSelectedGameType(gameType);
        if(!model.isGameSaved(gameType)){
            launchGameWithOldGame(null);
        }
    }

    /**
     * launch game with or without save model
     * @param isloard variable who indicate if user want to load save game
     */
    public void startGameWithSaveData(Boolean isloard) {
        if(!isloard){
            launchGameWithOldGame(null);
        } else {
            Game saveGame = dbRepository.getGame(model.getSelectedGameType());
            launchGameWithOldGame(saveGame);
        }
    }

    /**
     * create game with MVC patern and subscribe view to the model
     * @param gameModel save game model  if existe
     */
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

            // remove all register of appModel before change view
            model.removeAllObserver();

            StageRepository.getInstance().replaceScene(gameView, gameController);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * use by other controller to return in this AppView
     */
    public void backToGameSelection() {
        model.removeAllObserver();
        AppView appView = new AppView(this);
        registerView(appView);
        StageRepository.getInstance().replaceScene(appView, this);
    }

    /**
     * use for menuhandler, do nothing
     */
    @Override
    public void onNewGame() {
    }

    /**
     * use for menuhandler, do nothing
     */
    @Override
    public void onSaveGame() {

    }

    /**
     * use for menuhandler, exit
     */
    @Override
    public void onExit() {
        System.exit(0);
    }

    /**
     * register view to the model
     * @param view App view
     */
    public void registerView(AppView view) {
        this.model.addObserver(view);
    }

    /**
     * Use for delete save file of a game
     * @param gameType type of game user want to delete
     */
    public void deleteGame(GameType gameType) {
        dbRepository.deleteGame(gameType);
    }
}
