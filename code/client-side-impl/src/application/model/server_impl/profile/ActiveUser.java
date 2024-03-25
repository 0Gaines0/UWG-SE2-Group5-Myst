package application.model.server_impl.profile;

import org.json.JSONException;
import org.json.JSONObject;

import application.model.server_impl.Server;

/**
 * The Class ActiveUser.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class ActiveUser {
	private static application.model.local_impl.profile.UserProfile activeUser;

	/**
	 * Gets the active user.
	 *
	 * @return the active user
	 */
	public static application.model.local_impl.profile.UserProfile getActiveUser() {
		return activeUser;
	}

	/**
	 * Sets the active user.
	 *
	 * @param activeUser the new active user
	 */
	public static void setActiveUser(application.model.local_impl.profile.UserProfile activeUser) {
		var json = new JSONObject();
		try {
			json.put("request_type", "set_active_user");
			json.put("username", activeUser.getUsername());
			Server.sendRequest(json.toString());
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		ActiveUser.activeUser = activeUser;
	}
	
	
}
