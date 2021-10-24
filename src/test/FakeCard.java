public class FakeCard implements CardInterface {
    private GameCardType _cardType;

    FakeCard(GameCardType __cardType) {
        _cardType = __cardType;
    }

    public void evaluate(TurnStatus t) {
    }

    public GameCardType cardType() {
        return _cardType;
    }
}
