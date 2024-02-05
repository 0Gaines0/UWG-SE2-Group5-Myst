package application.model.profile;

import java.util.ArrayList;
import java.util.List;

import application.model.game.Game;

/**
 * The Class UserProfile.
 * @author Jeffrey Gaines
 */
public class UserProfile {
	private List<Game> allOwnedGames;
	private List<Game> allLikedGames;
	private List<Game> allDislikedGames;
	
	private String username;
	private String password;
	
	/**
	 * Instantiates a new user profile.
	 */
	public UserProfile() {
		this.setUpUserGameData();
	}
	
	/**
	 * Instantiates a new user profile.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public UserProfile(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	private void setUpUserGameData() {
		this.allOwnedGames = new ArrayList<Game>();
		this.allLikedGames = new ArrayList<Game>();
		this.allDislikedGames = new ArrayList<Game>();
	}

	/**
	 * Gets the all owned games.
	 *
	 * @return the all owned games
	 */
	public List<Game> getAllOwnedGames() {
		return this.allOwnedGames;
	}

	/**
	 * Sets the all owned games.
	 *
	 * @param allOwnedGames the new all owned games
	 */
	public void setAllOwnedGames(List<Game> allOwnedGames) {
		this.allOwnedGames = allOwnedGames;
	}

	/**
	 * Gets the all liked games.
	 *
	 * @return the all liked games
	 */
	public List<Game> getAllLikedGames() {
		return this.allLikedGames;
	}

	/**
	 * Sets the all liked games.
	 *
	 * @param allLikedGames the new all liked games
	 */
	public void setAllLikedGames(List<Game> allLikedGames) {
		this.allLikedGames = allLikedGames;
	}

	/**
	 * Gets the all disliked games.
	 *
	 * @return the all disliked games
	 */
	public List<Game> getAllDislikedGames() {
		return this.allDislikedGames;
	}

	/**
	 * Sets the all disliked games.
	 *
	 * @param allDislikedGames the new all disliked games
	 */
	public void setAllDislikedGames(List<Game> allDislikedGames) {
		this.allDislikedGames = allDislikedGames;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
