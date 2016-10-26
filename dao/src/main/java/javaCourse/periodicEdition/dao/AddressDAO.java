package javaCourse.periodicEdition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaCourse.periodicEdition.entity.Address;


/**
 * класс для взаимодействии с таблицей адресов БД получился "на вырост" в
 * приложении пока не используется
 * 
 * @author Vladimir Pliuta
 *
 */
public class AddressDAO extends AbstractDAO<Address> {
	private static final String SQL_SELECT_ALL_ADDRESS = "SELECT * FROM address";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM address WHERE id_address=?";
	private static final String SQL_INSERT = "INSERT INTO address (postcod, city, street, house, flat) VALUES (?,?,?,?,?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM address WHERE id_address=?";

	public AddressDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Address> findAll() {
		List<Address> addresss = new ArrayList<>();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_ALL_ADDRESS);
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Address address = new Address();
				address.setId(resultSet.getInt("id_address"));
				address.setPostcode(resultSet.getInt("postcod"));
				address.setCity(resultSet.getString("city"));
				address.setStreet(resultSet.getString("street"));
				address.setHouse(resultSet.getString("house"));
				address.setFlat(resultSet.getInt("flat"));
				addresss.add(address);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return addresss;
	}

	@Override
	public Address findById(int id) {
		Address address = new Address();
		PreparedStatement preparedStatement = getPrepareStatement(SQL_SELECT_BY_ID);
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				address.setId(resultSet.getInt("id_reader"));
				address.setId(resultSet.getInt("id_address"));
				address.setPostcode(resultSet.getInt("postcod"));
				address.setCity(resultSet.getString("city"));
				address.setStreet(resultSet.getString("street"));
				address.setHouse(resultSet.getString("house"));
				address.setFlat(resultSet.getInt("flat"));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or note failed): " + e);
		} finally {
			this.closeStatement(preparedStatement);
		}
		return address;
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
	public boolean create(Address address) {
		PreparedStatement preparedStatement = getPrepareStatement(SQL_INSERT);
		boolean flag = false;
		try {
			preparedStatement.setInt(1, address.getPostcode());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getHouse());
			preparedStatement.setInt(5, address.getFlat());
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
