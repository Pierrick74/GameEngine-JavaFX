package org.Games.Controller;

import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.AppView;
import org.Games.JavaFX.Views.ChoosePlayerView;
import org.Games.JavaFX.Views.GameView;
import org.Games.JavaFX.Views.MenuHandler;
import org.Games.Vue.ConsoleView;
import org.Games.model.AppModel;
import org.Games.model.ChoosePlayerModel;
import org.Games.model.game.GameModel;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;

import static org.Games.model.game.GameState.ASKTORESTOREGAME;
import static org.Games.model.game.GameState.INVALIDINPUT;


public class AppController implements MenuHandler {
    private final AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    }
    /**
     * Select a new game with a type
     * @param gameType Type of game
     */
    public void startNewGame(GameType gameType) {
        ChoosePlayerModel model = new ChoosePlayerModel();
        ChoosePlayerController controller = new ChoosePlayerController(this, gameType, model);
        ChoosePlayerView view = new ChoosePlayerView(controller);

        controller.registerView(ConsoleView.getInstance());
        controller.setGameState(GameState.ASKFORCHOSENUMBEROFPLAYER);

        StageRepository.getInstance().replaceScene(view, controller);
    }

    /**
     * select to load save game of type
     * @param gameType Type of game
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
            model.removeAllObserver();
            gameController.removeAllObserver();

            gameController.registerView(gameView);
            gameController.registerView(ConsoleView.getInstance());
            ConsoleView.getInstance().setGameController(gameController);
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
        ConsoleView.getInstance().clearGameController();
        registerView(ConsoleView.getInstance());
        StageRepository.getInstance().replaceScene(appView, this);
    }

    /**
     * Stop the current game when leaving
     * @param gameController the game controller to stop
     */
    public void stopCurrentGame(GameController gameController) {
        if (gameController != null) {
            gameController.stopGame();
        }
    }

    /**
     * check if there is a save data
     * @param gameType data type
     */
    public void gameSelected(GameType gameType) {
        if(isSaveGame(gameType)) {
            model.setSelectedGameType(gameType);
            model.setGameState(ASKTORESTOREGAME);
        } else {
            startNewGame(gameType);
        }
    }

    public void isLoadSaveGame(Boolean bool) {
        if(bool) {
            startOldGame(model.getSelectedGameType());
        } else {
            startNewGame(model.getSelectedGameType());
        }
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
    public void registerView(Observer view) {
        this.model.addObserver(view);
    }

    /**
     * Use for delete save file of a game
     * @param gameType type of game user want to delete
     */
    public void deleteGame(GameType gameType) {
        model.deleteGame(gameType);
    }

    public void keyPressed(String keyCode) {
        switch (keyCode) {
            case "0":
                if(model.getSelectedGameType() != null){
                    isLoadSaveGame(true);
                } else {
                    gameSelected(GameType.TICTACTOE);
                }
                break;
            case "1":
                if(model.getSelectedGameType() != null){
                    isLoadSaveGame(false);
                } else {
                    gameSelected(GameType.GOMOKU);
                }
                break;
                case "2":
                    if(model.getSelectedGameType() == null){
                        gameSelected(GameType.POWER4);
                    } else {
                        model.setGameState(INVALIDINPUT);
                    }
                    break;
            default:
                model.setGameState(INVALIDINPUT);
                break;
        }
    }
}
