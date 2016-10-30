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
 * command to delete a periodic edition from database
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionDeleteCommand implements ActionCommand {

	@Override
	public String execute(RequestContent requestContent) {
		String page = null;
		String idString = requestContent.getRequestParameter("id");
		int id = Integer.valueOf(idString);

		try {
			page = ConfigurationManager.getProperty("page.periodicEditionsAdmin");
			Connection conn = DataSource.getInstance().getConnection();
			conn.setAutoCommit(false);
			PeriodicEditionDAO periodicEdition = new PeriodicEditionDAO(conn);
			boolean delete = periodicEdition.delete(id);
			if (delete) {
				List<PeriodicEdition> periodicEditions = new PeriodicEditionDAO(conn).findAll();
				requestContent.setRequestAttribute("periodicEditionsList", periodicEditions);
			}
			conn.commit();
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
