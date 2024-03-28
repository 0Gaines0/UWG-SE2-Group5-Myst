package application.viewModel.mystiverse;

import application.model.server_impl.profile.ActiveUser;

/**
 * The Class MystiverseViewModel.
 * 
 * @author daniel rivera
 * @version sprint 1
 */
public class MystiverseViewModel {

	private String cachedProfilePicturePath;
	
	/**
	 * the mystiverse page view model.
	 */
	public MystiverseViewModel() {
		this.cachedProfilePicturePath = "";

	}
	
	/**
	 * Profile picture has changed.
	 *
	 * @return true, if successful
	 */
	public boolean profilePictureHasChanged() {
		var profilePictureSet = !ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath()
				.equals("");
		var profilePictureNotTheSameAsCached = !this.cachedProfilePicturePath
				.equals(ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath());

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
