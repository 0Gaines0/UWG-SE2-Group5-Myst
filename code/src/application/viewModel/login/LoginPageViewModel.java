package application.viewModel.login;

import application.model.profile.UserProfile;
import application.model.profile.credentials.CredentialManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class LoginPageViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class LoginPageViewModel {
	private StringProperty usernameProperty;
	private StringProperty passwordProperty;
	private CredentialManager credentialManager;
	
	/**
	 * Instantiates a new login page view model.
	 */
	public LoginPageViewModel() {
		this.usernameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
		this.credentialManager = new CredentialManager();
	}

	
	/**
	 * User login is successful.
	 *
	 * @return true, if successful
	 */
	public boolean userLoginIsSuccessful() {
		this.credentialManager.setUpUserCredentials();
		var username = this.usernameProperty.getValue().trim();
		var password = this.passwordProperty.getValue().trim();
		if (this.credentialManager.userNameExist(username)) {
			var credential = this.credentialManager.getSpecifiedCredential(username);
			if (credential.getPassword().equals(password)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
		
		
		
	}

	/**
	 * Gets the username property.
	 *
	 * @return the username property
	 */
	public StringProperty getUsernameProperty() {
		return this.usernameProperty;
	}

	/**
	 * Gets the password property.
	 *
	 * @return the password property
	 */
	public StringProperty getPasswordProperty() {
		return this.passwordProperty;
	}


	public UserProfile generateUser() {
		var user = new UserProfile(this.usernameProperty.getValue(), this.passwordProperty.getValue());
		return user;
	}

}
