package at.htlle.shooter;

import at.htlle.shooter.gameobjects.SpaceShip;
import javafx.animation.AnimationTimer;

public class GameAnimationTimer extends AnimationTimer {

	long prevTime;
	private int frameCounter = 0;
	private long prevSecTimestamp = 0;

	private IGameSceneHandler gameScHandler; 
	private IStatusBar statusPane;
	
	public GameAnimationTimer(IGameSceneHandler gameSceneHandler, IStatusBar statusPane) {
		this.gameScHandler = gameSceneHandler;
		this.statusPane = statusPane;
	}
	/**
	 * Entscheidet ob das aktuelle Bild für die gegebene FPS gerechnet werden soll
	 * oder nicht
	 */
	private boolean needsHandling_FPS(int desiredFrameRatePerSecond) {
		long currentNanoTime = System.nanoTime();

		// Framerate = x Frames / second.
		double timePerFrame_s = 1.0 / desiredFrameRatePerSecond;
		long timePerFrame_ns = (long) (timePerFrame_s * 1e9);

		if (prevTime + timePerFrame_ns < currentNanoTime) {
			prevTime = currentNanoTime;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void handle(long currentNanoTime) {
	
		long currentMiliSecondTime = System.currentTimeMillis();
		// EXT-CHANGES - Start
		if (currentMiliSecondTime - prevSecTimestamp < 1000) {
			frameCounter++;
		} else {
			prevSecTimestamp = currentMiliSecondTime;
			statusPane.setFPS(frameCounter);
			frameCounter = 0;
		}
		
		gameScHandler.handleGameScene();
		
		// EXT-CHANGES - End
		SpaceShip enterprise = gameScHandler.getEnterprise();
		
		if (enterprise.isDestroyed())
			this.stop();	

	}
}
