package application.test.local_impl.viewModel.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.profile.credentials.CredentialManager;
import application.viewModel.login.CreatePageViewModel;

/**
 * The Class TestCreatePageViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestCreatePageViewModel {
	private CreatePageViewModel viewModel;
	private CredentialManager credentialManager;
	
	private static final String TEST_USER = "testUser";
	private static final String PASSWORD = "testPassword";
	private static final String FAKE_PASSWORD = "password";
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new CreatePageViewModel();
		this.credentialManager = new CredentialManager();
	}
	
	/**
	 * Reset info.
	 */
	@AfterEach
	public void resetInfo() {
		this.credentialManager.clearUserCredentials();
		this.credentialManager.addCredential("changePassword", FAKE_PASSWORD);
		this.credentialManager.addCredential("0Gaines0", FAKE_PASSWORD);
		this.credentialManager.addCredential("username", FAKE_PASSWORD);
	}
	
	/**
	 * Test attempt create new account successful.
	 */
	@Test
	public void testAttemptCreateNewAccountSuccessful() {
		this.viewModel.getUserNameProperty().setValue(TEST_USER);
		this.viewModel.getPasswordProperty().setValue(PASSWORD);
		this.viewModel.getReenterPasswordProperty().setValue(PASSWORD);
		
		assertFalse(this.viewModel.attemptCreateNewAccount());
	}
	
	/**
	 * Test attempt create new account invalid username.
	 */
	@Test
	public void testAttemptCreateNewAccountInvalidUsername() {
		this.viewModel.getUserNameProperty().setValue("");
		this.viewModel.getPasswordProperty().setValue(PASSWORD);
		this.viewModel.getReenterPasswordProperty().setValue(PASSWORD);
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.viewModel.attemptCreateNewAccount();
		});
		
	}
	
	/**
	 * Test attempt create new account invalid password.
	 */
	@Test
	public void testAttemptCreateNewAccountInvalidPassword() {
		this.viewModel.getUserNameProperty().setValue(TEST_USER);
		this.viewModel.getPasswordProperty().setValue("");
		this.viewModel.getReenterPasswordProperty().setValue("");
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.viewModel.attemptCreateNewAccount();
		});
	}
}
