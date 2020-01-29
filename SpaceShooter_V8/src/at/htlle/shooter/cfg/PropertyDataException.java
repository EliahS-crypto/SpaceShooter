package at.htlle.shooter.cfg;

/**
 * Exception für Fehler beim Lesen von Properties
 * 
 * @author uweko
 *
 */
public class PropertyDataException extends Exception {

	private String propertyName = null;
	private DataType expectedDataType = null;
	
	/**
	 * Konstruktor zum Setzen des Properties, welches nicht existiert/gefunden wurde
	 * .
	 * @param propertyName Name des nicht gefundenen Properties
	 */
	public PropertyDataException(String propertyName) {
		this.propertyName = propertyName;
	}
	
	/**
	 * Konsturktor zum Setzen des Properties, sowie den erwarteten Datentyp,
	 * Konstruktor wird verwendet, wenn Fehler ein bei der Konvertierung in den erwarteten Datentyp
	 * auftritt.
	 * 
	 * @param propertyName
	 * @param expectedDataType
	 */
	public PropertyDataException(String propertyName, DataType expectedDataType) {
		this(propertyName);
		this.expectedDataType = expectedDataType;
	}

	@Override
	public String getMessage() {
		if (this.propertyName != null && this.expectedDataType == null) {
			return ("Property or value for Property " + this.propertyName + " does NOT exist!");
		} else if (this.expectedDataType != null) {
			return ("Value of Property " + this.propertyName + "has not Expected Datatyp " + this.expectedDataType);
		} else return super.getMessage();
	}
	
	
}
