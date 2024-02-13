package application.model.profile.credentials;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import application.fileIO.UserCredentialsIO;

/**
 * The Class CredentialManager.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class CredentialManager {
	private static final String USERNAME_MUST_BE_VALID = "username must not be null or empty";

	private HashSet<Credential> userCredentials;

	/**
	 * Instantiates a new credential manager.
	 */
	public CredentialManager() {
		this.userCredentials = new HashSet<Credential>();
		this.setUpUserCredentials();
	}

	/**
	 * User name exist.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean userNameExist(String username) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		for (var credential : this.userCredentials) {
			if (credential.getUsername().toLowerCase().equals(username.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the specified credential.
	 *
	 * @param username the username
	 * @return the specified credential
	 */
	public Credential getSpecifiedCredential(String username) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		Credential credential = null;
		for (var cred : this.userCredentials) {
			if (cred.getUsername().toLowerCase().equals(username.toLowerCase())) {
				credential = cred;
			}
		}
		return credential;
	}

	/**
	 * Gets the user credentials.
	 *
	 * @return the user credentials
	 */
	public HashSet<Credential> getUserCredentials() {
		return this.userCredentials;
	}

	/**
	 * Adds the credential.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean addCredential(String username, String password) {
		var credential = new Credential(username, password);
		if (this.userCredentials.contains(credential)) {
			throw new IllegalArgumentException("Credential can not be added, username already exist");
		} else {
			if (this.userCredentials.add(credential)) {
				this.updateCredentialFile();
				this.setUpUserCredentials();
				return true;
			} else {
				return false;
			}

		}
	}

	private void updateCredentialFile() {
		var credentials = new ArrayList<Credential>(this.userCredentials);
		try {
			UserCredentialsIO.writeCredentialsToXMLFile(credentials);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	/**
	 * Sets the up user credentials.
	 */
	public void setUpUserCredentials() {
		try {
			var credentialList = UserCredentialsIO.readCredentialsFromFile();
			this.userCredentials.addAll(credentialList);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}
