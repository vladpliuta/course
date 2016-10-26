package javaCourse.periodicEdition.resource;

import java.util.ResourceBundle;

/**
 * класс, извлекающий из properties информацию о адресах jsp
 * 
 * @author Vladimir Pliuta
 *
 */
public class ConfigurationManager {
	private final static ResourceBundle resource = ResourceBundle.getBundle("javaCourse.periodicEdition.resource.config");

	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resource.getString(key);
	}
}
