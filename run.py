import numpy as np
from enum import Enum


class Cards(Enum):
    ACE = {'card': 1, 'value': [1, 11]}
    TWO = {'card': 2, 'value': 2}
    THREE = {'card': 3, 'value': 3}
    FOUR = {'card': 4, 'value': 4}
    FIVE = {'card': 5, 'value': 5}
    SIX = {'card': 6, 'value': 6}
    SEVEN = {'card': 7, 'value': 7}
    EIGHT = {'card': 8, 'value': 8}
    NINE = {'card': 9, 'value': 9}
    TEN = {'card': 10, 'value': 10}
    JACK = {'card': 11, 'value': 10}
    QUEEN = {'card': 12, 'value': 10}
    KING = {'card': 13, 'value': 10}
#input number of decks being played on the game
numofdecks = int(input("Number of decks being used on this game: "))

deck = {
    Cards.ACE: 4*numofdecks,
    Cards.TWO: 4*numofdecks,
    Cards.THREE: 4*numofdecks,
    Cards.FOUR: 4*numofdecks,
    Cards.FIVE: 4*numofdecks,
    Cards.SIX: 4*numofdecks,
    Cards.SEVEN: 4*numofdecks,
    Cards.EIGHT: 4*numofdecks,
    Cards.NINE: 4*numofdecks,
    Cards.TEN: 4*numofdecks,
    Cards.JACK: 4*numofdecks,
    Cards.QUEEN: 4*numofdecks,
    Cards.KING: 4*numofdecks
}
#number of cards in the deck
numofcards = numofdecks * 52
#running count
count = 0 
#track cards that were previusly played on the game
yourhand = []
yourcount=0
dealershand = []
dealercount=0

# Start with a count of 0
# When you see an A, K, Q, J, or 10: Subtract 1 from your running count
# When you see a 9: Subtract - 0.5 from your running count
# When you see an 8: Do nothing(Worth 0)
# When you see a 2 or 7: Add 0.5 to your running count
# When you see a 3, 4, 5, or 6: Add 1 to your running count
def cardcount(dealt):
    decksLeft = int(numofcards % 52)
    if dealt == Cards.TWO["card"] or dealt == Cards.SEVEN["card"]:
        count = + 0.5
    elif dealt == Cards.THREE["card"] or dealt == Cards.FOUR["card"] or dealt == Cards.SIX["card"]:
        count = + 1
    elif dealt == Cards.FIVE["card"]:
        count = + 1.5
    elif dealt == Cards.EIGHT["card"]:
        count = + 0
    elif dealt == Cards.NINE["card"]:
        count = - 0.5
    elif dealt == Cards.ACE["card"] or dealt == Cards.TEN["card"] or dealt == Cards.JACK["card"] or dealt == Cards.QUEEN["card"] or dealt == Cards.KING["card"]:
        count = - 1
    return count/decksLeft

def dothathing():
    total= sum([x["value"] for x in deck if deck[x]["value"]>=(21-yourcount)])
    print(total)

    
#create class to tell how many copies of each card there are
# class Deck():

#make dictionary to hold the amount of each Cards there are using the enum



while(True):
    dealt= Cards(input())
    cardcount(dealt)
    


# How to Use the Wong Halves System?
# Before starting to count cards, players should determine their bankroll, as well as the minimum and maximum bets they are comfortable with. 
# A good betting spread for beginners is 1-5, while larger spreads such as 1-10, 1-20 or more, offer more profitability and greater risk at the same time. 
# Players start off the game with 1-unit bets after a reshuffle.
# They begin the count at 0 and add or subtract numbers from it as the cards appear on the table.

# As the structure of this system is a bit more complicated than usual,
#  let’s take a 6-deck game as an example. The first cards dealt on the table are 7, 2, Queen, 4, and 8, corresponding to +0.5, +0.5, -1, +1, and 0.
#  This means that our running count is +1, indicating that the player has a slight advantage over the house. 
# However, these are just the first five cards dealt out of all 312 cards in the six decks used in this game.

# If even 2 cards with a negative value are drawn in the next deal, the count will dip below 0. 
# This lack of uncertainty caused by the many cards still remaining in the shoe makes the running count of +1 completely irrelevant. 
# In order to provide players with a more adequate count, the system requires conversion to the so-called “true count”.

# True Count
# Unlike the running count that is constantly kept by players during the game, the true count takes the number of decks still not played into consideration. 
# There are several different ways to determine the true count but most players stick to a simple and proven method – they just divide the running count by the number of decks in the shoe.

# Following the same example, we have a running count of +1.
#  Assuming some cards have been burnt and some have been dealt, there will be around 5 decks and half still left in the shoe. 
# If 5 decks of cards are remaining (at best), we need to divide 1 by 5 for a true count of +0.20.

# Since higher counts indicate better odds for the player, the true count we have is not impressive at all. 
# In fact, we need a minimum true count of +2 to gain some actual advantage over the house. 
# This means that we keep placing our minimum wager until the count becomes +2 or higher. 
# Then, we increase the bet size to maximize the potential profits resulting from a favorable situation. 
# The higher the count, the greater the bets should be. Of course, players should bet less when the count is low.