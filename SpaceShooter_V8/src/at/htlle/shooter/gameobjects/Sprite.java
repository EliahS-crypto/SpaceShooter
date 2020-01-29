package at.htlle.shooter.gameobjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import at.htlle.shooter.Starter;
import at.htlle.shooter.utils.ImageLoader;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Sprite {
	
    Image image;
    String imageName;
    double x;
	double y;
	
	// Intervall in Milliseconds
	int intervall;
	// Pixel per Second
	int speed;
	Direction direction;
	boolean visible;
	
	// Prozentwert von 0 - 100 (zerstört)
	int demageDegree = 0;
	boolean isDemaged = false;
	
	// Ausrichtung des Bildes (in Grad 0=WEST - 360° im Uhrzeigersinn)
	int heading;
	
	public Sprite() {
		
	}
	
	
	public Sprite(String imageFileName, double x, double y, int intervall, int speed, Direction direction, boolean visible) {
		
		this(imageFileName, x, y, intervall, speed, direction, true, 0, false, 0);
		
	}
	
	

	public Sprite(String imageName, double x, double y, int intervall, int speed, Direction direction, boolean visible,
			int demageDegree, boolean isDemaged, int heading) {
		
		this.imageName = imageName;
		this.image = ImageLoader.loadImage(imageName);
		
		this.x = x;
		this.y = y;
		this.intervall = intervall;
		this.speed = speed;
		this.direction = direction;
		this.visible = visible;
		this.demageDegree = demageDegree;
		this.isDemaged = isDemaged;
		this.heading = heading;
	}


	/**
	 * Standardimplementierung der Sprite - Ränder als umschließendes Rechteck
	 * @return
	 */
	public Bounds getBounds() {
		Rectangle r = new Rectangle(this.x, this.y, this.image.getWidth(), this.image.getHeight());
		return r.getBoundsInLocal();
	}
	
	public void paint(GraphicsContext gc) {
		gc.drawImage(this.image, this.x, this.y);
	}
	
	public void move(Direction direction) {
		double pixelPerIntervall = this.speed * (this.intervall / 1000.0);
		// System.out.println("pixelPerIntervall: " + pixelPerIntervall);
		// 0.7 ~ sin(45°)
		switch (direction) {
			case NORTH: y-=pixelPerIntervall;break;
			case NORTH_EAST: y-=pixelPerIntervall*0.7;x+=pixelPerIntervall*0.7;
			case EAST: x+=pixelPerIntervall;break;
			case SOUTH_EAST: y+=pixelPerIntervall*0.7; x+=pixelPerIntervall*0.7;
			case SOUTH: y+=pixelPerIntervall;break;
			case SOUTH_WEST: y+=pixelPerIntervall*0.7; x-=pixelPerIntervall*0.7;
			case WEST: x-=pixelPerIntervall;break;
			case NORTH_WEST: y-=pixelPerIntervall*0.7; x-=pixelPerIntervall*0.7;
			default: break;
		}
	}
	
	/************************************************************************
	 * 
	 *                   Getter- und Setter Methods
	 * 
	 ************************************************************************/
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getIntervall() {
		return intervall;
	}

	public void setIntervall(int intervall) {
		this.intervall = intervall;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getDemageDegree() {
		return demageDegree;
	}

	public void setDemageDegree(int demageDegree) {
		this.demageDegree = demageDegree;
	}

	public boolean isDemaged() {
		return isDemaged;
	}

	public void setDemaged(boolean isDemaged) {
		this.isDemaged = isDemaged;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	
}
