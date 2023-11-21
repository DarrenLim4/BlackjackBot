import java.util.ArrayList;

/**
 * This class represents a player in the game.
 * 
 * @since Fall 2023
 * @Author Michael Plekan
 */
public class Game{
    private static final int HIT = 0;
    private static final int STD = 1;
    private static final int DD = 2;
    /**
     * This function runs the game.
     * 
     * @param args The arguments that are supposed to be passed in.
     */
    public static void main(String[] args){
        int shuffles = 1;
        int numdecks = 1;
        ArrayList<Player> players;
        Deck deck;
        Dealer dealer;
        int currentCount = 0;
        //error check for args
        if(args.length < 2){
            System.err.println("Usage: java Game <number of players> <number of shuffles> <number of decks>");
            System.err.flush();
            System.exit(1);
        }
        //get input from args and create players
        players = new ArrayList<Player>();
        for(int i = 0; i < Integer.parseInt(args[0]); i++){
            players.add(new Player());
        }
        //get input from args for number of shuffles and number of decks
        if(args.length > 2){
            shuffles = Integer.parseInt(args[1]);
            numdecks = Integer.parseInt(args[2]);
        }
        deck = new Deck(numdecks);
        currentCount = deck.runningcount();
        dealer = new Dealer(deck);
        
        //run game
        for(int x = 0; x < shuffles; x++){
            while(deck.size>(players.size()+1)*5){
                //print
                System.out.println("New Round");
                //reset difference
                for(Player player : players){
                    player.difference = 0;
                }
                //deal cards
                for(Player player : players){
                    player.hands.add(new Hand(deck.draw(), deck.draw()));
                }
                //handle bets
                for(Player player : players){
                    player.bet();
                }
                //handle each players hand(s)
                for(Player player : players){
                    for(int i = 0; i < player.hands.size(); i++){
                        int action;
                        do{
                            action = player.decide(i,dealer.hand.getCard(0).value);
                            dealer.handle(action, player, i, deck);
                        }while(action != 1);
                    }
                }
                //handle dealer's hand
                while(dealer.decide(deck) != STD);
                //determine winners
                for(Player player : players){
                    for(Hand hand : player.hands){
                        if(hand.getValue() > 21 && hand.getSoftValue() > 21){//player bust
                            player.difference -= player.bet;
                        }
                        else if (dealer.hand.getValue() > 21 && dealer.hand.getSoftValue() > 21){//dealer bust
                            player.difference += player.bet;
                        }
                        else if (dealer.hand.getValue() == 21 || dealer.hand.getSoftValue() == 21){//dealer blackjack
                            player.difference -= player.bet;
                        }
                        else if (hand.getValue() == 21 || hand.getSoftValue() == 21){//player blackjack
                            player.difference += player.bet*1.5;
                        }
                        else if(hand.getValue() == dealer.hand.getValue()){//push
                            player.difference += 0;
                        }
                        else if(hand.getValue() > dealer.hand.getValue()){//player win
                            player.difference += player.bet;
                        }
                        else if ( hand.getValue() < dealer.hand.getValue()){//player lose
                            player.difference -= player.bet;
                        }
                        else{
                            Logging.logToGroup("error","Error in determining winner");
                            System.exit(1);
                        }
                    }
                }
                //print results
                for(Player player : players){
                    //System.out.print(player.hands);
                    //System.out.println(player.difference);
                    Logging.logToGroup("game", new String[] {currentCount+"", player.difference+""});
                }
                //reset hands and set intialHand to false
                for(Player player : players){
                    player.hands = new ArrayList<Hand>();
                    player.intialHand = false;
                }
                currentCount = deck.runningcount();
                //reset dealer hand
                dealer.hand = new Hand(deck.draw(), deck.draw());
            }//end of round
            System.out.println("Hit cut card");
            deck= new Deck(numdecks);
            
        }//end of shuffles
    }
}
