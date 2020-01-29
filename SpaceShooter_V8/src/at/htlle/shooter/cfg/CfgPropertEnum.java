package at.htlle.shooter.cfg;

public enum CfgPropertEnum {
	
		SHIP_IMG("sprite.spaceship.img", DataType.STRING,"spaceship2"), 
		SCREEN_WIDTH("screen.width", DataType.INTEGER,new Integer(800)),
		SCREEN_HEIGHT("screen.height",DataType.INTEGER,new Integer(600)),
		SHIP_SPEED("space-ship.speed", DataType.INTEGER,new Integer(70)),
		DEBUG_ON("debug.on", DataType.BOOLEAN,false),
		ASTEROID_IMG("sprite.asteroid.img", DataType.STRING, "asteroid"),
		ROCKET_IMG("sprite.rocket.img", DataType.STRING, "missile"),
		ASTEROID_NUM("sprite.asteroid.num",DataType.INTEGER, new Integer(20)),
		ASTEROID_SCORE("sprite.asteroid.score",DataType.INTEGER, new Integer(10)),
		ROCKET_SPEED("sprite.rocket.speed",DataType.INTEGER, new Integer(50)),
		SPACESHIP_SPEED("sprite.spaceship.speed",DataType.INTEGER, new Integer(50)),
		ASTEROID_SPEED_MAX("sprite.asteroid.max.speed",DataType.INTEGER, new Integer(30)),
		ASTEROID_SPEED_MIN("sprite.asteroid.min.speed",DataType.INTEGER, new Integer(20)),
		FPS("fps",DataType.INTEGER,60)	
		;
		
		private String propName;
		private DataType dataType;
		private Object defaultValue; 
		
		CfgPropertEnum(String propName, DataType dataType, Object defaultValue) {
			this.propName = propName;
			this.dataType = dataType;
			this.defaultValue = defaultValue;
		}
		
		/**
		 * Gibt den Propertynamen zurück
		 * @return
		 */
		public  String getPropertyName() {
			return this.propName;
		}
		
		/**
		 * Gibt den Datentyp (als String-Name) zurück
		 * @return
		 */
		public DataType getDataType() {
			return this.dataType;
		}
		
		public Object getDefaultValue() {
			
			
			return defaultValue;
			
			
			
		}
	}



