package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import javaCourse.periodicEdition.connect.DataSource;
import javaCourse.periodicEdition.controller.RequestContent;
import javaCourse.periodicEdition.dao.PeriodicEditionDAO;
import javaCourse.periodicEdition.entity.PeriodicEdition;
import javaCourse.periodicEdition.resource.ConfigurationManager;



/**
 * command to display all periodic editions for user page
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionsUserCommand implements ActionCommand {

	@Override
	public String execute(RequestContent requestContent) {
		String page = null;
		try {
			page = ConfigurationManager.getProperty("page.periodicEditionsUser");
			Connection conn = DataSource.getInstance().getConnection();
			List<PeriodicEdition> periodicEditions = new PeriodicEditionDAO(conn).findAll();
			conn.close();
			requestContent.setRequestAttribute("periodicEditionsList", periodicEditions);
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
