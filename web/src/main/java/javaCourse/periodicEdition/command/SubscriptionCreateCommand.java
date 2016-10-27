package javaCourse.periodicEdition.command;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javaCourse.periodicEdition.connect.DataSource;
import javaCourse.periodicEdition.dao.PaymentDAO;
import javaCourse.periodicEdition.dao.PeriodicEditionDAO;
import javaCourse.periodicEdition.dao.SubscriptionDAO;
import javaCourse.periodicEdition.entity.Payment;
import javaCourse.periodicEdition.entity.PeriodicEdition;
import javaCourse.periodicEdition.entity.Subscription;
import javaCourse.periodicEdition.resource.ConfigurationManager;
import javaCourse.periodicEdition.services.CalculateCoast;

/**
 * command to create a new subscription
 * 
 * @author Vladimir Pliuta
 *
 */
public class SubscriptionCreateCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String issnString = request.getParameter("idPeriodicEdition");
		int issn = Integer.valueOf(issnString);
		HttpSession session = request.getSession();
		String idReaderString = (String) session.getAttribute("userId");
		int idReader = Integer.valueOf(idReaderString);
		String periodString = request.getParameter("period");
		int period = Integer.valueOf(periodString);

		try {
			Connection conn = DataSource.getInstance().getConnection();
			conn.setAutoCommit(false);

			PeriodicEditionDAO periodicEditionDAO = new PeriodicEditionDAO(conn);
			SubscriptionDAO subscriptionDAO = new SubscriptionDAO(conn);
			PaymentDAO paymentDAO = new PaymentDAO(conn);

			PeriodicEdition periodicEdition = periodicEditionDAO.findById(issn);
			double coast = CalculateCoast.calculate(periodicEdition, period);

			Subscription subscription = new Subscription(idReader, issn, period);
			Payment payment = new Payment(idReader, coast);

			boolean createSubscription = subscriptionDAO.create(subscription);
			boolean createPayment = paymentDAO.create(payment);

			if (createPayment & createSubscription) {
				page = ConfigurationManager.getProperty("page.userPayment");
				request.setAttribute("coast", coast);
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
