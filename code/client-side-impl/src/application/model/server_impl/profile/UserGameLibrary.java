package application.model.server_impl.profile;

import java.util.List;

import application.model.local_impl.game.Game;
import application.model.server_impl.profile.UserProfile;

/**
 * The Class UserGameLibrary.
 * @author Jeffrey Gaines
 * @version Sprint 2
 */
public class UserGameLibrary {
	
	private UserProfile user;
	private List<Game> library;
	
	
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
