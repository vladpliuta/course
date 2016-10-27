package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javaCourse.periodicEdition.connect.DataSource;
import javaCourse.periodicEdition.dao.PeriodicEditionDAO;
import javaCourse.periodicEdition.entity.PeriodicEdition;
import javaCourse.periodicEdition.resource.ConfigurationManager;
import javaCourse.periodicEdition.services.CreatePeriodicEdition;

/**
 * command to create a new periodic edition
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionCreateCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;

		PeriodicEdition periodicEdition = CreatePeriodicEdition.createPeriodicEdition(request);

		try {
			page = ConfigurationManager.getProperty("page.periodicEditionsAdmin");

			Connection conn = DataSource.getInstance().getConnection();
			conn.setAutoCommit(false);
			PeriodicEditionDAO periodicEditionDAO = new PeriodicEditionDAO(conn);
			boolean create = periodicEditionDAO.create(periodicEdition);
			if (create) {
				List<PeriodicEdition> periodicEditions = new PeriodicEditionDAO(conn).findAll();
				request.setAttribute("periodicEditionsList", periodicEditions);
			}
			conn.commit();
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
