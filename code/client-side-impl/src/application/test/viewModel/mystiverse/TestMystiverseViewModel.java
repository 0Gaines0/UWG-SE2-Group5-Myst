package application.test.viewModel.mystiverse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.UserProfile;
import application.viewModel.mystiverse.MystiverseViewModel;

class TestMystiverseViewModel {

    private MystiverseViewModel viewModel;

    @BeforeEach
    void setUp() {
        ActiveUser.setActiveUser(new UserProfile("username", "password"));
        this.viewModel = new MystiverseViewModel();
    }

	/*
	 * @Test void testProfilePictureHasChanged() {
	 * assertFalse(this.viewModel.profilePictureHasChanged());
	 * this.viewModel.setCachedProfilePicturePath("cached/profile/picture.png");
	 * ActiveUser.getActiveUser().getProfileAttributes().setUserProfilePicturePath(
	 * "new/profile/picture.png");
	 * assertTrue(this.viewModel.profilePictureHasChanged()); }
	 * 
	 * @Test void testProfilePictureNotChanged() {
	 * assertFalse(this.viewModel.profilePictureHasChanged());
	 * this.viewModel.setCachedProfilePicturePath("cached/profile/picture.png");
	 * ActiveUser.getActiveUser().getProfileAttributes().setUserProfilePicturePath(
	 * "cached/profile/picture.png");
	 * assertFalse(this.viewModel.profilePictureHasChanged()); }
	 */

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
