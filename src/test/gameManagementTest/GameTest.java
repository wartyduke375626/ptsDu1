package gameManagementTest;

import gameComponents.GameCardType;
import gameComponents.TurnInterface;
import gameComponents.TurnStatus;
import gameManagement.EndGameStrategy;
import gameManagement.Game;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private TurnInterface fakeTurn;
    private EndGameStrategy endGameStrategy;

    private void setUp() {
        fakeTurn = new TurnInterface() {
            @Override
            public TurnStatus getCurrentTurnStatus() {
                return null;
            }

            @Override
            public void newTurn() {

            }

            @Override
            public boolean playCard(int handIndex) {
                return true;
            }

            @Override
            public boolean buyCard(GameCardType gameCardType) {
                return true;
            }
        };
        endGameStrategy = new EndGameStrategy() {
            @Override
            public boolean isGameOver() {
                return false;
            }
        };
        game = new Game(fakeTurn, endGameStrategy);
    }

    @Test
    public void test_playCard() {
        setUp();
        boolean flag = game.playCard(0);
        assertTrue(flag);
        game.endPlayCardPhase();
        flag = game.playCard(0);
        assertFalse(flag);
        flag = game.endTurn();
        assertTrue(flag);
    }

    @Test
    public void test_buyCard() {
        setUp();
        boolean flag = game.buyCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertFalse(flag);
        game.endPlayCardPhase();
        flag = game.buyCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertTrue(flag);
        game.endTurn();
        flag = game.buyCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertFalse(flag);
    }

    @Test
    public void test_endPlayCardPhase() {
        setUp();
        boolean flag = game.endPlayCardPhase();
        assertTrue(flag);
        flag = game.endPlayCardPhase();
        assertFalse(flag);
        game.endTurn();
        flag = game.endPlayCardPhase();
        assertTrue(flag);
    }

    @Test
    public void test_endTurn() {
        setUp();
        boolean flag = game.endTurn();
        assertFalse(flag);
        game.endPlayCardPhase();
        flag = game.endTurn();
        assertTrue(flag);
        flag = game.endTurn();
        assertFalse(flag);
    }
}
