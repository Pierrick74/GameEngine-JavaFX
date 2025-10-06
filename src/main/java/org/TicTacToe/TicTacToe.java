package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;
import org.TicTacToe.player.ArtificialPlayer;
import org.TicTacToe.player.Player;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TicTacToe {
    Board board;
    Player[] players;
    Rules rules;

    Integer activePlayer = 0;
    Integer size;

    public TicTacToe(Integer size) {
        this.size = size;
        this.board = new Board(size);
        this.rules = new Rules();
    }

    public void start() throws InterruptedException {

        createPlayers();

        boolean isFinished = false;
        board.display();

        while (!isFinished) {
            activePlayer = activePlayer == 0 ? 1 : 0;

            if( players[activePlayer] instanceof ArtificialPlayer){
                artificialPlayerTurn();
            } else {
                humainPlayerTurn();
            }
            board.display();

            if(rules.isFinished(board)){
                isFinished = true;
                Display.getInstance().displayText("Joueur " + activePlayer + " gagne");
            }

            if(board.isFull()){
                isFinished = true;
                Display.getInstance().displayText("Personne ne gagne");
            }
        }
    }

    private void humainPlayerTurn(){
        Display.getInstance().displayText("Joueur " + activePlayer);
        Coordinate coordinate = askForCoordinate();
        board.setCell(coordinate, players[activePlayer]);
    }

    private void artificialPlayerTurn() throws InterruptedException {
        Display.getInstance().displayText("Joueur " + activePlayer);
        Coordinate coordinate = getCoordinateForIAPlayer();
        TimeUnit.SECONDS.sleep(2);
        board.setCell(coordinate, players[activePlayer]);
    }

    private Coordinate askForCoordinate() {
        Coordinate coordinate = null;
        boolean isValide = false;

        while (!isValide) {
            Display.getInstance().displayText("quel est la ligne que vous voulez");
            int row = Terminal.getInstance().askForInteger(size);

            Display.getInstance().displayText("quel est la colonne que vous voulez");
            int col = Terminal.getInstance().askForInteger(size);

            coordinate = new Coordinate(row, col);
            Display.getInstance().displayText(coordinate.toString());

            if(!isEmptyCase(coordinate)) {
                Display.getInstance().displayText("La case est déja prise, merci de rentrer une nouvelle valeur");
            } else {
                isValide = true;
            }
        }
        return coordinate;
    }

    private Coordinate getCoordinateForIAPlayer() {
        boolean isValide = false;
        Random rand = new Random();
        Coordinate coordinate = null;

        coordinate = rules.getCoordinateToBlock(board, players[activePlayer].getType());
        if(coordinate != null){
            return coordinate;
        }

        while (!isValide) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            coordinate = new Coordinate(row, col);

            if(isEmptyCase(coordinate)){
                isValide = true;
            }
        }
        return coordinate;
    }

    private Boolean isEmptyCase(Coordinate coordinate) {
        return board.getCell(coordinate).getType() == Representation.EMPTY;
    }

    private void createPlayers() {
        Display.getInstance().displayText("Comment voulez-vous jouer?");
        Display.getInstance().displayText("0: 2 Joueurs Humain");
        Display.getInstance().displayText("1: 1 humain et un joueur artificiel");
        Display.getInstance().displayText("2: 2 Joueurs artificiels");
        int result = Terminal.getInstance().askForInteger(3);

        switch (result) {
            case 0:
                this.players = new Player[]{new Player(Representation.ROUND), new Player(Representation.CROSS)};
                break;
            case 1:
                this.players = new Player[]{new Player(Representation.ROUND), new ArtificialPlayer(Representation.CROSS)};
                break;
            case 2:
                this.players = new Player[]{new ArtificialPlayer(Representation.ROUND), new ArtificialPlayer(Representation.CROSS)};
                break;
        }
    }
}
