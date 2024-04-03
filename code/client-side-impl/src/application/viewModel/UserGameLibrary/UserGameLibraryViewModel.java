package application.viewModel.UserGameLibrary;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserGameLibrary;
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
	
	/** The liked games list property. */
	private ListProperty<Game> likedGamesListProperty;
	
	/** The disliked games list property. */
	private ListProperty<Game> dislikedGamesProperty;
	
	/** The owned games list property. */
	private ListProperty<Game> ownedGamesProperty;
	
	/** The selected game. */
	private Game selectedGame;
	
	/** The selected game genres list property. */
	private ListProperty<Genre> selectedGameGenresListProperty;
	
	/** The selected game name property. */
	private StringProperty selectedGameNameProperty;
	
	/** The selected game developer property. */
	private StringProperty selectedGameDeveloperProperty;
	
	private ListProperty<Game> selectedGamesListProperty;

	/**
	 * Creates a new userGameLibraryViewModel.
	 */
	public UserGameLibraryViewModel() {
		this.likedGamesListProperty = new SimpleListProperty<Game>();
		this.dislikedGamesProperty = new SimpleListProperty<Game>();
		this.ownedGamesProperty = new SimpleListProperty<Game>();
		this.selectedGameNameProperty = new SimpleStringProperty();
		this.selectedGameDeveloperProperty = new SimpleStringProperty();
		this.selectedGameGenresListProperty = new SimpleListProperty<Genre>();
		this.selectedGamesListProperty = new SimpleListProperty<Game>();

	}

	/**
	 * Sets the up game library.
	 */
	public void setUpGameLibrary() {
		this.userGameLibrary = new UserGameLibrary(ActiveUser.getActiveUser());
		ObservableList<Game> likedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllLikedGames());
		ObservableList<Game> dislikedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllDislikedGames());
		ObservableList<Game> ownedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllOwnedGames());
		this.likedGamesListProperty.set(likedGames);
		this.dislikedGamesProperty.set(dislikedGames);
		this.ownedGamesProperty.set(ownedGames);
	}

	/**
	 * Gets the list of owned Games.
	 *
	 * @return the list of owned games
	 * @pre none
	 * @post none
	 */
	public ListProperty<Game> getLikedGames() {
		return this.likedGamesListProperty;
	}
	
	/**
	 * Gets the list of disliked games.
	 * 
	 * @return the list of disliked games
	 * @pre none
	 * @post none
	 */
	public ListProperty<Game> getDislikedGames() {
		return this.dislikedGamesProperty;
	}
	
	/**
	 * Gets the list of owned games.
	 * 
	 * @return the list of owned games.
	 * @pre none
	 * @post none
	 */
	public ListProperty<Game> getOwnedGames() {
		return this.ownedGamesProperty;
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
	
	public ListProperty<Game> getSelectedGamesListProperty() {
		return this.selectedGamesListProperty;
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
	
	public void setSelectedList(String selectedList) {
		ObservableList<Game> likedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllLikedGames());
		ObservableList<Game> dislikedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllDislikedGames());
		ObservableList<Game> ownedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllOwnedGames());
		if (selectedList.equals("Liked Games")) {
			this.selectedGamesListProperty.clear();
			this.selectedGamesListProperty.set(likedGames);
		} else if (selectedList.equals("Disliked Games")) {
			this.selectedGamesListProperty.clear();
			this.selectedGamesListProperty.set(dislikedGames);
		} else {
			this.selectedGamesListProperty.clear();
			this.selectedGamesListProperty.set(ownedGames);
		}
	}
	
	public boolean removeSelectedGameFromList() {
		if (this.selectedGame != null) {
			var game = this.selectedGame;
			var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
			likedGames.remove(game);
			ActiveUser.getActiveUser().setAllLikedGames(likedGames);
			ObservableList<Game> updatedLikedGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getAllLikedGames());
			this.likedGamesListProperty.set(updatedLikedGames);
			this.selectedGamesListProperty.clear();
			this.selectedGamesListProperty.set(updatedLikedGames);
			return true;
		}
		return false;
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
