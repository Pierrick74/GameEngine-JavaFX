package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;

public class TicTacToe {
    Board board;
    Player player;
    Integer size;

    public TicTacToe(Integer size) {
        this.size = size;
        this.board = new Board(size);
        this.player = new Player(Representation.ROUND);
    }

    public void start() {
        board.display();
        Coordinate coordinate = askForCoordinate();
        board.setCell(coordinate, player);
        board.display();
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
