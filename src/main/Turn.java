import java.util.List;
import java.util.Optional;

public class Turn {

    private DiscardPileInterface discardPile;
    private Deck deck;
    private Hand hand;
    private Play play;
    private TurnStatus turnStatus;

    public Turn(List<CardInterface> deckCards, TurnStatus firstTurnStatus) {
        discardPile = new DiscardPile(deckCards);
        deck = new Deck(discardPile);
        hand = new Hand();
        play = new Play();
        turnStatus = firstTurnStatus;
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
        if (handIndex >= hand.getSize()) throw new IndexOutOfBoundsException();
        Optional<CardInterface> playedCard = hand.play(handIndex);
        if (playedCard.isEmpty()) return false;
        play.putTo(playedCard.get());
        turnStatus.setActions(turnStatus.getActions() + playedCard.get().getCardType().getPlusActions());
        turnStatus.setBuys(turnStatus.getBuys() + playedCard.get().getCardType().getPlusBuys());
        turnStatus.setCoins(turnStatus.getCoins() + playedCard.get().getCardType().getPlusCoins());
        return true;
    }




}
