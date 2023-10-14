public class Card {
    public final int value;
    public final String name;

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

    public String toString() {
        return name;
    }

}