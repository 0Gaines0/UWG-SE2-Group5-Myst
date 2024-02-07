package application.model.profile.credentials;

import java.io.FileNotFoundException;
import java.util.HashSet;

import application.fileIO.UserCredentialsIO;

public class CredentialManager {
	private HashSet<Credential> userCredentials;
	
	/**
	 * Instantiates a new credential manager.
	 */
	public CredentialManager() {
		this.userCredentials = new HashSet<Credential>();
		this.setUpUserCredentials();
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
			return this.userCredentials.add(credential);
		}
	}


	private void setUpUserCredentials() {
		try {
			var credentialList = UserCredentialsIO.readCredentialsFromFile();
			this.userCredentials.addAll(credentialList);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

}
