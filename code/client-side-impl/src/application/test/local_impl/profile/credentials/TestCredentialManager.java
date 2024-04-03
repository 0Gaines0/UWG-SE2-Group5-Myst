package application.test.local_impl.profile.credentials;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.profile.credentials.CredentialManager;

/**
 * The Class TestCredentialManager.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestCredentialManager {

	private CredentialManager credentialManager;

	private static final String NON_EXISTING_USER = "nonexistentuser";
	private static final String FAKE_PASSWORD = "password";
	private static final String REAL_USERNAME = "0Gaines0";

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.credentialManager = new CredentialManager();
	}
	
	/**
	 * Clean up and re add.
	 */
	@AfterEach
	public void cleanUpAndReAdd() {
		this.credentialManager.clearUserCredentials();
		this.credentialManager.addCredential("changePassword", FAKE_PASSWORD);
		this.credentialManager.addCredential("0Gaines0", FAKE_PASSWORD);
		this.credentialManager.addCredential("username", FAKE_PASSWORD);

	}

	/**
	 * Test user name exist.
	 */
	@Test
	public void testUserNameExist() {
		assertFalse(this.credentialManager.userNameExist(NON_EXISTING_USER));
		assertTrue(this.credentialManager.userNameExist(REAL_USERNAME));
	}

	/**
	 * Test user name exist null.
	 */
	@Test
	public void testUserNameExistNull() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.userNameExist(null));
	}

	/**
	 * Test user name exist empty.
	 */
	@Test
	public void testUserNameExistEmpty() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.userNameExist(""));
	}

	/**
	 * Test get specified credential.
	 */
	@Test
	public void testGetSpecifiedCredential() {
		assertNull(this.credentialManager.getSpecifiedCredential(NON_EXISTING_USER));
		assertNotNull(this.credentialManager.getSpecifiedCredential(REAL_USERNAME));
	}

	/**
	 * Test get specific credential null.
	 */
	@Test
	public void testGetSpecifiedCredentialNull() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.getSpecifiedCredential(null));
	}

	/**
	 * Test get specified credential empty.
	 */
	@Test
	public void testGetSpecifiedCredentialEmpty() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.getSpecifiedCredential(""));
	}

	/**
	 * Test add credential.
	 */
	@Test
	public void testAddCredential() {
		assertTrue(this.credentialManager.addCredential("usee2", FAKE_PASSWORD));
		assertThrows(IllegalArgumentException.class,
				() -> this.credentialManager.addCredential(REAL_USERNAME, FAKE_PASSWORD));
	}

	/**
	 * Test change user name.
	 */
	@Test
	public void testChangeUserName() {
		this.credentialManager.changeCredentialUserName("username", "username2");
	}

	/**
	 * Test change credential user name null current username.
	 */
	@Test
	public void testChangeCredentialUserNameNullCurrentUsername() {
		assertThrows(NullPointerException.class,
				() -> this.credentialManager.changeCredentialUserName(null, "newUser"));

	}
	
	/**
	 * Test change credential user name null current username.
	 */
	@Test
	public void testChangeCredentialUserNameBlankCurrentUsername() {
		assertThrows(IllegalArgumentException.class,
				() -> this.credentialManager.changeCredentialUserName("", "newUser"));

	}
	
	
	/**
	 * Test change credential user name not exist.
	 */
	@Test
	public void testChangeCredentialUserNameNotExist() {
		assertFalse(this.credentialManager.changeCredentialUserName("troller", REAL_USERNAME));
	}

	/**
	 * Test change credential user name null new user name.
	 */
	@Test
	public void testChangeCredentialUserNameNullNewUserName() {
		assertThrows(NullPointerException.class,
				() -> this.credentialManager.changeCredentialUserName("newUser", null));

	}
	
	/**
	 * Test change credential user name null new user name.
	 */
	@Test
	public void testChangeCredentialUserNameBlankNewUserName() {
		assertThrows(IllegalArgumentException.class,
				() -> this.credentialManager.changeCredentialUserName("newUser", ""));

	}
	
	/**
	 * Test change credential password.
	 */
	@Test
	public void testChangeCredentialPassword() {
		assertTrue(this.credentialManager.changeCredentialPassword("changePassword", FAKE_PASSWORD, "password1"));
	}
	
	/**
	 * Test change credential password null current user name.
	 */
	@Test
	public void testChangeCredentialPasswordNullCurrentUserName() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.changeCredentialPassword(null, FAKE_PASSWORD, FAKE_PASSWORD));
	}
	
	
	/**
	 * Test change credential password empty current user name.
	 */
	@Test
	public void testChangeCredentialPasswordEmptyCurrentUserName() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.changeCredentialPassword("", FAKE_PASSWORD, FAKE_PASSWORD));
	}
	
	/**
	 * Test change credential password null current user name.
	 */
	@Test
	public void testChangeCredentialPasswordNullCurrentPassword() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.changeCredentialPassword("fskijsk", null, FAKE_PASSWORD));
	}
	
	/**
	 * Test change credential password null current user name.
	 */
	@Test
	public void testChangeCredentialPasswordEmptyCurrentPassword() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.changeCredentialPassword("fskijsk", "", FAKE_PASSWORD));
	}
	
	/**
	 * Test change credential password null current user name.
	 */
	@Test
	public void testChangeCredentialPasswordNullNewPassword() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.changeCredentialPassword("fskijsk", FAKE_PASSWORD, null));
	}
	
	/**
	 * Test change credential password null current user name.
	 */
	@Test
	public void testChangeCredentialPasswordEmptyNewPassword() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.changeCredentialPassword("fskijsk", FAKE_PASSWORD, ""));
	}
	
	/**
	 * Test change credential password nonexisting user.
	 */
	@Test
	public void testChangeCredentialPasswordNonexistingUser() {
		assertFalse(this.credentialManager.changeCredentialPassword("fskijsk", FAKE_PASSWORD, "password1"));
	}

}
