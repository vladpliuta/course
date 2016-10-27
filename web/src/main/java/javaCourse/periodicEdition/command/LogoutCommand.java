package javaCourse.periodicEdition.command;

import javax.servlet.http.HttpServletRequest;

import javaCourse.periodicEdition.resource.ConfigurationManager;



/**
 * command out of the system and the destruction of session
 * 
 * @author Vladimir Pliuta
 *
 */
public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("page.index");
		request.getSession().invalidate();
		return page;
	}
}
