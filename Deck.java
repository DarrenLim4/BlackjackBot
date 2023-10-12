import java.util.Random;
import java.util.ArrayList;

/**
 * This class represents the shoe of cards that the dealer draws from.
 * 
 * @since Fall 2023
 * @author Chris Sorros
 */
public class Deck {
    public ArrayList<Card> shoe;
    public int[] cardsLeft;
    public int size;

    /**
     * This constructor creates the shoe with a given number of decks.
     * 
     * @param size The number of decks to be used in the shoe.
     */
    public Deck(int size) {
        this.shoe = new ArrayList<Card>();
        this.cardsLeft = new int[13];
        // add the correct number of cards to the shoe
        for (int i = 0; i < 4 * size; i++) {
            Card card = new Card("Ace");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("2");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("3");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("4");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("5");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("6");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("7");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("8");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("9");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("10");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("Jack");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;
            
            card = new Card("Queen");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;

            card = new Card("King");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)] = cardsLeft[getIndexForCardsLeft(card)] + 1;
        }
    }

    public Card draw() {
        // draw a random card from the deck and return it
        Random rand = new Random();
        int index = rand.nextInt(52 * size);
        Card card = shoe.get(index);
        this.shoe.remove(index);
        this.cardsLeft[getIndexForCardsLeft(card)]--;
        return card;
    }

    private int getIndexForCardsLeft(Card card) {
        int index = -1;

        switch (card.name) {
            case "Ace":
                index = 0;
                break;
            case "2":
                index = 1;
                break;
            case "3":
                index = 2;
                break;
            case "4":
                index = 3;
                break;
            case "5":
                index = 4;
                break;
            case "6":
                index = 5;
                break;
            case "7":
                index = 6;
                break;
            case "8":
                index = 7;
                break;
            case "9":
                index = 8;
                break;
            case "10":
                index = 9;
                break;
            case "Jack":
                index = 10;
                break;
            case "Queen":
                index = 11;
                break;
            case "King":
                index = 12;
                break;
        }
        return index;
    }
}