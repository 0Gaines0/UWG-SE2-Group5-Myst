package application.model.server_impl.profile.credentials;

import org.json.JSONException;
import org.json.JSONObject;

import application.model.local_impl.profile.credentials.Credential;
import application.model.server_impl.Server;
import application.model.server_impl.ServerConstants;

/**
 * The Class CredentialManager.
 * @author Jeffrey Gaines
 * @version Sprint 2
 */
public class CredentialManager extends application.model.abstract_impl.profile.credentials.CredentialManager {
	private static final String USERNAME_MUST_BE_VALID = "username must not be null or empty";
	private static final String PASSWORD_MUST_BE_VALID = "password must not be null or empty";

	@Override
	public boolean userNameExist(String username) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		var json = new JSONObject();
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_USERNAME_EXIST);
			json.put(ServerConstants.KEY_USERNAME, username);
			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);

			var result = responseJson.get(ServerConstants.KEY_SUCCESS);
			if (result.equals(ServerConstants.VALUE_TRUE)) {
				return true;
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return false;
	}

	@Override
	public Credential getSpecifiedCredential(String username) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		var json = new JSONObject();

		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_SPECIFIED_CREDENTIAL);
			json.put(ServerConstants.KEY_USERNAME, username);
			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);

			var result = responseJson.get(ServerConstants.KEY_SUCCESS);
			if (result.equals(ServerConstants.VALUE_TRUE)) {
				var responseUsername = (String) responseJson.get("username");
				var responsePassword = (String) responseJson.get("password");
				var credential = new Credential(responseUsername, responsePassword);
				return credential;
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
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
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_BE_VALID);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_BE_VALID);
		}
		var json = new JSONObject();
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_ADD_CREDENTIAL);
			json.put(ServerConstants.KEY_USERNAME, username);
			json.put(ServerConstants.KEY_PASSWORD, password);
			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);

			var result = responseJson.get(ServerConstants.KEY_SUCCESS);
			if (result.equals(ServerConstants.VALUE_TRUE)) {
				return true;
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return false;
	}

}
