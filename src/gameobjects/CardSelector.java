package gameobjects;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class CardSelector extends HBox {
	
	private RadioButton[] optionButton;
	private ToggleGroup group;
	private int cardsSelected = 0; 
	
	public CardSelector(int numberOfCards) {
		this.optionButton = new RadioButton[numberOfCards];
		
		createOptionButtons();
	}
	
	public void createOptionButtons() {
		for(int i = 0; i < optionButton.length; i++) {
			optionButton[i] = new RadioButton();
			
			optionButton[i].setToggleGroup(group);
			
			optionButton[i].setPadding(new Insets(0, 60, 20, 0));
			
			final int index = i;
			optionButton[i].setOnAction(e -> {
				
				cardsSelected = index + 1;
			});
			
			this.getChildren().add(optionButton[i]);
			
		}
	}

	public int getCardsSelected() {
		return cardsSelected;
	}

	public void clearOptions() {
		for(int i = 0; i < optionButton.length; i++) {
			optionButton[i].setSelected(false);
		}
		
		cardsSelected = 0;
	}
}
