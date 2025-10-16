package org.Games.model;

import org.Games.model.bd.GameSerialization;
import org.Games.model.bd.Persistence;
import org.Games.model.game.GameModel;
import org.Games.model.game.GameType;
import org.Games.observer.Observable;

import java.util.ArrayList;


public class AppModel extends Observable {
    private GameType selectedGameType;

    private Persistence dbRepository;

    public AppModel() {
        this.selectedGameType = null;
        this.observers = new ArrayList<>();
        this.dbRepository = new GameSerialization();
    }

    /**
     * Check if a game is saved and notifie the view
     * @param gameType Type of game
     * @return Boolean
     */
    public boolean isGameSaved(GameType gameType) {
        return dbRepository.haveAGameSave(gameType);
    }

    // Getters
    public GameType getSelectedGameType() {
        return selectedGameType;
    }

    public GameModel getSaveGame(GameType gameType) {
        return dbRepository.getGame(gameType);
    }

    public void deleteGame(GameType gameType) {
        dbRepository.deleteGame(gameType);
    }

    // Setters
    public void setSelectedGameType(GameType gameType) {
        this.selectedGameType = gameType;
        notifyObservers();
    }
}

