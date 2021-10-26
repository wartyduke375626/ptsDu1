package gameComponents;

public class GameCard implements CardInterface {
    private final GameCardType cardType;

    public GameCard(GameCardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public int evaluate(TurnStatus turnStatus) {
        turnStatus.setActions(turnStatus.getActions() + cardType.getPlusActions());
        turnStatus.setBuys(turnStatus.getBuys() + cardType.getPlusBuys());
        turnStatus.setCoins(turnStatus.getCoins() + cardType.getPlusCoins());
        return cardType.getPlusCards();
    }

    @Override
    public GameCardType getGameCardType() {
        return cardType;
    }
}
