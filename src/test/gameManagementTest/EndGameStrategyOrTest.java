package gameManagementTest;

import gameManagement.EndGameStrategy;
import gameManagement.EndGameStrategyOr;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EndGameStrategyOrTest {

    private EndGameStrategyOr endGameStrategyOr;
    private EndGameStrategy endGameStrategyTrue;
    private EndGameStrategy endGameStrategyFalse;
    private List<EndGameStrategy> returnFalse = new ArrayList<>();
    private List<EndGameStrategy> returnTrue = new ArrayList<>();
    private List<EndGameStrategy> returnTrueFalse = new ArrayList<>();

    private void setUp() {
        endGameStrategyTrue = new EndGameStrategy() {
            @Override
            public boolean isGameOver() {
                return true;
            }
        };
        endGameStrategyFalse = new EndGameStrategy() {
            @Override
            public boolean isGameOver() {
                return false;
            }
        };
        returnFalse.add(endGameStrategyFalse);
        returnFalse.add(endGameStrategyFalse);
        returnTrue.add(endGameStrategyTrue);
        returnTrueFalse.add(endGameStrategyTrue);
        returnTrueFalse.add(endGameStrategyFalse);
    }

    @Test
    public void test_isGameOver() {
        setUp();
        endGameStrategyOr = new EndGameStrategyOr(returnFalse);
        assertFalse(endGameStrategyOr.isGameOver());
        endGameStrategyOr = new EndGameStrategyOr(returnTrue);
        assertTrue(endGameStrategyOr.isGameOver());
        endGameStrategyOr = new EndGameStrategyOr(returnTrueFalse);
        assertTrue(endGameStrategyOr.isGameOver());
    }
}
