package gameComponentsTest;

import gameComponents.CardInterface;
import gameComponents.GameCardType;
import gameComponents.NonShufflingDiscardPile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NonShufflingDiscardPileTest {

    private NonShufflingDiscardPile discardPile;
    List<CardInterface> testCards;

    private void setUp(){
        testCards = new ArrayList<>();
        testCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        testCards.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        discardPile = new NonShufflingDiscardPile(testCards);
    }

    @Test
    public void test_shuffle() {
        setUp();
        assertEquals(testCards, discardPile.shuffle());
        assertEquals(discardPile.getSize(), 0);
    }
}
