package application.viewModel.UserGameLibrary;

import application.model.game.Game;
import application.model.profile.UserGameLibrary;
import application.model.profile.UserProfile;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

/**
 * The userGameLibraryViewModel
 * @author Dillon Emmons
 * @version Sprint 1
 */
public class UserGameLibraryViewModel {
	
	private UserGameLibrary userGameLibrary;
	private UserProfile user = new UserProfile();
	
	private ListProperty<Game> ownedGamesListProperty;
	
	/**
	 * Creates a new userGameLibraryViewModel
	 */
	public UserGameLibraryViewModel() {
		
		this.userGameLibrary = new UserGameLibrary(user);
		this.ownedGamesListProperty = new SimpleListProperty<Game>();
	}
	
	/**
	 * Gets the list of owned Games
	 * @pre none
	 * @post none
	 * @return the list of owned games
	 */
	public ListProperty<Game> getOwnedGames() {
		return this.ownedGamesListProperty;
	}

}
