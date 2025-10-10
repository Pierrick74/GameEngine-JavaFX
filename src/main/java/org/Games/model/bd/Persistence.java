package org.Games.model.bd;

import org.Games.model.game.Game;
import org.Games.model.game.GameType;

import java.sql.Connection;

public interface Persistence {
    void saveGame(Game game);
    Game getGame(GameType gameType);
    boolean haveAGameSave(GameType gameType);
}
