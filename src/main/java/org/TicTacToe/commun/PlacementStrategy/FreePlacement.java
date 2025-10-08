package org.TicTacToe.commun.PlacementStrategy;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;

import java.util.ArrayList;
import java.util.List;

public class FreePlacement implements PlacementStrategie {
    public Coordinate askForPlacement(Board board) {
        int xSize = board.getXSize();
        int ySize = board.getYSize();

        Coordinate coordinate = null;
        boolean isValide = false;

        while (!isValide) {
            Display.getInstance().displayText("quel est la ligne que vous voulez");
            int row = Terminal.getInstance().askForInteger(ySize);

            Display.getInstance().displayText("quel est la colonne que vous voulez");
            int col = Terminal.getInstance().askForInteger(xSize);

            coordinate = new Coordinate(row, col);
            Display.getInstance().displayText(coordinate.toString());

            if(!board.isEmptyCase(coordinate)) {
                Display.getInstance().displayText("La case est déja prise, merci de rentrer une nouvelle valeur");
            } else {
                isValide = true;
            }
        }
        return coordinate;
    }

    public List<Coordinate> getAvailableMove(Board board){
        int xSize = board.getXSize();
        int ySize = board.getYSize();
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < board.getXSize(); i++) {
            for (int j = 0; j < board.getYSize(); j++) {
                if (board.getCell(new Coordinate(i, j)).getType() == Representation.EMPTY) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        return coordinates;
    }
}
