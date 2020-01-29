package at.htlle.shooter;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class FXMLStatusBar implements IStatusBar {

	private FXMLLoader loader;
	private StatusBarController controller;
	
	public FXMLStatusBar() {
		//Schritt 1 - Ermittlung der URL der FXML - Datei der View
		URL viewUrl = getClass().getResource("StatusBar.fxml");
	    
		//Schritt 2 - Erzeugen der Instanz vom FXML - Loader der View
		loader = new FXMLLoader(viewUrl);
		Parent view = null;
		try {
			view = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    // Schritt 3 - Holen des Controllers
	    controller = loader.getController();
	
		
	}
	@Override
	public Pane getPane() {
		
		return controller.getStatusBar();
	}

	@Override
	public void setScore(int score) {
		controller.setScore(score);
		
	}

	@Override
	public void setLifes(int lifes) {
		controller.setLifes(lifes);
		
	}

	@Override
	public void setFPS(int fps) {
		controller.setFPS(fps);
		
	}

	@Override
	public void setLevel(int level) {
		controller.setLevel(level);
		
	}

}
