package application.test.server_impl.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserProfile;

public class TestUserProfile {

	private UserProfile userProfile;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.userProfile = new UserProfile("username", "password");
		ActiveUser.setActiveUser(this.userProfile);
	}

	/**
	 * Test parameterized constructor.
	 */
	@Test
	public void testParameterizedConstructor() {
		var name = "testUser";
		var password = "testPassword";

		var userProfile = new UserProfile(name, password);

		assertEquals(name, userProfile.getUsername());
		assertEquals(password, userProfile.getPassword());
	}

	/**
	 * Test get all owned games.
	 */
	@Test
	public void testGetAllOwnedGames() {
		assertNotNull(this.userProfile.getAllOwnedGames());
	}

	/**
	 * Test set all owned games.
	 */
	@Test
	public void testSetAllOwnedGames() {
		var games = this.userProfile.getAllOwnedGames();
		games.add(new Game("testGame", new ArrayList<Genre>(), 01));
		this.userProfile.setAllOwnedGames(games);
		assertNotNull(this.userProfile.getAllOwnedGames());

	}

	/**
	 * Test get all liked games.
	 */
	@Test
	public void testGetAllLikedGames() {
		assertNotNull(this.userProfile.getAllLikedGames());
	}

	/**
	 * Test set all liked games.
	 */
	@Test
	public void testSetAllLikedGames() {
		var games = this.userProfile.getAllLikedGames();
		games.add(new Game("testGame", new ArrayList<Genre>(), 01));
		this.userProfile.setAllLikedGames(games);
		assertNotNull(this.userProfile.getAllLikedGames());

	}

	/**
	 * Test get all disliked games.
	 */
	@Test
	public void testGetAllDislikedGames() {
		assertNotNull(this.userProfile.getAllDislikedGames());

	}

	/**
	 * Test set all disliked games.
	 */
	@Test
	public void testSetAllDislikedGames() {
		var games = this.userProfile.getAllDislikedGames();
		games.add(new Game("testGame", new ArrayList<Genre>(), 01));
		this.userProfile.setAllDislikedGames(games);
		assertNotNull(this.userProfile.getAllDislikedGames());

	}

	/**
	 * Test get preferred genres.
	 */
	@Test
	public void testGetPreferredGenres() {
		assertNotNull(this.userProfile.getPreferredGenres());
	}

	/**
	 * Test set preferred genres.
	 */
	@Test
	public void testSetPreferredGenres() {
		var genres = this.userProfile.getPreferredGenres();
		genres.add(Genre.ACCOUNTING);
		this.userProfile.setPreferredGenres(genres);
		assertNotNull(this.userProfile.getPreferredGenres());

	}
	
	/**
	 * Test is first time login.
	 */
	@Test
	public void testIsFirstTimeLogin() {
		assertFalse(this.userProfile.isFirstTimeLogin());
	}
	
	/**
	 * Test set first time login.
	 */
	@Test
	public void testSetFirstTimeLogin() {
		this.userProfile.setFirstTimeLogin(false);
		assertFalse(this.userProfile.isFirstTimeLogin());

	}
	
	/**
	 * Test calculate genre percentages.
	 */
	@Test
	public void testCalculateGenrePercentages() {
		assertNotNull(this.userProfile.calculateGenrePercentages());
	}

	/**
	 * Test constructor with null username.
	 */
	@Test
	public void testConstructorWithNullUsername() {
		assertThrows(NullPointerException.class, () -> new UserProfile(null, "testPassword"));
	}

	/**
	 * Test constructor with blank username.
	 */
	@Test
	public void testConstructorWithBlankUsername() {
		assertThrows(IllegalArgumentException.class, () -> new UserProfile("", "testPassword"));
	}

	/**
	 * Test constructor with null password.
	 */
	@Test
	public void testConstructorWithNullPassword() {
		assertThrows(NullPointerException.class, () -> new UserProfile("testUser", null));
	}

	/**
	 * Test constructor with blank password.
	 */
	@Test
	public void testConstructorWithBlankPassword() {
		assertThrows(IllegalArgumentException.class, () -> new UserProfile("testUser", ""));
	}
}
