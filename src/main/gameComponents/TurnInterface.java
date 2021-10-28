package gameComponents;

public interface TurnInterface {
    public TurnStatus getCurrentTurnStatus();
    public void newTurn();
    public boolean playCard(int handIndex);
    public boolean buyCard(GameCardType gameCardType);
}
