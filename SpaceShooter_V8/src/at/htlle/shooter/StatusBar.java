package at.htlle.shooter;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StatusBar extends HBox implements IStatusBar {
	private Label scoreLabel;
	private Label scoreValueLabel;
	private Label fpsLabel;
	private Label fpsValueLabel;
	
	public StatusBar() {
		scoreLabel = new Label("Score: ");
        scoreValueLabel = new Label("0");
        scoreValueLabel.setPadding(new Insets(0,40,0,0));
        
        fpsLabel = new Label("FPS: ");
        fpsValueLabel = new Label("0");
        fpsLabel.setPadding(new Insets(0,0,0,0));
        
        this.getChildren().addAll(scoreLabel, scoreValueLabel, fpsLabel, fpsValueLabel);
	}


	@Override
	public Pane getPane() {
		
		return this;
	}

	@Override
	public void setScore(int score) {
		this.scoreValueLabel.setText(score + "");
		
	}

	@Override
	public void setLifes(int lifes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFPS(int fps) {
		this.fpsValueLabel.setText(fps + "");
		
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
		
	}

}
