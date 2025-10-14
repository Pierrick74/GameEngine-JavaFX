package org.Games.Controller;

import org.Games.model.AppModel;
import org.Games.model.game.GameState;
import org.Games.model.game.GameType;
import org.Games.observer.Observer;

public class AppController implements Observer {
    private AppModel model;

    public AppController(AppModel model) {
        this.model = model;
        this.model.addObserver(this);
    }

    public void onGameSelected(GameType gameType) {
        model.setSelectedGameType(gameType);
    }

    public void onQuitRequested() {
        model.setShouldQuit(true);
    }

    @Override
    public void updateState(GameState gameState) {
        if (model.shouldLaunchGame()) {
            launchGame(model.getSelectedGameType());
            model.resetLaunchFlag(); // Reset le flag pour éviter de relancer
        }

        if (model.shouldQuit()) {
            //TODO penser a mettre la sauvegarde
            System.exit(0);
        }
    }

    private void launchGame(GameType gameType) {
        // TODO: Créer le MVC du jeu choisi
        // Pour l'instant, juste un message de debug
        System.out.println("Lancement du jeu: " + gameType);

          /*
          Quand vous aurez le MVC du jeu, ce sera quelque chose comme :

          switch(gameType) {
              case TICTACTOE -> {
                  Game game = new Game(GameType.TICTACTOE);
                  GameModel gameModel = new GameModel(game);
                  GameController gameController = new GameController(gameModel);
                  GameView gameView = new GameView(gameController);
                  StageRepository.getInstance().replaceScene(gameView);
              }
              case GOMOKU -> { ... }
              case POWER4 -> { ... }
          }
          */
    }
}
