package gameComponents;

public interface TurnInterface {
    public void setTurnStatus(TurnStatus turnStatus);
    public void newTurn();
    public boolean playCard(int handIndex);
    public boolean buyCard(GameCardType gameCardType);
}
