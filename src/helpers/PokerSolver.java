package helpers;

import java.util.Arrays;
import card.Card;
import hand.PokerHand;

public class PokerSolver {
	private static String[] cardFaces = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	private static String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private static int[] handFace;
	private static int[] handSuit;
	private static int handScore = 0;
	
	public static void evaluateHand(PokerHand hand) {
		/* Evaluate hand to determine rank:
	    	High Card: 1
	    	Pair: 2
	    	Two Pair: 3
	    	Three of a Kind: 4
			Straight: 5
			Flush: 6
			Full House: 7
			Four of a Kind: 8
			Straight Flush: 9
			Royal Flush: 10
		*/

		int handSize = hand.getCards().length;
		//Create arrays to hold card values and card suits
		handFace = new int[handSize];
		handSuit = new int[handSize];
		handScore = 0;
		
		//Added hand.getHand to cover when hand is changed to ArrayList
		getHandData(hand.getCards());

		//First check for groups: pair, 2 pair, trips, full house, 4 of a kind
		//If those don't exist, move on
		if(!checkGroups(hand)) {
			boolean straight = checkStraits(hand, handFace);
			boolean flush = checkFlush(hand, handSuit);
			
			if(straight && flush) {
				straightFlush(hand, handFace);
			} else if (!straight && !flush){ //just a high card 
				setHighCard(hand, handFace);
			}
		}
	}
		
	public static int[] evaluatePokerGame(PokerHand...hands) {
		int[] rankings = new int[hands.length];
		int[] handRanks = new int[hands.length];
		int[] handScores = new int[hands.length];
		int[] highCard = new int[hands.length];
		
		//See if the hands have been evaluated
		//If not, send for evaluation
		for(int i=0; i<hands.length; i++) {
			evaluateHand(hands[i]);
		}
		
		//Set default order based on order that was sent
		//Set the array for hand rankings
		for(int i=0; i<rankings.length; i++) {
			rankings[i] = i;
			handRanks[i] = hands[i].getHandRank();
			handScores[i] = hands[i].getHandScore();
			highCard[i] = hands[i].getHighCard();
		}

		//Now sort the arrays by handRank
		sortByHandRank(rankings, handRanks, handScores, highCard);
		
		//In case of a tied hand ranking, sort by score
		sortByHandScore(rankings, handRanks, handScores, highCard);
		
		//In case of a tied hand ranking and score, sort by high card
		sortByHighCard(rankings, handRanks, handScores, highCard);
		
		//Now determine the results
		//Set up a new array for results
		//0 - lose, 1 - win, 2 - tie
		int[] results = new int[rankings.length];
		
		//Set results
		results[rankings[0]] = 1; //Assume first place ranking won
		for(int i=1; i<handRanks.length; i++) {
			if(handRanks[i] == handRanks[i-1]) {  //same rank, check score
				if(handScores[i] == handScores[i-1]) { //we have a tie, check high cards
					if(highCard[i] == highCard[i-1]) { //We have a tie
						results[rankings[i-1]] = 2;
						results[rankings[i]] = 2;
					} else {
						break; //No more ties
					}
				} else {
					break;  //No more ties
				}
			} else {
				break; //No ties
			}
		}
		
		return results;
		
	}
	
	private static void sortByHighCard(int[] rankings, int[] handRanks, int[] handScore, int[] highCard) {
		int tempRanking;
		int tempHandRank;
		int tempScore;
		int tempHighCard;
		
		for(int maxElement = highCard.length-1; maxElement>0; maxElement--) {
			for(int i=1; i<=maxElement; i++) {
				if(handRanks[i] == handRanks[i-1]) {
					if(handScore[i] == handScore[i-1]) {
					
						//Tied score, check high cards
						if(highCard[i] > highCard[i-1]) {
							//Store values for swap
							tempScore = handScore[i];
							tempHandRank = handRanks[i];
							tempRanking = rankings[i];
							tempHighCard = highCard[i];
							
							//Swap previous value
							handScore[i] = handScore[i-1];
							handRanks[i] = handRanks[i-1];
							rankings[i] = rankings[i-1];
							highCard[i] = highCard[i-1];
							
							//Now place temps
							handScore[i-1] = tempScore;
							handRanks[i-1] = tempHandRank;
							rankings[i-1] = tempRanking;
							highCard[i-1] = tempHighCard;
						}
					}
				}
			}
		}
		

		
	}

