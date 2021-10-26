package gameComponentsTests;

import gameComponents.BuyDeck;
import gameComponents.BuyDeckInterface;
import gameComponents.CardInterface;
import gameComponents.GameCardType;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

public class BuyDeckTest {

    private BuyDeckInterface buyDeck;

    private void setUp() {
        buyDeck = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 2);
    }

    @Test
    public void test_buy() {
        setUp();
        Optional<CardInterface> card = buyDeck.buy();
        assertTrue(card.isPresent());
        assertEquals(card.get().getGameCardType(), GameCardType.GAME_CARD_TYPE_COPPER);
        card = buyDeck.buy();
        assertTrue(card.isPresent());
        assertEquals(card.get().getGameCardType(), GameCardType.GAME_CARD_TYPE_COPPER);
        card = buyDeck.buy();
        assertTrue(card.isEmpty());
    }
}
