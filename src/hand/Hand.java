package hand;

import java.util.ArrayList;

import card.Card;
import deck.Deck;
import helpers.PokerSolver;

public abstract class Hand {
	//Create the attributes
	private ArrayList<Card> cards;
	
	
	private int handScore = 0;
	private int handRank = 0;
	private int highCard = 0;
	
	//Attributes used use by the PokerSolver 
	
	
	//Use this to prevent adding too many items to the cards array
	//private int cardCounter = 0;

	public Hand() {
		//Only need to establish an empty cards array here
		//cards = new Card[handSize];
		cards = new ArrayList<Card>();

	}
	
	public Hand(Card[] dealtCards) {
		//Create the empty array
		//cards = new Card[dealtCards.length];
		cards = new ArrayList<Card>();
		//Loop through the dealtCards array and add each card object to the cards array
		for(int i = 0; i < cards.size(); i++) {
			//cards[i]= dealtCards[i];
			cards.set(i, dealtCards[i]);
		}
	}
	
	public void addCard(Card dealtCard) {
		//We are sent 1 card
		//If the array is not full, then add the card
		//Otherwise, just ignore it
		//Keep track of the number of cards added to the cards array		
		/*if(cardCounter < cards.length) {
			cards[cardCounter] = dealtCard;
			cardCounter++;
		}*/
		
		cards.add(dealtCard);
	}
	
	public void addCard(Card[] dealtCards) {
		//We are sent multiple cards
		//Loop through the array and try to add each card to the cards array
		//If the array is not full, then add the card
		//Otherwise, just ignore it
		//Keep track of the number of cards added to the cards array
		/*if(cardCounter < cards.length) {
			for(int i = 0; i < dealtCards.length; i++) {
				cards[cardCounter] = dealtCards[i];
				cardCounter++;*/
		for(int i = 0; i < dealtCards.length; i++) {
			cards.add(dealtCards[i]);
		}
	}	
	
	
	//Put a card into a specific element within the Hand
    public void setCard(int index, Card dealtCard){
    	//cards[index] = dealtCard;
    	cards.set(index, dealtCard);
    }
	
	//Get a specific Card object at a specific index
	public Card getCard(int index) {
		//return cards[index];
		return cards.get(index);
	}
	
	//Get a specific Card object at a specific index and remove from Hand
	public Card removeCard(int index) {
		//Assignment 2.1
		return cards.remove(index);
	}
	

	public abstract void evaluateHand();
	
	
	public void discard(Deck deck, int index) {
		//Assignment 2.1
		Card[] tempCards = this.getCards();
		deck.addDiscard(tempCards[index]);
	}
	
	public void discardAll(Deck deck) {
		//Assignment 2.1
		while(cards.size() > 0) {
			//Remove the card at the 0 index from the hand
			Card tempCard = cards.remove(0);
			//Send the card to the deck using the Deck class' addDiscard method
			deck.addDiscard(tempCard);
		}

	}
	
	@Override
	public String toString() {
		//Return a string with the card objects in the hand
		String handString = "";

		for(int i = 0; i < cards.size(); i++) {
			/*if(i != 0 && i % 3 == 0) {
				handString += "\n";
			} */
			handString += cards.get(i) + " ";
		}
		
		return handString;
		
	}
	
	//Add the typical getters/setters
	
	public Card[] getCards() {
		Card[] tempCards = new Card[cards.size()];
		cards.toArray(tempCards);
		
		return tempCards;
	}

	public int getHandScore() {
		return handScore;
	}

	public void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	public int getHandRank() {
		return handRank;
	}

	public void setHandRank(int handRank) {
		this.handRank = handRank;
	}

	public int getHighCard() {
		return highCard;
	}

	public void setHighCard(int highCard) {
		this.highCard = highCard;
	}

}
