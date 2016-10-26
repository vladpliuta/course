package javaCourse.periodicEdition.command;

import javax.servlet.http.HttpServletRequest;

import javaCourse.periodicEdition.resource.ConfigurationManager;



/**
 * команда выхода из системы и уничтожения сессии
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
