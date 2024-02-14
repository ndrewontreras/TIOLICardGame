package gameobjects;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PayoutTable extends VBox {

	private String[] bonusNames = {
			"High Card",
			"Pair",
			"2 Pair",
			"3 of a Kind",
			"Straight",
			"Flush",
			"Full House",
			"4 of a Kind",
			"Straight Flush",
			"Royal Flush"
	};
	
	private int[][] bonusOdds = {
			{-1, 1},    //High card 
			{1, 1},   //pair
			{2, 1},  //2 pair
			{5, 2},  //3 of a kind
			{3, 1},  //straight
			{5, 1},  //flush
			{6, 1},  //full house
			{30, 1},  //4 of a kind
			{50, 1}, //straight flush
			{100, 1}, //royal flush
	};
	
	private Label[] textList = new Label[bonusNames.length];
	private Label[] multiplierList = new Label[bonusOdds.length];
	private Label[] oddsBaseList = new Label[bonusOdds.length];
	private Label[] oddsTo = new Label[bonusOdds.length];
	
	private ArrayList<GridPane> bonusText = new ArrayList<GridPane>(); 
	private String borderColor = "white";
	
	public PayoutTable() {
		createBoard();
	}
	
	public PayoutTable(String borderColor) {
		this.borderColor = borderColor;
		createBoard();
	}

	public int getPayout(int index, int wager) {
		return wager * (bonusOdds[index-1][0] / bonusOdds[index-1][1]);
	}
	
	private void createBoard() {
		createLabels(); //Create and format labels
		createBonusText(); //Create each bonus object
		addToVBox(); //Create final object to be used
		styleVBox();  //Add some styling
	}

	private void createLabels() {
		//EC2
		String satoshi = "file:fonts/Satoshi-Regular.otf";
		String satoshiMed = "file:fonts/Satoshi-Medium.otf";
		for(int i=0; i<textList.length; i++) {
			textList[i] = new Label(bonusNames[i]);
			textList[i].setFont(Font.loadFont(satoshiMed, 18));
			textList[i].setTextFill(Color.WHITE);
			textList[i].setAlignment(Pos.CENTER_LEFT);
			textList[i].setPadding((new Insets(2, 10, 2, 10)));
			
			int multiplier = bonusOdds[i][0];
			int oddsBase = bonusOdds[i][1];
			DecimalFormat formatter = new DecimalFormat("#,###");
			
			multiplierList[i] = new Label(formatter.format(multiplier));
			multiplierList[i].setFont(Font.loadFont(satoshi, 18));
			multiplierList[i].setTextFill(Color.WHITE);
			multiplierList[i].setAlignment(Pos.BASELINE_RIGHT);
			multiplierList[i].setPadding((new Insets(2, 10, 2, 10)));

			oddsBaseList[i] = new Label(formatter.format(oddsBase));
			oddsBaseList[i].setFont(Font.loadFont(satoshi, 18));
			oddsBaseList[i].setTextFill(Color.WHITE);
			oddsBaseList[i].setAlignment(Pos.BASELINE_RIGHT);
			oddsBaseList[i].setPadding((new Insets(2, 10, 2, 10)));
			
			oddsTo[i] = new Label("To");
			oddsTo[i].setFont(Font.loadFont(satoshi, 18));
			oddsTo[i].setTextFill(Color.WHITE);
			oddsTo[i].setAlignment(Pos.BASELINE_RIGHT);
			oddsTo[i].setPadding((new Insets(2, 10, 2, 10)));
		}
	}

	private void createBonusText() {		
		for(int i=0; i<bonusOdds.length; i++) {
			bonusText.add(new GridPane());
			bonusText.get(i).add(textList[i], 0, 0);
			bonusText.get(i).add(multiplierList[i], 1, 0);
			bonusText.get(i).add(oddsTo[i], 2, 0);
			bonusText.get(i).add(oddsBaseList[i], 3, 0);
			
			GridPane.setFillWidth(textList[i], true);
			textList[i].setPrefWidth(150);

			GridPane.setFillWidth(multiplierList[i], true);
			multiplierList[i].setPrefWidth(70);

			GridPane.setFillWidth(oddsTo[i], true);
			oddsTo[i].setPrefWidth(50);
		}		
	}

	private void addToVBox() {
		for(int i=bonusText.size()-1; i>=0; i--) {
			if(bonusOdds[i][0] != -1) {
				this.getChildren().add(bonusText.get(i));
			}
		}		
	}
	
	private void styleVBox() {
		if(borderColor.toLowerCase() != "none") {
			String cssLayout = 
					"-fx-border-color: " + borderColor + ";\n" +
	                "-fx-border-insets: 5;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-border-style: solid;\n" + 
	                "-fx-border-radius: 10;\n";
	
			this.setStyle(cssLayout);
			this.setPrefWidth(300);
			this.setPrefHeight(230);
			this.setMaxHeight(USE_PREF_SIZE);
		}		
	}
}
