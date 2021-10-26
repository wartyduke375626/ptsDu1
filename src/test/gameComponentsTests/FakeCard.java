package gameComponentsTests;

import gameComponents.CardInterface;
import gameComponents.GameCardType;
import gameComponents.TurnStatus;

public class FakeCard implements CardInterface {
    private final GameCardType cardType;

    FakeCard(GameCardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public int evaluate(TurnStatus turnStatus) {
        return 0;
    }

    @Override
    public GameCardType getGameCardType() {
        return cardType;
    }
}
