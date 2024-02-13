package application.test.viewModel.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.viewModel.login.LoginPageViewModel;

public class TestLoginPageViewModel {

	private LoginPageViewModel loginPageViewModel;
	
	private static final String TEST_USERNAME = "username";
	private static final String TEST_PASSWORD = "password";
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.loginPageViewModel = new LoginPageViewModel();

		this.loginPageViewModel.getUsernameProperty().set(TEST_USERNAME);
		this.loginPageViewModel.getPasswordProperty().set(TEST_PASSWORD);
	}
	
	/**
	 * Test user login is successful with correct credentials.
	 */
	@Test
	public void testUserLoginIsSuccessfulWithCorrectCredentials() {
		assertTrue(this.loginPageViewModel.userLoginIsSuccessful());
	}
	
	/**
	 * Test user login with incorrect password.
	 */
	@Test
	public void testUserLoginWithIncorrectPassword() {
		this.loginPageViewModel.getPasswordProperty().set("wrongPassword");
		assertFalse(this.loginPageViewModel.userLoginIsSuccessful());
	}
	
	/**
	 * Test user login with nonexistent username.
	 */
	@Test
	public void testUserLoginWithNonexistentUsername() {
		this.loginPageViewModel.getUsernameProperty().set("nonexistentUser");
		assertFalse(this.loginPageViewModel.userLoginIsSuccessful());
	}
}
