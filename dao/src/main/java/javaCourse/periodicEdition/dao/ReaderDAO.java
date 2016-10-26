package javaCourse.periodicEdition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaCourse.periodicEdition.entity.Reader;


/**
 * класс для взаимодействии с таблицей пользователей БД, большинство методов "на вырост" в
 * приложении пока используется только findAll
 * 
 * @author Vladimir Pliuta
 *
 */
public class ReaderDAO extends AbstractDAO<Reader> {
	private static final String SQL_SELECT_ALL_READER = "SELECT * FROM reader";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM reader WHERE id_reader=?";
	private static final String SQL_INSERT = "INSERT INTO reader (surname, forename, login, password, id_address, admin_flag) VALUES (?,?,?,?,?,?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM reader WHERE id_reader=?";
	private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM reader WHERE login=?";

	public ReaderDAO(Connection connection) {
		super(connection);

	}

	@Override
	public List<Reader> findAll() {
		List<Reader> readers = new ArrayList<>();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_ALL_READER);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Reader reader = new Reader();
				reader.setId(resultSet.getInt("id_reader"));
				reader.setSurname(resultSet.getString("surname"));
				reader.setForename(resultSet.getString("forename"));
				reader.setLogin(resultSet.getString("login"));
				reader.setPassword(resultSet.getString("password"));
				reader.setIdAddress(resultSet.getInt("id_address"));
				reader.setAdminFlag(resultSet.getInt("admin_flag"));
				readers.add(reader);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return readers;
	}

	@Override
	public Reader findById(int id) {
		Reader reader = new Reader();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_BY_ID);
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				reader.setId(resultSet.getInt("id_reader"));
				reader.setSurname(resultSet.getString("surname"));
				reader.setForename(resultSet.getString("forename"));
				reader.setLogin(resultSet.getString("login"));
				reader.setPassword(resultSet.getString("password"));
				reader.setIdAddress(resultSet.getInt("id_address"));
				reader.setAdminFlag(resultSet.getInt("admin_flag"));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or note failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return reader;
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
	public boolean create(Reader reader) {
		PreparedStatement preparedStatement = getPrepareStatement(SQL_INSERT);
		boolean flag = false;
		try {
			preparedStatement.setString(1, reader.getSurname());
			preparedStatement.setString(2, reader.getForename());
			preparedStatement.setString(3, reader.getLogin());
			preparedStatement.setString(4, reader.getPassword());
			preparedStatement.setInt(5, reader.getIdAddress());
			preparedStatement.setInt(6, reader.getAdminFlag());
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (not insert): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return flag;
	}
	public Reader findByLogin(String login) {
		Reader reader = new Reader();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_BY_LOGIN);
		try {
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				reader.setId(resultSet.getInt("id_reader"));
				reader.setSurname(resultSet.getString("surname"));
				reader.setForename(resultSet.getString("forename"));
				reader.setLogin(resultSet.getString("login"));
				reader.setPassword(resultSet.getString("password"));
				reader.setIdAddress(resultSet.getInt("id_address"));
				reader.setAdminFlag(resultSet.getInt("admin_flag"));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or note failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return reader;
	}
}
