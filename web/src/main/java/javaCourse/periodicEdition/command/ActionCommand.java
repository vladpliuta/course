package javaCourse.periodicEdition.command;

import javax.servlet.http.HttpServletRequest;

/**
 * command interface
 * 
 * @author Vladimir Pliuta
 *
 */
public interface ActionCommand {
	String execute(HttpServletRequest request);
}
