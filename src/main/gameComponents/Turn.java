package gameComponents;

import java.util.Optional;

public class Turn {

    private TurnStatus turnStatus;
    private DiscardPileInterface discardPile;
    private DeckInterface deck;
    private HandInterface hand;
    private PlayInterface play;

    public Turn(TurnStatus firstTurnStatus, DiscardPileInterface discardPile, DeckInterface deck, HandInterface hand, PlayInterface play) {
        turnStatus = firstTurnStatus;
        this.discardPile = discardPile;
        this.deck = deck;
        this.hand = hand;
        this.play = play;

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
        if (handIndex >= hand.getSize()) return false;
        Optional<CardInterface> playedCard = hand.play(handIndex);
        if (playedCard.isEmpty()) return false;
        play.putTo(playedCard.get());
        hand.addCards(deck.draw(playedCard.get().evaluate(turnStatus)));
        return true;
    }
}
