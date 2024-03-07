package application.viewModel.UserGameLibrary;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.UserGameLibrary;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The userGameLibraryViewModel.
 *
 * @author Dillon Emmons
 * @version Sprint 1
 */
public class UserGameLibraryViewModel {

	/** The user game library. */
	private UserGameLibrary userGameLibrary;
	
	/** The owned games list property. */
	private ListProperty<Game> ownedGamesListProperty;
	
	/** The selected game. */
	private Game selectedGame;
	
	/** The selected game genres list property. */
	private ListProperty<Genre> selectedGameGenresListProperty;
	
	/** The selected game name property. */
	private StringProperty selectedGameNameProperty;
	
	/** The selected game developer property. */
	private StringProperty selectedGameDeveloperProperty;

	/**
	 * Creates a new userGameLibraryViewModel.
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
		this.userGameLibrary = new UserGameLibrary(ActiveUser.getActiveUser());
		ObservableList<Game> ownedGames = FXCollections.observableArrayList(this.userGameLibrary.getGameLibrary());
		this.ownedGamesListProperty.set(ownedGames);
	}

	/**
	 * Gets the list of owned Games.
	 *
	 * @return the list of owned games
	 * @pre none
	 * @post none
	 */
	public ListProperty<Game> getOwnedGames() {
		return this.ownedGamesListProperty;
	}

	/**
	 * Gets the selected game name.
	 *
	 * @return the selected game name
	 * @pre none
	 * @post none
	 */
	public StringProperty getSelectedGameName() {
		return this.selectedGameNameProperty;
	}

	/**
	 * Gets the selected game developers.
	 *
	 * @return the selected game developers.
	 * @pre none
	 * @post none
	 */
	public StringProperty getSelectedGameDevelopers() {
		return this.selectedGameDeveloperProperty;
	}

	/**
	 * Gets the selected game.
	 *
	 * @return the selected game.
	 * @pre none
	 * @post none
	 */
	public Game getSelectedGame() {
		return this.selectedGame;
	}

	/**
	 * Sets the selected game.
	 *
	 * @param game the game to set.
	 * @pre !game = null
	 * @post this.selectedGame = game
	 */
	public void setSelectedGame(Game game) {
		this.selectedGame = game;
		ObservableList<Genre> genres = FXCollections.observableArrayList(game.getGenres());
		this.selectedGameGenresListProperty.clear();
		this.selectedGameGenresListProperty.set(genres);
		this.selectedGameNameProperty.set(this.selectedGame.getName());
		this.selectedGameDeveloperProperty.set(this.selectedGame.getDevelopers());

	}

	
	/**
	 * Gets the selected game genres.
	 *
	 * @return the selected game genres
	 */
	public ListProperty<Genre> getSelectedGameGenres() {
		return this.selectedGameGenresListProperty;
	}

}
