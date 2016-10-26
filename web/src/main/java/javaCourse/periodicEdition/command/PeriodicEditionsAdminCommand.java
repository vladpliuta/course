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
 * команда отображения списка всех периодических изданий (извлекая их из БД) для
 * страницы админа
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionsAdminCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		try {
			page = ConfigurationManager.getProperty("page.periodicEditionsAdmin");
			Connection conn = DataSource.getInstance().getConnection();
			List<PeriodicEdition> periodicEditions = new PeriodicEditionDAO(conn).findAll();
			request.setAttribute("periodicEditionsList", periodicEditions);
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
