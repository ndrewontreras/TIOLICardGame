package hand;

import card.Card;
import helpers.PokerSolver;

public class PokerHand extends Hand{
	
	private String handDescr = "";


	public PokerHand() {
		
	}
	
	public PokerHand(Card[] dealtCards) {
		super(dealtCards);
	}
	
	@Override
	public void evaluateHand() {
		//Call the PokerSolver class to evaluate the hand (see PokerSolver UML)
		//Note: "this" represents the current object <----------------------------------------------------------
		//Hand hand = new Hand(dealtCards);
		//PokerSolver.evaluateHand(hand);
		PokerSolver.evaluateHand(this);
	}
	
	public String getHandDescr() {
		return handDescr;
	}

	public void setHandDescr(String handDescr) {
		this.handDescr = handDescr;
	}


}
