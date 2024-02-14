package game;

import card.Card;
import deck.StandardDeck;
import gamedata.GameData;
import gamedata.GameFile;
import gameobjects.CardSelector;
import gameobjects.GameOptions;
import gameobjects.GameTimer;
import gameobjects.PayoutTable;
import gameobjects.ScoreBoard;
import gameobjects.Wager;
import hand.PokerHand;
import helpers.PokerSolver;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import player.Dealer;
import player.Player;
import reports.GameReport;

public class TioliGame {
	//We use a BorderPane to hold all objects
	private BorderPane gameScreen = new BorderPane();
	
	//Panes to hold the nodes in each section
	private HBox topSection = new HBox(10);
	private HBox bottomSection = new HBox(10);
	private VBox leftSection = new VBox(10);	
	private VBox rightSection = new VBox(10);
	private VBox centerSection = new VBox(30);
	
	//New attributes (HW 4.1)
	private PayoutTable payoutTable;
	private ScoreBoard scoreBoard;
	private Wager wager;
	
	//New button attributes (HW 4.2)
	private Button btnDeal = new Button("Deal");
	private Button btnTake = new Button("Take");
	private Button btnLeave = new Button("Leave");
	private Button btnExit = new Button("Exit");
	private VBox takeLeaveItButtonPane = new VBox(10);
	
	//Instantiate game timer (HW 4.2)
	private GameTimer timeObj;
	
	//Create players
	private Dealer dealer; 
	private Player player;
	
	//Player and Dealer areas
	private PlayerArea playerArea;
	private DealerArea dealerArea;
	
	//HBox for the header 
	private HBox header;
	private Text headerAlt;
	
	//HW 5.1 game option object 
	private GameOptions gameOptions;
	
	//Lab 16.6 Attribute
	private CardSelector cardSelector = new CardSelector(5);
	
	//HW 5.2 Attributes
	private int maxTioliCards = 6; 
	private int tioliCardsDealt = 0;
	
	//Final Project
	Button btnRprt = new Button("Report");
	
	//Fonts
	String skyM = "file:fonts/SkyMarshalBoldItalicBoldItalic-dYOV.otf";
	
	public TioliGame(Player player) {
		
		//Initialize our player
		this.player = player;
		
		//Instantiate dealer
		dealer = new Dealer (new StandardDeck(), new PokerHand());
		
		//Instantiate player and dealer area
		dealerArea = new DealerArea(dealer);
		playerArea = new PlayerArea(this.player);
		
		//Set custom background 
		setBackground();
		
		//Instantiate 3 of 4 new objects 
		createObjects();
		
		//Create Header 
		createHeader();
		createAltHeader();
		
		//Add objects to each section
		addObjectsToTopSection();
		addObjectsToBottomSection();
		addObjectsToLeftSection();
		addObjectsToRightSection();
		addObjectsToCenterSection();
		
		addObjectsToGameScreen();
		
		//Take it or leave it button pane 
		btnCntrl();
		
		// HW 4.2 adding button functionality
		addButtonListeners();
		
		//Lab 16.6 
		cardSelector.setDisable(true);
		
		//HW 5 .2 
		takeLeaveItButtonPane.setDisable(true);
		
		showGame();
	}
	
	private void createObjects() {
		
		payoutTable = new PayoutTable();
		wager = new Wager(player, 100);
		scoreBoard = new ScoreBoard(player);
		timeObj = new GameTimer(30, btnLeave);
		gameOptions = new GameOptions(dealerArea, timeObj);
	}
	
	private void addButtonListeners() {

		btnTake.setOnAction(e -> {
			//EC12
			if(cardSelector.getCardsSelected() > 0) {
				takeIt();
			}
		});
		btnLeave.setOnAction(e -> leaveIt());
		btnDeal.setOnAction(e -> startDeal());
		btnExit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		btnRprt.setOnAction(e -> new GameReport(player));
	}

	private void showGame() {
		// Instantiate a scene object
		Scene scene = new Scene(gameScreen, 1100, 850);
		// Instantiate a scene object
		Stage primaryStage = new Stage();
		
		//Set title 
		primaryStage.setTitle("Andrew's Tioli");
		
		//Add the scene to the stage 
		primaryStage.setScene(scene);
		
		//Show the stage
		primaryStage.show();
	}
	
