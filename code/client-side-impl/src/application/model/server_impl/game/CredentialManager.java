package application.model.server_impl.game;

import application.model.local_impl.profile.credentials.Credential;

public class CredentialManager extends application.model.abstract_impl.profile.credentials.CredentialManager {

	@Override
	public boolean userNameExist(String username) {
		
		return false;
	}

	@Override
	public Credential getSpecifiedCredential(String username) {
		return null;
	}

	@Override
	public boolean changeCredentialUserName(String currentUsername, String newUsername) {
		return false;
	}

	@Override
	public boolean changeCredentialPassword(String currentUsername, String currentPassword, String newPassword) {
		return false;
	}

	@Override
	public boolean addCredential(String username, String password) {
		return false;
	}

}
