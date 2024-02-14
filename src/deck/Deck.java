package deck;

import java.util.ArrayList;

import card.Card;

public abstract class Deck {
	//Attributes go here
	protected ArrayList<Card> cards;
	private ArrayList<Card> discardPile;
	
	//Shorthand version combing attributes and Deck constructor
	//private ArrayList<Card> cards = cards = new ArrayList<Card>();
	//private ArrayList<Card> discardPile = discardPile = new ArrayList<Card>();
	
	public Deck() {
		//Create the attribute arrays with a size of 52
		cards = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
	}
	
	//This method populates the deck object with cards (Turned into abstract in PA 2.3)
	public abstract void createDeck();

	public void shuffleDeck() {
		
		//Create a routine to mix up the deck
		Card tempCard = null;
		
		for(int i = 0; i < cards.size(); i++) {
			int randomCard = (int)(Math.random() * cards.size());
			
			//Comments refer to code when we used arrays instead of arraylists
			//tempCard = cards[randomCard];
			tempCard = cards.get(randomCard);
			
			//cards[randomCard] = cards[i];
			cards.set(randomCard, cards.get(i));
			
			//cards[i] = tempCard;
			cards.set(i, tempCard);
			
		}
	}

	public void addDiscard(Card card) {
		//Lab 11.1
		discardPile.add(card);
	}

	public void addDiscard(Card[] discards) {
		//Lab 11.1
		for(int i = 0; i < discards.length; i++) {
			discardPile.add(discards[i]);
		}
	}

	public void restack() {
		//Lab 11.1
		//Remove card from discardPile
		//Add to cards array
		while(discardPile.size() > 0) {
			cards.add(discardPile.remove(0));
		}
		
		//Shuffle deck
		shuffleDeck();
	}
	
	

	//To return a single Card object and leave in the Deck
	public Card getCard(int index) {
		//return cards[index];
		return cards.get(index);
	}
	
	//To return a Card object and remove from the Deck
	public Card removeCard(int index) {
		return cards.remove(index);
		
	}
	
	//To return the entire deck
	public Card[] getCards() {
		//return cards;
		//We are converting the ArrayList into an array
		Card[] tempCards = new Card[cards.size()];
		cards.toArray(tempCards);
		
		return tempCards;
	}
	
	//To return the discardPile
	public Card[] getDiscardPile() {
		//return discardPile;
		Card[] tempCards = new Card[discardPile.size()];
		discardPile.toArray(tempCards);
		
		return tempCards;
	}

	@Override
	//We need a toString()
	public String toString() {
		String deckString = "Deck:\n";

		for(int i = 0; i < cards.size(); i++) {
			if(i != 0 && i % 13 == 0) {
				deckString += "\n";
			}
			//deckString += cards[i] + " ";
			deckString += cards.get(i) + " ";
		}
		
		deckString += "\nDiscard Pile:\n";
		for(int i = 0; i < discardPile.size(); i++) {
			if(i != 0 && i % 13 == 0) {
				deckString += "\n";
			}
			//deckString += cards[i] + " ";
			deckString += discardPile.get(i) + " ";
		}
		
		return deckString;
	}

	

	
	
}
