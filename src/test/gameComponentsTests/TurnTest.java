package gameComponentsTests;

import gameComponents.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TurnTest {

    private CardInterface testCard = new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET);

    Turn turn1;
    Turn turn2;
    TurnStatus turnStatus1 = new TurnStatus(0,0,0);
    TurnStatus turnStatus2 = new TurnStatus(0,0,0);

    private void setUp() {
        DiscardPileInterface discardPile = new DiscardPileInterface() {
            @Override
            public Optional<CardInterface> getTopCard() {
                return Optional.empty();
            }

            @Override
            public void addCards(List<CardInterface> cards) {
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public List<CardInterface> shuffle() {
                return null;
            }
        };
        DeckInterface deck = new DeckInterface() {
            @Override
            public List<CardInterface> draw(int count) {
                List<CardInterface> ret= new ArrayList<>();
                ret.add(testCard);
                return ret;
            }
        };
        HandInterface hand1 = new HandInterface() {
            @Override
            public void addCards(List<CardInterface> cards) {
            }

            @Override
            public int getSize() {
                return Integer.MAX_VALUE;
            }

            @Override
            public List<CardInterface> throwAll() {
                return null;
            }

            @Override
            public boolean isActionCard(int cardIndex) {
                return true;
            }

            @Override
            public Optional<CardInterface> play(int cardIndex) {
                return Optional.of(testCard);
            }
        };
        HandInterface hand2 = new HandInterface() {
            @Override
            public void addCards(List<CardInterface> cards) {
            }

            @Override
            public int getSize() {
                return Integer.MAX_VALUE;
            }

            @Override
            public List<CardInterface> throwAll() {
                return null;
            }

            @Override
            public boolean isActionCard(int cardIndex) {
                return false;
            }

            @Override
            public Optional<CardInterface> play(int cardIndex) {
                return Optional.empty();
            }
        };
        PlayInterface play = new PlayInterface() {
            @Override
            public void putTo(CardInterface card) {
            }

            @Override
            public List<CardInterface> throwAll() {
                return null;
            }
        };
        turn1 = new Turn(turnStatus1, discardPile, deck, hand1, play);
        turn2 = new Turn(turnStatus2, discardPile, deck, hand2, play);
    }

    @Test
    public void test_playCard() {
        setUp();
        boolean flag = turn1.playCard(1);
        assertTrue(flag);
        assertEquals(turnStatus1.getActions(),1);
        assertEquals(turnStatus1.getBuys(),1);
        assertEquals(turnStatus1.getCoins(),1);
        flag = turn2.playCard(1);
        assertFalse(flag);
    }
}
