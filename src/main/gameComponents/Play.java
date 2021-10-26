package gameComponents;

import java.util.ArrayList;
import java.util.List;

public class Play implements PlayInterface {

    private List<CardInterface> cardsInPlay = new ArrayList<>();

    @Override
    public void putTo(CardInterface card) {
        cardsInPlay.add(card);
    }

    @Override
    public List<CardInterface> throwAll() {
        List<CardInterface> tmp = new ArrayList<>(cardsInPlay);
        cardsInPlay = new ArrayList<>();
        return tmp;
    }
}
