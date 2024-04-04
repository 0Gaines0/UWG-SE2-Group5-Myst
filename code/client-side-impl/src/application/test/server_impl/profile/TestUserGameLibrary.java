package application.test.server_impl.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserGameLibrary;
import application.model.server_impl.profile.UserProfile;

public class TestUserGameLibrary {
	@Test
	void testInvalidConstruction() {
		assertThrows(IllegalArgumentException.class, () -> new UserGameLibrary(null));
	}
	
	@Test
	void testValidConstrution() {
		UserProfile user = new UserProfile("username", "password");
		ActiveUser.setActiveUser(user);

		UserGameLibrary gameLibrary = new UserGameLibrary(user);
		
		assertEquals(user, gameLibrary.getUser());
		assertEquals(user.getAllOwnedGames(), gameLibrary.getOwnedGames());
	}

}
