package gameComponentsTests;

import gameComponents.CardInterface;
import gameComponents.GameCardType;

public class FakeCard implements CardInterface {
    private final GameCardType cardType;

    FakeCard(GameCardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public GameCardType getGameCardType() {
        return cardType;
    }
}
