package at.htlle.shooter;

import javafx.scene.layout.Pane;

public interface IStatusBar {

	
	/**
	 * Rückgabe der Pane Objekts der StatusPane
	 * @return StatusPane Objekt
	 */
	public Pane getPane();

	
	/**
	 * Setzen des Scores in der StatusPane
	 * @param score
	 */
	public void setScore(int score);
	/**
	 * Setzen der Anzahl von Leben (Raumschiffen) in der StatusPane
	 * @param lifes
	 */
	public void setLifes(int lifes);
	/**
	 * Setzen der aktuellen Anzahl der Frames pro Sekunde (FPS)
	 * @param fps
	 */
	public void setFPS(int fps);
	
	/**
	 * Setzen des aktuellen Spiel-Levels
	 * @param level
	 */
	public void setLevel(int level);
}
