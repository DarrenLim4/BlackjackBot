public class Card {
    public final int value;
    public final String name;

    /**
     * Creates a new card with the given name.
     * 
     * @param name The name of the card.
     */
    public Card(String name) {
        this.name = name;
        // depending on the name, set the value
        switch (name) {
            case "Ace":
                value = 11;
                break;
            case "King":
            case "Queen":
            case "Jack":
                value = 10;
                break;
            default:
                value = Integer.parseInt(name);
        }
    }

    /**
     * Returns the string representation of the card.
     * 
     * @return the string representation of the card.
     */
    @Override
    public String toString() {
        return name;
    }

}