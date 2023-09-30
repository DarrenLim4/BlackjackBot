import utils #importing the utils.py file
#gets the file name from the user and removes the .py and check if it isnt empty
fileName = input("Enter file name: ").replace(".py","")

#imports the file from the user or the default strategy.py file
strategy=__import__(fileName if fileName else "strategy") 
deck = utils.Deck(6) #creates a deck object
deck.draw_card()#draws a card from the deck
deck.draw_card()#draws a card from the deck
deck.draw_card()#draws a card from the deck
deck.draw_card()#draws a card from the deck
deck.draw_card()#draws a card from the deck
deck.draw_card()#draws a card from the deck
print(deck) #prints the deck
print(strategy.decide())
print(strategy.bet())
print(strategy.split())
print(strategy.double_down())
print(strategy.insurance())
