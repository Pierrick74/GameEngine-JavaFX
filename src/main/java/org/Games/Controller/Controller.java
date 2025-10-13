package org.Games.Controller;

import javafx.application.Platform;
import org.Games.JavaFX.StageRepository;
import org.Games.JavaFX.Views.ChooseGameView;
import org.Games.JavaFX.Views.MainView;
import org.Games.model.bd.GameSerialization;
import org.Games.model.bd.Persistence;
import org.Games.model.game.Game;
import org.Games.model.board.Coordinate;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.model.rules.PlacementStrategy.TypeOfPlacement;
import org.Games.Vue.Display;
import org.Games.Vue.Terminal;


public class Controller {
    Game game;
    Coordinate coordinate;
    Persistence dbRepository;

    private static Controller instance;

    public static Controller getInstance() {
        return instance==null?instance = new Controller():instance;
    }

    private Controller() {
        this.game = new Game(GameType.TICTACTOE);
        this.coordinate = null;
        this.dbRepository = new GameSerialization();
    }

    public void start() throws InterruptedException {
        while(game.getGameState() != GameState.FINISHED) {
            switch (game.getGameState()) {
                case MAIN:
                    Platform.runLater(() -> StageRepository.getInstance().replaceScene(new MainView()));
                break;

                case ASKFORCHOSEGAME:
                    Platform.runLater(() -> StageRepository.getInstance().replaceScene(new ChooseGameView()));
                    break;

                case  CREATETICTACTOE:
                    game = new Game(GameType.TICTACTOE);
                    game.setGameState(GameState.INITGAME);
                    break;

                case  CREATEGOMOKU:
                    game = new Game(GameType.GOMOKU);
                    game.setGameState(GameState.INITGAME);
                    break;

                case  CREATEPOWER4:
                    game = new Game(GameType.POWER4);
                    game.setGameState(GameState.INITGAME);
                    break;

                case INITGAME:
                    if(dbRepository.haveAGameSave(game.getGameType())) {
                        if(isHumainWantToRestoreGame()){
                            game = dbRepository.getGame(game.getGameType());
                            game.setGameState(GameState.INITGAME);
                            game.setGameState(GameState.DISPLAYBOARD);
                        } else {
                            game.setGameState(GameState.INITPLAYER);
                        }
                    } else {
                        game.setGameState(GameState.INITPLAYER);
                    }
                    break;

                case INITPLAYER:
                    game.createPlayersWithNumberOfHumain(getNumberOfHumainPlayer());
                    game.setGameState(GameState.DISPLAYBOARD);
                    break;

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

    public void setGameState(GameState gameState) {
        this.game.setGameState(gameState);
    }
}
