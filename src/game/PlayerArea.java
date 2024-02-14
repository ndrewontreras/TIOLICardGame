package game;

import card.Card;
import hand.Hand;
import hand.PokerHand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import player.Player;

public class PlayerArea extends VBox{
	//Lab 14.2
	//Who does the player area belong to?
	private Player player;
	
	//An HBox to hold the cards
	private HBox playerCardArea = new HBox(10);

	//A Text box to hold the handDesr
	private Text handResults = new Text(" ");

	
	public PlayerArea(Player player) {  //What parameter do we need?
		//Note which player owns this object
		this.player = player;
		
		//Create the display for the card hands
		createCardArea(); 
		createHandResults();
		addObjectsToTheVBox();

	}
	
	private void createCardArea() {
		//LAb 4.2 
		
		//Create an HBox to hold our card images
		playerCardArea.setAlignment(Pos.CENTER);
		playerCardArea.setPadding(new Insets(5,5,5,5));
		
		//Use some CSS to style the HBox
		styleCardHolder();

	}
	
	private void createHandResults() {
		//Lab 14.2
		//Create an object for the Hand Results (the handDescr object from PokerHand)
		//EC3
		String skyM = "file:fonts/SkyMarshalBoldItalicBoldItalic-dYOV.otf";
		handResults.setFont(Font.loadFont(skyM, 32));
		handResults.setFill(Color.WHITE);
		VBox.setMargin(handResults, new Insets(0, 0, 15, 0));
	}

	private void addObjectsToTheVBox() {
		//Lab 14.2
		//Place the card area and hand description into a VBox
		//Remember, this class defines a VBox object so use "this"
		this.getChildren().addAll(handResults, playerCardArea);
		
		//Align cards in center
		this.setAlignment(Pos.BOTTOM_CENTER);		

	}
	
	private void styleCardHolder() {
		//EC3
		String cssLayout = 
				"-fx-border-color: white;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n" +
                "-fx-border-style: solid;\n" + 
                "-fx-border-radius: 10;\n";

		playerCardArea.setStyle(cssLayout);
		playerCardArea.setPrefWidth(425);
		playerCardArea.setPrefHeight(130);
		playerCardArea.setMaxHeight(USE_PREF_SIZE);
		playerCardArea.setMaxWidth(USE_PREF_SIZE);
	}
	
	//This adds the appropriate card images to the screen
	protected void showCards() {
		//Lab 14.2
		
		//If cards existed previously, clear them
		clearPlayerHand();
		
		//We need to get the player's card array from the Hand object
		//so we can get the imageURLs
		Hand playerHand = player.getHand();
		Card[] playerCards = playerHand.getCards();
		String imageURL;
		
		//Loop through the hand to get the image URLs
		for(int i = 0; i < playerCards.length; i++) {
			imageURL = playerCards[i].getCardImage();
			playerCardArea.getChildren().add(new ImageView(imageURL));
		}
		
	}

	protected void clearPlayerHand() {
		//Lab 14.2
		//Clear the PlayerCardArea pane
		playerCardArea.getChildren().clear();
	}

	
	protected void showHandDescr() {
		//Lab 14.2
		
		//We need to get the handDesr from the player's hand object
		//Remember, the player has a Hand but the descr sits on PokerHand
		
		Hand playerHand = player.getHand();
		PokerHand pokerHand = (PokerHand)playerHand;
		handResults.setText(pokerHand.getHandDescr());
	
		//handResults.setText(player.getHand()
	}



}
