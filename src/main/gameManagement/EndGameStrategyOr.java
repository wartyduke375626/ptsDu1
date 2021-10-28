package gameManagement;

import java.util.ArrayList;
import java.util.List;

public class EndGameStrategyOr implements EndGameStrategy {

    List<EndGameStrategy> endGameStrategies = new ArrayList<>();

    public EndGameStrategyOr(List<EndGameStrategy> endGameStrategies) {
        this.endGameStrategies.addAll(endGameStrategies);
    }

    @Override
    public boolean isGameOver() {
        for (EndGameStrategy x : endGameStrategies) {
            if (x.isGameOver()) return true;
        }
        return false;
    }
}
