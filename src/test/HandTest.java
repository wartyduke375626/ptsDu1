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
        assertEquals(1, hand.getSize());
        assertTrue(card.isPresent());
        CardInterface card1 = card.get();
        assertEquals(card1.getCardType(), GameCardType.GAME_CARD_TYPE_MARKET);
        assertEquals(1, hand.getSize());
        card = hand.play(0);
        assertTrue(card.isEmpty());
    }
}
