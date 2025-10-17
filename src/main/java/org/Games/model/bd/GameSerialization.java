package org.Games.model.bd;

import org.Games.model.game.GameModel;
import org.Games.model.game.GameType;

import java.io.*;

public class GameSerialization implements Persistence {

    @Override
    public void saveGame(GameModel game) {
        if(game == null){
            throw new IllegalArgumentException("GameModel ne peut pas être null");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(getNameOfFile(game.getGameType()));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            throw new IllegalArgumentException(i.getMessage());
        }
    }

    @Override
    public GameModel getGame(GameType gameType) {
        if(gameType == null){
            throw new IllegalArgumentException("gameType ne peut pas être null");
        }
        try {
            FileInputStream fileIn = new FileInputStream(getNameOfFile(gameType));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GameModel game = (GameModel) in.readObject();
            System.out.println(game != null);
            in.close();
            fileIn.close();
            return game;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException i) {
            System.err.println("Erreur de lecture sauvegarde");
            return null;
        } catch (ClassNotFoundException c) {
            System.err.println("Fichier corrompu détecté, suppression...");
            deleteGame(gameType); // Nettoie le fichier corrompu
            return null;
        }
    }

    public boolean haveAGameSave(GameType gameType) {
        if(gameType == null){
            throw new IllegalArgumentException("gameType ne peut pas être null");
        }
        GameModel game = getGame(gameType);
        return game != null;
    }
    public void deleteGame(GameType gameType) {
        if(gameType == null){
            throw new IllegalArgumentException("gameType ne peut pas être null");
        }
        File file = new File(getNameOfFile(gameType));
        file.delete();
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
