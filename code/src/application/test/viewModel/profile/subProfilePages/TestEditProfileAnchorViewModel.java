package application.test.viewModel.profile.subProfilePages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.viewModel.profile.subProfilePages.EditProfileAnchorViewModel;

/**
 * The Class TestEditProfileAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestEditProfileAnchorViewModel {

	private EditProfileAnchorViewModel viewModel;
	private static final String ABOUT_ME_TEST = "Test about me description";
	private static final String IMAGE_PATH_TEST = "image.jpg";
	
	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new EditProfileAnchorViewModel();
		ActiveUser.setActiveUser(new UserProfile());
	}
	
	/**
	 * Test set active user about me.
	 */
	@Test
	public void testSetActiveUserAboutMe() {
		var aboutMeTest = ABOUT_ME_TEST;
		this.viewModel.getAboutMeProperty().set(aboutMeTest);
		this.viewModel.setActiveUserAboutMe();
		assertEquals(aboutMeTest, ActiveUser.getActiveUser().getProfileAttributes().getAboutMeDescription());
	}
	
	/**
	 * Test set about me text area.
	 */
	@Test
	public void testSetAboutMeTextArea() {
		var aboutMeTest = ABOUT_ME_TEST;
		ActiveUser.getActiveUser().getProfileAttributes().setAboutMeDescription(ABOUT_ME_TEST);
		
		this.viewModel.setAboutMeTextArea();
		
		assertEquals(aboutMeTest, this.viewModel.getAboutMeProperty().get());
	}
	
	/**
	 * Test configure user profile picture.
	 */
	@Test
	public void testConfigureUserProfilePicture() {
		this.viewModel.configureUsersProfilePicture(IMAGE_PATH_TEST);
		assertEquals(IMAGE_PATH_TEST, ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath());
	}
}
