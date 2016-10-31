package javaCourse.periodicEdition.services;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Parameterized test class for method CheckEnter CheckLogin class.
 * 
 * @author Vladimir Pliuta
 *
 */
@RunWith(Parameterized.class)
public class CheckLoginTest {
	private String expectedCheckReralt;
	private String enterLogin;
	private String enterPassword;

	public CheckLoginTest(String expectedCheckReralt, String enterLogin, String enterPassword) {
		this.expectedCheckReralt = expectedCheckReralt;
		this.enterLogin = enterLogin;
		this.enterPassword = enterPassword;
	}

	@Parameterized.Parameters
	public static List<Object[]> isTestData() {
		return Arrays.asList(new Object[][] { 
			{ "admin", "admin", "admin" }, 
			{ "1", "ivan1980", "1234" },
			{ "error", "admin", "1234" }, });
	}

	@Test
	public void testCheckEnter() throws Exception {
		String actualCheckResalt = CheckLogin.checkEnter(enterLogin, enterPassword);
		assertTrue("The expected result is not obtained", expectedCheckReralt.equals(actualCheckResalt));
	}

}