	private void addObjectsToGameScreen() {
		// TODO Auto-generated method stub
		
		gameScreen.getChildren().add(headerAlt);
		headerAlt.setX(10);
		headerAlt.setY(85);
		
		gameScreen.setTop(topSection);
		gameScreen.setCenter(centerSection);
		gameScreen.setRight(rightSection);
		gameScreen.setLeft(leftSection);
		gameScreen.setBottom(bottomSection);
		
		BorderPane.setMargin(playerArea, new Insets(0,0,50,0));
	}

	private void addObjectsToCenterSection() {
		//Add Timer object
		centerSection.getChildren().add(timeObj);
		
		//Add player areas and padding
		centerSection.getChildren().addAll(dealerArea, playerArea);	
		centerSection.setPadding(new Insets(60,10,10,10));
	}

	private void addObjectsToRightSection() {
		// Add items to right section
		//EC3
		rightSection.getChildren().addAll(payoutTable, wager, scoreBoard, btnRprt, btnExit);
		VBox.setMargin(payoutTable, new Insets(40, 0, 0, 0));
		rightSection.setAlignment(Pos.CENTER);
	}

	private void addObjectsToLeftSection() {
		// Add items to left section
		gameOptions.setPadding(new Insets(52, 0, 0, 15));
		leftSection.getChildren().addAll(gameOptions);
		leftSection.setAlignment(Pos.TOP_LEFT);
		leftSection.setPadding(new Insets(0,0,10,0));
	}

	private void addObjectsToBottomSection() {
		// Add items to bottom section
	}

	private void addObjectsToTopSection() {
		// Add items to top section
		
		//EC1
		topSection.getChildren().addAll(header);
		topSection.setAlignment(Pos.BASELINE_RIGHT);
		topSection.setPadding(new Insets(50,10,80,10));
	
	}
	
	private void btnCntrl() {
		//Add take and leave buttons to the takeLeave button pane
		takeLeaveItButtonPane.getChildren().addAll(btnTake, btnLeave);
		
		//Add takeLeave button pane to the dealerArea HBox
		dealerArea.getChildren().add(takeLeaveItButtonPane);
		takeLeaveItButtonPane.setAlignment(Pos.CENTER_LEFT);
		
		//Add the card selector to the playerArea HBox 
		playerArea.getChildren().add(cardSelector);
		//Sets spacing for the cardSelectors
		cardSelector.setPadding(new Insets(0,0,0,116));
		
		//Add the deal button to the playerArea HBox
		playerArea.getChildren().add(btnDeal);
		
		//EC2
		//Set font size and size for deal button 
		btnDeal.setStyle("-fx-border-color:lightgray; -fx-background-color: dimgray");
		btnDeal.setFont(Font.loadFont(skyM, 18));
		btnDeal.setTextFill(Color.WHITE);
		btnDeal.setPrefSize(80, 45);
		
		//Set font size and size for take button 
		btnTake.setStyle("-fx-border-color:lightgray; -fx-background-color: dimgray");
		btnTake.setFont(Font.loadFont(skyM, 20));
		btnTake.setTextFill(Color.WHITE);
		
		//Set font size and size for leave button 
		btnLeave.setStyle("-fx-border-color:lightgray; -fx-background-color: dimgray");
		btnLeave.setFont(Font.loadFont(skyM, 18));
		btnLeave.setTextFill(Color.WHITE);
		
		//Set font size and size for report button 
		btnRprt.setStyle("-fx-border-color:lightgray; -fx-background-color: dimgray");
		btnRprt.setFont(Font.loadFont(skyM, 16));
		btnRprt.setTextFill(Color.WHITE);
		
		//Set font size and size for exit button 
		btnExit.setStyle("-fx-border-color:lightgray; -fx-background-color: dimgray");
		btnExit.setFont(Font.loadFont(skyM, 28));
		btnExit.setTextFill(Color.WHITE);
	}

	private void createHeader() {
		// Create header
		Text headerText = new Text("Welcome, " + player.getName());
		
		//EC2
		headerText.setFont(Font.loadFont(skyM, 24));
		headerText.setFill(Color.WHITE);
		
		header = new HBox(headerText);
	}
	
