package game;

import card.Card;
import hand.Hand;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import player.Dealer;
import player.Player;

public class DealerArea extends HBox {
	//Lab 14.2
	//Who does the dealer area belong to?
	private Player dealer;
	
	//An HBox to hold 3 card holders
	
	//The 3 cards holders (using a pane so we can style it)
	private HBox cardBack = new HBox ();
	
	private HBox tioliHolder = new HBox();
	
	private HBox discardHolder = new HBox();
	
	public DealerArea(Player dealer) { //What parameter do we need?
		//Lab 14.2
		//Note which player owns this object
		this.dealer = dealer;
		
		//Create the display for the face down deck
		createCardBackArea(); 
		
		//Create the TIOLI holder
		createTioliHolder();
		
		//Create the discard pile
		createDiscardHolder();
		
		//Now add them all the the HBox (using 'this')
		addObjectsToTheHBox();

	}
	
	private void createCardBackArea() {
		
		//Style HBox which holds our face down deck
		cardBack.setAlignment(Pos.CENTER);
		
		//Use some CSS to style the HBox
		styleCardHolders(cardBack);
		
		//Lab 14.2
		//Now add the image to the pane
		setCardBack("blue");

	}
	
	private void createTioliHolder() {
		
		//Style HBox which holds our face down deck
		tioliHolder.setAlignment(Pos.CENTER);
		
		//Use some CSS to style the HBox
		styleCardHolders(tioliHolder);
		
	}
	
	private void createDiscardHolder() {
		
		//Style HBox which holds our face down deck
		discardHolder.setAlignment(Pos.CENTER);
		
		//Use some CSS to style the HBox
		styleCardHolders(discardHolder);
		
	}
	
	private void addObjectsToTheHBox() {
		this.getChildren().addAll(cardBack, tioliHolder, discardHolder);
		this.setAlignment(Pos.CENTER);
	}
	
	private void styleCardHolders(Pane paneToStyle) {
		String cssLayout = 
				"-fx-border-color: white;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n" +
                "-fx-border-style: solid;\n" +
                "-fx-border-radius: 10;\n";

		paneToStyle.setStyle(cssLayout);
		paneToStyle.setPrefWidth(100);
		paneToStyle.setPrefHeight(120);
		paneToStyle.setMaxHeight(USE_PREF_SIZE);
		paneToStyle.setMaxWidth(USE_PREF_SIZE);
	}
	
	//Add the card back image to the the pane
	public void setCardBack(String color) {
		//Lab 14.2
		
		//First clear any current image (It can only hold 1)
		cardBack.getChildren().clear();
		//Then add the card to the cardBack object
		String imageURL = ("file:images/card/" + color + ".png");
		cardBack.getChildren().add(new ImageView(imageURL));
	}
	
	//This adds the appropriate card images to the screen
	public void showTioliCard() {
		//Lab 14.2

		//If a card existed previously, clear it
		clearTioliHolder();
		
		//Get the image URL from the dealer's hand
		Card[] dealerCard = dealer.getHand().getCards();
		String imageURL;
		
		imageURL = dealerCard[0].getCardImage();
		//Create the image in the TioliHolder
		
		tioliHolder.getChildren().add(new ImageView(imageURL));
		
	}
	
	public void showDiscardedCard(Card card) {
		//Lab 14.2
		
		//If a card existed previously, clear it
		clearDiscardHolder();

		
		//Display the card image
		String cardImageURL = card.getCardImage();
		discardHolder.getChildren().add(new ImageView(cardImageURL));
	}
	
	public void clearTioliHolder() {
		//Lab 14.2
		//Clear the TioliHolder pane
		tioliHolder.getChildren().clear();
		
	}
	
	public void clearDiscardHolder() {
		//Lab 14.2
		//Clear the DiscardHolder pane
		discardHolder.getChildren().clear();
	}
	
}
