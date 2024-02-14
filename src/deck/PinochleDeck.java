package deck;

import card.Card;

public class PinochleDeck extends Deck{
	
	public PinochleDeck() {
		
	}
	
	@Override
	public void createDeck() {
		int suitIndex;
		int cardRank;
		
		for(int i=0; i < 48; i++) {
			
			//Determine suit index
			if(i < 12){
			    suitIndex = 0;
			}
			else if(i < 24){
			    suitIndex = 1;
			}
			else if(i < 36){
			    suitIndex = 2;
			}
			else {
			    suitIndex = 3;
			}
			
			//Calculate card rank (9-14)
			
			cardRank = ((i) % 6) + 9;
			
			//cards[i] = new Card(i, suitIndex, cardRank);
			
			cards.add(new Card(i, suitIndex, cardRank));
		}
	}
	

}
