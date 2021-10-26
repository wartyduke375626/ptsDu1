package gameManagement;

import java.util.Optional;
 
public interface SimpleDominionInterface {
    public Optional<GameState> playCard(int handIdx);
    public Optional<GameState> endPlayCardPhase();
    public Optional<GameState> buyCard(int buyCardIdx);
    public Optional<GameState> endTurn();
}
