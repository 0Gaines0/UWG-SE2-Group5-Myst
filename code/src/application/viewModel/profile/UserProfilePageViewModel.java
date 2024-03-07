package application.viewModel.profile;

import application.model.local_impl.profile.ActiveUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class UserProfilePageViewModel.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class UserProfilePageViewModel {

	private StringProperty usernameTextProperty;
	private String cachedProfilePicturePath;

	/**
	 * Instantiates a new user profile page view model.
	 */
	public UserProfilePageViewModel() {
		this.usernameTextProperty = new SimpleStringProperty();
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
	 * Update username on page.
	 */
	public void updateUsernameOnPage() {
		if (this.usernameHasChanged()) {
			this.usernameTextProperty.setValue(ActiveUser.getActiveUser().getUsername());
		}
	}

	private boolean usernameHasChanged() {
		var usernameChanged = !this.getUsernameTextProperty().getValue()
				.equals(ActiveUser.getActiveUser().getUsername());
		return usernameChanged;
	}

	/**
	 * Gets the username text property.
	 *
	 * @return the username text property
	 */
	public StringProperty getUsernameTextProperty() {
		return this.usernameTextProperty;
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
