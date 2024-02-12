package application.model.profile;

import javax.swing.ImageIcon;

import application.model.game.Game;


/**
 * The Class ProfileAttributes.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class ProfileAttributes {
	private String aboutMeDescription;
	private Game favoriteGame;
	private ImageIcon userProfilePicture;
	private int totalLikedGames;
	private int totalDislikedGame;
	
	/**
	 * Instantiates a new profile attributes.
	 */
	public ProfileAttributes() {
		this.aboutMeDescription = "";
		this.userProfilePicture = new ImageIcon();
		this.totalLikedGames = 0;
		this.totalDislikedGame = 0;
	}
	
	/**
	 * Instantiates a new profile attributes.
	 *
	 * @param description the description
	 * @param imagePath the image path
	 * @param likedGames the liked games
	 * @param dislikedGames the disliked games
	 */
	public ProfileAttributes(String description, String imagePath, int likedGames, int dislikedGames) {
		this.aboutMeDescription = description;
		this.userProfilePicture = new ImageIcon(imagePath);
		
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
	public ImageIcon getUserProfilePicture() {
		return this.userProfilePicture;
	}

	/**
	 * Sets the user profile picture.
	 *
	 * @param userProfilePicture the new user profile picture
	 */
	public void setUserProfilePicture(ImageIcon userProfilePicture) {
		this.userProfilePicture = userProfilePicture;
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
