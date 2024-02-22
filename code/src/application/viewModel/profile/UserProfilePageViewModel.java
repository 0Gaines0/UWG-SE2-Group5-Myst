package application.viewModel.profile;

import application.model.profile.ActiveUser;

/**
 * The Class UserProfilePageViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class UserProfilePageViewModel {

	private String cachedProfilePicturePath;
	
	/**
	 * Instantiates a new user profile page view model.
	 */
	public UserProfilePageViewModel() {
		this.cachedProfilePicturePath = "";
	}
	
	/**
	 * Profile picture has changed.
	 *
	 * @return true, if successful
	 */
	public boolean profilePictureHasChanged() {
		var profilePictureSet = !ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath().equals("");
		var profilePictureNotTheSameAsCached = !this.cachedProfilePicturePath.equals(ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath());
		
		return profilePictureSet && profilePictureNotTheSameAsCached;
	}

	/**
	 * Gets the cached profile picture path.
	 *
	 * @return the cached profile picture path
	 */
	public String getCachedProfilePicturePath() {
		return this.cachedProfilePicturePath;
	}

	/**
	 * Sets the cached profile picture path.
	 *
	 * @param cachedProfilePicturePath the new cached profile picture path
	 */
	public void setCachedProfilePicturePath(String cachedProfilePicturePath) {
		this.cachedProfilePicturePath = cachedProfilePicturePath;
	}
}
