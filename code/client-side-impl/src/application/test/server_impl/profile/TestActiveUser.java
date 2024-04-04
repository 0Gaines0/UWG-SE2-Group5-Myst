package application.test.server_impl.profile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserProfile;

public class TestActiveUser {

	/**
	 * Test get and active server user.
	 */
	@Test
	public void testGetAndActiveServerUser() {
		var userProfile = new UserProfile("username", "password");
		ActiveUser.setActiveUser(userProfile);
		assertNotNull(ActiveUser.getActiveUser());
	}
	
}
