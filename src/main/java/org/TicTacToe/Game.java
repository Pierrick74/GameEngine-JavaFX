package org.TicTacToe;

import org.TicTacToe.board.Board;
import org.TicTacToe.commun.Coordinate;
import org.TicTacToe.commun.GameType;
import org.TicTacToe.commun.RepresentationStrategy.GomokuRepresentation;
import org.TicTacToe.commun.RepresentationStrategy.Power4Representation;
import org.TicTacToe.commun.RepresentationStrategy.RepresentationStrategy;
import org.TicTacToe.commun.RepresentationStrategy.TicTacToeRepresentation;
import org.TicTacToe.interaction.Display;
import org.TicTacToe.interaction.Terminal;
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
    Integer xSize;
    Integer ySize;
    Coordinate lastCoordinate = null;

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
        this.rules = new Rules(3);
        this.brain = new Brain();
        this.symboleStrategie = new TicTacToeRepresentation();
    }

    public void initGomoku() {
        this.xSize = 15;
        this.ySize = 15;
        this.board = new Board(15, 15);
        this.rules = new Rules(5);
        this.brain = new Brain();
        this.symboleStrategie = new GomokuRepresentation();
    }

    public void initPower4() {
        this.xSize = 7;
        this.ySize = 6;
        this.board = new Board(7, 6);
        this.rules = new Rules(4);
        this.brain = new Brain();
        this.symboleStrategie = new Power4Representation();
    }

    public void start() throws InterruptedException {

        createPlayers();

        boolean isFinished = false;
        board.display();

        while (!isFinished) {
            activePlayer = activePlayer == 0 ? 1 : 0;

            if( players[activePlayer] instanceof ArtificialPlayer){
                artificialPlayerTurn();
            } else {
                humainPlayerTurn();
            }
            board.display();

            if(rules.isFinished(board, lastCoordinate)){
                isFinished = true;
                Display.getInstance().displayText("Joueur " + activePlayer + " gagne");
            }

            if(board.isFull()){
                isFinished = true;
                Display.getInstance().displayText("Personne ne gagne");
            }
        }
    }

    private void humainPlayerTurn(){
        Display.getInstance().displayText("Joueur " + activePlayer);
        lastCoordinate = askForCoordinate();
        board.setCell(lastCoordinate, players[activePlayer]);
    }

    private void artificialPlayerTurn() throws InterruptedException {
        Display.getInstance().displayText("Joueur " + activePlayer);
        lastCoordinate = brain.getCoordinateForIAPlayer(board, players[activePlayer]);
        TimeUnit.SECONDS.sleep(1);
        board.setCell(lastCoordinate, players[activePlayer]);
    }

    private Coordinate askForCoordinate() {
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

    private void createPlayers() {
        Display.getInstance().displayText("Comment voulez-vous jouer?");
        Display.getInstance().displayText("0: 2 Joueurs Humain");
        Display.getInstance().displayText("1: 1 humain et un joueur artificiel");
        Display.getInstance().displayText("2: 2 Joueurs artificiels");
        int result = Terminal.getInstance().askForInteger(3);

        switch (result) {
            case 0:
                this.players = new Player[]{new Player(symboleStrategie.getPlayerSymbol(0)), new Player(symboleStrategie.getPlayerSymbol(1))};
                break;
            case 1:
                this.players = new Player[]{new Player(symboleStrategie.getPlayerSymbol(0)), new ArtificialPlayer(symboleStrategie.getPlayerSymbol(1))};
                break;
            case 2:
                this.players = new Player[]{new ArtificialPlayer(symboleStrategie.getPlayerSymbol(0)), new ArtificialPlayer(symboleStrategie.getPlayerSymbol(1))};
                break;
        }
    }
}
