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

/**
 * сложная команда по данным из jsp записывает в БД новую подписку, расчитывает
 * стоимость с учетом периода и скидок, и записывает в БД новый платеж
 * 
 * надо бы похорошему на несколько разбить
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
			Subscription subscription = new Subscription(idReader, issn, period);
			boolean createSubscription = subscriptionDAO.create(subscription);

			int discount = 0;
			switch (period) {
			case 1:
				discount = 0;
				break;
			case 3:
				discount = periodicEdition.getDiscountQuarteryear();
				break;
			case 6:
				discount = periodicEdition.getDiscountHalfyear();
				break;
			case 12:
				discount = periodicEdition.getDiscountHalfyear();
				break;
			}
			double coast = (double) (periodicEdition.getMonthPrice() * period * (100 - discount) / 100);

			Payment payment = new Payment(idReader, coast);

			boolean createPayment = paymentDAO.create(payment);

			if (createPayment & createSubscription) {
				page = ConfigurationManager.getProperty("page.userPayment");
				request.setAttribute("coast", coast);
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
