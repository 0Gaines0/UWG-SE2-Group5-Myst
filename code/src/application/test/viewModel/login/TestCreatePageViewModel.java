package application.test.viewModel.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.viewModel.login.CreatePageViewModel;

/**
 * The Class TestCreatePageViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestCreatePageViewModel {
	private CreatePageViewModel viewModel;
	
	private static final String TEST_USER = "testUser";
	private static final String PASSWORD = "testPassword";
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new CreatePageViewModel();
	}
	
	/**
	 * Test attempt create new account successful.
	 */
	@Test
	public void testAttemptCreateNewAccountSuccessful() {
		this.viewModel.getUserNameProperty().setValue(TEST_USER);
		this.viewModel.getPasswordProperty().setValue(PASSWORD);
		this.viewModel.getReenterPasswordProperty().setValue(PASSWORD);
		
		assertTrue(this.viewModel.attemptCreateNewAccount());
	}
	
	/**
	 * Test attempt create new account invalid username.
	 */
	@Test
	public void testAttemptCreateNewAccountInvalidUsername() {
		this.viewModel.getUserNameProperty().setValue("");
		this.viewModel.getPasswordProperty().setValue(PASSWORD);
		this.viewModel.getReenterPasswordProperty().setValue(PASSWORD);
		
		assertFalse(this.viewModel.attemptCreateNewAccount());
	}
	
	/**
	 * Test attempt create new account invalid password.
	 */
	@Test
	public void testAttemptCreateNewAccountInvalidPassword() {
		this.viewModel.getUserNameProperty().setValue(TEST_USER);
		this.viewModel.getPasswordProperty().setValue("");
		this.viewModel.getReenterPasswordProperty().setValue("");
		
		assertFalse(this.viewModel.attemptCreateNewAccount());
	}
}
