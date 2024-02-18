package application.test.viewModel.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.profile.ActiveUser;
import application.viewModel.profile.UserProfilePageViewModel;

public class TestUserProfilePageViewModel {

	private UserProfilePageViewModel userProfilePageViewModel;
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.userProfilePageViewModel = new UserProfilePageViewModel();
	}
	
	/**
	 * Test profile picture changed.
	 */
	@Test
	public void testProfilePictureChanged() {
		assertFalse(this.userProfilePageViewModel.profilePictureHasChanged());
		this.userProfilePageViewModel.setCachedProfilePicturePath("oldPath");
		ActiveUser.getActiveUser().getProfileAttributes().setUserProfilePicturePath("newPath");
		assertTrue(this.userProfilePageViewModel.profilePictureHasChanged());
		
	}
	
	/**
	 * Test get set cached profile picture path.
	 */
	@Test
	public void testGetSetCachedProfilePicturePath() {
		assertEquals("", this.userProfilePageViewModel.getCachedProfilePicturePath());
		this.userProfilePageViewModel.setCachedProfilePicturePath("newPath");
		assertEquals("newPath", this.userProfilePageViewModel.getCachedProfilePicturePath());
	}
}
