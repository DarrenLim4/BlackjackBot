from dataclasses import dataclass
import random


@dataclass
class Card:
    name: str
    value: list

class Deck:
    
    def __init__(self,num_decks=1):
        self.cards = {
            'Ace': { 'value': [1,11], 'count': 4*num_decks },
            'Two': { 'value': [2], 'count': 4*num_decks },
            'Three': { 'value': [3], 'count': 4*num_decks },
            'Four': { 'value': [4], 'count': 4*num_decks },
            'Five': { 'value': [5], 'count': 4*num_decks },
            'Six': { 'value': [6], 'count': 4*num_decks },
            'Seven': { 'value': [7], 'count': 4*num_decks },
            'Eight': { 'value': [8], 'count': 4*num_decks },
            'Nine': { 'value': [9], 'count': 4*num_decks },
            'Ten': { 'value': [10], 'count': 4*num_decks },
            'Jack': { 'value': [10], 'count': 4*num_decks },
            'Queen': { 'value': [10], 'count': 4*num_decks },
            'King': { 'value': [10], 'count': 4*num_decks },
        }
        
    def draw_card(self)->Card:
        #draws a random card from the deck
        while True:
            card_type = random.choice(list(self.cards.keys()))
            if self.cards[card_type]['count'] > 0:
                self.cards[card_type]['count'] -= 1
                break
        return Card(card_type,self.cards[card_type]['value'])

    def drawn_card(self,card_type)->None:
        #removes a card from the deck after it has been drawn
        self.cards[card_type]['count'] -= 1
    
    def __str__(self):
        #returns how many of each type of card is in the deck
        return str(self.cards)
                    
deck = Deck()
print(deck)
