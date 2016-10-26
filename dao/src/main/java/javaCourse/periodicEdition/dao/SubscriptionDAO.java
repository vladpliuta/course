package javaCourse.periodicEdition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaCourse.periodicEdition.entity.Subscription;



/**
 * класс для взаимодействии с таблицей подписок БД, большинство методов "на
 * вырост" в приложении пока используется только create
 * 
 * @author Vladimir Pliuta
 *
 */
public class SubscriptionDAO extends AbstractDAO<Subscription> {
	private static final String SQL_SELECT_ALL_READER = "SELECT * FROM subscription";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM subscription WHERE id_subscription=?";
	private static final String SQL_INSERT = "INSERT INTO subscription (id_reader, ISSN, period) VALUES (?,?,?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM subscription WHERE id_subscription=?";

	public SubscriptionDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Subscription> findAll() {
		List<Subscription> subscriptions = new ArrayList<>();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_ALL_READER);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(resultSet.getInt("id_subscription"));
				subscription.setIdReader(resultSet.getInt("id_reader"));
				subscription.setISSN(resultSet.getInt("ISSN"));
				subscription.setPeriod(resultSet.getInt("period"));
				subscriptions.add(subscription);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return subscriptions;
	}

	@Override
	public Subscription findById(int id) {
		Subscription subscription = new Subscription();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_BY_ID);
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				subscription.setId(resultSet.getInt("id_subscription"));
				subscription.setIdReader(resultSet.getInt("id_reader"));
				subscription.setISSN(resultSet.getInt("ISSN"));
				subscription.setPeriod(resultSet.getInt("period"));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or note failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return subscription;
	}

	@Override
	public boolean delete(int id) {
		PreparedStatement preparedStatement = getPrepareStatement(SQL_DELETE_BY_ID);
		boolean flag = false;
		try {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (not id): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return flag;
	}

	@Override
	public boolean create(Subscription subscription) {
		PreparedStatement preparedStatement = getPrepareStatement(SQL_INSERT);
		boolean flag = false;
		try {
			preparedStatement.setInt(1, subscription.getIdReader());
			preparedStatement.setInt(2, subscription.getISSN());
			preparedStatement.setInt(3, subscription.getPeriod());
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (not insert): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return flag;
	}

}
