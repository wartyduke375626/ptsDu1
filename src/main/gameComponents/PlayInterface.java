package gameComponents;

import java.util.List;

public interface PlayInterface {
    public void putTo(CardInterface card);
    public List<CardInterface> throwAll();
    public int getSize();
}
