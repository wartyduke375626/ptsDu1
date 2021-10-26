package gameComponentsTests;

import gameComponents.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TurnTest {

    private CardInterface testCard = new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET);

    TurnStatus turnStatus = new TurnStatus(0,0,0);
    DiscardPileInterface discardPile;
    DeckInterface deck;
    HandInterface hand;
    PlayInterface play;

    private int handSize = 5;
    private Turn turn;

    private void setUp() {
        discardPile = new DiscardPileInterface() {
            private int size = 0;
            @Override
            public Optional<CardInterface> getTopCard() {
                return Optional.empty();
            }

            @Override
            public void addCards(List<CardInterface> cards) {
                size += cards.size();
            }

            @Override
            public int getSize() {
                return size;
            }

            @Override
            public List<CardInterface> shuffle() {
                return null;
            }
        };
        deck = new DeckInterface() {
            @Override
            public List<CardInterface> draw(int count) {
                List<CardInterface> ret= new ArrayList<>();
                for (int i=0; i<count; i++) ret.add(testCard);
                return ret;
            }
        };
        hand = new HandInterface() {
            private int size = handSize;
            @Override
            public void addCards(List<CardInterface> cards) {
                size += cards.size();
            }

            @Override
            public int getSize() {
                return size;
            }

            @Override
            public List<CardInterface> throwAll() {
                List<CardInterface> ret= new ArrayList<>();
                for (int i=0; i<size; i++) ret.add(testCard);
                return ret;
            }

            @Override
            public boolean isActionCard(int cardIndex) {
                return cardIndex%2 == 0;
            }

            @Override
            public Optional<CardInterface> play(int cardIndex) {
                if (isActionCard(cardIndex)) return Optional.of(testCard);
                else return Optional.empty();
            }
        };
        play = new PlayInterface() {
            private int size = 0;
            @Override
            public void putTo(CardInterface card) {
                size++;
            }

            @Override
            public List<CardInterface> throwAll() {
                List<CardInterface> ret= new ArrayList<>();
                for (int i=0; i<size; i++) ret.add(testCard);
                return ret;
            }
        };
        turn = new Turn(turnStatus, discardPile, deck, hand, play);
    }

    @Test
    public void test_playCard() {
        setUp();
        boolean flag = turn.playCard(0);
        assertTrue(flag);
        assertEquals(play.throwAll().size(), 1);
        flag = turn.playCard(1);
        assertFalse(flag);
        assertEquals(play.throwAll().size(), 1);
    }

    @Test
    public void test_newTurn() {
        setUp();
        turn.playCard(0);
        turn.playCard(0);
        turn.playCard(0);
        assertEquals(discardPile.getSize(), 0);
        turn.newTurn();
        assertEquals(discardPile.getSize(), 8);
        assertEquals(hand.getSize(), 5);
    }
}
