package gameComponents;

import java.util.Optional;

public interface BuyDeckInterface {
    public Optional<CardInterface> buy();
    public boolean isEmpty();
}
