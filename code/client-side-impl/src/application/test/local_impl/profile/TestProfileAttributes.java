/*
 * 
 */
package application.test.local_impl.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ProfileAttributes;

/**
 * The Class TestProfileAttributes.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
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
	}

	/**
	 * Test parameter constructor.
	 */
	@Test
	public void testParameterConstructor() {
		var description = TEST_DESCRIPTION;
		var imagePath = TEST_IMAGE_PATH;


		var profile = new ProfileAttributes(description, imagePath);

		assertEquals(description, profile.getAboutMeDescription());
		assertNotNull(profile.getUserProfilePicturePath());

	}

	/**
	 * Test null description constructor.
	 */
	@Test
	public void testNullDescriptionConstructor() {
		assertThrows(NullPointerException.class, () -> new ProfileAttributes(null, TEST_IMAGE_PATH));
	}
	
	/**
	 * Test empty description constructor.
	 */
	@Test
	public void testEmptyDescriptionConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new ProfileAttributes("", TEST_IMAGE_PATH));
	}
	
	/**
	 * Test null image path constructor.
	 */
	@Test
	public void testNullImagePathConstructor() {
		assertThrows(NullPointerException.class, () -> new ProfileAttributes(TEST_DESCRIPTION, null));
	}
	
	/**
	 * Test empty image path constructor.
	 */
	@Test
	public void testEmptyImagePathConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new ProfileAttributes(TEST_DESCRIPTION, ""));
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
