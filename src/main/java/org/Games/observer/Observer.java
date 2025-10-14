package org.Games.observer;

import org.Games.model.game.GameState;

public interface Observer {
    void updateState(GameState gameState);
}
