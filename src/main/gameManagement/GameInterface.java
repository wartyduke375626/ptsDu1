package gameManagement;

import gameComponents.GameCardType;

public interface GameInterface {

    public boolean playCard(int handIndex);
    public boolean endPlayCardPhase();
    public boolean buyCard(GameCardType gameCardType);
    public boolean endTurn();
    public boolean isGameOver();
}
