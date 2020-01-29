package at.htlle.shooter;

import java.util.List;

import at.htlle.shooter.gameobjects.Asteroid;
import at.htlle.shooter.gameobjects.Rocket;
import at.htlle.shooter.gameobjects.SpaceShip;

public interface IGameSceneHandler {

	/**
	 *  Initialisierung aller Sprite Objekte, die im Spiel verwendet werden	
	 */
	public void initializeGame();

	/**
	 * Behandlet (steuert) die aktuelle GameSzene (Darstellung des aktuellen Frames)
	 */
	public void handleGameScene();
	
	
	public List<Asteroid> getAsteroiden();

	public List<Rocket> getRaketen();

	public SpaceShip getEnterprise();
}
