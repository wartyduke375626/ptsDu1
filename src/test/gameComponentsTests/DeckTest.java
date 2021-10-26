package gameComponentsTests;

import gameComponents.CardInterface;
import gameComponents.Deck;
import gameComponents.DiscardPileInterface;
import gameComponents.GameCardType;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DeckTest {

    private Set<CardInterface> expectedCards;
    private Deck deck;

    private void setUp() {
        expectedCards = new HashSet<>();
        for (int i=0; i<5; i++) {
            expectedCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            expectedCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        }
        deck = new Deck(new DiscardPileInterface() {
            boolean empty = false;
            @Override
            public Optional<CardInterface> getTopCard() {
                return Optional.empty();
            }

            @Override
            public void addCards(List<CardInterface> cards) {

            }

            @Override
            public int getSize() {
                if (empty) return 0;
                else return 10;
            }

            @Override
            public List<CardInterface> shuffle() {
                if (empty) return new ArrayList<>();
                empty = true;
                return new ArrayList<>(expectedCards);
            }
        });
    }

    @Test
    public void test_draw() {
        setUp();
        Set<CardInterface> cardsDrawn = new HashSet<>(deck.draw(5));
        assertEquals(cardsDrawn.size(),5);
        cardsDrawn.addAll(deck.draw(5));
        //discardPile should be shuffled at this point and put into deck
        assertEquals(cardsDrawn, expectedCards);
        cardsDrawn.addAll(deck.draw(5));
        assertEquals(cardsDrawn, expectedCards);

    }
}
