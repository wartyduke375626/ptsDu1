package gameManagement;

import gameComponents.*;

public class Game {

    private TurnInterface turnInterface;
    private boolean isPlayCardPhase;
    private EndGameStrategy endGameStrategy;
    private boolean isGameOver;

    public Game(TurnInterface turnInterface, EndGameStrategy endGameStrategy) {
        isPlayCardPhase = true;
        this.turnInterface = turnInterface;
        this.endGameStrategy = endGameStrategy;
        isGameOver = endGameStrategy.isGameOver();
    }

    public boolean playCard(int handIndex) {
        if (isGameOver) return false;
        if (isPlayCardPhase) return turnInterface.playCard(handIndex);
        return false;
    }

    public boolean endPlayCardPhase() {
        if (isGameOver) return false;
        if (!isPlayCardPhase) return false;
        isPlayCardPhase = false;
        return true;
    }

    public boolean buyCard(GameCardType gameCardType) {
        if (isGameOver) return false;
        if (!isPlayCardPhase) return turnInterface.buyCard(gameCardType);
        return false;
    }

    public boolean endTurn() {
        if (isGameOver) return false;
        if (isPlayCardPhase) return false;
        isPlayCardPhase = true;
        turnInterface.newTurn();
        isGameOver = endGameStrategy.isGameOver();
        return true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

}
