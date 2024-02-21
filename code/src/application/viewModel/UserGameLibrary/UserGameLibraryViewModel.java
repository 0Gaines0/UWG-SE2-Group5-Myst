package application.viewModel.UserGameLibrary;

import application.model.game.Game;
import application.model.game.Genre;
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
	private Game selectedGame;
	private ListProperty<Genre> selectedGameGenresListProperty;
	
	/**
	 * Creates a new userGameLibraryViewModel
	 */
	public UserGameLibraryViewModel() {
		
		this.userGameLibrary = new UserGameLibrary(user);
		this.ownedGamesListProperty = new SimpleListProperty<Game>();
		this.selectedGameGenresListProperty = new SimpleListProperty<Genre>();
		this.ownedGamesListProperty.addAll(this.userGameLibrary.getGameLibrary());
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
	
	/**
	 * Gets the selected game.
	 * @pre none
	 * @post none
	 * @return the selected game.
	 */
	public Game getSelectedGame() {
		return this.selectedGame;
	}
	
	/**
	 * Sets the selected game.
	 * @pre !game = null
	 * @post this.selectedGame = game
	 * @param game the game to set.
	 */
	public void setSelectedGame(Game game) {
		this.selectedGame = game;
		this.selectedGameGenresListProperty.addAll(game.getGenres());
		
	}
	
	public ListProperty<Genre> getSelectedGameGenres() {
		return this.selectedGameGenresListProperty;
	}

}
