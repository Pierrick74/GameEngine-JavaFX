package org.Games.model.bd;

import org.Games.model.game.Game;
import org.Games.model.game.GameType;

import java.io.*;

public class GameSerialization implements Persistence {

    @Override
    public void saveGame(Game game) {

        try {
            FileOutputStream fileOut = new FileOutputStream(getNameOfFile(game.getGameType()));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/" + getNameOfFile(game.getGameType()) + "\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public Game getGame(GameType gameType) {
        try {
            FileInputStream fileIn = new FileInputStream(getNameOfFile(gameType));
            System.out.println("Loading data from /tmp/" + getNameOfFile(gameType) + "\n");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game game = (Game) in.readObject();
            System.out.println(game != null);
            in.close();
            fileIn.close();
            return game;
        } catch (IOException i) {
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Game class not found");
            return  null;
        }
    }

    public boolean haveAGameSave(GameType gameType) {
        Game game = getGame(gameType);
        return game != null;
    }

    private String getNameOfFile(GameType gameType) {
        String name = "";
        switch(gameType){
            case TICTACTOE -> name = "TicTactoe.ser";
            case GOMOKU ->  name = "GoMoku.ser";
            case POWER4 ->  name = "Power4.ser";
        }
        return name;
    }
}
