package org.Games.model.brain;

import org.Games.model.rules.Rules;
import org.Games.model.board.Board;
import org.Games.model.board.Coordinate;
import org.Games.model.player.Player;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Brain implements Serializable {
    Rules rules;

    public Brain(Rules rules) {
        if(rules == null) {
            throw new IllegalArgumentException("Rules cannot be null");
        }
        this.rules = rules;
    }

    public Coordinate getCoordinateForIAPlayer(Board board, Player me, Player opponent, int depth) {
        if(board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        if(me == null  || opponent == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        MinMaxResult result = minimaxAlt(board, depth, true, me, opponent, null);
        System.out.println("==> CHOIX FINAL: (" + result.move().getRow() + "," + result.move().getCol() + ") avec score " + result.score());
        return result.move();
    }

    private MinMaxResult minimaxAlt(
            Board board,
            int depth,
            boolean isMyTurn,
            Player me,
            Player opponent,
            Coordinate coordinate
    ) {
        if(board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        if(me == null  || opponent == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if(depth < 0) {
            depth = 0;
        }

        MinMaxResult result;
        MinMaxResult bestResult = null;

        if(coordinate != null){
            if (rules.isFinished(board, coordinate)) {
                return switch (rules.getResult(board, coordinate)) {
                    case WIN ->
                            !isMyTurn ? new MinMaxResult(50 * depth, coordinate) : new MinMaxResult(-50, coordinate);
                    case LOOSE ->
                            !isMyTurn ? new MinMaxResult(-50 * depth, coordinate) : new MinMaxResult(50, coordinate);
                    case TIE -> new MinMaxResult(0, coordinate);
                };
            }
        }

        if(depth == 0){
            return new MinMaxResult(0, coordinate);
        }

        List<Coordinate> possibleMove = rules.getAvailableMove(board);
       Collections.shuffle(possibleMove);
        if(possibleMove.isEmpty()) {
            return new MinMaxResult(0, coordinate);
        }

        for(Coordinate c : possibleMove) {
            Board testBoard = board.getClone();
            if(isMyTurn) {
                testBoard.setCell(c, me);
            } else {
                testBoard.setCell(c, opponent);
            }
            result = minimaxAlt(testBoard, depth-1, !isMyTurn, me, opponent, c);

            if(bestResult == null) {
                bestResult = new MinMaxResult(result.score(), c);
            } else if(isMyTurn) {
                if(result.score() > bestResult.score()) {
                    bestResult = new MinMaxResult(result.score(), c);
                }
            } else {
                if(result.score() < bestResult.score()) {
                    bestResult = new MinMaxResult(result.score(), c);
                }
            }

        }
        return bestResult;
    }
}
