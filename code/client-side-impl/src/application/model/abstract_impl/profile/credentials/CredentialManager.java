package application.model.abstract_impl.profile.credentials;

import application.model.local_impl.profile.credentials.Credential;

public abstract class CredentialManager {

	/**
	 * User name exist.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public abstract boolean userNameExist(String username);
	
	/**
	 * Gets the specified credential.
	 *
	 * @param username the username
	 * @return the specified credential
	 */
	public abstract Credential getSpecifiedCredential(String username);
	
	/**
	 * Change credential user name.
	 *
	 * @param currentUsername the current username
	 * @param newUsername the new username
	 * @return true, if successful
	 */
	public abstract boolean changeCredentialUserName(String currentUsername, String newUsername);
	
	
	/**
	 * Change credential password.
	 *
	 * @param currentUsername the current username
	 * @param currentPassword the current password
	 * @param newPassword the new password
	 * @return true, if successful
	 */
	public abstract boolean changeCredentialPassword(String currentUsername, String currentPassword, String newPassword);
	
	
	/**
	 * Adds the credential.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public abstract boolean addCredential(String username, String password);
	
	
	
	
	
	
	
	
	
	
}
