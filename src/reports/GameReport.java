package reports;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import gamedata.GameData;
import player.Player;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameReport {
	//Window dimensions - adjust as needed
	int windowWidth = 1300;
	int windowHeight = 550;
	
	//Instantiate a new Stage (window object)
	Stage reportStage = new Stage();

	//To do: We need to Declare a player object as an attribute
	Player player; 
	
	//To do: Declare a Text Object field for the page title
	Text txtTtle;
	
	//The panes to hold our nodes
	BorderPane pane = new BorderPane(); //Main pain for the scene
	GridPane dataGrid = new GridPane();  //To display our data (from the Text objects)
	ScrollPane scrollPane;  //So our data will scroll
	GridPane titleContainer;  //A container for our report title
	
	//To do: We need to instantiate database object
	GameData gameData = new GameData();
	
	//Create array lists to hold each piece of data in a Text object
	ArrayList<Text> gameId = new ArrayList<Text>();
	//To do: Now create the array lists that are needed for the other data being reported
	ArrayList<Text> hDescr = new ArrayList<Text>();
	ArrayList<Text> amntWn = new ArrayList<Text>();
	ArrayList<Text> plyrBnk = new ArrayList<Text>();
	
	//Fonts
	String skyM = "file:fonts/SkyMarshalBoldItalicBoldItalic-dYOV.otf";
	String satoshi = "file:fonts/Satoshi-Regular.otf";

	//We will need to close the screen when done
	private Button btnExit = new Button("Exit");
	
	
	Text gmeIdT; 
	Text hDescrT;
	Text amntWnT;
	Text plyrBnkT;
	
	VBox cntrSctn = new VBox();
	GridPane clmnHdrGrd = new GridPane();

	
	public GameReport(Player player) {
		//First, use the parameter to set the appropriate attribute
		this.player = player;
		
		//Step 1:
		//Get our data from the database
		getData();

		//Step 2:
		//Put our Text objects into the GridPane
		populateGridPane();

		//Step 3:
		//Put the grid pane into a scroll pane
		addGridToScroll();

		//Step 4:
		//Set the listener for the Exit button
		createExitButtonListener();

		//Step 5:
		//Create a report title
		createReportTitle();
				
		
		//Step 6:
		//Put our objects into the BorderPane
		addObjectsToPage();
		
		//Step 7:
		//Add styling to make it look pretty
		styleStuff();
		
		//createTextHeaders();

		//Make the screen appear
		showScene();

	}
	
	private void getData() {
		//1A
		//Call the getReportData method you created
		//Use your GameData object 
		//Put the data returned into a local result set 
		ResultSet rsltSt = gameData.getReportData(player);
		
		//Add the column headers to the Text object ArrayLists
		
		//1B
		//Now loop through the result set
		//Add each of the appropriate columns to Text objects
		//Add the Text Objects to the appropriate Array List
		//The format is like the header but instead of harcoded text, use the
		//information from the ResultSet
		try {
			while(rsltSt.next()) {
				gameId.add(new Text(rsltSt.getString(1)));
				hDescr.add(new Text(rsltSt.getString(3)));
				amntWn.add(new Text(rsltSt.getString(4)));
				plyrBnk.add(new Text(rsltSt.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void populateGridPane() {
		//2
		//We use this method to add the Text objects to the GridPane
		//Again, you will have to loop through the Text ArrayLists
		//Hint...all the Text ArrayLists are the same size
		for(int i = 0; i < gameId.size(); i++) {
			dataGrid.add(gameId.get(i), 1, i);
			dataGrid.add(hDescr.get(i), 2, i);
			dataGrid.add(amntWn.get(i), 3, i);
			dataGrid.add(plyrBnk.get(i), 4, i);
		}
		
	}
	
	private void addGridToScroll() {
		//3
		//Here you add the GridPane to the ScrollPane by
		//Instantiating your scrollPane object and feed the grid pane to the ScrollPane constructor
		scrollPane = new ScrollPane(dataGrid);
		
		
	}
	
	private void createExitButtonListener() {
		//4A
		//Define the exit button listener to call exitReport()
		btnExit.setOnAction(e -> {
			reportStage.close();
		});
		
		//4B - complete the exitReport method (already defined below)
		exitReport();
	}
	
	private void createReportTitle() {
		
		//5A
		//Instantiate the Text object attribute for the title at the top of the page 
		//Title should include the player's name and "Game Data" or "Report", etc.
		txtTtle = new Text(player.getName() + "'s Report");
		
		//5B
		//Instantiate the titleContainer and put the titleText into it
		titleContainer = new GridPane();
		titleContainer.add(txtTtle, 2, 5);
		
		//Column Headers 
		gmeIdT = new Text("Game ID");
		hDescrT = new Text("Hand Descr.");
		amntWnT = new Text ("Amount Won");
		plyrBnkT = new Text("Player Bank");

		
		pane.getChildren().add(gmeIdT);
		pane.getChildren().add(hDescrT);
		pane.getChildren().add(amntWnT);
		pane.getChildren().add(plyrBnkT);
		gmeIdT.setX(175);
		gmeIdT.setY(100);
		hDescrT.setX(490);
		hDescrT.setY(100);
		amntWnT.setX(710);
		amntWnT.setY(100);
		plyrBnkT.setX(898);
		plyrBnkT.setY(100);
	
		
		
		scrollPane.setPadding(new Insets(30, 10, 10, 10));
		cntrSctn.getChildren().addAll(clmnHdrGrd, scrollPane);
		
	}
	
	private void addObjectsToPage() {
		//Here we will add our objects to the page (the BorderPane):
		
		//6A
		//Put the title in the top
		pane.setTop(titleContainer);
		
		//6B
		//Put the ScrollPane in the center
		pane.setCenter(cntrSctn);
		
		//6C
		//Put the Button object in the bottom
		//See if you can center it (width-wise) in the styling method
		pane.setBottom(btnExit);
		BorderPane.setMargin(btnExit, new Insets(0,0,35,620));
	}
	
	private void exitReport() {
		//4B
		//Close the database object before closing the window
		gameData.close();
		
		//Close the window
		reportStage.close();
	}
	
	private void styleStuff() {
		//Some styling for the GridPane...should work ok
		//Feel free to adjust as needed
		dataGrid.setHgap(160);
		dataGrid.setVgap(20);
		clmnHdrGrd.setHgap(160);
		clmnHdrGrd.setPadding(new Insets(0, 0, 0, 0));
		//clmnHdrGrd.setVgap(20);

		//Here is some styling to help keep the scroll pane from squishing to the edge of the window
		//Feel free to modify
		int leftRightSpace = 40;
		scrollPane.setPrefWidth(windowWidth - leftRightSpace);
		scrollPane.setMaxWidth(windowWidth - leftRightSpace);
		
		//Any styling for the Page title?
		txtTtle.setFont(Font.loadFont(skyM, 32));
		txtTtle.setFill(Color.WHITESMOKE);
		titleContainer.setPadding(new Insets(20, 0, 60, 25));
		//Any styling for your exit button?
		btnExit.setStyle("-fx-border-color:lightgray; -fx-background-color: dimgray");
		btnExit.setFont(Font.loadFont(skyM, 20));
		btnExit.setTextFill(Color.WHITE);
		
		//Can you make the exit button centered horizontally?
		//Hint: note there is a window width attribute
		

		//Style your Text objects?
		//Consider font type, font size, color, etc.
		//Remember you will have to loop through the Text object ArrayLists
		//Hint...all the Text ArrayLists are the same size
		
		for(int i = 0; i < gameId.size(); i++) {
			gameId.get(i).setFont(Font.loadFont(satoshi, 16));
			gameId.get(i).setFill(Color.WHITESMOKE);
			hDescr.get(i).setFont(Font.loadFont(satoshi, 16));
			hDescr.get(i).setFill(Color.WHITESMOKE);
			amntWn.get(i).setFont(Font.loadFont(satoshi, 16));
			amntWn.get(i).setFill(Color.WHITE);
			plyrBnk.get(i).setFont(Font.loadFont(satoshi, 16));
			plyrBnk.get(i).setFill(Color.WHITESMOKE);
		}
		
		
		//Change column headers' size/color?
		gmeIdT.setFont(Font.loadFont(skyM, 16));
		gmeIdT.setFill(Color.WHITESMOKE);
		amntWnT.setFont(Font.loadFont(skyM, 16));
		amntWnT.setFill(Color.WHITESMOKE);
		plyrBnkT.setFont(Font.loadFont(skyM, 16));
		plyrBnkT.setFill(Color.WHITESMOKE);
		hDescrT.setFont(Font.loadFont(skyM, 16));
		hDescrT.setFill(Color.WHITESMOKE);
		
		//Maybe style the scroll pane so there is some padding on top of the GridPane?
		scrollPane.setPadding(new Insets(0, 75, 0, 0));
		
		//Background
		setBackground();
		scrollPane.setStyle("-fx-background-color: transparent");
		
		//Headers for columns 
		gmeIdT = new Text("Game ID");
		hDescrT = new Text("Hand Descr.");
		amntWnT = new Text ("Amount Won");
		plyrBnkT = new Text("Player Bank");
		
		
		
	}
		
	private void showScene() {
		//This will make our report show
		//No changes needed
		Scene scene = new Scene(pane, windowWidth, windowHeight);
		scene.getStylesheets().add("file:cssStyles/sPaneTrnsprt.css");
		reportStage.setTitle("Draw Poker Data For " + player.getName());
		reportStage.setScene(scene);
		reportStage.show();		
	}
	
	private void setBackground() {
		BackgroundImage myBMI = new BackgroundImage(new Image("file:images/gameBackground2.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBMI));
	}
}
