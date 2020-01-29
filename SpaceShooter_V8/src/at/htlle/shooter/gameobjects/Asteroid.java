package at.htlle.shooter.gameobjects;

import at.htlle.shooter.Starter;
import at.htlle.shooter.utils.ImageLoader;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Asteroid extends Sprite implements IScoreable
{
	private int exploding_x = -1;

	private int scoreValue = 0;
	
	// Punkte für getroffenen Asteroid bereits gezählt (Ja/Nein)
	private boolean scored = false;

	/**
	 * ctor
	 * @param x Startposition X 
	 * @param y Startposition Y
	 * @param speed Geschwindigkeit des Asteroiden 
	 */
	public Asteroid(String imageFileName, int x, int y, int intervall, int speed)
	{
		super(imageFileName, x, y, intervall, speed, Direction.WEST, true);
	}
	

	/**
	 * Überschreiben der toString Methode, damit wir den Asteroiden bequem
	 * ausgeben können
	 */
	public String toString()
	{
		return "Asteroid(" + this.x + "|" + this.y + ")";
	}
	
	@Override
	public boolean isVisible()
	{
		return (this.x > 0);
	}
	

	
	@Override
	public Bounds getBounds()
	{
		int centerX = (int) (super.x + super.image.getWidth()/2);
		int centerY = (int) (super.y + super.image.getHeight()/2);
		
		Ellipse c = new Ellipse(centerX, centerY, super.image.getWidth() / 2, super.image.getHeight() / 2);
		return c.getBoundsInLocal();
	}
	
	/**
	 * Bringt den Asteroid zum explodieren
	 */
	public void explode()
	{
		this.exploding_x = (int)this.x; 
		// entfernt die Zahl im Namen der Asteroidengrafiknamen
		String explodeImgName = this.imageName.replaceAll("[0-9]", "") + "_explode";
		
		this.image = ImageLoader.loadImage(explodeImgName);
		
		if (!isDemaged) {
			this.isDemaged = true;
			this.scored = true;
		}
		
		 
	}
	
	/**
	 * Liefert zurück ob der Asteroid explodiert ist
	 * @return
	 */
	public boolean isExploded() 
	{
		return (this.exploding_x != -1);
	}

	/**
	 * Wie weit weg ist der Asteroid explodiert ?
	 * @return
	 */
	public int getExplosionDistance() 
	{
		return (int)(this.exploding_x - this.x);
	}


	@Override
	public void setScore(int value) {
		this.scoreValue = value;
		
	}


	@Override
	public boolean hasScored() {
		
		return this.scored;
	}


	@Override
	public void resetScored() {
		this.scored = false;
		
	}


	@Override
	public int getScore() {
		
		return this.scoreValue;
	}

}

