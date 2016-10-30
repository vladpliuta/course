package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javaCourse.periodicEdition.connect.DataSource;
import javaCourse.periodicEdition.controller.RequestContent;
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
	public String execute(RequestContent requestContent) {
		String page = null;
		try {
			page = ConfigurationManager.getProperty("page.usersList");
			Connection conn = DataSource.getInstance().getConnection();
			List<Reader> usersList = new ReaderDAO(conn).findAll();
			requestContent.setRequestAttribute("usersList", usersList);
			conn.close();
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
