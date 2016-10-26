package javaCourse.periodicEdition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaCourse.periodicEdition.entity.Payment;



/**
 * класс для взаимодействии с таблицей платежей БД, большинство методов "на
 * вырост" в приложении пока используется только create
 * 
 * @author Vladimir Pliuta
 *
 */
public class PaymentDAO extends AbstractDAO<Payment> {
	private static final String SQL_SELECT_ALL_READER = "SELECT * FROM payment";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM payment WHERE id_payment=?";
	private static final String SQL_INSERT = "INSERT INTO payment (id_reader, coast) VALUES (?,?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM payment WHERE id_payment=?";

	public PaymentDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Payment> findAll() {
		List<Payment> payments = new ArrayList<>();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_ALL_READER);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Payment payment = new Payment();
				payment.setId(resultSet.getInt("id_payment"));
				payment.setIdReader(resultSet.getInt("id_reader"));
				payment.setCoast(resultSet.getDouble("coast"));
				payments.add(payment);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return payments;
	}

	@Override
	public Payment findById(int id) {
		Payment payment = new Payment();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_BY_ID);
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				payment.setId(resultSet.getInt("id_payment"));
				payment.setIdReader(resultSet.getInt("id_reader"));
				payment.setCoast(resultSet.getDouble("coast"));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or note failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return payment;
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
	public boolean create(Payment payment) {
		PreparedStatement preparedStatement = getPrepareStatement(SQL_INSERT);
		boolean flag = false;
		try {
			preparedStatement.setInt(1, payment.getIdReader());
			preparedStatement.setDouble(2, payment.getCoast());
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
