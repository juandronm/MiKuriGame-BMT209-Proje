package Cards;

public class Card extends BaseCard {
    private static int uniqueIdCounter = 1;

    public Card(String[] characterDatum) {
        super(uniqueIdCounter++, characterDatum[0],
                Integer.parseInt(characterDatum[1]),
                characterDatum[2],
                Integer.parseInt(characterDatum[3]));
    }
}
