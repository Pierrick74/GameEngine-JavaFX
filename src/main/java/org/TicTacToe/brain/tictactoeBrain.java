package org.TicTacToe.brain;

import org.TicTacToe.Brain;
import org.TicTacToe.Rules;
import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.player.Player;

import java.util.Collections;
import java.util.List;

public class tictactoeBrain implements Brain {
    Rules rules;

    public tictactoeBrain(Rules rules) {
        this.rules = rules;
    }

    public Coordinate getCoordinateForIAPlayer(Board board, Player me, Player opponent, Integer depth) {
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

        MinMaxResult result;
        MinMaxResult bestResult = null;

        // si gagner / perdu
        if(coordinate != null){
            if (rules.isFinished(board, coordinate)) {
                switch (rules.getResult(board, coordinate)) {
                    case WIN:
                        return !isMyTurn ? new MinMaxResult(50, coordinate) : new MinMaxResult(-50, coordinate);
                    case LOOSE:
                        return !isMyTurn ? new MinMaxResult(-50, coordinate) : new MinMaxResult(50, coordinate);
                    case TIE:
                        return new MinMaxResult(0, coordinate);
                }
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
            System.out.println("Coup testé: (" + c.getRow() + "," + c.getCol() + ") -> score: " + result.score());
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
