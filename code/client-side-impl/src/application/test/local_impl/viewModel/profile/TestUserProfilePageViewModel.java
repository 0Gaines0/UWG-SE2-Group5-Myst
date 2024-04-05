package application.test.local_impl.viewModel.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.UserProfile;
import application.viewModel.profile.UserProfilePageViewModel;

/**
 * The Class TestUserProfilePageViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
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
		ActiveUser.setActiveUser(new UserProfile());
		assertTrue(this.userProfilePageViewModel.profilePictureHasChanged());
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
	
	/**
	 * Test update username on page.
	 */ 
	@Test
	public void testupdateUsernameOnPage() {
		ActiveUser.setActiveUser(new UserProfile());
		this.userProfilePageViewModel.getUsernameTextProperty().setValue("John");
		ActiveUser.getActiveUser().setUsername("Sam");
		
		this.userProfilePageViewModel.updateUsernameOnPage();
		
		assertEquals("username", this.userProfilePageViewModel.getUsernameTextProperty().getValue());
	}
}
