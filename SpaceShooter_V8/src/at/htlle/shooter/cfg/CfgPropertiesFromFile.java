package at.htlle.shooter.cfg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import at.htlle.shooter.IGameConfig;

/**
 * Ladet die Configurationsproperties (definiert über {@link CfgPropertEnum}) von einem
 * Properties File.
 * 
 * @author elili
 *
 */
public class CfgPropertiesFromFile implements IGameConfig{

	public static String EXC_MSG_FILE_NOT_FOUND_TEMPLATE = "property file: %s not found!";
	public static String EXC_MSG_IO_TEMPLATE = "property file: %s could not be read!";
	
	private Map<CfgPropertEnum, Object> propertyMap;
	public static CfgPropertiesFromFile object = null;
	private static String propertyFile = "";
	
	/**
	 * Konstuktukor 
	 * @param propertyFile Name des Property Files (inklusive .properties Endung)
	 */
	private CfgPropertiesFromFile(String propertyFile) {
		// https://mkyong.com/java/java-properties-file-examples/
		try (InputStream ios = new FileInputStream(propertyFile)) {
			propertyMap = new HashMap<CfgPropertEnum, Object>();
			
			Properties prop = new Properties();
			prop.load(ios);
			
			fillPropertyMap(prop);
			
			
		} catch (FileNotFoundException e) {
			System.out.println(String.format(EXC_MSG_FILE_NOT_FOUND_TEMPLATE, propertyFile));
			System.exit(-1);
		} catch (IOException e) {
			System.out.println(String.format(EXC_MSG_IO_TEMPLATE, propertyFile));
			System.exit(-2);
		} catch (PropertyDataException e) {
			System.out.println(e.getMessage());
			System.exit(-3);
		}
	}
	public static CfgPropertiesFromFile Singleton() {
		
		if(object ==null) {
			object = new CfgPropertiesFromFile(propertyFile);
			return object;
		}else {
			return object;
		}
		
	}
	
	/**
	 * (S3) - 2 Punkte
	 * 
	 * Befüllt die Klassenvariable propertyMap mit den gelesenen Property - Werten aus
	 * der Properties Datei, wobei mit Hilfe der checkDataType Methode, überprüft wird:
	 *  (1) ob das Property überhaupt definiert ist, bzw. einen Wert hat
	 *  (2) ob der gelesene Property Value dem im ConfigProperty Enum zugewiesenen Datentyp 
	 *      entspricht. 
	 *  Trifft eine der beiden Fälle nicht zu, so muss eine PropertyDataException geworfen werden!
	 *  
	 * @param prop
	 */
	private void fillPropertyMap(Properties prop) throws PropertyDataException {
		// https://www.baeldung.com/java-enum-iteration
		
		for (CfgPropertEnum confProp : CfgPropertEnum.values()) {
			String propName =confProp.getPropertyName();
			String propStrgValue = prop.getProperty(propName);
			
			if (propStrgValue == null) throw new PropertyDataException(propName);
			
			Object propValue = validateDataType(propStrgValue, confProp);
			
			propertyMap.put(confProp, propValue);
		}
	}
	
	/**
	 * (S3) - 2 Punkte
	 * 
	 * Überprüft, ob der Wert des Properties, dem hierfür definierten Datentyp entspricht (definiert
	 * im {@link CfgPropertEnum}. Ist dies der Fall, so soll der Wert in diesen Datentyp umgewandelt
	 * und retourniert werden. Scheitert die Konvertierung, so muss eine PropertyDataException geworfen
	 * werden!
	 * 
	 * Überprüft werden sollen die Datentypen Integer, Boolean und String
	 * 
	 * @param value propertyWert
	 * @param propType ConfigPropertyType
	 * @throws PropertyDataException
	 */
	private Object validateDataType(String value, CfgPropertEnum propType) throws PropertyDataException {
		try {
			switch(propType.getDataType()) {
			
			case INTEGER : return Integer.parseInt(value);
			
			case BOOLEAN : return Boolean.parseBoolean(value);
			
			case DOUBLE : return Double.parseDouble(value);
			
			case STRING : return value;
			
			default: return value;
			
			}
			/*if (propType.getDataType() == DataType.INTEGER) {
				//Switch case für Enums
					Integer intValue = Integer.parseInt(value);
					return intValue;
			} else if (propType.getDataType() == DataType.BOOLEAN) {
				Boolean boolValue = Boolean.parseBoolean(value);
				return boolValue;
			}else if(propType.getDataType() == DataType.DOUBLE){
				Double doubleValue = Double.parseDouble(value);
				return doubleValue;
			} else return value;
			*/
			
		} catch (Exception ex) {
			throw new PropertyDataException(propType.getPropertyName(), propType.getDataType());
		}
	}
	
	/**
	 * (S1) - 1 Punkt
	 */
	@Override
	public String getStringProperty(CfgPropertEnum propType) {
	
		return (String)propertyMap.get(propType);
	}

	/**
	 * (S1) - 1 Punkt
	 */
	@Override
	public Integer getIntProperty(CfgPropertEnum propType) {
		
		return (Integer)propertyMap.get(propType);
	}

	/**
	 * (S1) - 1 Punkt
	 */
	@Override
	public Boolean getBoolProperty(CfgPropertEnum propType) {
		
		return (Boolean)propertyMap.get(propType);
	}

	public Map<CfgPropertEnum, Object> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<CfgPropertEnum, Object> propertyMap) {
		this.propertyMap = propertyMap;
	}
	
	public int getSpriteMoveIntervall() {
		return (int)((1.0/getIntProperty(CfgPropertEnum.FPS)*1000));
	}

}
