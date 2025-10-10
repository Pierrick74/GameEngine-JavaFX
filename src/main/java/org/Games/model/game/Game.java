package org.Games.model.game;

import org.Games.model.board.Board;
import org.Games.model.brain.Brain;
import org.Games.model.board.Coordinate;
import org.Games.model.rules.PlacementStrategy.FreePlacement;
import org.Games.model.rules.PlacementStrategy.GravityPlacement;
import org.Games.model.rules.PlacementStrategy.TypeOfPlacement;
import org.Games.model.commun.RepresentationStrategy.GomokuRepresentation;
import org.Games.model.commun.RepresentationStrategy.Power4Representation;
import org.Games.model.commun.RepresentationStrategy.RepresentationStrategy;
import org.Games.model.commun.RepresentationStrategy.TicTacToeRepresentation;
import org.Games.model.player.ArtificialPlayer;
import org.Games.model.player.Player;
import org.Games.model.rules.Rules;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Game implements Serializable {
    private Board board;
    private Rules rules;
    private Brain brain;
    private RepresentationStrategy symboleStrategie;

    private Player[] players;
    private Integer activePlayer = 1;
    private Coordinate lastCoordinate = null;
    private int maxDepth;
    private GameType gameType;

    private GameState gameState =  GameState.MAIN;
    private GameState oldGameState;

    public Game(GameType gameType) {
        this.gameType = gameType;
        switch(gameType) {
            case TICTACTOE -> initTicTacToe();
            case GOMOKU -> initGomoku();
            case POWER4 -> initPower4();
        }
    }

    //Init
    public void initTicTacToe() {
        this.board = new Board(3, 3);
        this.rules = new Rules(3, new FreePlacement());
        this.brain = new Brain(rules);
        this.symboleStrategie = new TicTacToeRepresentation();
        this.maxDepth = 99;
    }

    public void initGomoku() {
        this.board = new Board(15, 15);
        this.rules = new Rules(5, new FreePlacement());
        this.brain = new Brain(rules);
        this.symboleStrategie = new GomokuRepresentation();
        this.maxDepth = 2;
    }

    public void initPower4() {
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

    public void humainPlayerTurn(Coordinate coordinate) {
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
        return board.getXSize();
    }

    public Integer getySize() {
        return board.getYSize();
    }

    public boolean isValideCoordinate(Coordinate coordinate) {
        return rules.isValideCoordinate(this.board, coordinate);
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public GameType getGameType() {
        return gameType;
    }
}
