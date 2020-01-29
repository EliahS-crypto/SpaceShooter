package at.htlle.shooter.cfg;

public interface IConfigProperties {

	/**
	 * Liefert Propertywert vom Typ String zur�ck
	 * 
	 * @param propType Property
	 * @return String-Wert des Properties
	 */
	public String getStringProperty(CfgPropertEnum propType);
	
	/**
	 * Liefert Propertywert vom Typ Integer zur�ck
	 * 
	 * @param propType Config Property
	 * @return Integer-Wert des Properties
	 */
	public Integer getIntProperty(CfgPropertEnum propType);
	
	/**
	 * Liefert Propertywert vom Typ Boolean
	 * 
	 * @param prpType Config Property
	 * @return Boolean - Wert des Properties
	 */
	public Boolean getBoolProperty(CfgPropertEnum prpType);
}
