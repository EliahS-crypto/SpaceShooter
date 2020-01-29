package at.htlle.shooter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameConfigProperties implements IGameConfig {

	//public static final String PROPERTY_SCREEN_WIDTH = "screen.width";
	//public static final String PROPERTY_SCREEN_HEIGHT = "screen.height";
	//public static final String PROPERTY_ASTEROID_IMG_NAME = "img.name.asteroid";
	//public static final String PROPERTY_ROCKET_IMG_NAME = "img.name.rocket";
	//public static final String PROPERTY_SPACESHIP_IMG_NAME = "img.name.spaceship";
	//public static final String PROPERTY_FPS = "fps";
	//public static final String PROPERTY_ASTEROID_SPEED_MIN = "sprite.astreoid.min.speed";
	//public static final String PROPERTY_ASTEROID_SPEED_MAX = "sprite.asteroid.max.speed";
	//public static final String PROPERTY_SPACESHIP_SPEED = "sprite.spaceship.speed";
	//public static final String PROPERTY_ROCKET_SPEED = "sprite.rocket.speed";
	//public static final String PROPERTY_ASTEROID_SCORE = "sprite.asteroid.score";
	//public static final String PROPERTY_ASTEROID_NUM = "sprite.asteroid.num";

	//public static final int DEFAULT_SCREEN_WIDTH = 800;
	//public static final int DEFAULT_SCREEN_HEIGHT = 600;
	//public static final String DEFAULT_ASTEROID_IMG_NAME = "sprite.asteroid";
	//public static final String DEFAULT_ROCKET_IMG_NAME = "sprite.rocket";
	//public static final String DEFAULT_SPACESHIP_IMG_NAME = "sprite.spaceship";

	//public static final int DEFAULT_FPS = 60;
	//public static final int DEFAULT_SPACESHIP_SPEED = 70;
	//public static final int DEFAULT_ROCKET_SPEED = 50;
	//public static final int DEFAULT_ASTEROID_MAX_SPEED = 30;
	//public static final int DEFAULT_ASTEROID_MIN_SPEED = 20;
	//public static final int DEFAULT_ASTEROID_SCORE_VALUE = 10;
	//public static final int DEFAULT_ASTEROID_NUM = 20;
	//public static final boolean DEFAULT_DEBUG_ON = false;

	private Properties props = new Properties();

	private String configPropertiesFile = "game-config.properties";

	// Width and Height of Game Screen
	private int screenWidth;
	private int screenHeight;
	
	// Imagenames of the Sprites stored in the root directory
	private String asteroidImgName;
	private String rocketImgName;
	private String spaceShipImgName;

	// Frames per Second
	private int maxFPS;
	// MoveIntervall (ms) -> fps / 1000
	private int spriteMoveIntervall;

	// Sprite - Speed Settings (in Pixel per Second)
	private int spaceShipSpeed;
	private int rocketSpeed;
	private int asteroidMaxSpeed;
	private int asteroidMinSpeed;

	// Score - Values for scoreable Sprites
	private int asteroidScoreValue;
	private int asteroidNum;

	private boolean debugOn = false;
	
	


	public GameConfigProperties(String configPropertiesFile) throws Exception {
		this.configPropertiesFile = configPropertiesFile;

		try (InputStream is = new FileInputStream(configPropertiesFile)) {
			
			props.load(is);

			screenWidth = getIntPropertyValue(PROPERTY_SCREEN_WIDTH, DEFAULT_SCREEN_WIDTH);
			screenHeight = getIntPropertyValue(PROPERTY_SCREEN_HEIGHT, DEFAULT_SCREEN_HEIGHT);
			asteroidImgName = getStringPropertyValue(PROPERTY_ASTEROID_IMG_NAME, DEFAULT_ASTEROID_IMG_NAME);
			rocketImgName = getStringPropertyValue(PROPERTY_ROCKET_IMG_NAME, DEFAULT_ROCKET_IMG_NAME);
			spaceShipImgName = getStringPropertyValue(PROPERTY_SPACESHIP_IMG_NAME, DEFAULT_SPACESHIP_IMG_NAME);

			asteroidMinSpeed = getIntPropertyValue(PROPERTY_ASTEROID_SPEED_MIN, DEFAULT_ASTEROID_MIN_SPEED);
			asteroidMaxSpeed = getIntPropertyValue(PROPERTY_ASTEROID_SPEED_MAX, DEFAULT_ASTEROID_MAX_SPEED);
			rocketSpeed = getIntPropertyValue(PROPERTY_ROCKET_SPEED, DEFAULT_ROCKET_SPEED);
			spaceShipSpeed = getIntPropertyValue(PROPERTY_SPACESHIP_SPEED, DEFAULT_SPACESHIP_SPEED);

			asteroidScoreValue = getIntPropertyValue(PROPERTY_ASTEROID_SCORE, DEFAULT_ASTEROID_SCORE_VALUE);
			asteroidNum = getIntPropertyValue(PROPERTY_ASTEROID_NUM, DEFAULT_ASTEROID_NUM);
			
			maxFPS = getIntPropertyValue(PROPERTY_FPS, DEFAULT_FPS);
			spriteMoveIntervall = (int) ((1.0 / maxFPS) * 1000);

			debugOn = getBooleanPropertyValue("debug.on", false);

		} catch (FileNotFoundException | NumberFormatException ex) {
			if (ex instanceof FileNotFoundException) {
				throw new Exception("Config - File: " + configPropertiesFile + " nicht gefunden!");
			} else throw ex;
		}
	}
	

	/**
	 * Liest ein Property mit einem Integer Wert
	 * 
	 * @param propName     Name des Properties
	 * @param defaultValue default Wert, wenn Property nicht existiert
	 * @return
	 * @throws NumberFormatException
	 */
	private int getIntPropertyValue(String propName, int defaultValue) throws NumberFormatException {

		try {
			int intPropValue = defaultValue;

			if (props.containsKey(propName)) {
				intPropValue = Integer.parseInt(props.getProperty(propName, String.valueOf(defaultValue)));
			} else {
				System.out.println("Achtung: Property " + propName + " nicht gefunden/gesetzt - Defaultwert: " + defaultValue
						+ " wird verwendet!");
			}

			return intPropValue;
		} catch (Exception e) {
			throw new NumberFormatException("Property " + propName + " muss eine Ganzzahl sein!");
		}
	}

	private String getStringPropertyValue(String propName, String defaultValue) {

		String propValue = defaultValue;

		if (props.containsKey(propName)) {
			propValue = props.getProperty(propName, String.valueOf(defaultValue));
		} else {
			System.out.println("Achtung: Property " + propName + " nicht gefunden/gesetzt - Defaultwert: " + defaultValue
					+ " wird verwendet!");
		}

		return propValue;
	}

	/**
	 * Liest ein Property mit einem Boolean - Wert
	 * 
	 * @param propName
	 * @param defaultValue
	 * @return
	 */
	private boolean getBooleanPropertyValue(String propName, boolean defaultValue) {
		String propValue = props.getProperty(propName, Boolean.toString(defaultValue));

		return Boolean.parseBoolean(propValue);

	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @return the asteroidImgName
	 */
	public String getAsteroidImgName() {
		return asteroidImgName;
	}

	/**
	 * @return the rocketImgName
	 */
	public String getRocketImgName() {
		return rocketImgName;
	}

	/**
	 * @return the spaceShipImgName
	 */
	public String getSpaceShipImgName() {
		return spaceShipImgName;
	}

	/**
	 * Intervall in milliseconds for the movement of the sprites
	 * 
	 * @return the spriteMoveIntervall
	 */
	public int getSpriteMoveIntervall() {
		return spriteMoveIntervall;
	}

	/**
	 * @return the spaceShipSpeed
	 */
	public int getSpaceShipSpeed() {
		return spaceShipSpeed;
	}

	/**
	 * @return the rocketSpeed
	 */
	public int getRocketSpeed() {
		return rocketSpeed;
	}

	/**
	 * @return the asteroidMaxSpeed
	 */
	public int getAsteroidMaxSpeed() {
		return asteroidMaxSpeed;
	}

	/**
	 * @return the asteroidMinSpeed
	 */
	public int getAsteroidMinSpeed() {
		return asteroidMinSpeed;
	}

	/**
	 * @return the asteroidScoreValue
	 */
	public int getAsteroidScoreValue() {
		return asteroidScoreValue;
	}

	public boolean isDebugOn() {
		return debugOn;
	}

	@Override
	public int getMaxFPS() {
		// TODO Auto-generated method stub
		return maxFPS;
	}


	public int getScreenWidth() {
		return screenWidth;
	}


	public int getScreenHeight() {
		return screenHeight;
	}


	public int getAsteroidNum() {
		return asteroidNum;
	}
	
	

}
