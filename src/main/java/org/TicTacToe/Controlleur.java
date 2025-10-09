package org.TicTacToe;

import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.GameState;
import org.TicTacToe.commun.GameType;
import org.TicTacToe.commun.PlacementStrategy.TypeOfPlacement;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;


public class Controlleur {
    Game game;
    Coordinate coordinate;

    public Controlleur(GameType gameType) {
        game = new Game(gameType);
    }

    public void start() throws InterruptedException {
        while(game.getGameState() != GameState.FINISHGAME) {
            switch (game.getGameState()) {
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
                        case MOVE:
                            game.setGameState(GameState.CHECKFINISH);
                            break;
                        case IAMOVE:
                            game.setGameState(GameState.CHECKFINISH);
                            break;
                    }
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
                        game.setGameState(GameState.FINISHGAME);
                    } else {
                        game.changeActivePlayer();
                        game.setGameState(GameState.CHECKPLAYER);
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
}
