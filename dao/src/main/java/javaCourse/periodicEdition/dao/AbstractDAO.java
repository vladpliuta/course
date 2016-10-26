package javaCourse.periodicEdition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javaCourse.periodicEdition.entity.Entity;


/**
 * родительский класс с описанием общих методов, которые будут использоваться
 * при взаимодействии с таблицами БД
 * 
 * @author Vladimir Pliuta
 *
 */
public abstract class AbstractDAO<T extends Entity> {
	private Connection connection;

	public AbstractDAO(Connection connection) {
		this.connection = connection;
	}

	public abstract List<T> findAll();

	public abstract T findById(int id);

	public abstract boolean delete(int id);

	public abstract boolean create(T entity);

	public PreparedStatement getPrepareStatement(String sql) {
		PreparedStatement preparedStatement = null;
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(sql);
			} catch (SQLException e) {
				System.err.println("connection is null");
			}
		}
		return preparedStatement;
	}

	public void closeStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.err.println("statement is null " + e);
			}
		}
	}
}
