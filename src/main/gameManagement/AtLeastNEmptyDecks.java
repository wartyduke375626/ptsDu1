package gameManagement;

import gameComponents.BuyDeckInterface;

import java.util.ArrayList;
import java.util.List;

public class AtLeastNEmptyDecks implements EndGameStrategy {

    private final int n;
    private final List<BuyDeckInterface> buyDecksToCheck = new ArrayList<>();

    public AtLeastNEmptyDecks(int n, List<BuyDeckInterface> buyDecksToCheck) {
        this.n = n;
        this.buyDecksToCheck.addAll(buyDecksToCheck);
    }

    @Override
    public boolean isGameOver() {
        int empty = 0;
        for(BuyDeckInterface x : buyDecksToCheck) {
            if (x.isEmpty()) {
                empty++;
            }
        }
        return empty == n;
    }
}
