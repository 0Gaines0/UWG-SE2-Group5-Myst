package application.test.viewModel.profile.subProfilePages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.viewModel.profile.subProfilePages.ProfileAnchorViewModel;

/**
 * The Class TestProfileAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestProfileAnchorViewModel {

	private ProfileAnchorViewModel viewModel;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new ProfileAnchorViewModel();
		ActiveUser.setActiveUser(new UserProfile());
	}

	/**
	 * Test set up about me description.
	 */
	@Test
	public void testSetUpAboutMeDescription() {
		var aboutMeDescription = "About me test";
		ActiveUser.getActiveUser().getProfileAttributes().setAboutMeDescription(aboutMeDescription);
		this.viewModel.setUpAboutMeDescription();

		assertEquals(aboutMeDescription, this.viewModel.getAboutMeProperty().getValue());

	}
	
}
