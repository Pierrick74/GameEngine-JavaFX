package org.Games.Controller;

import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.AppView;
import org.Games.JavaFX.Views.ChoosePlayerView;
import org.Games.JavaFX.Views.GameView;
import org.Games.JavaFX.Views.MenuHandler;
import org.Games.model.AppModel;
import org.Games.model.game.GameModel;
import org.Games.model.game.GameType;


public class AppController implements MenuHandler {
    private AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    }

    /**
     * Change type of game selected in the model
     * if this type have save game, do nothing and wait for instruction
     * if had, launch game with a new model
     * @param gameType type of game user want
     */
    public void onGameSelected(GameType gameType) {
        model.setSelectedGameType(gameType);
        if(!model.isGameSaved(gameType)){
            launchGameWithAModel(null);
        }
    }

    /**
     * Select a new game with a type
     * @param gameType Type of game
     */
    public void startNewGame(GameType gameType) {
        ChoosePlayerController controller = new ChoosePlayerController(this, gameType);
        ChoosePlayerView view = new ChoosePlayerView(controller);
        StageRepository.getInstance().replaceScene(view, controller);
    }

    /**
     * select to load save game of type
     * @param gameType
     */
    public void startOldGame(GameType gameType) {
        GameModel saveGame = model.getSaveGame(gameType);
        launchGameWithAModel(saveGame);
    }

    public boolean isSaveGame(GameType gameType) {
        return model.isGameSaved(gameType);
    }

    /**
     * create game with MVC pattern and subscribe view to the model
     * @param gameModel save game model  if existe
     */
    private void launchGameWithAModel(GameModel gameModel) {
        System.out.println("Lancement du jeu: ");

        try {
            GameController gameController;
            if(gameModel != null){
                gameController = new GameController(gameModel, this);
            } else {
                gameController = new GameController(model.getSelectedGameType(), this, 2);
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
     * use for menu handler, do nothing
     */
    @Override
    public void onNewGame() {
    }

    /**
     * use for menu handler, do nothing
     */
    @Override
    public void onSaveGame() {

    }

    /**
     * use for menu handler, exit
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
        model.deleteGame(gameType);
    }
}
