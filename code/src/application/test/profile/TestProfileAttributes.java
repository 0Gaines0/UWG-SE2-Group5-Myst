/*
 * 
 */
package application.test.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ProfileAttributes;

public class TestProfileAttributes {

	private ProfileAttributes profileAttributes;

	private static final String TEST_DESCRIPTION = "Test description";
	private static final String TEST_IMAGE_PATH = "path/to/image.png";

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.profileAttributes = new ProfileAttributes();
	}

	/**
	 * Test default contructor.
	 */
	@Test
	public void testDefaultContructor() {
		assertNotNull(this.profileAttributes);
		assertEquals("", this.profileAttributes.getAboutMeDescription());
		assertNotNull(this.profileAttributes.getUserProfilePicturePath());
		assertEquals(0, this.profileAttributes.getTotalLikedGames());
		assertEquals(0, this.profileAttributes.getTotalDislikedGame());
	}

	/**
	 * Test parameter constructor.
	 */
	@Test
	public void testParameterConstructor() {
		var description = TEST_DESCRIPTION;
		var imagePath = TEST_IMAGE_PATH;
		var likedGames = 5;
		var dislikedGames = 3;

		var profile = new ProfileAttributes(description, imagePath, likedGames, dislikedGames);

		assertEquals(description, profile.getAboutMeDescription());
		assertNotNull(profile.getUserProfilePicturePath());
		assertEquals(likedGames, profile.getTotalLikedGames());
		assertEquals(dislikedGames, profile.getTotalDislikedGame());
	}

	/**
	 * Test null description constructor.
	 */
	@Test
	public void testNullDescriptionConstructor() {
		assertThrows(NullPointerException.class, () -> new ProfileAttributes(null, TEST_IMAGE_PATH, 5, 3));
	}
	
	/**
	 * Test empty description constructor.
	 */
	@Test
	public void testEmptyDescriptionConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new ProfileAttributes("", TEST_IMAGE_PATH, 5, 3));
	}
	
	/**
	 * Test null image path constructor.
	 */
	@Test
	public void testNullImagePathConstructor() {
		assertThrows(NullPointerException.class, () -> new ProfileAttributes(TEST_DESCRIPTION, null, 5, 3));
	}
	
	/**
	 * Test empty image path constructor.
	 */
	@Test
	public void testEmptyImagePathConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new ProfileAttributes(TEST_DESCRIPTION, "", 5, 3));
	}
	
	/**
	 * Test increase liked game count.
	 */
	@Test
	public void testIncreaseLikedGameCount() {
		this.profileAttributes.increaseLikedGameCount();
		assertEquals(1, this.profileAttributes.getTotalLikedGames());
	}
	
	/**
	 * Test decrease disliked game count.
	 */
	@Test
	public void testDecreaseDislikedGameCount() {
		this.profileAttributes.increaseDislikedGameCount();
		assertEquals(1, this.profileAttributes.getTotalDislikedGame());
	}
	
	/**
	 * Test set about me description.
	 */
	@Test
	public void testSetAboutMeDescription() {
		var description = TEST_DESCRIPTION;
		this.profileAttributes.setAboutMeDescription(description);
		assertEquals(description, this.profileAttributes.getAboutMeDescription());
	}
	
	/**
	 * Test set favorite game.
	 */
	@Test
	public void testSetFavoriteGame() {
		var genre = new ArrayList<Genre>();
		genre.add(Genre.ACCOUNTING);
		var game = new Game("test name", genre, 1990);
		this.profileAttributes.setFavoriteGame(game);
		assertEquals(game, this.profileAttributes.getFavoriteGame());
	}
	
	/**
	 * Test set user profile picture.
	 */
	@Test
	public void testSetUserProfilePicture() {
		this.profileAttributes.setUserProfilePicturePath(TEST_IMAGE_PATH);
		assertEquals(TEST_IMAGE_PATH, this.profileAttributes.getUserProfilePicturePath());
	}
	
	/**
	 * Test set total liked games.
	 */
	@Test
	public void testSetTotalLikedGames() {
		this.profileAttributes.setTotalLikedGames(10);
		assertEquals(10, this.profileAttributes.getTotalLikedGames());
	}
	
	/**
	 * Test set total disliked games.
	 */
	@Test
	public void testSetTotalDislikedGames() {
		this.profileAttributes.setTotalDislikedGame(10);
		assertEquals(10, this.profileAttributes.getTotalDislikedGame());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
