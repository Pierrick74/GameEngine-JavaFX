package org.Games.Controller;

import org.Games.JavaFX.Views.MenuHandler;
import org.Games.model.board.Cell;
import org.Games.observer.Observer;
import org.Games.model.game.GameModel;
import org.Games.model.board.Coordinate;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;

import static java.lang.System.exit;
import static org.Games.model.game.GameState.INVALIDINPUT;

public class GameController implements Observer, MenuHandler {
    private final GameModel model;
    private AppController appController;

    public GameController(GameType gameType, AppController appController, Integer numberOfPlayer) {
        if (gameType == null) {
            throw new IllegalArgumentException("gameType ne peut pas être null");
        }
        if (appController == null) {
            throw new IllegalArgumentException("appController ne peut pas être null");
        }
        if (numberOfPlayer == null) {
            throw new IllegalArgumentException("Le nombre de joueurs ne peut pas être null");
        }

        if (numberOfPlayer < 0 || numberOfPlayer > 2) {
            throw new IllegalArgumentException(
                    "Le nombre de joueurs  doit être entre 0 et 2"
            );
        }

        this.model = new GameModel(gameType);
        initController(appController, numberOfPlayer);
        this.appController = appController;
    }

    public GameController(GameModel game, AppController appController) {
        if (game == null) {
            throw new IllegalArgumentException("game ne peut pas être null");
        }
        if (appController == null) {
            throw new IllegalArgumentException("appController ne peut pas être null");
        }
        this.model = game;
        this.appController = appController;
        model.whoPlay();
    }

    private void initController(AppController appController, Integer numberOfPlayer) {
        model.createPlayersWithNumberOfHumain(numberOfPlayer);
        model.whoPlay();
        this.appController = appController;
    }

    @Override
    public void updateState(GameState gameState) {
        if (gameState == null) {
            throw new IllegalArgumentException("gameState ne peut pas être null");
        }
        this.model.setGameState(gameState);
    }

    public void registerView(Observer view) {
        if (view == null) {
            throw new IllegalArgumentException("view ne peut pas être null");
        }
        this.model.addObserver(view);
    }

    public void restartNewGame(){
        if (appController != null) {
            appController.deleteGame(model.getGameType());
            restart();
        } else {
            throw new IllegalArgumentException("pas de appController , impossible de restart");
        }

    }

    public void setGameState(GameState gameState){
        model.setGameState(gameState);
    }

    public void onCellClicked(int row, int col) {
        try {
            model.humainPlayerTurn(new Coordinate(row, col));
        } catch (InterruptedException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public int getButtonSize(){
        return model.getButtonSize();
    }

    public void removeAllObserver() {
        if(model != null) {
            model.removeAllObserver();
        } else {
            throw new IllegalArgumentException("il manque le model pour removeAllObserver");
        }
    }

    public String getPlayerName() {
        return model.getPlayerName();
    }

    public void keyPressed(String keyCode) {
        if (keyCode.matches("[0-9]")){
            model.keyPressed(keyCode);
        } else if ( (keyCode.equals("\r") || keyCode.equals("\n"))){
            model.valideKeyInput();
        } else {
            model.setGameState(INVALIDINPUT);
        }
    }

    @Override
    public void onNewGame() {
        restart();
    }

    @Override
    public void onSaveGame() {
        saveGame();
    }

    @Override
    public void onExit() {
        saveGame();
        exit(1);
    }

    private void saveGame() {
        model.saveGame();
    }

    private void stopGame() {
        if(model != null) {
            model.stopGame();
        } else {
            throw new IllegalArgumentException("il manque le model pour stopgame");
        }
    }

    private void restart() {
        stopGame();
        appController.backToGameSelection();
    }

    public String getGameName() {
        return model.getGameName();
    }

    public Cell[][] getBoard() {
        return model.getBoard();
    }

    public int getRowCount() {
        return model.getRowCount();
    }

    public int getColumnCount() {
        return model.getColumnCount();
    }

    public String getRepresentation(int row, int col) {
        return model.getRepresentation(row, col);
    }

    public String getWinner() {
        return model.getWinner();
    }

    public Integer getLastRow(){
        return model.getLastCoordinate().getRow();
    }

    public Integer getLastColumn(){
        return model.getLastCoordinate().getCol();
    }


/*
    private Coordinate getCoordinate(int maxLigne, int maxColonne) {
        Display.getInstance().displayText("quel est la ligne que vous voulez");
        int row = Terminal.getInstance().askForInteger(maxLigne);

        Display.getInstance().displayText("quel est la colonne que vous voulez");
        int col = Terminal.getInstance().askForInteger(maxColonne);

        return new Coordinate(row, col);
    }

    private int getColumn(int maxColonne) {
        Display.getInstance().displayText("quel est la colonne que vous voulez");
        return Terminal.getInstance().askForInteger(maxColonne);
    }

    private boolean isHumainWantToSaveGame(){
        Display.getInstance().displayText("Voulez-vous sauvgarder le jeu?");
        Display.getInstance().displayText("0: oui");
        Display.getInstance().displayText("1: non");
        int result =  Terminal.getInstance().askForInteger(2);
        return  result == 0;
    }

    private boolean isHumainWantToRestoreGame(){
        Display.getInstance().displayText("Il y a une partie sauvegardée, voulez vous la restaurer?");
        Display.getInstance().displayText("0: oui");
        Display.getInstance().displayText("1: non");
        int result =  Terminal.getInstance().askForInteger(2);
        return  result == 0;
    }
*/
}
