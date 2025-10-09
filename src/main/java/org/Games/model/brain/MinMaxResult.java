package org.Games.model.brain;

import org.Games.model.board.Coordinate;

public record MinMaxResult(Integer score, Coordinate move) {
}
