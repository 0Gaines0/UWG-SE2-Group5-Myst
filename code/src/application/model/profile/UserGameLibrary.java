package application.model.profile;

import java.util.List;

import application.model.game.Game;

/**
 * Stores and manages the game library for a user.
 * 
 * @author Dillon Emmons
 * @version Sprint 1
 */
public class UserGameLibrary {
	
	private UserProfile user;
	private List<Game> library;
	
	/**
	 * Instantiates a new UserGameLibrary.
	 *
	 * @param user the user
	 */
	public UserGameLibrary(UserProfile user) {
		this.user = user;
		this.library = user.getAllOwnedGames();
	}
	
	/**
	 * Gets the user's library
	 * @return the owned games
	 */
	public List<Game> getGameLibrary() {
		
		return this.library;
	}
	
	/**
	 * Gets the user profile
	 * @return the user
	 */
	public UserProfile getUser() {
		
		return this.user;
	}
}
