package card;

public class Card {

	//Define the attributes
	private int cardNumber;
	
	private String suit;
	
	private int suitIndex;
	
	private String color;
	
	private int cardRank;
	
	private String face;
	
	private int faceValue;
	
	private static int cardsCreated;
	
	private String cardImage;
	
	
	
	public Card(int cardNumber, int suitIndex, int cardRank) {
		//Set the appropriate attributes that we know about at this point
		this.cardNumber = cardNumber;
		this.suitIndex = suitIndex;
		this.cardRank = cardRank;


		//Set the suit and the card values
		setSuit();
		setCardValues();

		//Set the cardImage
		setCardImage();
		

		//Increase the number of cards created by 1
		cardsCreated++;
	}

	private void setSuit() {
		switch(suitIndex) {
			case 0:
				this.suit = "s";
				this.color = "b";
				break;
			case 1:
				this.suit = "h";
				this.color = "r";
				break;
			case 2:
				this.suit = "d";
				this.color = "r";
				break;
			case 3:
				this.suit = "c";
				this.color = "b";
				break;
		}	
	}

	private void setCardValues() {
				
		switch(this.cardRank) {
	    	case 1:
	    		this.face = "A";
	    		this.faceValue = cardRank;
	    		break;
	      case 2:
	      case 3:
	      case 4:
	      case 5:
	      case 6:
	      case 7:
	      case 8:
	      case 9:
	    	  this.face = Integer.toString(cardRank);
	    	  this.faceValue = cardRank;
	    	  break;
	      case 10:
	    	  this.face = "T";
	    	  this.faceValue = 10;
	    	  break;
	      case 11:
	    	  this.face = "J";
	    	  this.faceValue = 10;
	    	  break;
	      case 12:
	    	  this.face = "Q";
	    	  this.faceValue = 10;
	    	  break;
	      case 13:
	    	  this.face = "K";
	    	  this.faceValue = 10;
	    	  break;
		  case 14:
			  this.face = "A";
	    	  this.faceValue = cardRank;
	    	  break;
	    }
		
	}

	private void setCardImage() {
		//We will update this later in the project
		this.cardImage = "file:images/card/" + Integer.toString(cardNumber+1) + ".png";
	}

	//We need a toString()
	public String toString() {
		return face + suit;
		
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getSuitIndex() {
		return suitIndex;
	}

	public void setSuitIndex(int suitIndex) {
		this.suitIndex = suitIndex;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCardRank() {
		return cardRank;
	}

	public void setCardRank(int cardRank) {
		this.cardRank = cardRank;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	public static int getCardsCreated() {
		return cardsCreated;
	}

	public static void setCardsCreated(int cardsCreated) {
		Card.cardsCreated = cardsCreated;
	}

	public String getCardImage() {
		return cardImage;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	
	

	
	
	//Put in the getters and setters.  Remember, this class is immutable.
	

}
