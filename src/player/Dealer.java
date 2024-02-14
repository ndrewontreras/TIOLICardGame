package player;

import card.Card;
import deck.Deck;
import hand.Hand;

public class Dealer extends Player{
	
	private Deck deck;
	
	public Dealer(Deck deck) {
		this.deck = deck;
		
		prepareDeck();
	}
	
	public Dealer(Deck deck, Hand hand) {
		super("Dealer", "9999", 0, hand);
		
		this.deck = deck;
		
		prepareDeck();
	}
	
	private void prepareDeck() {
		deck.createDeck();
		deck.shuffleDeck();
	}
	
	public void dealCard(Player player) {
		//Deal the card at the index to the player's hand
		//Assignment 1.2
		Card tempCard = deck.removeCard(0);
		player.getHand().addCard(tempCard);
	}
	
	public void reshuffleDeck() {
		Card[] tempDeck = deck.getCards();
		Card[] tempDiscard = deck.getDiscardPile();
		
		int totalCards = tempDeck.length + tempDiscard.length;
		
		int reshuffleCount = (int)(totalCards * .65);
		if(tempDeck.length < reshuffleCount) {
			deck.restack();
		}
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public Card getCard(int index) {
		Card tempCard = deck.getCard(index);
		return tempCard;
	}
}
