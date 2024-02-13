package application.test.profile.credentials;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.profile.credentials.CredentialManager;

public class TestCredentialManager {
	
	private CredentialManager credentialManager;
	
	private static final String NON_EXISTING_USER = "nonexistentuser";
	private static final String EXISTING_USER = "username";
	private static final String FAKE_PASSWORD = "password";
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.credentialManager = new CredentialManager();
	}
	
	/**
	 * Test user name exist.
	 */
	@Test
	public void testUserNameExist() {
		assertFalse(this.credentialManager.userNameExist(NON_EXISTING_USER));
		assertTrue(this.credentialManager.userNameExist(EXISTING_USER));
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
		assertNotNull(this.credentialManager.getSpecifiedCredential(EXISTING_USER));
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
		assertTrue(this.credentialManager.addCredential(NON_EXISTING_USER, FAKE_PASSWORD));
		assertThrows(IllegalArgumentException.class, () -> this.credentialManager.addCredential(EXISTING_USER, FAKE_PASSWORD));
	}

}