	private static void sortByHandScore(int[] rankings, int[] handRanks, int[] handScore, int[] highCard) {
		int tempRanking;
		int tempHandRank;
		int tempScore;
		int tempHighCard;
		
		for(int maxElement = handScore.length-1; maxElement>0; maxElement--) {
			for(int i=1; i<=maxElement; i++) {
				if(handRanks[i] == handRanks[i-1]) {
					
					//Tied rank, check alt ranks
					if(handScore[i] > handScore[i-1]) {
						//Store values for swap
						tempScore = handScore[i];
						tempHandRank = handRanks[i];
						tempRanking = rankings[i];
						tempHighCard = highCard[i];
						
						//Swap previous value
						handScore[i] = handScore[i-1];
						handRanks[i] = handRanks[i-1];
						rankings[i] = rankings[i-1];
						highCard[i] = highCard[i-1];
						
						//Now place temps
						handScore[i-1] = tempScore;
						handRanks[i-1] = tempHandRank;
						rankings[i-1] = tempRanking;
						highCard[i-1] = tempHighCard;
					}
				}
			}
		}		
	}

	private static void sortByHandRank(int[] rankings, int[] handRanks, int[] handScores, int[] highCard) {
	    //Insertion Sort
		int unsortedRanking;
		int unsortedHandRank;
		int unsortedHandScore;
		int unsortedHighCard;
		
		int scan;
		
		for(int i=1; i<handRanks.length; i++) {
			unsortedHandRank = handRanks[i];
			unsortedRanking = rankings[i];
			unsortedHandScore = handScores[i];
			unsortedHighCard = highCard[i];

	        scan = i;

	        while(scan > 0 && handRanks[scan - 1] < unsortedHandRank) {
	        	handRanks[scan] = handRanks[scan-1];
	        	rankings[scan] = rankings[scan-1];
	        	handScores[scan] = handScores[scan-1];
	        	highCard[scan] = highCard[scan-1];

	            scan--;
	        }
	        
	        handRanks[scan] = unsortedHandRank;
	        rankings[scan] = unsortedRanking;
	        handScores[scan] = unsortedHandScore;
	        highCard[scan] = unsortedHighCard;
		}
	}
		
	private static void getHandData(Card[] cards) {
		String faceString;
		
		//Break the cards down into face and suit
		for(int i=0; i<cards.length; i++) {
			//change the face to a card value
			faceString = cards[i].getFace();
			switch(faceString) {
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					handFace[i] = Integer.parseInt(faceString);
					break;
				case "T":
					handFace[i] = 10;
					break;
				case "J":
					handFace[i] = 11;					
					break;
				case "Q":
					handFace[i] = 12;
					break;
				case "K":
					handFace[i] = 13;
					break;
				case "A":
					handFace[i] = 14;
					break;				
			}
						
			handSuit[i] = cards[i].getSuitIndex();
		}
		
		
		//Now sort each array
		Arrays.sort(handFace);
		Arrays.sort(handSuit);			
	}

