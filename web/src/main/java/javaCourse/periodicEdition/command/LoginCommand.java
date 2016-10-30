package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import javaCourse.periodicEdition.controller.RequestContent;
import javaCourse.periodicEdition.resource.ConfigurationManager;
import javaCourse.periodicEdition.resource.MessageManager;
import javaCourse.periodicEdition.services.CheckLogin;


/**
 * command for authentication
 * 
 * @author Vladimir Pliuta
 *
 */
public class LoginCommand implements ActionCommand {

	@Override
	public String execute(RequestContent requestContent) {
		String page = null;
		String enterLogin = requestContent.getRequestParameter("login");
		String enterPassword = requestContent.getRequestParameter("password");
		try {
			if ("admin".equals(CheckLogin.checkEnter(enterLogin, enterPassword))) {
				page = ConfigurationManager.getProperty("page.admin");
			} else if ("error".equals(CheckLogin.checkEnter(enterLogin, enterPassword))) {
				requestContent.setRequestAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				page = ConfigurationManager.getProperty("page.login");
			} else {
				requestContent.setSessionAttribute("userId", CheckLogin.checkEnter(enterLogin, enterPassword));
				page = ConfigurationManager.getProperty("page.user");
			}
		} catch (SQLException e) {
			requestContent.setRequestAttribute("error", "data base exception");
			page = ConfigurationManager.getProperty("page.error");
		} catch (IOException e) {
			requestContent.setRequestAttribute("error", "I/O exception");
			page = ConfigurationManager.getProperty("page.error");
		} catch (PropertyVetoException e) {
			requestContent.setRequestAttribute("error", "property exception");
			page = ConfigurationManager.getProperty("page.error");
		}
		return page;
	}
}
