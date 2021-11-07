package gameComponentsTest;

import gameComponents.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TurnTest {

    private final CardInterface testCard = new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER);

    private final TurnStatus newTurnStatus = new TurnStatus(2,1,0);
    private DiscardPileInterface discardPile;
    private DeckInterface deck;
    private HandInterface hand;
    private PlayInterface play;
    private final Map<GameCardType, BuyDeckInterface> buyDecks = new HashMap<>();

    private final int handSize = 5;
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
            public void addCards(CardInterface... cards) {
                for (CardInterface x : cards) size++;
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
            public int getSize() {
                return 0;
            }

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
                size = 0;
                return ret;
            }

            @Override
            public boolean isActionCard(int cardIndex) {
                return cardIndex%2 == 0;
            }

            @Override
            public Optional<CardInterface> play(int cardIndex) {
                if (cardIndex < handSize) return Optional.of(testCard);
                else return Optional.empty();
            }

            @Override
            public Optional<CardInterface> lookAt(int cardIndex) {
                return Optional.empty();
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
                size = 0;
                return ret;
            }

            @Override
            public int getSize() {
                return size;
            }
        };
        buyDecks.put(testCard.getGameCardType(), new BuyDeckInterface() {
            @Override
            public Optional<CardInterface> buy() {
                return Optional.of(new FakeCard(testCard.getGameCardType()));
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        turn = new Turn(newTurnStatus, discardPile, deck, hand, play, buyDecks);
    }

    @Test
    public void test_playCard() {
        setUp();
        assertEquals(turn.getCurrentTurnStatus().getActions(), 2);

        boolean flag = turn.playCard(0);
        assertTrue(flag);
        assertEquals(play.throwAll().size(), 1);
        assertEquals(turn.getCurrentTurnStatus().getActions(), 1);

        flag = turn.playCard(1);
        assertTrue(flag);

        turn.playCard(0);
        assertEquals(turn.getCurrentTurnStatus().getActions(), 0);

        flag = turn.playCard(0);
        assertFalse(flag);

        flag = turn.playCard(1);
        assertTrue(flag);

        assertEquals(play.throwAll().size(), 3);
        flag = turn.playCard(5);
        assertFalse(flag);
        assertEquals(play.throwAll().size(), 0);
    }

    @Test
    public void test_newTurn() {
        setUp();
        turn.playCard(0);
        turn.playCard(0);
        turn.buyCard(testCard.getGameCardType());
        turn.newTurn();
        /* discardPile will contain:
            5 cards thrown from hand,
            2 cards thrown from play,
            1 card bought from buyDeck
         */
        assertEquals(discardPile.getSize(), 8);
        assertEquals(hand.getSize(), 5);
        assertEquals(turn.getCurrentTurnStatus().getActions(), newTurnStatus.getActions());
        assertEquals(turn.getCurrentTurnStatus().getBuys(), newTurnStatus.getBuys());
        assertEquals(turn.getCurrentTurnStatus().getCoins(), newTurnStatus.getCoins());
    }

    @Test
    public void test_buyCard() {
        setUp();
        boolean flag = turn.buyCard(testCard.getGameCardType());
        assertTrue(flag);
        assertEquals(discardPile.getSize(), 1);
        assertEquals(turn.getCurrentTurnStatus().getBuys(), 0);
        flag = turn.buyCard(testCard.getGameCardType());
        assertFalse(flag);
        assertEquals(discardPile.getSize(), 1);

    }
}
