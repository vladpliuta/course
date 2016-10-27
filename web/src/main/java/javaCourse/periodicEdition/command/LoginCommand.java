package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javaCourse.periodicEdition.resource.ConfigurationManager;
import javaCourse.periodicEdition.resource.MessageManager;

/**
 * бизнес-логика команды на аутентификацию
 * 
 * @author Vladimir Pliuta
 *
 */
public class LoginCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String enterLogin = request.getParameter("login");
		String enterPassword = request.getParameter("password");
		try {
			if (CheckLogin.checkEnter(enterLogin, enterPassword).equals("admin")) {
				page = ConfigurationManager.getProperty("page.admin");
			} else if (CheckLogin.checkEnter(enterLogin, enterPassword).equals("error")) {
				request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				page = ConfigurationManager.getProperty("page.login");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("userId", CheckLogin.checkEnter(enterLogin, enterPassword));
				page = ConfigurationManager.getProperty("page.user");
			}
		} catch (SQLException e) {
			request.getSession().setAttribute("error", e);
			page = ConfigurationManager.getProperty("page.error");
		} catch (IOException e) {
			request.getSession().setAttribute("error", e);
			page = ConfigurationManager.getProperty("page.error");
		} catch (PropertyVetoException e) {
			request.getSession().setAttribute("error", e);
			page = ConfigurationManager.getProperty("page.error");
		}
		return page;
	}
}
