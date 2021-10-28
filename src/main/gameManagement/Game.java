package gameManagement;

import gameComponents.*;

import java.util.List;
import java.util.Map;

public class Game {

    private TurnInterface turnInterface;
    private boolean isPlayCardPhase;
    private EndGameStrategy endGameStrategy;
    private boolean isGameOver;

    public Game(TurnStatus newTurnStatus, List<CardInterface> initialCards, Map<GameCardType, BuyDeckInterface> buyDecks, EndGameStrategy endGameStrategy) {
        isPlayCardPhase = true;
        DiscardPileInterface discardPile = new DiscardPile(initialCards);
        turnInterface = new Turn(newTurnStatus, discardPile, new Deck(discardPile), new Hand(), new Play(), buyDecks);
        this.endGameStrategy = endGameStrategy;
        isGameOver = endGameStrategy.isGameOver();
    }

    public boolean playCard(int handIndex) {
        if (isPlayCardPhase) return turnInterface.playCard(handIndex);
        return false;
    }

    public boolean endPlayCardPhase() {
        if (!isPlayCardPhase) return false;
        isPlayCardPhase = false;
        return true;
    }

    public boolean buyCard(GameCardType gameCardType) {
        if (!isPlayCardPhase) return turnInterface.buyCard(gameCardType);
        return false;
    }

    public boolean endTurn() {
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
