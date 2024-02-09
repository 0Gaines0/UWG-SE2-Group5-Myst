package application.viewModel.login;

import application.model.profile.credentials.CredentialManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class CreatePageViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class CreatePageViewModel {
	private StringProperty userNameProperty;
	private StringProperty passwordProperty;
	private StringProperty reenterPasswordProperty;
	private CredentialManager credentialManger;
	
	/**
	 * Instantiates a new creates the page view model.
	 */
	public CreatePageViewModel() {
		this.userNameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
		this.reenterPasswordProperty = new SimpleStringProperty();
		this.credentialManger = new CredentialManager();
	}
	
	/**
	 * Attempt create new account.
	 *
	 * @return true, if successful
	 */
	public boolean attemptCreateNewAccount() {
		var username = this.userNameProperty.getValue();
		var password = this.passwordProperty.getValue();
		
		try {
			this.credentialManger.addCredential(username, password);
		} catch (Exception e) {
			return false;
		} 
		return true;
		
	}

	/**
	 * Gets the user name property.
	 *
	 * @return the user name property
	 */
	public StringProperty getUserNameProperty() {
		return this.userNameProperty;
	}

	/**
	 * Gets the password property.
	 *
	 * @return the password property
	 */
	public StringProperty getPasswordProperty() {
		return this.passwordProperty;
	}

	/**
	 * Gets the reenter password property.
	 *
	 * @return the reenter password property
	 */
	public StringProperty getReenterPasswordProperty() {
		return this.reenterPasswordProperty;
	}
}
