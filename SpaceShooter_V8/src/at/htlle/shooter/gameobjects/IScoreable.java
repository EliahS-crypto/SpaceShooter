package at.htlle.shooter.gameobjects;

public interface IScoreable {
	/**
	 * Setzen der zu vergebenden Punkte für das Objekt
	 * @param value
	 */
	public void setScore(int value);
	
	/**
	 * Können die Punkte (für dieses Objekt) dem Spieler gutgeschrieben werden.
	 * @return true - Punkte können gutgeschrieben werden
	 *         false - Punkte können nicht gutgeschrieben werden
	 */
	public boolean hasScored();
	
	/**
	 * Wurden die Punkte gutgschrieben (mit getScore) muss der "Status" hasScored mit dieser Methode
	 * wieder auf false zurück gesetzt werden.
	 */
	public void resetScored(); 
	
	/**
	 * Punkte die dem Spieler gutgeschrieben werden
	 * @return gutgeschriebene Punkte
	 */
	public int getScore();

}
