import java.util.ArrayList;

/**
 * This class represents a player in the game.
 * 
 * @since Fall 2023
 * @Author Michael Plekan
 */
public class Player {
    private static final int HIT = 0;
    private static final int STD = 1;
    private static final int DD = 2;
    private static final int SPT = 4;
    // private final int SPT = 3;
    public ArrayList<Hand> hands;
    public int bet = 0;
    public int difference = 0;
    public boolean intialHand = true;

    private final int[][] hardTable = {
            // Dealer's up card from 2 to Ace
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 3: index 0
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 4: index 1
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 5: index 2
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 6: index 3
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 7
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 8
            { HIT, DD, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value 9
            { DD, DD, DD, DD, DD, DD, DD, DD, HIT, HIT }, // for value 10
            { DD, DD, DD, DD, DD, DD, DD, DD, DD, DD }, // for value 11
            { HIT, HIT, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 12
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 13
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 14
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 15
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 16
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 17
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 18
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 19
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 20
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD } // for value 21: index 19
    };

    private final int[][] softTable = {
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,2
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,3
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,4
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,5
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,6
            { STD, DD, DD, DD, DD, STD, STD, HIT, HIT, HIT }, // for value A,7
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value A,8
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value A,9
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value A,10
    };

    private final int[][] matchingTable = {
        // Dealer's up card from 2 to Ace
        {HIT,HIT,SPT,SPT,SPT,SPT,HIT,HIT,HIT,HIT},//for value 2,2
        {HIT,HIT,SPT,SPT,SPT,SPT,HIT,HIT,HIT,HIT},//for value 3,3
        {HIT,HIT,HIT,HIT,HIT,HIT,HIT,HIT,HIT,HIT},//for value 4,4
        {DD,DD,DD,DD,DD,DD,DD,DD,HIT,HIT},//for value 5,5
        {SPT,SPT,SPT,SPT,SPT,HIT,HIT,HIT,HIT,HIT},//for value 6,6
        {SPT,SPT,SPT,SPT,SPT,SPT,HIT,HIT,HIT,HIT},//for value 7,7
        {SPT,SPT,SPT,SPT,SPT,SPT,SPT,SPT,SPT,SPT},//for value 8,8
        {SPT,SPT,SPT,SPT,SPT,STD,SPT,SPT,STD,STD},//for value 9,9
        {STD,STD,STD,STD,STD,STD,STD,STD,STD,STD},//for value 10,10
        {SPT,SPT,SPT,SPT,SPT,SPT,SPT,SPT,SPT,SPT},//for value A,A
    };

    /**
     * This function creates a new player
     * 
     * @param hand The hand to be added to the player
     */
    public Player() {
        this.hands = new ArrayList<Hand>();
    }

    /**
     * This function decides if the player wants to hit, stand, double down, or split.
     * 
     * @param hand The hand to be decided upon.
     * @param DealerUpCard The dealer's up card.
     * 
     * @return The action to be taken.
     */
    public int decide(int hand, int DealerUpCard) {
        if (hands.get(hand).getValue() > 21) {
            return STD;
        }
        //System.out.println(hands.get(hand).getValue()+" : "+hands.get(hand));
        // decide what to do
        if (intialHand) {
            if (hands.get(hand).hand.size()==2 && hands.get(hand).isPair() && hands.size()<5) {
                return matchingTable[(hands.get(hand).getValue()/2)-2][DealerUpCard - 2];
            }else if (hands.get(hand).isSoft()) {
                return softTable[hands.get(hand).getSoftValue()-3][DealerUpCard - 2];
            } else {
                return hardTable[hands.get(hand).getValue()-3][DealerUpCard - 2];
            }
        } else {
            if (hands.get(hand).hand.size()==2 && hands.get(hand).isPair() && hands.size()<5) {
                return matchingTable[(hands.get(hand).getValue()/2)-2][DealerUpCard - 2] == DD ? HIT
                        : matchingTable[(hands.get(hand).getValue()/2)-2][DealerUpCard - 2];
            }else if (hands.get(hand).isSoft()) {
                return softTable[hands.get(hand).getSoftValue()-3][DealerUpCard - 2] == DD ? HIT
                        : softTable[hands.get(hand).getSoftValue()-3][DealerUpCard - 2];
            } else {
                return hardTable[hands.get(hand).getValue()-3][DealerUpCard - 2] == DD ? HIT
                        : hardTable[hands.get(hand).getValue()-3][DealerUpCard - 2];
            }
        }
    }

    /**
     * This function decides how much to bet.
     * 
     */
    public void bet() {
        // return the amount of money to bet
        bet = 2;
    }

    /**
     * This returns the toString representation of the player.
     */
    @Override
    public String toString() {
        return "Player: " + hands;
    }
}