	private void createAltHeader() {
		headerAlt = new Text("Take It || Leave It");
		
		//EC2
		headerAlt.setFont(Font.loadFont(skyM, 42));
		headerAlt.setFill(Color.WHITE);
	}
	
	
	//==============================================================
	//==============================================================
	// Code for playing the game
	private void startDeal() {
		//EC10
		if(player.getBank() < wager.getWagerAmount() || player.getBank() == 0 ) {
			btnDeal.setDisable(true);
		}
		
		clearCards();
		playerArea.clearPlayerHand();
		dealerArea.clearDiscardHolder();
		
		dealPlayer();
		
		evaluateHand();
		
		playerArea.showCards();
		
		playerArea.showHandDescr();
		
		dealDealer();
		
		//Lab 16.6
		cardSelector.setDisable(false);
		
		//HW 5.2
		takeLeaveItButtonPane.setDisable(false);
		
		btnDeal.setDisable(true);
		
		timeObj.startTimer();
		
		//EC5
		wager.setDisable(true);
		
		//EC7
		scoreBoard.setWinAmount(0);
		
		
		
	}

	private void dealDealer() {
		dealer.dealCard(dealer);
		
		dealerArea.showTioliCard();
		
		tioliCardsDealt++;
	}



	private void evaluateHand() {
		player.getHand().evaluateHand();
	}



	private void dealPlayer() {
		for(int i = 0; i < 5; i++) {
			dealer.dealCard(player);
		}
	}
	
	//HW 4.2 new methods 
	private void takeIt() {
		System.out.println("Take It");
		
		//HW 5.2 
		timeObj.stopTimer();
		
		int index = cardSelector.getCardsSelected() - 1;
		
		//Determine the card selected to get rid of
		Card selectedCard = player.getHand().getCard(index);
		//Remove card from dealer
		Card tioliCard = dealer.getHand().removeCard(0);
		//Set the tioli card into selected players hand index
		player.getHand().setCard(index, tioliCard);
		//Send card to discard pile
		dealer.getDeck().addDiscard(selectedCard);
		//Clear the tioli holder
		dealerArea.clearTioliHolder();
		
		dealerArea.showDiscardedCard(selectedCard);
		
		playerArea.showCards();
		
		evaluateHand();
		
		//Show player hand desc
		playerArea.showHandDescr();
		
		//EC8
		cardSelector.clearOptions();
		
		if(tioliCardsDealt == maxTioliCards) {
			endHand();
		} else {
			dealDealer();
			timeObj.startTimer();
		}
	}
	
	private void leaveIt() {
		System.out.println("Leave It");
		
		//HW 5.2
		
		timeObj.stopTimer();
		
		Card tioliCard = dealer.getHand().removeCard(0);
		
		dealer.getDeck().addDiscard(tioliCard);
		
		dealerArea.clearTioliHolder();
		
		dealerArea.showDiscardedCard(tioliCard);
		
		//EC8
		cardSelector.clearOptions();
		
		
		if(tioliCardsDealt == maxTioliCards) {
			endHand();
		} else {
			dealDealer();
			timeObj.startTimer();
		}
	}
	
	private void clearCards() {
		if(player.getHand().getCards().length != 0) {
			player.getHand().discardAll(dealer.getDeck());
		} 
	}
	
	public void endHand() {
		
		int amountWon = getPayout();
		
		displayFinalResults(amountWon);
		
		tioliCardsDealt = 0;
		
		btnDeal.setDisable(false);
		takeLeaveItButtonPane.setDisable(true);
		
		cardSelector.setDisable(true);
		
		writeDataToFile(amountWon);
		
		saveDataInDatabase(amountWon);
		
		wager.setDisable(false);
		
		//EC9
		dealer.reshuffleDeck();
		
		takeLeaveItButtonPane.setDisable(true);
	}

	private void saveDataInDatabase(int amountWon) {
		//Create new database object
		GameData gameData = new GameData();
		
		gameData.insertHand(player);
		
		gameData.updateBank(player);
		gameData.insertResults(player, amountWon);
		
		//When done w the database, close it
		
		gameData.close();
	}

	private void displayFinalResults(int amountWon) {
		scoreBoard.setWinAmount(amountWon);
		player.setBank(player.getBank() + amountWon);
		scoreBoard.updateBank();
	}
	
	private int getPayout() {
		return payoutTable.getPayout(player.getHand().getHandRank(), wager.getWagerAmount());
		
	}
	
	public void writeDataToFile(int amountWon) {
		GameFile.writeData("files/gamedata.dat", player, amountWon);
	}
	
	private void setBackground() {
		BackgroundImage myBMI = new BackgroundImage(new Image("file:images/gameBackground2.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		gameScreen.setBackground(new Background(myBMI));
	}
}
