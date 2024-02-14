package gamedata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import game.TioliGame;
import hand.PokerHand;
import player.Player;

public class GameFile {
	
	
	public static void writeData(String fileName, Player playerObj, int winAmount) {
		
		try(DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName, true))){
			/*for(int i = 0; i < TioliGame.getGamesPlayed(); i++) {
				output.writeUTF(playerObj.getId());
				output.writeUTF(playerObj.getName());
				output.writeUTF(((PokerHand)(playerObj.getHand())).getHandDescr());
				output.writeInt(winAmount);				
				output.writeInt(playerObj.getBank());
			}*/
			//if(playerObj.getHand().)
			
			output.writeUTF(playerObj.getId());
			output.writeUTF(playerObj.getName());
			output.writeUTF(((PokerHand)(playerObj.getHand())).getHandDescr());
			output.writeInt(winAmount);				
			output.writeInt(playerObj.getBank());
	
			
		} catch(IOException ex) {
			System.out.println("Error writing data!");
		}
	}
}
