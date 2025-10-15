package org.Games.model.bd;

import org.Games.model.game.GameModel;
import org.Games.model.game.GameType;

public interface Persistence {
    void saveGame(GameModel game);
    GameModel getGame(GameType gameType);
    boolean haveAGameSave(GameType gameType);
    public void deleteGame(GameType gameType);
}
