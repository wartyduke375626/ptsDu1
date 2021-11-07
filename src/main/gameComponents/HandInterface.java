package gameComponents;

import java.util.List;
import java.util.Optional;

public interface HandInterface {
    public void addCards(List<CardInterface> cards);
    public int getSize();
    public List<CardInterface> throwAll();
    public boolean isActionCard(int cardIndex);
    public Optional<CardInterface> play(int cardIndex);
    public Optional<CardInterface> lookAt(int cardIndex);

}
