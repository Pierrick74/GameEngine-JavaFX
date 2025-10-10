package org.Games.model.rules.PlacementStrategy;

import org.Games.model.board.Board;
import org.Games.model.board.Coordinate;
import org.Games.model.player.Representation;
import org.Games.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FreePlacement implements PlacementStrategie, Serializable {

    public boolean isValideCoordinate(Board board, Coordinate coordinate) {
        return board.isEmptyCase(coordinate);
    }

    public List<Coordinate> getAvailableMove(Board board){
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < board.getXSize(); i++) {
            for (int j = 0; j < board.getYSize(); j++) {
                if (board.getCell(new Coordinate(i, j)).getType() == Representation.EMPTY) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        return coordinates;
    }

    public Coordinate setCell(Board board, Coordinate coordinate, Player player){
        board.setCell(coordinate, player);
        return coordinate;
    }
}
