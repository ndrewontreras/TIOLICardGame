package hand;

import card.Card;
import helpers.BlackjackSolver;

public class BlackjackHand extends Hand {
	
	private boolean blackjack = false;
	private boolean charlie = false; 
	
	public BlackjackHand () {
		
	}
	
	public BlackjackHand(Card[] dealtCards) {
		super(dealtCards);
	}
	
	@Override
	public void evaluateHand() {
		int tempHandScore = BlackjackSolver.handScore(this);
		super.setHandScore(tempHandScore);
		
		this.blackjack = BlackjackSolver.hasBlackjack(this);
		
		this.charlie = BlackjackSolver.hasCharlie(this);
	}

	public boolean isBlackjack() {
		return blackjack;
	}

	public void setBlackjack(boolean blackjack) {
		this.blackjack = blackjack;
	}

	public boolean isCharlie() {
		return charlie;
	}

	public void setCharlie(boolean charlie) {
		this.charlie = charlie;
	}
	
	
	
	
	
	
	
}
