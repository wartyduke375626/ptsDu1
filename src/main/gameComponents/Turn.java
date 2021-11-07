package gameComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Turn implements TurnInterface {

    private final TurnStatus turnStatus;
    private final TurnStatus newTurnStatus; //TurnStatus values that will be set after end of each turn
    private final DiscardPileInterface discardPile;
    private final DeckInterface deck;
    private final HandInterface hand;
    private final PlayInterface play;
    private final Map<GameCardType, BuyDeckInterface> buyDecks;

    public Turn(TurnStatus newTurnStatus, DiscardPileInterface discardPile, DeckInterface deck, HandInterface hand, PlayInterface play, Map<GameCardType, BuyDeckInterface> buyDecks) {
        this.newTurnStatus = newTurnStatus;
        turnStatus = new TurnStatus(newTurnStatus.getActions(), newTurnStatus.getBuys(), newTurnStatus.getCoins());
        this.discardPile = discardPile;
        this.deck = deck;
        this.hand = hand;
        this.play = play;
        this.buyDecks = buyDecks;
    }

    @Override
    public TurnStatus getCurrentTurnStatus() {
        return turnStatus;
    }

    @Override
    public void newTurn() {
        discardPile.addCards(play.throwAll());
        discardPile.addCards(hand.throwAll());
        hand.addCards(deck.draw(5));
        turnStatus.setActions(newTurnStatus.getActions());
        turnStatus.setBuys(newTurnStatus.getBuys());
        turnStatus.setCoins(newTurnStatus.getCoins());
    }

    @Override
    public boolean playCard(int handIndex) {
        Optional<CardInterface> playedCard;
        boolean isActionCard = hand.isActionCard(handIndex);
        if (isActionCard && turnStatus.getActions() == 0) return false;
        playedCard = hand.play(handIndex);
        if (playedCard.isEmpty()) return false;
        if (isActionCard) turnStatus.setActions(turnStatus.getActions()-1);
        play.putTo(playedCard.get());
        hand.addCards(deck.draw(playedCard.get().evaluate(turnStatus)));
        return true;
    }

    @Override
    public boolean buyCard(GameCardType gameCardType) {
        if (turnStatus.getBuys() == 0) return false;
        if (turnStatus.getCoins() < gameCardType.getCost()) return false;
        if (!buyDecks.containsKey(gameCardType)) return false;
        Optional<CardInterface> boughtCard = buyDecks.get(gameCardType).buy();
        if (boughtCard.isEmpty()) return false;
        turnStatus.setBuys(turnStatus.getBuys()-1);
        turnStatus.setCoins(turnStatus.getCoins()-boughtCard.get().getGameCardType().getCost());
        discardPile.addCards(boughtCard.get());
        return true;
    }
}
