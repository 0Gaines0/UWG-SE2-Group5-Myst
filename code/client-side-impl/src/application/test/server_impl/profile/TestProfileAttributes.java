package application.test.server_impl.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.ProfileAttributes;
import application.model.server_impl.profile.UserProfile;

public class TestProfileAttributes {
	
	private static final String TEST_DESCRIPTION = "Test description";
	private static final String TEST_IMAGE_PATH = "path/to/image.png";

	/**
	 * Test parameter constructor.
	 */
	@Test
	public void testParameterConstructor() {
		ActiveUser.setActiveUser(new UserProfile("username", "password"));
		var description = TEST_DESCRIPTION;
		var imagePath = TEST_IMAGE_PATH;


		var profile = new ProfileAttributes(description, imagePath);

		assertEquals("", profile.getAboutMeDescription());
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
	
}
