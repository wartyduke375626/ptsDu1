package gameManagement;

import gameComponents.*;

import java.util.List;
import java.util.Map;

public class Game {

    private TurnInterface turnInterface;
    private boolean isPlayCardPhase;

    public Game(TurnStatus newTurnStatus, List<CardInterface> initialCards, Map<GameCardType, BuyDeckInterface> buyDecks) {
        isPlayCardPhase = true;
        DiscardPileInterface discardPile = new DiscardPile(initialCards);
        turnInterface = new Turn(newTurnStatus, discardPile, new Deck(discardPile), new Hand(), new Play(), buyDecks);
    }

    public boolean playCard() {
        return false;
    }

    public boolean endPlayCardPhase() {
        if (!isPlayCardPhase) return false;
        isPlayCardPhase = false;
        return true;
    }

    public boolean buyCard() {
        return false;
    }

    public boolean endTurn() {
        if (isPlayCardPhase) return false;
        isPlayCardPhase = true;
        turnInterface.newTurn();
        return true;
    }

}
