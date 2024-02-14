package player;

import hand.Hand;

public class Player {

	private String name;
	private String id;
	private int bank;
	private Hand hand;
	
	public Player(Hand hand) { 
		/*name = "Stacey";
		id = "2900";
		bank = 1000;
		this.hand = hand;*/
		this("Stacey", "2900", 1000, hand);
		
	}
	
	public Player(String name, String id, Hand hand) {
		this.name = name;
		this.id = id; 
		bank = 1000;
		this.hand = hand;
	}
	
	public Player(String name, String id, int bank, Hand hand ) {
		this.name = name;
		this.id = id; 
		this.bank = bank;
		this.hand = hand;
	}
	
	public Player() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}
	
	public Hand getHand() { 
		return hand;
	}
	
	@Override
	public String toString() {
		return "Player " + name + ", ID # " + id + " has a bank amount of $" + bank;
	}
		
}
