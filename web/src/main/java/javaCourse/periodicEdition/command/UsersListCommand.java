package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javaCourse.periodicEdition.connect.DataSource;
import javaCourse.periodicEdition.dao.ReaderDAO;
import javaCourse.periodicEdition.entity.Reader;
import javaCourse.periodicEdition.resource.ConfigurationManager;



/**
 * command to display all users for admin page
 * 
 * @author Vladimir Pliuta
 *
 */
public class UsersListCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			page = ConfigurationManager.getProperty("page.usersList");
			Connection conn = DataSource.getInstance().getConnection();
			List<Reader> usersList = new ReaderDAO(conn).findAll();
			request.setAttribute("usersList", usersList);
			conn.close();
		} catch (SQLException e) {
			request.getSession().setAttribute("error", "data base exception");
			page = ConfigurationManager.getProperty("page.error");
		} catch (IOException e) {
			request.getSession().setAttribute("error", "I/O exception");
			page = ConfigurationManager.getProperty("page.error");
		} catch (PropertyVetoException e) {
			request.getSession().setAttribute("error", "property exception");
			page = ConfigurationManager.getProperty("page.error");
		}
		return page;
	}
}
