package application.model.server_impl.profile;

import java.util.List;

import application.model.local_impl.game.Game;

/**
 * The Class UserGameLibrary.
 * @author Jeffrey Gaines
 * @version Sprint 2
 */
public class UserGameLibrary {
	
	private UserProfile user;
	private List<Game> library;
	private List<Game> dislikedGames;
	private List<Game> ownedGames;
	
	
	/**
	 * Instantiates a new user game library.
	 *
	 * @param user the user
	 */
	public UserGameLibrary(UserProfile user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null.");
		}
		
		this.user = user;
		this.library = user.getAllLikedGames();
		this.dislikedGames = user.getAllDislikedGames();
		this.ownedGames = user.getAllOwnedGames();
	}
	
	/**
	 * Gets the user's library
	 * @return the owned games
	 */
	public List<Game> getLikedGames() {
		
		return this.library;
	}
	
	/**
	 * Gets the user's disliked games
	 * @return the disliked games
	 */
	public List<Game> getDislikedGames() {
		
		return this.dislikedGames;
	}
	
	/**
	 * Gets the user's owned games
	 * @return the owned games
	 */
	public List<Game> getOwnedGames() {
		
		return this.ownedGames;
	}
	
	/**
	 * Gets the user profile
	 * @return the user
	 */
	public UserProfile getUser() {
		
		return this.user;
	}
}
