package at.htlle.shooter.gameobjects;

import at.htlle.shooter.IGameConfig;
import at.htlle.shooter.Starter;
import at.htlle.shooter.utils.ImageLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class SpaceShip extends Sprite
{
	private IGameConfig cfg;
	
	private boolean destroyed;


	public SpaceShip(IGameConfig cfg, int x, int y, Direction direction) {
		this(cfg.getSpaceShipImgName(), x, y, cfg.getSpriteMoveIntervall(), cfg.getSpaceShipSpeed(), direction);
		this.cfg = cfg;
	}
	
	/**
	 * ctor
	 * @param x Startposition X 
	 * @param y Startposition X 
	 */
	private SpaceShip(String imageName, int x, int y, int intervall, int speed, Direction direction)
	{
		super(imageName, x, y, intervall, speed, direction, true);
	}
	

	/**
	 * Überschreiben der toString Methode, damit wir das RS bequem
	 * ausgeben können
	 */
	public String toString()
	{
		return "SpaceShip(" + this.x + "|" + this.y + ")";
	}
	
	
	/**
	 * Feuert eine Rakete ab
	 * @return
	 */
	public Rocket fireRocket()
	{
		int x = (int)(this.x + image.getWidth());
		int y = (int)(this.y + image.getHeight()/2);
		
		return new Rocket(cfg.getRocketImgName(), x, y, cfg.getSpriteMoveIntervall(), cfg.getRocketSpeed(), true);
	}

	/**
	 * Gibt den Umriss eines Schiffes zurück
	 * @param ship
	 * @return
	 */
	public Bounds getBounds()
	{
		Rectangle r = new Rectangle(x, y, image.getWidth(), image.getHeight());
		return r.getBoundsInLocal();
	}

	/**
	 * Wird aufgerufen wenn die Enterprise besch�digt wird
	 */
	public void damage() 
	{
		this.image = ImageLoader.loadImage(imageName + "_explode");
		this.destroyed = true;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}
