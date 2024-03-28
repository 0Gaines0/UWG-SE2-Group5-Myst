package application.model.abstract_impl.profile;

import application.model.local_impl.game.Game;

public abstract class ProfileAttributes {

	/**
	 * Gets the about me description.
	 *
	 * @return the about me description
	 */
	public abstract String getAboutMeDescription();

	/**
	 * Sets the about me description.
	 *
	 * @param aboutMeDescription the new about me description
	 */
	public abstract void setAboutMeDescription(String aboutMeDescription);

	/**
	 * Gets the favorite game.
	 *
	 * @return the favorite game
	 */
	public abstract Game getFavoriteGame();

	/**
	 * Sets the favorite game.
	 *
	 * @param favoriteGame the new favorite game
	 */
	public abstract void setFavoriteGame(Game favoriteGame);

	/**
	 * Gets the user profile picture path.
	 *
	 * @return the user profile picture path
	 */
	public abstract String getUserProfilePicturePath();

	/**
	 * Sets the user profile picture path.
	 *
	 * @param userProfilePicture the new user profile picture path
	 */
	public abstract void setUserProfilePicturePath(String userProfilePicture);

}
