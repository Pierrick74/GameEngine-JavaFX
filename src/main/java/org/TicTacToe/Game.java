package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.brain.Brain;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.GameState;
import org.TicTacToe.commun.GameType;
import org.TicTacToe.commun.PlacementStrategy.FreePlacement;
import org.TicTacToe.commun.PlacementStrategy.GravityPlacement;
import org.TicTacToe.commun.PlacementStrategy.TypeOfPlacement;
import org.TicTacToe.commun.RepresentationStrategy.GomokuRepresentation;
import org.TicTacToe.commun.RepresentationStrategy.Power4Representation;
import org.TicTacToe.commun.RepresentationStrategy.RepresentationStrategy;
import org.TicTacToe.commun.RepresentationStrategy.TicTacToeRepresentation;
import org.TicTacToe.player.ArtificialPlayer;
import org.TicTacToe.player.Player;

import java.util.concurrent.TimeUnit;

public class Game {
    Board board;
    Rules rules;
    Brain brain;
    RepresentationStrategy symboleStrategie;

    Player[] players;
    Integer activePlayer = 1;
    private Integer xSize;
    private Integer ySize;
    Coordinate lastCoordinate = null;
    int maxDepth;

    private GameState gameState =  GameState.INITPLAYER;
    private GameState oldGameState;

    public Game(GameType gameType) {
        switch(gameType) {
            case TICTACTOE ->   initTicTacToe();
            case GOMOKU -> initGomoku();
            case POWER4 -> initPower4();
        }
    }

    //Init
    public void initTicTacToe() {
        this.xSize = 3;
        this.ySize = 3;
        this.board = new Board(3, 3);
        this.rules = new Rules(3, new FreePlacement());
        this.brain = new Brain(rules);
        this.symboleStrategie = new TicTacToeRepresentation();
        this.maxDepth = 99;
    }

    public void initGomoku() {
        this.xSize = 15;
        this.ySize = 15;
        this.board = new Board(15, 15);
        this.rules = new Rules(5, new FreePlacement());
        this.brain = new Brain(rules);
        this.symboleStrategie = new GomokuRepresentation();
        this.maxDepth = 2;
    }

    public void initPower4() {
        this.xSize = 7;
        this.ySize = 6;
        this.board = new Board(7, 6);
        this.rules = new Rules(4, new GravityPlacement());
        this.brain = new Brain(rules);
        this.symboleStrategie = new Power4Representation();
        this.maxDepth = 5;
    }

    public void changeActivePlayer() {
        activePlayer = activePlayer == 0 ? 1 : 0;
    }

    public boolean isPlayerHumainTurn() {
        return !(players[activePlayer] instanceof ArtificialPlayer);
    }

    public String isGameFinished() {
        if(rules.isFinished(board, lastCoordinate)){
            return "Joueur " + activePlayer + " gagne";
        }

        if(board.isFull()){
            return "Personne ne gagne";
        }
        return null;
    }

    public void humainPlayerTurn(Coordinate coordinate) throws InterruptedException {
        lastCoordinate = rules.setCell(board, coordinate, players[activePlayer]);
    }

    public void artificialPlayerTurn() throws InterruptedException {
        int inactivePlayer = activePlayer == 1 ? 0 : 1;

        lastCoordinate = brain.getCoordinateForIAPlayer(board, players[activePlayer],players[inactivePlayer], maxDepth);
        TimeUnit.SECONDS.sleep(1);
        lastCoordinate = rules.setCell(board, lastCoordinate, players[activePlayer]);
    }

    public void createPlayersWithNumberOfHumain(int numberOfHumain) {
        switch (numberOfHumain) {
            case 2:
                this.players = new Player[]{new Player(symboleStrategie.getPlayerSymbol(0)), new Player(symboleStrategie.getPlayerSymbol(1))};
                break;
            case 1:
                this.players = new Player[]{new Player(symboleStrategie.getPlayerSymbol(0)), new ArtificialPlayer(symboleStrategie.getPlayerSymbol(1))};
                break;
            case 0:
                this.players = new Player[]{new ArtificialPlayer(symboleStrategie.getPlayerSymbol(0)), new ArtificialPlayer(symboleStrategie.getPlayerSymbol(1))};
                break;
        }
    }

    public void displayBoard() {
        board.display();
    }

    public void setGameState(GameState gameState) {
        this.oldGameState = this.gameState;
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameState getOldGameState() {
        return oldGameState;
    }

    public TypeOfPlacement getTypeOfPlacement() {
        return rules.getTypeOfPlacement();
    }

    public Integer getxSize() {
        return xSize;
    }

    public Integer getySize() {
        return ySize;
    }

    public boolean isValideCoordinate(Coordinate coordinate) {
        return rules.isValideCoordinate(this.board, coordinate);
    }

    public int getActivePlayer() {
        return activePlayer;
    }
}
