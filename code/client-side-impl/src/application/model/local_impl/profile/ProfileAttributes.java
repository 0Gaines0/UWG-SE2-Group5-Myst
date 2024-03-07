package application.model.local_impl.profile;

import application.model.local_impl.game.Game;

/**
 * The Class ProfileAttributes.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class ProfileAttributes {
	private String aboutMeDescription;
	private Game favoriteGame;
	private String userProfilePicturePath;

	private static final String DESCRIPTION_MUST_BE_VALID = "description inputted must not be null or empty";
	private static final String IMAGE_PATH_MUST_BE_VALID = "image path inputted must not be null or empty";

	/**
	 * Instantiates a new profile attributes.
	 */
	public ProfileAttributes() {
		this.aboutMeDescription = "";
		this.userProfilePicturePath = "";
	}

	/**
	 * Instantiates a new profile attributes.
	 *
	 * @param description the description
	 * @param imagePath   the image path
	 */
	public ProfileAttributes(String description, String imagePath) {
		if (description == null) {
			throw new NullPointerException(DESCRIPTION_MUST_BE_VALID);
		} else if (description.isBlank()) {
			throw new IllegalArgumentException(DESCRIPTION_MUST_BE_VALID);
		} else if (imagePath == null) {
			throw new NullPointerException(IMAGE_PATH_MUST_BE_VALID);
		} else if (imagePath.isBlank()) {
			throw new IllegalArgumentException(IMAGE_PATH_MUST_BE_VALID);
		}
		this.aboutMeDescription = description;
		this.userProfilePicturePath = imagePath;

	}

	/**
	 * Gets the about me description.
	 *
	 * @return the about me description
	 */
	public String getAboutMeDescription() {
		return this.aboutMeDescription;
	}

	/**
	 * Sets the about me description.
	 *
	 * @param aboutMeDescription the new about me description
	 */
	public void setAboutMeDescription(String aboutMeDescription) {
		this.aboutMeDescription = aboutMeDescription;
	}

	/**
	 * Gets the favorite game.
	 *
	 * @return the favorite game
	 */
	public Game getFavoriteGame() {
		return this.favoriteGame;
	}

	/**
	 * Sets the favorite game.
	 *
	 * @param favoriteGame the new favorite game
	 */
	public void setFavoriteGame(Game favoriteGame) {
		this.favoriteGame = favoriteGame;
	}

	/**
	 * Gets the user profile picture.
	 *
	 * @return the user profile picture
	 */
	public String getUserProfilePicturePath() {
		return this.userProfilePicturePath;
	}

	/**
	 * Sets the user profile picture.
	 *
	 * @param userProfilePicture the new user profile picture
	 */
	public void setUserProfilePicturePath(String userProfilePicture) {
		this.userProfilePicturePath = userProfilePicture;
	}

}
