/*
 * 
 */
package application.viewModel.profile.subProfilePages;

import application.model.server_impl.profile.ActiveUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class EditProfileAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class EditProfileAnchorViewModel {
	private StringProperty aboutMeProperty;

	/**
	 * Instantiates a new edits the profile anchor view model.
	 */
	public EditProfileAnchorViewModel() {
		this.aboutMeProperty = new SimpleStringProperty();
	}

	
	/**
	 * Configure users profile picture.
	 *
	 * @param imagePath the image path
	 */
	public void configureUsersProfilePicture(String imagePath) {
		ActiveUser.getActiveUser().getProfileAttributes().setUserProfilePicturePath(imagePath);
	}

	/**
	 * Sets the active user about me.
	 */
	public void setActiveUserAboutMe() {
		ActiveUser.getActiveUser().getProfileAttributes().setAboutMeDescription(this.aboutMeProperty.getValue());
	}

	/**
	 * Sets the about me text area.
	 */
	public void setAboutMeTextArea() {
		this.aboutMeProperty.setValue(ActiveUser.getActiveUser().getProfileAttributes().getAboutMeDescription());
	}

	/**
	 * Gets the about me property.
	 *
	 * @return the about me property
	 */
	public StringProperty getAboutMeProperty() {
		return this.aboutMeProperty;
	}
}
