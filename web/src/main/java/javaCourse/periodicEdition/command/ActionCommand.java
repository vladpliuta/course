package javaCourse.periodicEdition.command;

import javax.servlet.http.HttpServletRequest;

/**
 * интерфейс для команд
 * 
 * @author Vladimir Pliuta
 *
 */
public interface ActionCommand {
	String execute(HttpServletRequest request);
}
