package application.model.server_impl.profile.credentials;

import org.json.JSONException;
import org.json.JSONObject;

import application.model.local_impl.profile.credentials.Credential;
import application.model.server_impl.Server;

public class CredentialManager extends application.model.abstract_impl.profile.credentials.CredentialManager {

	@Override
	public boolean userNameExist(String username) {
		var json = new JSONObject();
		try {
			json.put("request_type", "username_exist");
			json.put("username", username);
			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);
			
			var result = responseJson.get("success");
			if (result.equals("true")) {
				return true;
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

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
		var json = new JSONObject();
		try {
			json.put("request_type", "add_credential");
			json.put("username", username);
			json.put("password", password);
			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);
			
			var result = responseJson.get("success");
			if (result.equals("true")) {
				return true;
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		return false;
	}

}
