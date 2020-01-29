package at.htlle.shooter.gameobjects;

public class Rocket extends Sprite
{
	
	/**
	 * ctor
	 * @param x Startposition X 
	 * @param y Startposition Y
	 */
	public Rocket(String imageName, int x, int y, int intervall, int speed, boolean visible)
	{
		super(imageName, x, y,intervall, speed, Direction.EAST, true);
	}
	

	/**
	 * Überschreiben der toString Methode, damit wir den Asteroiden bequem
	 * ausgeben können
	 */
	public String toString()
	{
		return "Rocket(" + this.x + "|" + this.y + ")";
	}
	
	@Override
	public boolean isVisible()
	{
		return (this.x < 1000 && visible); // EXT-CHANGES
		 
	}

}
