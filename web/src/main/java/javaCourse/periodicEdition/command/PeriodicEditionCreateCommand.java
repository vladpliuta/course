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


/**
 * команда создания нового периодического издания с извлечением данных из jsp и
 * записью их в БД
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionCreateCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;

		String idString = request.getParameter("ISSN");
		int id = Integer.valueOf(idString);

		String title = request.getParameter("title");

		String shortDescription = request.getParameter("shortDescription");

		String monthPeriodicityString = request.getParameter("monthPeriodicity");
		int monthPeriodicity = Integer.valueOf(monthPeriodicityString);

		String monthPriceString = request.getParameter("monthPrice");
		double monthPrice = Double.valueOf(monthPriceString);

		String discountQuarteryearString = request.getParameter("discountQuarteryear");
		int discountQuarteryear = Integer.valueOf(discountQuarteryearString);

		String discountHalfyearString = request.getParameter("discountHalfyear");
		int discountHalfyear = Integer.valueOf(discountHalfyearString);

		try {
			page = ConfigurationManager.getProperty("page.periodicEditionsAdmin");
			Connection conn = DataSource.getInstance().getConnection();

			conn.setAutoCommit(false);
			PeriodicEdition periodicEdition = new PeriodicEdition(id, title, shortDescription, monthPeriodicity,
					monthPrice, discountQuarteryear, discountHalfyear);
			PeriodicEditionDAO periodicEditionDAO = new PeriodicEditionDAO(conn);
			boolean create = periodicEditionDAO.create(periodicEdition);
			if (create) {
				List<PeriodicEdition> periodicEditions = new PeriodicEditionDAO(conn).findAll();
				request.setAttribute("periodicEditionsList", periodicEditions);
			}
			conn.commit();
			conn.close();
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
