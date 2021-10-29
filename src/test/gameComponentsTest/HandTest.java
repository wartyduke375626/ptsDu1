package gameComponentsTest;

import gameComponents.CardInterface;
import gameComponents.GameCardType;
import gameComponents.Hand;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HandTest {

    private Hand hand;
    private Hand emptyHand;
    private List<CardInterface> handCards;

    private void setUp() {
        hand = new Hand();
        handCards = new ArrayList<>();
        handCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        handCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        hand.addCards(handCards);
        emptyHand = new Hand();
    }

    @Test
    public void test_throwAll() {
        setUp();
        assertEquals(hand.getSize(), 2);
        Set<CardInterface> expectedThrow = new HashSet<>(handCards);
        Set<CardInterface> thrown = new HashSet<>(hand.throwAll());
        assertEquals(hand.getSize(), 0);
        assertEquals(expectedThrow, thrown);
        assertEquals(emptyHand.getSize(), 0);
        thrown = new HashSet<>(emptyHand.throwAll());
        assertTrue(thrown.isEmpty());
        assertEquals(emptyHand.getSize(), 0);
    }

    @Test
    public void test_addCards_getSize() {
        hand = new Hand();
        handCards = new ArrayList<>();
        assertEquals(hand.getSize(), 0);
        handCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        handCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        hand.addCards(handCards);
        assertEquals(hand.getSize(), 2);
        hand.addCards(handCards);
        assertEquals(hand.getSize(), 4);
    }

    @Test
    public void test_isActionCard() {
        setUp();
        assertTrue(hand.isActionCard(0));
        assertFalse(hand.isActionCard(1));
        assertFalse(hand.isActionCard(2));
        assertFalse(emptyHand.isActionCard(0));
    }

    @Test
    public void test_play() {
        setUp();
        Optional<CardInterface> card = hand.play(0);
        assertEquals(1, hand.getSize());
        assertTrue(card.isPresent());
        CardInterface card1 = card.get();
        assertEquals(card1.getGameCardType(), GameCardType.GAME_CARD_TYPE_MARKET);
        assertEquals(1, hand.getSize());
        card = hand.play(0);
        assertFalse(card.isEmpty());
        card = hand.play(1);
        assertTrue(card.isEmpty());
        card = emptyHand.play(0);
        assertTrue(card.isEmpty());
    }
}
