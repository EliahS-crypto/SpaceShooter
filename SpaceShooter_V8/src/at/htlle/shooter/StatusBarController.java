package at.htlle.shooter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBarController  {

	@FXML
	HBox statusBar;
	
	@FXML
	Label scoreValue;
	
	@FXML
	Label lifesValue;
	
	@FXML
	Label levelValue;
	
	@FXML
	Label fpsValue;
	

	public void setScore(int score) {
		this.scoreValue.setText(Integer.toString(score));

	}


	public void setLifes(int lifes) {
		this.lifesValue.setText(Integer.toString(lifes));

	}

	
	public void setFPS(int fps) {
		this.fpsValue.setText(Integer.toString(fps));

	}

	
	public void setLevel(int level) {
		this.levelValue.setText(Integer.toString(level));

	}
	
	public HBox getStatusBar() {
		return this.statusBar;
	}

}
