package application.test.profile.credentials;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import application.model.profile.credentials.Credential;

/**
 * The Class TestCredential.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestCredential {
	
	private static final String TEST_USERNAME = "testuser";
	private static final String TEST_PASSWORD = "testpassword";
	private static final String TEST_USERNAME2 = "testuser2";
	private static final String TEST_PASSWORD2 = "testpassword2";

	/**
	 * Test constructor valid inputs.
	 */
	@Test
	public void testConstructorValidInputs() {
		var username = TEST_USERNAME;
		var password = TEST_PASSWORD;
		
		var credential = new Credential(username, password);
		
		assertEquals(username, credential.getUsername());
		assertEquals(password, credential.getPassword());
	}
	
	/**
	 * Test constructor null username.
	 */
	@Test
	public void testConstructorNullUsername() {
		String username = null;
		var password = TEST_PASSWORD;
		
		assertThrows(NullPointerException.class, () -> new Credential(username, password));
	}
	
	/**
	 * Test constructor empty username.
	 */
	@Test
	public void testConstructorEmptyUsername() {
		var username = "";
		var password = TEST_PASSWORD;
		
		assertThrows(IllegalArgumentException.class, () -> new Credential(username, password));
	}
	
	/**
	 * Test constructor null password.
	 */
	@Test
	public void testConstructorNullPassword() {
		var username = TEST_USERNAME;
		String password = null;
		
		assertThrows(NullPointerException.class, () -> new Credential(username, password));
	}
	
	/**
	 * Test constructor empty password.
	 */
	@Test
	public void testConstructorEmptyPassword() {
		var username = TEST_USERNAME;
		String password = "";
		
		assertThrows(IllegalArgumentException.class, () -> new Credential(username, password));
	}
	
	/**
	 * Test equals same credentials.
	 */
	@Test
	public void testEqualsSameCredentials() {
		var username1 = TEST_USERNAME;
		var username2 = TEST_USERNAME;
		
		var password1 = TEST_PASSWORD;
		var password2 = TEST_PASSWORD2;
		
		var credential1 = new Credential(username1, password1);
		var credential2 = new Credential(username2, password2);
		
		assertEquals(credential1, credential2);
	}
	
	/**
	 * Test equals different credential.
	 */
	@Test
	public void testEqualsDifferentCredential() {
		var username1 = TEST_USERNAME;
		var username2 = TEST_USERNAME2;
		
		var password1 = TEST_PASSWORD;
		var password2 = TEST_PASSWORD2;
		
		var credential1 = new Credential(username1, password1);
		var credential2 = new Credential(username2, password2);
		
		assertNotEquals(credential1, credential2);
	}
	
	/**
	 * Test hash code same credential.
	 */
	@Test
	public void testHashCodeSameCredential() {
		var username1 = TEST_USERNAME;
		var username2 = TEST_USERNAME;
		
		var password1 = TEST_PASSWORD;
		var password2 = TEST_PASSWORD2;
		
		var credential1 = new Credential(username1, password1);
		var credential2 = new Credential(username2, password2);
		
		assertEquals(credential1.hashCode(), credential2.hashCode());
	}
	
	/**
	 * Test hash code different credential.
	 */
	@Test
	public void testHashCodeDifferentCredential() {
		var username1 = TEST_USERNAME;
		var username2 = TEST_USERNAME2;
		
		var password1 = TEST_PASSWORD;
		var password2 = TEST_PASSWORD2;
		
		var credential1 = new Credential(username1, password1);
		var credential2 = new Credential(username2, password2);
		
		assertNotEquals(credential1.hashCode(), credential2.hashCode());
	}
	
	/**
	 * Test equals with same object.
	 */
	@Test
	public void testEqualsWithSameObject() {
		var username1 = TEST_USERNAME;
		var password1 = TEST_PASSWORD;
		
		var credential1 = new Credential(username1, password1);
		
		assertTrue(credential1.equals(credential1));
	}
	
	/**
	 * Test equals with non credential object.
	 */
	@Test
	public void testEqualsWithNonCredentialObject() {
		var username1 = TEST_USERNAME;
		var password1 = TEST_PASSWORD;
		
		var credential1 = new Credential(username1, password1);
		
		assertFalse(credential1.equals(new Object()));
	}
	
	/**
	 * Test set username valid input.
	 */
	@Test
	public void testSetUsernameValidInput() {
		var credential = new Credential(TEST_USERNAME, TEST_PASSWORD);
		var newUsername = TEST_USERNAME2;
		
		credential.setUsername(newUsername);
		
		assertEquals(newUsername, credential.getUsername());
	}

	/**
	 * Test set username null input.
	 */
	@Test
	public void testSetUsernameNullInput() {
		var credential = new Credential(TEST_USERNAME, TEST_PASSWORD);
		assertThrows(NullPointerException.class, () -> credential.setUsername(null));
	}
	
	/**
	 * Test set username empty input.
	 */
	@Test
	public void testSetUsernameEmptyInput() {
		var credential = new Credential(TEST_USERNAME, TEST_PASSWORD);
		assertThrows(IllegalArgumentException.class, () -> credential.setUsername(""));
	}
	
	/**
	 * Test set password valid input.
	 */
	@Test
	public void testSetPasswordValidInput() {
		var credential = new Credential(TEST_USERNAME, TEST_PASSWORD);
		var newPassword = TEST_PASSWORD2;
		
		credential.setPassword(newPassword);
		
		assertEquals(newPassword, credential.getPassword());
	}
	
	/**
	 * Test set password null input.
	 */
	@Test
	public void testSetPasswordNullInput() {
		var credential = new Credential(TEST_USERNAME, TEST_PASSWORD);
		assertThrows(NullPointerException.class, () -> credential.setPassword(null));
	}
	
	/**
	 * Test set password empty input.
	 */
	@Test
	public void testSetPasswordEmptyInput() {
		var credential = new Credential(TEST_USERNAME, TEST_PASSWORD);
		assertThrows(IllegalArgumentException.class, () -> credential.setPassword(""));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
