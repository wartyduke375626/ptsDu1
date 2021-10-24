import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class HandTest {

    private Hand hand;
    private List<CardInterface> handCards;

    private void setUp() {
        hand = new Hand();
        handCards = new ArrayList<>();
        handCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        handCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        hand.addCards(handCards);
    }

    @Test
    public void test_isActionCard() {
        setUp();
        assertTrue(hand.isActionCard(0));
        assertFalse(hand.isActionCard(1));
    }

    @Test
    public void test_play() {
        setUp();
        Optional<CardInterface> card = hand.play(0);
        assertEquals(1, hand.getCards().size());
        assertTrue(card.isPresent());
        CardInterface card1 = card.get();
        assertEquals(card1.cardType(), GameCardType.GAME_CARD_TYPE_MARKET);
        card = hand.play(10);
        assertTrue(card.isEmpty());
        assertEquals(1, hand.getCards().size());
        card = hand.play(0);
        assertTrue(card.isEmpty());
    }
}
