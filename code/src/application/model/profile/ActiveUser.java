package application.model.profile;

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
		ActiveUser.activeUser = activeUser;
	}
	
	
}
