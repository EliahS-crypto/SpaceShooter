package at.htlle.shooter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import at.htlle.shooter.gameobjects.Asteroid;
import at.htlle.shooter.gameobjects.Direction;
import at.htlle.shooter.gameobjects.IScoreable;
import at.htlle.shooter.gameobjects.Rocket;
import at.htlle.shooter.gameobjects.SpaceShip;
import javafx.scene.canvas.Canvas;

public class GameSceneHandler implements IGameSceneHandler {

	private Random rnd;
	private Canvas canvas;
	private List<Asteroid> asteroiden;
	private List<Rocket> raketen;
	private SpaceShip enterprise;
	private int gameScore = 0;
	private IStatusBar statusPane;
	private IGameConfig cfg;
	


	public GameSceneHandler(IGameConfig cfg, Canvas canvas, IStatusBar statusPane, int gameScore) {
		super();
		this.canvas = canvas;
		this.gameScore = gameScore;
		this.statusPane = statusPane;
		this.cfg = cfg;
	}

	public List<Asteroid> getAsteroiden() {
		return asteroiden;
	}

	public List<Rocket> getRaketen() {
		return raketen;
	}

	public SpaceShip getEnterprise() {
		return enterprise;
	}

	@Override
	public void initializeGame() {
		rnd = new Random();
		initializeSpaceShip();
		initializeAsteroids();
		initializeRockets();
	}

	@Override
	public void handleGameScene() {

		canvas.getGraphicsContext2D().clearRect(0, 0, cfg.getScreenWidth(), cfg.getScreenHeight());
		canvas.getGraphicsContext2D().fillRect(0, 0, cfg.getScreenWidth(), cfg.getScreenHeight());

		handleAsteroids(canvas);
		handleRockets(canvas);
		handleCollisions();

		handleSpaceShip();

		handleGameScore(asteroiden); // CHANGES

	}
	
	private void initializeSpaceShip() {
		enterprise = new SpaceShip(cfg, 0, cfg.getScreenHeight() / 2, Direction.EAST);
	}

	private void initializeAsteroids() {
		asteroiden = new LinkedList<Asteroid>();
		
		for (int i = 0; i < cfg.getAsteroidNum(); i++) {
			this.asteroiden.add(getNewRandomizedAsteroid());
		}
	}
	
	private void initializeRockets() {
		raketen = new LinkedList<Rocket>();
	}

	private void handleSpaceShip() {
		enterprise.paint(canvas.getGraphicsContext2D());
	}

	private void handleCollisions() {
		// Kollisionen Asteroid - Raumschiff entdecken
		for (Asteroid a : asteroiden) {
			if (enterprise.getBounds().intersects(a.getBounds()) && a.isExploded() == false) {
				// Treffer
				enterprise.damage();
			}
		}

		// Kollisionen Asteroid - Raumschiff entdecken
		for (Rocket r : raketen) {
			for (Asteroid a : asteroiden) {
				if (r.getBounds().intersects(a.getBounds())) {
					// Treffer
					a.explode();
					r.setVisible(false); // EXT-CHANGES
				}
			}
		}
	}

	private void handleRockets(Canvas canvas) {
		List<Rocket> oldRockets = new ArrayList<>();
		for (Rocket r : raketen) {

			// EXT-CHANGES - Start
			if (r.isVisible() == false) {
				oldRockets.add(r);
			} else {
				r.move(Direction.EAST);
				r.paint(canvas.getGraphicsContext2D());
			}
			// EXT-CHANGES - End
		}

		// Vorbeigeflogene Raketen löschen
		raketen.removeAll(oldRockets);
		for (Rocket r : oldRockets) {
			if (cfg.isDebugOn()) System.out.println("Loesche " + r.toString());
			r = null; // Garbage Collector kann ihn wegröumen
		}
	}

	private void handleAsteroids(Canvas canvas) {
		List<Asteroid> oldAsteroids = new ArrayList<>();
		for (Asteroid a : asteroiden) {
			a.move(Direction.WEST);
			a.paint(canvas.getGraphicsContext2D());

			if (a.isVisible() == false) {
				oldAsteroids.add(a);
			}

			// Explodierte Asteroiden
			if (a.isExploded() && a.getExplosionDistance() > 30) {
				oldAsteroids.add(a);
			}
		}

		// Vorbeigeflogene Asteroiden löschen
		asteroiden.removeAll(oldAsteroids);

		for (Asteroid a : oldAsteroids) {
			if (cfg.isDebugOn()) System.out.println("Lösche " + a.toString());
			a = null; // Garbage Collector kann ihn wegräumen

			asteroiden.add(getNewRandomizedAsteroid());
		}

	}

	// CHANGES - Start
	private void handleGameScore(List<? extends IScoreable> scoreableObjects) {
		for (IScoreable scoreable : scoreableObjects) {
			if (scoreable.hasScored()) {
				gameScore += scoreable.getScore();
				scoreable.resetScored();
				if (cfg.isDebugOn()) System.out.println("GameScore: " + gameScore + " / " + (Asteroid) scoreable);
				statusPane.setScore(gameScore);
			}
		}
	}

	private Asteroid getNewRandomizedAsteroid() {

		int x = cfg.getScreenWidth() + rnd.nextInt(cfg.getScreenWidth());
		int y = rnd.nextInt(cfg.getScreenHeight());
		int s = rnd.nextInt(cfg.getAsteroidMaxSpeed() - cfg.getAsteroidMinSpeed()) + cfg.getAsteroidMinSpeed();

		// Zufällige Erzeugung von eines der 3 unterschiedlichen Asteroiden
		String asteroidImg = cfg.getAsteroidImgName() + (rnd.nextInt(3) + 1);
		// CHANGES - Start
		Asteroid newAsteroid = new Asteroid(asteroidImg, x, y, cfg.getSpriteMoveIntervall(), s);
		newAsteroid.setScore(cfg.getAsteroidScoreValue());

		return newAsteroid;
	}

}
