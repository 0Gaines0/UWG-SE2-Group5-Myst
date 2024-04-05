package application.model.server_impl.profile;

import org.json.JSONException;
import org.json.JSONObject;

import application.model.server_impl.Server;
import application.model.server_impl.ServerConstants;

/**
 * The Class ActiveUser.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class ActiveUser {
	private static UserProfile activeUser;

	/**
	 * Gets the active user.
	 *
	 * @return the active user
	 */
	public static UserProfile getActiveUser() {
		return activeUser;
	}

	/**
	 * Sets the active user.
	 *
	 * @param activeUser the new active user
	 */
	public static void setActiveUser(UserProfile activeUser) {
		var json = new JSONObject();
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.KEY_REQUEST_TYPE);
			json.put(ServerConstants.KEY_USERNAME, activeUser.getUsername());
			Server.sendRequest(json.toString());
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		ActiveUser.activeUser = activeUser;
	}
	
	
}
