package helpers;

import card.Card;
import hand.Hand;

public class BlackjackSolver {
	
	private static int[] altFaceValue = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

	public static int handScore(Hand hand) {
		Card[] cards = hand.getCards();
		int score = 0;
		int cardVal = 0;    // Stores the card's value
		int aces = 0;       // Stores the # of aces in the Hand

		for (int i=0; i<cards.length; i++) {
	        cardVal = altFaceValue[cards[i].getCardRank()-1];
	        if (cardVal == 11) {
	            aces += 1;
	        }
	        score += cardVal;
		}
		      
		/* Check to see if Aces should be 1 or 11 */
		while (score > 21 && aces > 0) {
			score -= 10;
		    aces -= 1;
		}
		      
		return score;		      
	}

	public static boolean hasBlackjack(Hand hand){

		Card[] cards = hand.getCards();
		if(cards.length == 2) { //Only run for first two cards
			
			if(handScore(hand) == 21 ) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}

	public static boolean hasCharlie(Hand hand) {
		handScore(hand);
		
		if(hand.getHandScore() <= 21 && hand.getCards().length >= 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean dealerHit(Hand dealerHand, Hand playerHand) {
				
		if(handScore(dealerHand) < 17 && handScore(playerHand) <=21 && !hasBlackjack(playerHand)) {
			return true;
		} else {
			return false;
		}
	}
}
