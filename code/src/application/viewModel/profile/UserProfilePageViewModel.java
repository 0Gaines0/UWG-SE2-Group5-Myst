package application.viewModel.profile;

public class UserProfilePageViewModel {

	private String cachedProfilePicturePath;
	
	/**
	 * Instantiates a new user profile page view model.
	 */
	public UserProfilePageViewModel() {
		this.setCachedProfilePicturePath("");
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
