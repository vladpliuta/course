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
 * command to create a new periodic edition
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionCreateCommand implements ActionCommand {

	@Override
	public String execute(RequestContent requestContent) {
		String page = null;
		String idString = requestContent.getRequestParameter("ISSN");
		int id = Integer.valueOf(idString);

		String title = requestContent.getRequestParameter("title");

		String shortDescription = requestContent.getRequestParameter("shortDescription");

		String monthPeriodicityString = requestContent.getRequestParameter("monthPeriodicity");
		int monthPeriodicity = Integer.valueOf(monthPeriodicityString);

		String monthPriceString = requestContent.getRequestParameter("monthPrice");
		double monthPrice = Double.valueOf(monthPriceString);

		String discountQuarteryearString = requestContent.getRequestParameter("discountQuarteryear");
		int discountQuarteryear = Integer.valueOf(discountQuarteryearString);

		String discountHalfyearString = requestContent.getRequestParameter("discountHalfyear");
		int discountHalfyear = Integer.valueOf(discountHalfyearString);

		PeriodicEdition periodicEdition = new PeriodicEdition(id, title, shortDescription, monthPeriodicity, monthPrice,
				discountQuarteryear, discountHalfyear);
		

		try {
			page = ConfigurationManager.getProperty("page.periodicEditionsAdmin");

			Connection conn = DataSource.getInstance().getConnection();
			conn.setAutoCommit(false);
			PeriodicEditionDAO periodicEditionDAO = new PeriodicEditionDAO(conn);
			boolean create = periodicEditionDAO.create(periodicEdition);
			if (create) {
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
