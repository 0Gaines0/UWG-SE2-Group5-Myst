/*
 * 
 */
package application.test.server_impl.profile.credentials;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import application.model.server_impl.profile.credentials.CredentialManager;

public class TestCredentialManager {
	
	private CredentialManager credentialManager;
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	/**
	 * Instantiates a new test credential manager.
	 */
	public TestCredentialManager() {
		this.credentialManager = new CredentialManager();
	}
	
	/**
	 * Test add credential.
	 */
	@Test
	public void testAddCredential() {
		assertFalse(this.credentialManager.addCredential("username1", PASSWORD));
	}
	
	/**
	 * Test add credential empty username.
	 */
	@Test
	public void testAddCredentialEmptyUsername() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.addCredential("", PASSWORD));

	}
	
	/**
	 * Test add credential empty password.
	 */
	@Test
	public void testAddCredentialEmptyPassword() {
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.addCredential(USERNAME, ""));

	}
	
	/**
	 * Test add credential user name exist.
	 */
	@Test
	public void testAddCredentialUserNameExist() {
		assertFalse(this.credentialManager.addCredential(USERNAME, PASSWORD));
	}
	
	/**
	 * Test add credential null username.
	 */
	@Test
	public void testAddCredentialNullUsername() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.addCredential(null, PASSWORD));

	}
	
	/**
	 * Test add credential null password.
	 */
	@Test
	public void testAddCredentialNullPassword() {
		assertThrows(NullPointerException.class, () -> this.credentialManager.addCredential(USERNAME, null));

	}
	
	/**
	 * Test get specific credential.
	 */
	@Test
	public void testGetSpecificCredential() {
		assertNotNull(this.credentialManager.getSpecifiedCredential(USERNAME));
		assertNull(this.credentialManager.getSpecifiedCredential(PASSWORD));
	}
	
	/**
	 * Test get specified credential null username.
	 */
	@Test
	public void testGetSpecifiedCredentialNullUsername() {
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
	 * Test username exist.
	 */
	@Test
	public void testUsernameExist() {
		assertTrue(this.credentialManager.userNameExist(USERNAME));
		assertFalse(this.credentialManager.userNameExist(PASSWORD));
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
	 * Test change credential user name.
	 */
	@Test
	public void testChangeCredentialUserName() {
		assertFalse(this.credentialManager.changeCredentialUserName(USERNAME, PASSWORD));
	}
	
	/**
	 * Test change credential password.
	 */
	@Test
	public void testChangeCredentialPassword() {
		assertFalse(this.credentialManager.changeCredentialPassword(USERNAME, PASSWORD, "uer"));
	}


}
