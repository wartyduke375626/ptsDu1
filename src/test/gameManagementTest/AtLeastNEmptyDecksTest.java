package gameManagementTest;

import gameComponents.BuyDeckInterface;
import gameComponents.CardInterface;
import gameManagement.AtLeastNEmptyDecks;
import gameManagement.EndGameStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AtLeastNEmptyDecksTest {

    EndGameStrategy endGameStrategy1;
    EndGameStrategy endGameStrategy2;
    BuyDeckInterface emptyBuyDeck;
    BuyDeckInterface notEmptyBuyDeck;
    List<BuyDeckInterface> buyDecks = new ArrayList<>();

    private void setUp() {
        emptyBuyDeck = new BuyDeckInterface() {
            @Override
            public Optional<CardInterface> buy() {
                return Optional.empty();
            }

            @Override
            public boolean isEmpty() {
                return true;
            }
        };
        notEmptyBuyDeck = new BuyDeckInterface() {
            @Override
            public Optional<CardInterface> buy() {
                return Optional.empty();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        buyDecks.add(emptyBuyDeck);
        buyDecks.add(emptyBuyDeck);
        buyDecks.add(notEmptyBuyDeck);
        endGameStrategy1 = new AtLeastNEmptyDecks(3, buyDecks);
        endGameStrategy2 = new AtLeastNEmptyDecks(2, buyDecks);
    }

    @Test
    public void test_isGameOver() {
        setUp();
        assertFalse(endGameStrategy1.isGameOver());
        assertTrue(endGameStrategy2.isGameOver());
    }
}
