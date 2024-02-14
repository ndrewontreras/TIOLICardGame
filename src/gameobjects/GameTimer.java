package gameobjects;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;


public class GameTimer extends HBox {
	private Text timerText;
	private int startSeconds;
	private int secondsLeft;
	private Timeline timeline;
	private Button button;
	private boolean useTimer = true;
	
	public GameTimer(int seconds, Button button) { 
		this.startSeconds = seconds;
		this.secondsLeft = seconds;
		this.button = button;
		
		timerText = new Text(Integer.toString(seconds));
		this.getChildren().add(timerText);
		timerText.setTextAlignment(TextAlignment.CENTER);
		
		styleText();
		this.setAlignment(Pos.CENTER);
	}
	
	private void styleText() {
		String skyM = "file:fonts/SkyMarshalBoldItalicBoldItalic-dYOV.otf";
		timerText.setFont(Font.loadFont(skyM, 60));
		timerText.setFill(Color.WHITE);		
	}
	
	public void startTimer() {
		if(useTimer) {
			refreshTimerDisplay();
			
			timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), 
	        		new EventHandler<ActionEvent>() {
	                    // KeyFrame event handler
						@Override
						public void handle(ActionEvent event) {
							updateTimer();
							
						}
	                }));	
	        
	        timeline.playFromStart();
		}
	}
	
	private void updateTimer() {
		secondsLeft--;
		
		timerText.setText(Integer.toString(secondsLeft));
		
		if(secondsLeft <=0) {
			timeline.stop();
			button.fire();			
		}
		
	}
	
	public void refreshTimerDisplay() {
		secondsLeft = startSeconds;
		timerText.setText(Integer.toString(startSeconds));
	}

	public void stopTimer() {
		if(useTimer)
			timeline.stop();
	}
	
	public int getStartSeconds() {
		return startSeconds;
	}

	public void setStartSeconds(int seconds) {
		this.startSeconds = seconds;
	}

	public void setUseTimer(boolean useTimer) {
		this.useTimer = useTimer;
	}

}
