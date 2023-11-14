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
    public int GUcount;

    /**
     * This constructor creates the shoe with a given number of decks.
     * 
     * @param size The number of decks to be used in the shoe.
     */
    public Deck(int numDecks) {
        this.shoe = new ArrayList<Card>();
        this.GUcount = 0;
        this.cardsLeft = new int[13];
        if(numDecks < 1){
            Logging.logToGroup("error", "Error: Invalid number of decks");
            System.exit(1);
        }
        
        this.size = 52 * numDecks;
        // add the correct number of cards to the shoe
        for (int i = 0; i < 4 * numDecks; i++) {
            Card card = new Card("Ace");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("2");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("3");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("4");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("5");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("6");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("7");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("8");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("9");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("10");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("Jack");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;
            
            card = new Card("Queen");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;

            card = new Card("King");
            this.shoe.add(card);
            this.cardsLeft[getIndexForCardsLeft(card)]++;
        }
    }

    /**
     * This function draws a card from the shoe.
     * 
     * @return The card that was drawn.
     */
    public Card draw() {
        // draw a random card from the deck and return it
        Random rand = new Random();
        int index = rand.nextInt(shoe.size());
        Card card = shoe.remove(index);
        this.cardsLeft[getIndexForCardsLeft(card)]--;
        this.size--;
        switch(card.name){
            case "Ace":
                GUcount -= 60;
                break;
            case "2":
                GUcount += 37;
                break;
            case "3":
                GUcount += 45;
                break;
            case "4":
                GUcount += 52;
                break;
            case "5":
                GUcount += 70;
                break;
            case "6":
                GUcount += 46;
                break;
            case "7":
                GUcount += 27;
                break;
            case "8":
                GUcount += 0;
                break;
            case "9":
                GUcount -= 17;
                break;
            case "10":
                GUcount -= 50;
                break;
            case "Jack":
                GUcount -= 50;
                break;
            case "Queen":
                GUcount -= 50;
                break;
            case "King":
                GUcount -= 50;
                break;
        }
        return card;
    }

    /**
     * This function returns the number of cards left in the shoe.
     * 
     * @param card The card to get the index for.
     * @return The number of cards left in the shoe.
     */
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

        if(index == -1){
            Logging.logToGroup("error", "Error: Invalid card name");
            System.exit(1);
            return -1;
        } else {
            return index;
        }
        
    }
    int runningcount(){
        return (int)((double)GUcount/(shoe.size()<=52?1:(double)shoe.size()/52.0));
    }
}