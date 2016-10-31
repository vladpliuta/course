package javaCourse.periodicEdition.services;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javaCourse.periodicEdition.connect.DataSource;
import javaCourse.periodicEdition.dao.PeriodicEditionDAO;
import javaCourse.periodicEdition.entity.PeriodicEdition;

/**
 * Parameterized test class for method calculate CalculateCoast class.
 *
 * @author Vladimir Pliuta
 *
 */

@RunWith(Parameterized.class)
public class CalculateCoastTest {
	private int period;
	private double expectedCoast;
	private static PeriodicEdition periodicEdition;
	private static Connection conn;

	public CalculateCoastTest(int period, double expectedCoast) {
		this.period = period;
		this.expectedCoast = expectedCoast;
	}

	@Parameterized.Parameters
	public static List<Object[]> isTestData() {
		return Arrays.asList(new Object[][] { 
			{ 1, 3.0 }, 
			{ 3, 8.73 }, 
			{ 6, 16.92 }, 
			{ 12, 33.84 } });
	}

	@BeforeClass
	public static void initValues() throws Exception {
		int issn = 18181147;
		conn = DataSource.getInstance().getConnection();
		PeriodicEditionDAO periodicEditionDAO = new PeriodicEditionDAO(conn);
		periodicEdition = periodicEditionDAO.findById(issn);
	}

	@Test
	public void testCalculateCoast() {
		double actualCoast = CalculateCoast.calculate(periodicEdition, period);
		assertEquals("The expected result is not obtained", expectedCoast, actualCoast, 0.01);
	}

	@AfterClass
	public static void clearValues() throws Exception {
		conn.close();
	}
}
