package application.viewModel.UserGameLibrary;

import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.model.profile.UserGameLibrary;
import application.model.profile.UserProfile;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The userGameLibraryViewModel
 * 
 * @author Dillon Emmons
 * @version Sprint 1
 */
public class UserGameLibraryViewModel {

	private UserGameLibrary userGameLibrary;
	private UserProfile user = ActiveUser.getActiveUser();

	private ListProperty<Game> ownedGamesListProperty;
	private Game selectedGame;
	private ListProperty<Genre> selectedGameGenresListProperty;
	private StringProperty selectedGameNameProperty;
	private StringProperty selectedGameDeveloperProperty;

	/**
	 * Creates a new userGameLibraryViewModel
	 */
	public UserGameLibraryViewModel() {

		this.ownedGamesListProperty = new SimpleListProperty<Game>();
		this.selectedGameNameProperty = new SimpleStringProperty();
		this.selectedGameDeveloperProperty = new SimpleStringProperty();
		this.selectedGameGenresListProperty = new SimpleListProperty<Genre>();
		
		
	}

	/**
	 * Sets the up game library.
	 */
	public void setUpGameLibrary() {
		this.userGameLibrary = new UserGameLibrary(this.user);
		ObservableList<Game> ownedGames = FXCollections.observableArrayList(this.userGameLibrary.getGameLibrary());
		this.ownedGamesListProperty.set(ownedGames);
	}

	/**
	 * Gets the list of owned Games
	 * 
	 * @pre none
	 * @post none
	 * @return the list of owned games
	 */
	public ListProperty<Game> getOwnedGames() {
		return this.ownedGamesListProperty;
	}
	
	/**
	 * Gets the selected game name.
	 * @pre none
	 * @post none
	 * @return the selected game name
	 */
	public StringProperty getSelectedGameName() {
		return this.selectedGameNameProperty;
	}
	
	/**
	 * Gets the selected game developers.
	 * @pre none
	 * @post none
	 * @return the selected game developers.
	 */
	public StringProperty getSelectedGameDevelopers() {
		return this.selectedGameDeveloperProperty;
	}

	/**
	 * Gets the selected game.
	 * 
	 * @pre none
	 * @post none
	 * @return the selected game.
	 */
	public Game getSelectedGame() {
		return this.selectedGame;
	}

	/**
	 * Sets the selected game.
	 * 
	 * @pre !game = null
	 * @post this.selectedGame = game
	 * @param game the game to set.
	 */
	public void setSelectedGame(Game game) {
		this.selectedGame = game;
		this.selectedGameGenresListProperty.addAll(game.getGenres());
		this.selectedGameNameProperty.set(this.selectedGame.getName());
		this.selectedGameDeveloperProperty.set(this.selectedGame.getDevelopers());

	}

	/**
	 * Gets the list of genres for the selected game
	 * @pre none
	 * @post none
	 * @return the selected game's genres.
	 */
	public ListProperty<Genre> getSelectedGameGenres() {
		return this.selectedGameGenresListProperty;
	}

}
