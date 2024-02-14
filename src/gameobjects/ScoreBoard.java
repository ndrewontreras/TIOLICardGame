package gameobjects;

import java.text.DecimalFormat;

import player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBoard extends VBox {
	
	private Label bankLabel = new Label("Bank: ");
	private Label winAmountLabel = new Label("Win Amount: ");
	private Label bankAmount = new Label();
	private Label winAmount = new Label("0");
	private GridPane[] scoreboard = new GridPane[2];
	private String borderColor = "white";
	
	private Player player;
	
	public ScoreBoard(Player player) {
		this.player = player;
		
		buildScoreBoard();
	}
	
	public ScoreBoard(Player player, String borderColor)  {
		//Score board consists of the players bank and amount won 
		this.player = player;
		this.borderColor = borderColor;	
		
		buildScoreBoard();
	}

	public void setWinAmount(int amount) {
		winAmount.setText(formattedNumber(amount));
	}
	
	public void updateBank() {
		bankAmount.setText(formattedNumber(player.getBank()));
	}

	private void buildScoreBoard() {
		bankAmount.setText(formattedNumber(this.player.getBank()));
		
		createLabels();
		createScoreBoard();
		addToVBox(); //Create final object to be used
		styleVBox();		
	}

	private void createLabels() {
		String satoshi = "file:fonts/Satoshi-Regular.otf";
		String satoshiMed = "file:fonts/Satoshi-Medium.otf";
		bankLabel.setFont(Font.loadFont(satoshiMed, 18));
		bankLabel.setTextFill(Color.ANTIQUEWHITE);
		bankLabel.setAlignment(Pos.CENTER_LEFT);
		bankLabel.setPadding((new Insets(5, 10, 5, 10)));
		
		bankAmount.setFont(Font.loadFont(satoshi, 18));
		bankAmount.setTextFill(Color.ANTIQUEWHITE);
		bankAmount.setAlignment(Pos.CENTER_RIGHT);
		bankAmount.setPadding((new Insets(5, 10, 5, 10)));
		
		winAmountLabel.setFont(Font.loadFont(satoshiMed, 18));
		winAmountLabel.setTextFill(Color.ANTIQUEWHITE);
		winAmountLabel.setAlignment(Pos.CENTER_LEFT);
		winAmountLabel.setPadding((new Insets(2, 10, 2, 10)));
		
		winAmount.setFont(Font.loadFont(satoshi, 18));
		winAmount.setTextFill(Color.ANTIQUEWHITE);
		winAmount.setAlignment(Pos.CENTER_RIGHT);
		winAmount.setPadding((new Insets(2, 10, 2, 10)));
	}

	private void createScoreBoard() {
		for(int i=0; i<scoreboard.length; i++) {
			scoreboard[i] = new GridPane();
		}
		//Set win amount text
		scoreboard[0].add(winAmountLabel, 0, 0);
		scoreboard[0].add(winAmount, 1, 0);
		
		//Set bank text
		scoreboard[1].add(bankLabel, 0, 0);
		scoreboard[1].add(bankAmount, 1, 0);
		
		GridPane.setFillWidth(winAmountLabel, true);
		winAmountLabel.setPrefWidth(150);

		GridPane.setFillWidth(bankLabel, true);
		bankLabel.setPrefWidth(150);		
	}

	private void addToVBox() {
		for(int i=0; i<scoreboard.length; i++) {
			this.getChildren().add(scoreboard[i]);
		}			
	}

	private void styleVBox() {
		if(borderColor.toLowerCase() != "none") {
			String cssLayout = 
					"-fx-border-color: " + borderColor + ";\n" +
	                "-fx-border-insets: 5;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-border-style: solid;\n" +
	                "-fx-border-radius: 10; \n";
			
			this.setStyle(cssLayout);
			this.setPrefWidth(280);
			this.setPrefHeight(80);
			this.setMaxHeight(USE_PREF_SIZE);
		}		
	}
	
	private String formattedNumber(int number) {
		DecimalFormat formatter = new DecimalFormat("#,###");		
		return formatter.format(number);
	}	
}
