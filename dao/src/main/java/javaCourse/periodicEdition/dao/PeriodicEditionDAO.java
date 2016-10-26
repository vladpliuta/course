package javaCourse.periodicEdition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaCourse.periodicEdition.entity.PeriodicEdition;



/**
 * класс для взаимодействии с таблицей периодических изданий БД,
 * 
 * @author Vladimir Pliuta
 *
 */
public class PeriodicEditionDAO extends AbstractDAO<PeriodicEdition> {
	private static final String SQL_SELECT_ALL_READER = "SELECT * FROM periodic_edition";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM periodic_edition WHERE ISSN=?";
	private static final String SQL_INSERT = "INSERT INTO periodic_edition (ISSN, title, short_description, month_periodicity, month_price, discount_quarteryear, discount_halfyear) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM periodic_edition WHERE ISSN=?";

	public PeriodicEditionDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<PeriodicEdition> findAll() {
		List<PeriodicEdition> periodicEditions = new ArrayList<>();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_ALL_READER);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PeriodicEdition periodicEdition = new PeriodicEdition();
				periodicEdition.setId(resultSet.getInt("ISSN"));
				periodicEdition.setTitle(resultSet.getString("title"));
				periodicEdition.setShortDescription(resultSet.getString("short_description"));
				periodicEdition.setMonthPeriodicity(resultSet.getInt("month_periodicity"));
				periodicEdition.setMonthPrice(resultSet.getDouble("month_price"));
				periodicEdition.setDiscountQuarteryear(resultSet.getInt("discount_quarteryear"));
				periodicEdition.setDiscountHalfyear(resultSet.getInt("discount_halfyear"));
				periodicEditions.add(periodicEdition);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return periodicEditions;
	}

	@Override
	public PeriodicEdition findById(int id) {
		PeriodicEdition periodicEdition = new PeriodicEdition();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_BY_ID);
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				periodicEdition.setId(resultSet.getInt("ISSN"));
				periodicEdition.setTitle(resultSet.getString("title"));
				periodicEdition.setShortDescription(resultSet.getString("short_description"));
				periodicEdition.setMonthPeriodicity(resultSet.getInt("month_periodicity"));
				periodicEdition.setMonthPrice(resultSet.getDouble("month_price"));
				periodicEdition.setDiscountQuarteryear(resultSet.getInt("discount_quarteryear"));
				periodicEdition.setDiscountHalfyear(resultSet.getInt("discount_halfyear"));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or note failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return periodicEdition;
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
	public boolean create(PeriodicEdition periodicEdition) {
		PreparedStatement preparedStatement = getPrepareStatement(SQL_INSERT);
		boolean flag = false;
		try {
			preparedStatement.setInt(1, periodicEdition.getId());
			preparedStatement.setString(2, periodicEdition.getTitle());
			preparedStatement.setString(3, periodicEdition.getShortDescription());
			preparedStatement.setInt(4, periodicEdition.getMonthPeriodicity());
			preparedStatement.setDouble(5, periodicEdition.getMonthPrice());
			preparedStatement.setInt(6, periodicEdition.getDiscountQuarteryear());
			preparedStatement.setInt(7, periodicEdition.getDiscountHalfyear());
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
