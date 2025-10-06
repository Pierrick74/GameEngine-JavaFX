package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;

public class TicTacToe {
    Board board;
    Integer activePlayer = 0;
    Integer size;
    Player[] players;

    public TicTacToe(Integer size) {
        this.size = size;
        this.board = new Board(size);
        this.players = new Player[]{new Player(Representation.ROUND), new Player(Representation.CROSS)};
    }

    public void start() {
        board.display();
        while (!board.isFull()) {
            activePlayer = activePlayer == 0 ? 1 : 0;
            Display.getInstance().displayText("Joueur " + activePlayer);
            Coordinate coordinate = askForCoordinate();
            board.setCell(coordinate, players[activePlayer]);
            board.display();
        }
    }

    private Coordinate askForCoordinate() {
        Coordinate coordinate = null;
        Boolean isValide = false;

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
        if( board.getCell(coordinate).getType() != Representation.EMPTY) {
            return false;
        }
        return true;
    }
}
