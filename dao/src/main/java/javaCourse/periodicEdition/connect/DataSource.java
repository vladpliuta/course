package javaCourse.periodicEdition.connect;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * пул соединений на основе c3p0 с извлечением необходимых данных по БД из файла
 * properties. пул реализован на основе паттерна Singleton
 * 
 * @author Vladimir Pliuta
 *
 */
public class DataSource {
	private ComboPooledDataSource cpds;
	private static DataSource dataSource;

	private DataSource() throws IOException, SQLException, PropertyVetoException {
		ResourceBundle resource = ResourceBundle.getBundle("javaCourse.periodicEdition.connect.database");
		String driver = resource.getString("db.driver");
		String url = resource.getString("db.url");
		String user = resource.getString("db.user");
		String password = resource.getString("db.password");

		cpds = new ComboPooledDataSource();
		cpds.setDriverClass(driver);
		cpds.setJdbcUrl(url);
		cpds.setUser(user);
		cpds.setPassword(password);

		cpds.setMinPoolSize(5);
		cpds.setMaxPoolSize(20);
		cpds.setAcquireIncrement(5);
		cpds.setMaxStatements(180);
	}

	public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (dataSource == null) {
			synchronized (DataSource.class) {
				if (dataSource == null) {
					dataSource = new DataSource();
				}
			}
		}
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}
}
