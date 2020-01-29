package at.htlle.shooter;

import java.util.List;

import at.htlle.shooter.gameobjects.Direction;
import at.htlle.shooter.gameobjects.Rocket;
import at.htlle.shooter.gameobjects.SpaceShip;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UIControlHandler implements EventHandler<KeyEvent> {

	private List<Rocket> raketen = null;
	private SpaceShip enterprise = null;
	private IGameConfig gc = null;
	
	private static int SPACESHIP_SPEED = 0;
	// Remember if Action-Key is pressed
	private boolean upCursorPressed = false;
	private boolean downCursorPressed = false;
	private boolean leftCursorPressed = false;
	private boolean rightCursorPressed = false;
	private boolean spaceKeyPressed = false;
	private boolean moveSpaceShip = false;
	private Direction spaceShipDirection = Direction.EAST;
	
	// in milliseconds
	private int fireIntervall=500;
	private long lastFired = 0;
	
	
	
	public UIControlHandler(IGameConfig gc, SpaceShip enterprise, List<Rocket> raketen) {
		super();
		this.gc =gc;
		this.enterprise = enterprise;
		this.raketen = raketen;
		SPACESHIP_SPEED = gc.getSpaceShipSpeed();
	}

	@Override
	public void handle(KeyEvent evnt) {
		// EXT-CHANGES - Start
		EventType<KeyEvent> eventType = evnt.getEventType();
		KeyCode keyCode = evnt.getCode();
		
		if (eventType == KeyEvent.KEY_PRESSED) {
			setKeyPressed(keyCode, true);
		} else if (eventType == KeyEvent.KEY_RELEASED) {
			setKeyPressed(keyCode, false);
		} else if (eventType == KeyEvent.KEY_TYPED) {
			if (lastFired == 0 || (System.currentTimeMillis() - lastFired) > fireIntervall) {
				Rocket r = enterprise.fireRocket();
				raketen.add(r);
				spaceKeyPressed = false;
				lastFired = System.currentTimeMillis();
			}
		}

		if (gc.isDebugOn()) System.out.println("EventType/Key: " + eventType.getName() + "/" + keyCode.getName());
		
		if (rightCursorPressed) {
			if (upCursorPressed) {
				spaceShipDirection = Direction.NORTH_EAST;
			} else if (downCursorPressed) {
				spaceShipDirection = Direction.SOUTH_EAST;
			} else spaceShipDirection = Direction.EAST; 
		} else if (leftCursorPressed) {
			if (upCursorPressed) {
				spaceShipDirection = Direction.NORTH_WEST;
			} else if (downCursorPressed) {
				spaceShipDirection = Direction.SOUTH_WEST;
			} else spaceShipDirection = Direction.WEST; 
		} else if (upCursorPressed)
			spaceShipDirection = Direction.NORTH;
		else if (downCursorPressed)
			spaceShipDirection = Direction.SOUTH;
		
		if ( (leftCursorPressed && rightCursorPressed) || (upCursorPressed && downCursorPressed)) {
			leftCursorPressed = rightCursorPressed = upCursorPressed = downCursorPressed = false;
		}
		
		if (gc.isDebugOn()) System.out.println(leftCursorPressed + " | " + upCursorPressed + " | " + downCursorPressed + " | " + rightCursorPressed + " - " + spaceKeyPressed);
		


		moveSpaceShip = (rightCursorPressed || leftCursorPressed || upCursorPressed || downCursorPressed);

		// EXT-CHANGES - END

		// Shift - Double Speed !
		int speed = SPACESHIP_SPEED;
		if (evnt.isShiftDown()) {
			speed = SPACESHIP_SPEED * 2;
		}
		enterprise.setSpeed(speed);
		
		if (moveSpaceShip) enterprise.move(spaceShipDirection);
	}
	
	// EXT-CHANGES
	/**
	 * Set the ...CursorPressed flag for the cursor keys
	 * 
	 * @param keyCode
	 * @param isKeyPressed value for cursorPressed Flag
	 */
	private void setKeyPressed(KeyCode keyCode, boolean isKeyPressed) {
		switch (keyCode) {
		case UP:
			upCursorPressed = isKeyPressed;
			break;

		case DOWN:
			downCursorPressed = isKeyPressed;
			break;

		case LEFT:
			leftCursorPressed = isKeyPressed;
			break;

		case RIGHT:
			rightCursorPressed = isKeyPressed;
			break;

		case SPACE:
			spaceKeyPressed = isKeyPressed;
			break;
		default:
			break;

		}
	}

	public Direction getSpaceShipDirection() {
		return spaceShipDirection;
	}

	public boolean isMoveSpaceShip() {
		return moveSpaceShip;
	}
	
	
	
	

}
