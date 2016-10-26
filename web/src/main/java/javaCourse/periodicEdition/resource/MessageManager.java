package javaCourse.periodicEdition.resource;

import java.util.ResourceBundle;

/**
 * класс, извлекающий из properties сообщения
 * 
 * @author Vladimir Pliuta
 *
 */
public class MessageManager {
	private final static ResourceBundle resource = ResourceBundle.getBundle("javaCourse.periodicEdition.resource.messages");

	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resource.getString(key);
	}
}
