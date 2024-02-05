package application.model.profile;

import java.util.ArrayList;
import java.util.List;

import application.model.game.Game;

/**
 * The Class UserProfile.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class UserProfile {
	private List<Game> allOwnedGames;
	private List<Game> allLikedGames;
	private List<Game> allDislikedGames;
	
	private String username;
	private String password;
	
	private static final String USERNAME_MUST_NOT_BE_NULL_OR_EMPTY = "username must not be null or empty";
	private static final String PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY = "password must not be null or empty";
	private static final String INPUT_LIST_MUST_NOT_BE_NULL = "inputted game list must not be null";
	
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
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		}
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
		if (allOwnedGames == null) {
			throw new NullPointerException(INPUT_LIST_MUST_NOT_BE_NULL);
		}
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
		if (allLikedGames == null) {
			throw new NullPointerException(INPUT_LIST_MUST_NOT_BE_NULL);
		}
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
		if (allDislikedGames == null) {
			throw new NullPointerException(INPUT_LIST_MUST_NOT_BE_NULL);
		}
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
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		}
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
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		this.password = password;
	}
}
