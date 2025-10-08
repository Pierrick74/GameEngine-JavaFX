package org.TicTacToe.brain;

import org.TicTacToe.Brain;
import org.TicTacToe.Rules;
import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.FinishName;
import org.TicTacToe.commun.Representation;
import org.TicTacToe.player.Player;

import static org.TicTacToe.commun.FinishName.*;

public class tictactoeBrain extends Brain {
    Rules rules =  new Rules(3);

    private Player getOpponent(Player player) {
        Representation opponentType = player.getType() == Representation.ROUND ? Representation.CROSS : Representation.ROUND;
        return new Player(opponentType);
    }

    public Coordinate getCoordinateForIAPlayer(Board board, Player player) {
        int bestScore = Integer.MIN_VALUE;
        Coordinate bestCoordinate = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board.getCell(new Coordinate(i,j)).getType() == Representation.EMPTY) {
                    Board testBoard = board.getClone();
                    testBoard.setCell(new Coordinate(i,j), player);
                    int score = minimax(testBoard, 0, false, new Coordinate(i,j), player, player);
                    System.out.println("Test (" + i + "," + j + ") -> score: " + score);
                    if(score > bestScore) {
                        bestScore = score;
                        bestCoordinate = new Coordinate(i,j);
                    }
                }
            }
        }
        System.out.println("Best move: " + bestCoordinate.getRow() + "," + bestCoordinate.getCol() + " avec score: " + bestScore);
        return bestCoordinate;
    }

    private int minimax(Board board, int depth, boolean isIATurn, Coordinate lastCoordinate, Player lastPlayer, Player iaPlayer) {
        int score = 0;
        int evaluation = evaluateMove(board, lastCoordinate, lastPlayer, iaPlayer);
        if(evaluation != Integer.MAX_VALUE) {
            return evaluation;
        }

        if (depth >= 4) {
            return 0;
        }

        Player currentPlayer = isIATurn ? iaPlayer : getOpponent(iaPlayer);

        if(isIATurn) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board.getCell(new Coordinate(i,j)).getType() == Representation.EMPTY) {
                        board.setCell(new Coordinate(i,j), currentPlayer);
                        score += minimax(board, depth + 1, false, new Coordinate(i,j), currentPlayer, iaPlayer);
                        board.setCell(new Coordinate(i,j), new Player(Representation.EMPTY));
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board.getCell(new Coordinate(i,j)).getType() == Representation.EMPTY) {
                        board.setCell(new Coordinate(i,j), currentPlayer);
                        score += minimax(board, depth + 1, true, new Coordinate(i,j), currentPlayer, iaPlayer);
                        board.setCell(new Coordinate(i,j), new Player(Representation.EMPTY));
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private int evaluateMove(Board board, Coordinate coordinate, Player player, Player iaPlayer) {
        FinishName result = rules.getResult(board, coordinate);

        if(result == null) {
            return Integer.MAX_VALUE; // Game continues
        }

        if (result == TIE) {
            return 0;
        }

        return player.getType() == iaPlayer.getType() ? 10 : -10;
    }
}
