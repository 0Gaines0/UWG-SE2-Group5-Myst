package application.test.viewModel.profile.subProfilePages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.UserProfile;
import application.model.local_impl.profile.credentials.CredentialManager;
import application.viewModel.profile.subProfilePages.SettingProfileAnchorViewModel;

public class TestSettingsAnchorViewModel {

	private SettingProfileAnchorViewModel viewModel;
	private CredentialManager credentialManager;
	private static final String FAKE_USERNAME = "username";
	private static final String FAKE_PASSWORD = "password";
	private static final String DEFAULT_USERNAME = "DefaultUsername";
	private static final String TEST_USERNAME = "changePassword";

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.credentialManager = new CredentialManager();
		var userProfile = new UserProfile(FAKE_USERNAME, FAKE_PASSWORD);
		ActiveUser.setActiveUser(userProfile);
		this.viewModel = new SettingProfileAnchorViewModel();
		this.viewModel.setUpUserInformationFields();
	}
	
	/**
	 * Clean up and re add.
	 */
	@AfterEach
	public void cleanUpAndReAdd() {
		this.credentialManager.clearUserCredentials();
		this.credentialManager.addCredential(TEST_USERNAME, FAKE_PASSWORD);
		this.credentialManager.addCredential("0Gaines0", FAKE_PASSWORD);
		this.credentialManager.addCredential(FAKE_USERNAME, FAKE_PASSWORD);

	}
	
	/**
	 * Test set up user info fields.
	 */
	@Test
	public void testSetUpUserInfoFields() {
		assertEquals(FAKE_USERNAME, this.viewModel.getUserInformationUsernameProperty().getValue());
		assertEquals(FAKE_PASSWORD, this.viewModel.getUserInformationPasswordProperty().getValue());

	}
	
	/**
	 * Test attempt to change user name success.
	 */
	@Test
	public void testAttemptToChangeUserNameSuccess() {
		this.viewModel.getChangeInformationCurrentUsernameProperty().set(FAKE_USERNAME);
		this.viewModel.getChangeInformationNewUsernameProperty().set(DEFAULT_USERNAME);
		this.viewModel.getChangeInformationReenterNewUsernameProperty().set(DEFAULT_USERNAME);
		
		assertTrue(this.viewModel.attemptToChangeUsername());
	
	}
	
	/**
	 * Test attempt change username invalid.
	 */
	@Test
	public void testAttemptChangeUsernameInvalid() {
		this.viewModel.getChangeInformationCurrentUsernameProperty().set(FAKE_USERNAME);
		this.viewModel.getChangeInformationNewUsernameProperty().set(TEST_USERNAME);
		this.viewModel.getChangeInformationReenterNewUsernameProperty().set(TEST_USERNAME);
	
		assertFalse(this.viewModel.attemptToChangeUsername());
	}
	
	/**
	 * Test attempt change password successful.
	 */
	@Test
	public void testAttemptChangePasswordSuccessful() {
		this.viewModel.getChangeInformationCurrentPasswordProperty().set(FAKE_PASSWORD);
		this.viewModel.getChangeInformationNewPasswordProperty().set("NewPassword");
		this.viewModel.getChangeInformationReenterNewPasswordProperty().set("NewPassword");
		
		assertTrue(this.viewModel.attemptToChangePassword());
	}
	
	/**
	 * Test attempt change password invalid.
	 */
	@Test
	public void testAttemptChangePasswordInvalid() {
		this.viewModel.getChangeInformationCurrentPasswordProperty().set("IncorrectPassword");
		this.viewModel.getChangeInformationNewPasswordProperty().set("Password123");
        this.viewModel.getChangeInformationReenterNewPasswordProperty().set("Password123");
        
        assertFalse(this.viewModel.attemptToChangePassword());
	}
}
