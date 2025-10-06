package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;
import org.TicTacToe.player.ArtificialPlayer;
import org.TicTacToe.player.Player;

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

    public void start() {

        createPlayers();

        boolean isFinished = false;
        board.display();

        while (!isFinished) {
            activePlayer = activePlayer == 0 ? 1 : 0;
            Display.getInstance().displayText("Joueur " + activePlayer);
            Coordinate coordinate = askForCoordinate();
            board.setCell(coordinate, players[activePlayer]);
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
            case 1:
                this.players = new Player[]{new Player(Representation.ROUND), new Player(Representation.CROSS)};
                break;
            case 2:
                this.players = new Player[]{new Player(Representation.ROUND), new ArtificialPlayer(Representation.CROSS)};
                break;
            case 3:
                this.players = new Player[]{new ArtificialPlayer(Representation.ROUND), new ArtificialPlayer(Representation.CROSS)};
                break;
        }
    }
}
