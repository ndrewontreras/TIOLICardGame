import game.TioliGame;
import gamedata.GameData;
import hand.PokerHand;
import javafx.application.Application;
import javafx.stage.Stage;
import player.Player;

public class PlayGame extends Application {
	
	public void start(Stage primaryStage) {
		Player player = new Player("FastFreddy", "9765467", 1450, new PokerHand());
		
		GameData databaseObj = new GameData();
		
		TioliGame tioiliGame = new TioliGame(databaseObj.getRandomPlayer());
		
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
 