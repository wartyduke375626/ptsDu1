package gameComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Turn {

    private TurnStatus turnStatus;
    private DiscardPileInterface discardPile;
    private DeckInterface deck;
    private HandInterface hand;
    private PlayInterface play;
    private Map<GameCardType, BuyDeckInterface> buyDecks;

    public Turn(TurnStatus firstTurnStatus, DiscardPileInterface discardPile, DeckInterface deck, HandInterface hand, PlayInterface play, Map<GameCardType, BuyDeckInterface> buyDecks) {
        turnStatus = firstTurnStatus;
        this.discardPile = discardPile;
        this.deck = deck;
        this.hand = hand;
        this.play = play;
        this.buyDecks = buyDecks;

    }

    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
    }

    public void newTurn() {
        discardPile.addCards(play.throwAll());
        discardPile.addCards(hand.throwAll());
        hand.addCards(deck.draw(5));
    }

    public boolean playCard(int handIndex) {
        if (turnStatus.getActions() == 0) return false;
        if (handIndex >= hand.getSize()) return false;
        Optional<CardInterface> playedCard = hand.play(handIndex);
        if (playedCard.isEmpty()) return false;
        turnStatus.setActions(turnStatus.getActions()-1);
        play.putTo(playedCard.get());
        hand.addCards(deck.draw(playedCard.get().evaluate(turnStatus)));
        return true;
    }

    public boolean buyCard(GameCardType gameCardType) {
        if (turnStatus.getBuys() == 0) return false;
        if (turnStatus.getCoins() < gameCardType.getCost()) return false;
        if (!buyDecks.containsKey(gameCardType)) return false;
        Optional<CardInterface> boughtCard = buyDecks.get(gameCardType).buy();
        if (boughtCard.isEmpty()) return false;
        turnStatus.setBuys(turnStatus.getBuys()-1);
        turnStatus.setCoins(turnStatus.getCoins()-boughtCard.get().getGameCardType().getCost());
        List<CardInterface> toDiscardPile = new ArrayList<>();
        toDiscardPile.add(boughtCard.get());
        discardPile.addCards(toDiscardPile);
        return true;
    }
}
