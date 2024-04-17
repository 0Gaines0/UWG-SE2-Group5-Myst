package application.viewModel.login;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.ActiveUser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * the first time login page view model.
 *
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class PreferencePageViewModel {
	
	/** The liked games. */
	private ListProperty<Game> allGames;	
	private ListProperty<Genre> allGenres;
	
	private List<Game> selectedLikedGames;
	private List<Genre> selectedLikedGenres;
	
	/**
	 * the first time login page view model.
	 */
	public PreferencePageViewModel() {
		this.allGames = new SimpleListProperty<Game>();
		this.allGenres = new SimpleListProperty<Genre>();
		
		this.selectedLikedGames = new ArrayList<Game>();
		this.selectedLikedGenres = new ArrayList<Genre>();
	}
	
	/**
	 * Search all games and filter.
	 *
	 * @param filterValue the filter value
	 */
	public void searchAllGamesAndFilter(String filterValue) {
		var filteredList = new ArrayList<Game>();
		
		for (var game : Main.getGames()) {
			if (game.getName().toLowerCase().startsWith(filterValue.toLowerCase())) {
				filteredList.add(game);
			} else if (game.getName().toLowerCase().contains(filterValue.toLowerCase()) && !filteredList.contains(game)) {
				filteredList.add(game);
			}
		}
		ObservableList<Game> filteredGames = FXCollections.observableArrayList(filteredList);
		this.allGames.setValue(filteredGames);
		
	}
	
	/**
	 * Sets the up all games list.
	 */
	public void setUpAllGamesList() {
		ObservableList<Game> allGamesList = FXCollections.observableArrayList(Main.getGames());
    	this.allGames.setValue(allGamesList);
	}
	
	/**
	 * Sets the up all genres list.
	 */
	public void setUpAllGenresList() {
		ObservableList<Genre> allGenreList = FXCollections.observableArrayList(Genre.values());
		this.allGenres.setValue(allGenreList);
	}

	/**
	 * Configure new user preferences.
	 */
	public void configureNewUserPreferences() {
		ActiveUser.getActiveUser().setAllLikedGames(this.selectedLikedGames);
		ActiveUser.getActiveUser().setPreferredGenres(this.selectedLikedGenres);
	}
	
	/**
	 * Adds the selected game.
	 *
	 * @param newValue the new value
	 */
	public void addSelectedGame(Game newValue) {
		if (!this.selectedLikedGames.contains(newValue)) {
			this.selectedLikedGames.add(newValue);
		}
	}
	
	/**
	 * Adds the selected genre.
	 *
	 * @param newGenre the new genre
	 */
	public void addSelectedGenre(Genre newGenre) {
		if (!this.selectedLikedGenres.contains(newGenre)) {
			this.selectedLikedGenres.add(newGenre);
		}
	}
	
	/**
	 * Gets the all genre property.
	 *
	 * @return the all genre property
	 */
	public ListProperty<Genre> getAllGenreProperty() {
		return this.allGenres;
	}

	
	/**
	 * Gets the selected liked game.
	 *
	 * @return the selected liked game
	 */
	public ListProperty<Game> getAllGames() {
		return this.allGames;
	}
	
	/**
	 * Gets the selected liked games.
	 *
	 * @return the selected liked games
	 */
	public List<Game> getSelectedLikedGames() {
		return this.selectedLikedGames;
	}
}
