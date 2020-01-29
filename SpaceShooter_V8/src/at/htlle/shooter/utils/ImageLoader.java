package at.htlle.shooter.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class ImageLoader {
	public static String IMAGE_FILE_PATH = "./images/";
	
	private static String IMAGE_FILE_PATH_TEMPLATE = IMAGE_FILE_PATH + "/%s.png";
	
	public static Image loadImage(String imageName) {
		
		InputStream ios = null;
		Image image = null;
		String imagePath = String.format(IMAGE_FILE_PATH_TEMPLATE, imageName);
		try {
			
			ios = new FileInputStream(imagePath);
			image =  new Image(ios);
		
		} catch (FileNotFoundException e) {
			System.err.println("SpriteImage Path not found: " + imagePath);
			System.exit(-1);
		}
		return image;
	}

}
