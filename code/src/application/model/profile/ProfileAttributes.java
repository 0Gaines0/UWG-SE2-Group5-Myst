package application.model.profile;

import application.model.game.Game;

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
	private int totalLikedGames;
	private int totalDislikedGame;

	private static final String DESCRIPTION_MUST_BE_VALID = "description inputted must not be null or empty";
	private static final String IMAGE_PATH_MUST_BE_VALID = "image path inputted must bot be null or empty";

	/**
	 * Instantiates a new profile attributes.
	 */
	public ProfileAttributes() {
		this.aboutMeDescription = "";
		this.userProfilePicturePath = "";
		this.totalLikedGames = 0;
		this.totalDislikedGame = 0;
	}

	/**
	 * Instantiates a new profile attributes.
	 *
	 * @param description   the description
	 * @param imagePath     the image path
	 * @param likedGames    the liked games
	 * @param dislikedGames the disliked games
	 */
	public ProfileAttributes(String description, String imagePath, int likedGames, int dislikedGames) {
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
		this.totalLikedGames = likedGames;
		this.totalDislikedGame = dislikedGames;

	}

	/**
	 * Increase liked game count.
	 */
	public void increaseLikedGameCount() {
		this.totalLikedGames++;
	}

	/**
	 * Increase disliked game count.
	 */
	public void increaseDislikedGameCount() {
		this.totalDislikedGame++;
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

	/**
	 * Gets the total liked games.
	 *
	 * @return the total liked games
	 */
	public int getTotalLikedGames() {
		return this.totalLikedGames;
	}

	/**
	 * Sets the total liked games.
	 *
	 * @param totalLikedGames the new total liked games
	 */
	public void setTotalLikedGames(int totalLikedGames) {
		this.totalLikedGames = totalLikedGames;
	}

	/**
	 * Gets the total disliked game.
	 *
	 * @return the total disliked game
	 */
	public int getTotalDislikedGame() {
		return this.totalDislikedGame;
	}

	/**
	 * Sets the total disliked game.
	 *
	 * @param totalDislikedGame the new total disliked game
	 */
	public void setTotalDislikedGame(int totalDislikedGame) {
		this.totalDislikedGame = totalDislikedGame;
	}

}
