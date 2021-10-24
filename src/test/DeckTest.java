import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck;
    private DiscardPile discardPile;

    private void setUp() {
        List<CardInterface> cards = new ArrayList<>();
        for (int i=0; i<3; i++){
            cards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_SMITHY));
            cards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        }
        discardPile = new DiscardPile(cards);
        deck = new Deck(discardPile);
    }

    @Test
    public void test_draw() {
        setUp();
        List<CardInterface> cardsDrawn = deck.draw(1);
        discardPile.addCards(cardsDrawn);
        Set<CardInterface> deckCards1 = new HashSet<>(cardsDrawn);
        for(int i=0; i<5; i++) {
            List<CardInterface> newCardsDrawn = deck.draw(1);
            discardPile.addCards(newCardsDrawn);
            assertNotEquals(cardsDrawn.get(0), newCardsDrawn.get(0));
            deckCards1.addAll(newCardsDrawn);
        }
        //discardPile should be shuffled at this point and put into deck
        Set<CardInterface> deckCards2 = new HashSet<>(deck.draw(6));
        assertEquals(discardPile.getSize(), 0);
        assertEquals(deckCards1, deckCards2);

    }
}
