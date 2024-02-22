package application.viewModel.profile.subProfilePages;

import application.model.profile.ActiveUser;
import application.model.profile.credentials.CredentialManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SettingProfileAnchorViewModel {

	private StringProperty changeInformationCurrentPasswordProperty;
	private StringProperty changeInformationNewPasswordProperty;
	private StringProperty changeInformationReenterNewPasswordProperty;

	private StringProperty changeInformationCurrentUsernameProperty;
	private StringProperty changeInformationNewUsernameProperty;
	private StringProperty changeInformationReenterNewUsernameProperty;

	private StringProperty userInformationPasswordProperty;
	private StringProperty userInformationUsernameProperty;
	
	private CredentialManager credentialManager;

	/**
	 * Instantiates a new setting profile anchor view model.
	 */
	public SettingProfileAnchorViewModel() {
		this.credentialManager = new CredentialManager();
		this.changeInformationCurrentPasswordProperty = new SimpleStringProperty();
		this.changeInformationCurrentUsernameProperty = new SimpleStringProperty();
		this.changeInformationNewPasswordProperty = new SimpleStringProperty();
		this.changeInformationNewUsernameProperty = new SimpleStringProperty();
		this.changeInformationReenterNewPasswordProperty = new SimpleStringProperty();
		this.changeInformationReenterNewUsernameProperty = new SimpleStringProperty();
		this.userInformationPasswordProperty = new SimpleStringProperty();
		this.userInformationUsernameProperty = new SimpleStringProperty();
	}

	/**
	 * Sets the up user information fields.
	 */
	public void setUpUserInformationFields() {
		this.userInformationUsernameProperty.set(ActiveUser.getActiveUser().getUsername());
		this.userInformationPasswordProperty.set(ActiveUser.getActiveUser().getPassword());
	}

	/**
	 * Attempt to change username.
	 *
	 * @return true, if successful
	 */
	public boolean attemptToChangeUsername() {
		var currentUserName = ActiveUser.getActiveUser().getUsername();
		var inputtedCurrentUserName = this.changeInformationCurrentUsernameProperty.getValue();
		var inputtedNewUserName = this.changeInformationNewUsernameProperty.getValue();
		var inputtedReenterNewUserName = this.changeInformationReenterNewUsernameProperty.getValue();

		if (this.invalidateChangeUsernameComponents(inputtedCurrentUserName, inputtedNewUserName,
				inputtedReenterNewUserName)) {
			return false;
		} else if (this.usernamesDoNotMatch(currentUserName, inputtedCurrentUserName)) {
			return false;
		} else if (this.credentialManager.getSpecifiedCredential(inputtedCurrentUserName) != null) {
			return false;
		} else {
			return this.credentialManager.changeCredentialUserName(currentUserName, inputtedCurrentUserName);
		}
	}

	private boolean usernamesDoNotMatch(String currentUserName, String inputtedCurrentUserName) {
		return !currentUserName.equals(inputtedCurrentUserName);
	}

	private boolean invalidateChangeUsernameComponents(String inputtedCurrentUserName, String inputtedNewUserName,
			String inputtedReenterNewUserName) {
		return inputtedCurrentUserName == null || inputtedCurrentUserName.isBlank() || inputtedNewUserName == null
				|| inputtedNewUserName.isBlank() || inputtedReenterNewUserName == null
				|| inputtedReenterNewUserName.isBlank();
	}

	/**
	 * Gets the change information current password property.
	 *
	 * @return the change information current password property
	 */
	public StringProperty getChangeInformationCurrentPasswordProperty() {
		return this.changeInformationCurrentPasswordProperty;
	}

	/**
	 * Gets the change information new password property.
	 *
	 * @return the change information new password property
	 */
	public StringProperty getChangeInformationNewPasswordProperty() {
		return this.changeInformationNewPasswordProperty;
	}

	/**
	 * Gets the change information reenter new password property.
	 *
	 * @return the change information reenter new password property
	 */
	public StringProperty getChangeInformationReenterNewPasswordProperty() {
		return this.changeInformationReenterNewPasswordProperty;
	}

	/**
	 * Gets the change information current username property.
	 *
	 * @return the change information current username property
	 */
	public StringProperty getChangeInformationCurrentUsernameProperty() {
		return this.changeInformationCurrentUsernameProperty;
	}

	/**
	 * Gets the change information new username property.
	 *
	 * @return the change information new username property
	 */
	public StringProperty getChangeInformationNewUsernameProperty() {
		return this.changeInformationNewUsernameProperty;
	}

	/**
	 * Gets the change information reenter new username property.
	 *
	 * @return the change information reenter new username property
	 */
	public StringProperty getChangeInformationReenterNewUsernameProperty() {
		return this.changeInformationReenterNewUsernameProperty;
	}

	/**
	 * Gets the user information password property.
	 *
	 * @return the user information password property
	 */
	public StringProperty getUserInformationPasswordProperty() {
		return this.userInformationPasswordProperty;
	}

	/**
	 * Gets the user information username property.
	 *
	 * @return the user information username property
	 */
	public StringProperty getUserInformationUsernameProperty() {
		return this.userInformationUsernameProperty;
	}
}
