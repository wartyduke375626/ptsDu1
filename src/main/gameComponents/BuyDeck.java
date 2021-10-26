package gameComponents;

import java.util.Optional;

public class BuyDeck implements BuyDeckInterface{

    private int cardCount;
    private GameCardType gameCardType;

    public BuyDeck(GameCardType gameCardType, int cardCount) {
        this.cardCount = cardCount;
        this.gameCardType =gameCardType;
    }

    @Override
    public Optional<CardInterface> buy() {
        if (cardCount > 0) {
            cardCount--;
            return Optional.of(new GameCard(gameCardType));
        }
        return Optional.empty();
    }
}
