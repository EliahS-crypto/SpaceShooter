package at.htlle.shooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.htlle.shooter.cfg.CfgPropertiesFromFile;
import at.htlle.shooter.cfg.CfgSingletonfactory;
import at.htlle.shooter.gameobjects.Asteroid;
import at.htlle.shooter.gameobjects.Direction;
import at.htlle.shooter.gameobjects.IScoreable;
import at.htlle.shooter.gameobjects.Rocket;
import at.htlle.shooter.gameobjects.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Starter extends Application {
	private int gameScore = 0;

	private IGameConfig cfg = null;
	private IGameSceneHandler gameScHandler = null;
	private UIControlHandler uiControlHandler = null;
	
	public static String GAME_CFG_FILE = "game-config.prperties";

	public static void main(String[] args) {
		Application.launch(Starter.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// SpaceShooter-V8.1 - Start
		// SIngelton von cfgPropertiesFrom File 
		cfg = CfgSingletonfactory.getCfgPropertiesFromFile(GAME_CFG_FILE)
		
		BorderPane root = new BorderPane();
		primaryStage.setTitle("SpaceShooter - Hutter");
		Canvas canvas = new Canvas(cfg.getScreenWidth()
				,cfg.getScreenHeight());
		canvas.setFocusTraversable(true);
				
		// SpaceShooter-V8.2 - End

		// CHANGES - Start
		IStatusBar statusPane = new FXMLStatusBar();

		// SpaceShooter-V8.3 - Start
		gameScHandler = new GameSceneHandler(cfg, canvas, statusPane, gameScore);
		gameScHandler.initializeGame();
		
		// SpaceShooter-V8.2 - Start
		uiControlHandler = new UIControlHandler(cfg,
				gameScHandler.getEnterprise(), 
				gameScHandler.getRaketen());

		canvas.setOnKeyPressed(uiControlHandler);
		canvas.setOnKeyReleased(uiControlHandler);
		canvas.setOnKeyTyped(uiControlHandler);

		
		root.setTop(statusPane.getPane());
		root.setCenter(canvas);
		// CHANGES - End

		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		// CHANGES SpaceShooter-V8.4 - Start
		// Initiate GameAnimationTimer and start it
		GameAnimationTimer gameTimer = new GameAnimationTimer(gameScHandler, statusPane);
		
		gameTimer.start();
		
		// SpaceShooter-V8.4 - END
	}

}