	private static boolean checkGroups(PokerHand hand) {
		int[] pairCounts = {1,1}; //Set default
		int[] pairFace = {0, 0};
		
		boolean pairFound = false;
		boolean foundFirstGroup = false;
		boolean foundGroup = true;
		
		int groupNum = 0; //Max of two groups possible with 5 cards
		
		int checkRank = 0;
		String suffix = "";
		
		for(int i=1; i<handFace.length; i++) {
			if(handFace[i] == handFace[i-1]) {
				if(foundFirstGroup) {
					if(!pairFound) {
						groupNum++;
					}
				}
				
				foundFirstGroup = true;
				pairFound = true;
				
				pairCounts[groupNum]++;
				pairFace[groupNum] = handFace[i];
			} else { 
				pairFound = false;
			}
		}

		//Now see what we have
		if(pairCounts[0] > 1) {
			
			//4 of a Kind?
			checkRank = (pairCounts[0] == 4 || pairCounts[1] == 4) ? 8 : checkRank;

			//Full house?
			checkRank = (checkRank == 0 && (pairCounts[0] + pairCounts[1]) == 5) ? 7 : checkRank;
			
			//Trips?
			checkRank = (checkRank == 0 && pairCounts[0] == 3) ? 4 : checkRank;
			
			//Two pair?
			checkRank = (pairCounts[0] == 2 && pairCounts[1] == 2) ? 3 : checkRank;
			
			//One pair?
			checkRank = (checkRank == 0 && pairCounts[0] == 2) ? 2 : checkRank;

		}

		//Update the attributes
		hand.setHandRank(checkRank);
		
		if(pairFace[0] > 0) {
			suffix = cardFaces[pairFace[0] - 1] + "s";
			handScore += pairFace[0] * pairCounts[0];
			
			if(pairFace[1] > 0) {
				suffix = (pairFace[0] > pairFace[1])
						? suffix + " & " + cardFaces[pairFace[1] - 1] + "s"
						: cardFaces[pairFace[1] - 1] + "s & " +  suffix;
				
				handScore += pairFace[1] * pairCounts[1];

			}
		}
		
		switch (checkRank) {
			case 2: //pair
				hand.setHandDescr("Pair " + suffix);
				break;
			case 3: // 2 pair
				hand.setHandDescr("Two Pair " + suffix);
				break;
			case 4: //trips
				hand.setHandDescr("Three of a Kind " + suffix);
				break;
			case 7: //full house
				hand.setHandDescr("Full House " + suffix);
				break;
			case 8: //4 of a Kind
				hand.setHandDescr("Four of a Kind " + suffix);
				break;
			default: //no groups
				foundGroup = false;
		}
		
		hand.setHandScore(handScore);
		
		//Set high card
		int highCard = handFace[handFace.length-1]; //Default
		for(int i=(handFace.length-1); i>0; i--) {
			for(int j=0; j<pairFace.length; j++) {
				//Make sure it's not in the pair, trips, etc.
				if(handFace[i] == pairFace[j]) {
					highCard = handFace[i-1];
				}
			}			
		}

		hand.setHighCard(highCard);

		return foundGroup;
	}

	private static boolean checkStraits(PokerHand hand, int[] handFace) {
		boolean straight = true;
		boolean hasAce;
		boolean aceTwo = false;
		
		//Check if ace exists, since it could be A, 2, ... or ...Q, K, A
		hasAce = (handFace[handFace.length-1] == 14) ? true : false;
		
		for(int i=0; i<handFace.length; i++) {
			if(i==0 && handFace[i]==2 && hasAce) {
				aceTwo = true;
				continue;
			} else if(i==handFace.length-1 && aceTwo) {
				//ignore since we handled this already
				continue;
			} else if(i != 0) {
				if(handFace[i] - handFace[i-1] == 1) {
					continue;
				} else {
					straight = false;
					break;
				}
			}
		}
		
		//Update attributes
		if(straight) {
			for(int i=0; i<handFace.length; i++) {
				handScore += handFace[i];
			}
			hand.setHandDescr("Straight");
			hand.setHandRank(5);
			hand.setHandScore(handScore);
		}
		
		return straight;
	}

	private static boolean checkFlush(PokerHand hand, int[] handSuit) {
		boolean flush = true;
		
		for(int i=1; i<handSuit.length; i++) {
			if(handSuit[i] == handSuit[i-1]) {
				continue;
			} else {
				flush = false;
				break;
			}
		}
		
		//Update attributes
		if(flush) {
			hand.setHandDescr("Flush " + suits[handSuit[0]]);
			hand.setHandRank(6);
			hand.setHandScore(0);
		}
		
		return flush;
	}

	private static void straightFlush(PokerHand hand, int[] handFace) {
		//First, default attributes to a straight flush
		hand.setHandDescr("Straight Flush");
		hand.setHandRank(9);
		
		//Now check for royal flush
		if(handFace[0] == 10 && handFace[handFace.length - 1] == 14) {
			//Update attributes
			hand.setHandDescr("Royal Flush");
			hand.setHandRank(10);
		}
		
	}

	private static void setHighCard(PokerHand hand, int[] handFace) {		
		hand.setHandDescr("High Card " + cardFaces[handFace[handFace.length-1]-1]);
		hand.setHandRank(1);
		hand.setHandScore(handFace[handFace.length-1]);
		hand.setHighCard(handFace[handFace.length-2]);
	}
}

