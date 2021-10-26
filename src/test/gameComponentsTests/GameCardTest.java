package gameComponentsTests;

import gameComponents.GameCard;
import gameComponents.GameCardType;
import gameComponents.TurnStatus;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameCardTest {
    GameCard gameCard1;
    GameCard gameCard2;
    TurnStatus turnStatus;

    private void setUp() {
        gameCard1 = new GameCard(GameCardType.GAME_CARD_TYPE_MARKET);
        gameCard2 = new GameCard(GameCardType.GAME_CARD_TYPE_COPPER);
        turnStatus = new TurnStatus(0,0,0);
    }

    @Test
    public void test_evaluate(){
        setUp();
        assertEquals(gameCard1.evaluate(turnStatus), 1);
        assertEquals(turnStatus.getActions(), 1);
        assertEquals(turnStatus.getBuys(), 1);
        assertEquals(turnStatus.getCoins(), 1);
        assertEquals(gameCard2.evaluate(turnStatus), 0);
        assertEquals(turnStatus.getActions(), 1);
        assertEquals(turnStatus.getBuys(), 1);
        assertEquals(turnStatus.getCoins(), 2);

    }
}
