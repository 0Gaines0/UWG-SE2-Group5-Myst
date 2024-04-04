package application.test.local_impl.viewModel.mystiverse;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserProfile;
import application.viewModel.mystiverse.MystiverseViewModel;

class TestMystiverseViewModel {

	private MystiverseViewModel viewModel;

	@BeforeEach
	void setUp() {
		ActiveUser.setActiveUser(new UserProfile("username", "password"));
		this.viewModel = new MystiverseViewModel();
	}

	@Test
	void testProfilePictureHasChanged() {
		this.viewModel.setCachedProfilePicturePath("cached/profile/picture.png");
		ActiveUser.getActiveUser().getProfileAttributes().setUserProfilePicturePath("new/profile/picture.png");
		assertTrue(this.viewModel.profilePictureHasChanged());
	}

	@Test
	void testProfilePictureNotChanged() {
		this.viewModel.setCachedProfilePicturePath("cached/profile/picture.png");
		ActiveUser.getActiveUser().getProfileAttributes().setUserProfilePicturePath("cached/profile/picture.png");
		assertFalse(this.viewModel.profilePictureHasChanged());
	}

	@Test
	void testGetCachedProfilePicturePath() {
		String cachedPath = "cached/profile/picture.png";
		this.viewModel.setCachedProfilePicturePath(cachedPath);
		assertEquals(cachedPath, this.viewModel.getCachedProfilePicturePath());
	}

	@Test
	void testSetCachedProfilePicturePath() {
		String newPath = "new/cached/profile/picture.png";
		this.viewModel.setCachedProfilePicturePath(newPath);
		assertEquals(newPath, this.viewModel.getCachedProfilePicturePath());
	}

}
