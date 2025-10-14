package org.Games.Controller;

import org.Games.JavaFX.Views.GameView;
import org.Games.JavaFX.Views.MenuHandler;
import org.Games.model.bd.GameSerialization;
import org.Games.observer.Observer;
import org.Games.model.bd.Persistence;
import org.Games.model.game.Game;
import org.Games.model.board.Coordinate;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.Vue.Display;
import org.Games.Vue.Terminal;

import static java.lang.System.exit;


public class GameController implements Observer, MenuHandler {
    Game model;
    Coordinate coordinate;
    private AppController appController;

    public GameController(GameType gameType, AppController appController) throws InterruptedException {
        this.model = new Game(gameType);
        this.coordinate = null;
        initController(appController);
    }

    public GameController(Game Game, AppController appController) throws InterruptedException {
        this.model = Game;
        this.coordinate = model.getLastCoordinate();
        this.appController = appController;
    }

    private void initController(AppController appController) throws InterruptedException {
        //TODO A CHANGER
        model.createPlayersWithNumberOfHumain(2);
        model.whoPlay();
        this.appController = appController;
    }

    @Override
    public void updateState(GameState gameState) {
        this.model.setGameState(gameState);
    }

    public void registerView(GameView view) {
        this.model.addObserver(view);
    }

    public String getGameName() {
        return model.getGameName();
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

    public void restart() {
        appController.backToGameSelection();
    }

    public void onCellClicked(int row, int col) {
        System.out.println("Clicked on cell " + row + " " + col);
        try {
            model.humainPlayerTurn(new Coordinate(row, col));
        } catch (InterruptedException e){
            System.err.println("Interrupted while waiting for player turn");
        }
    }

    public int getButtonSize(){
        return model.getButtonSize();
    }


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

    private int getNumberOfHumainPlayer(){
        Display.getInstance().displayText("Comment voulez-vous jouer?");
        Display.getInstance().displayText("2: 2 Joueurs Humain");
        Display.getInstance().displayText("1: 1 humain et un joueur artificiel");
        Display.getInstance().displayText("0: 2 Joueurs artificiels");
        return Terminal.getInstance().askForInteger(3);
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
        Persistence DBRepository = new GameSerialization();
        DBRepository.saveGame(model);
    }


/*
    public void start() throws InterruptedException {

        while(game.getGameState() != GameState.FINISHED) {
            switch (game.getGameState()) {

                case DISPLAYBOARD:
                    game.displayBoard();
                    switch (game.getOldGameState()) {
                        case INITPLAYER:
                            game.setGameState(GameState.CHECKPLAYER);
                            break;
                        case MOVE, IAMOVE:
                            game.setGameState(GameState.CHECKFINISH);
                            break;
                        case INITGAME:
                            game.setGameState(GameState.CHECKPLAYER);
                    }
                    break;

                case CHECKSAVE:
                    if(isHumainWantToSaveGame()){
                        dbRepository.saveGame(game);
                    }
                    game.setGameState(GameState.CHECKPLAYER);
                    break;

                case CHECKPLAYER:
                    Display.getInstance().displayText("Joueur " + game.getActivePlayer());
                    if(game.isPlayerHumainTurn()) {
                        game.setGameState(GameState.ASKPLAYER);
                    } else {
                        game.setGameState(GameState.IAMOVE);
                    }
                    break;

                case MOVE:
                    game.humainPlayerTurn(coordinate);
                    game.setGameState(GameState.DISPLAYBOARD);
                    break;

                case CHECKFINISH:
                    String winner = game.isGameFinished();

                    if (winner != null) {
                        Display.getInstance().displayText(winner);
                        game.setGameState(GameState.FINISHED);
                    } else {
                        game.changeActivePlayer();
                        game.setGameState(GameState.CHECKSAVE);
                    }
                    break;

                case ASKPLAYER:
                    if( game.getTypeOfPlacement() == TypeOfPlacement.FREE) {
                        coordinate = getCoordinate(game.getySize(),  game.getxSize());
                        if(!game.isValideCoordinate(coordinate)) {
                            Display.getInstance().displayText("La case est déja prise, merci de rentrer une nouvelle valeur");
                            break;
                        }
                    } else  {
                        int col = getColumn(game.getxSize());
                        coordinate = new  Coordinate(0, col);
                        if(!game.isValideCoordinate(coordinate)) {
                            Display.getInstance().displayText("La Colonne est Saturée");
                            break;
                        }
                    }

                    game.setGameState(GameState.MOVE);
                    break;

                case IAMOVE:
                    game.artificialPlayerTurn();
                    game.setGameState(GameState.DISPLAYBOARD);
                    break;
            }
            Thread.sleep(500);
        }
    }

*/
}
