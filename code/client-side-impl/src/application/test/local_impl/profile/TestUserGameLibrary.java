package application.test.local_impl.profile;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import application.model.local_impl.profile.UserGameLibrary;
import application.model.local_impl.profile.UserProfile;

class TestUserGameLibrary {

	@Test
	void testInvalidConstruction() {
		assertThrows(IllegalArgumentException.class, () -> new UserGameLibrary(null));
	}
	
	@Test
	void testValidConstrution() {
		UserProfile user = new UserProfile();
		UserGameLibrary gameLibrary = new UserGameLibrary(user);
		
		assertNotNull(gameLibrary.getLikedGames());
		assertNotNull(gameLibrary.getDislikedGames());
		
		assertEquals(user, gameLibrary.getUser());
		assertEquals(user.getAllOwnedGames(), gameLibrary.getOwnedGames());
	}

}
