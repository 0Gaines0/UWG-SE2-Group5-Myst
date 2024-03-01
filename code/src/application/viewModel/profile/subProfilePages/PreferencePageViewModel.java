package application.viewModel.profile.subProfilePages;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * the first time login page view model.
 *
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class PreferencePageViewModel {

	/** The high preference genre. */
	private ObjectProperty<Genre> highPreferenceGenre;
	
	/** The medium preference genre. */
	private ObjectProperty<Genre> mediumPreferenceGenre;
	
	/** The low preference genre. */
	private ObjectProperty<Genre> lowPreferenceGenre;
	
	/** The liked games. */
	private ListProperty<Game> allGames;	
	
	private List<Game> selectedLikedGames;
	
	/**
	 * the first time login page view model.
	 */
	public PreferencePageViewModel() {
		this.highPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.mediumPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.lowPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.allGames = new SimpleListProperty<Game>();
		this.selectedLikedGames = new ArrayList<Game>();
	}
	
	/**
	 * Sets the up all games list.
	 */
	public void setUpAllGamesList() {
		ObservableList<Game> allGamesList = FXCollections.observableArrayList(Main.getGames());
    	this.allGames.setValue(allGamesList);
	}

	/**
	 * Configure new user preferences.
	 */
	public void configureNewUserPreferences() {
		ActiveUser.getActiveUser().getPreferredGenres().add(this.highPreferenceGenre.getValue());
		ActiveUser.getActiveUser().getPreferredGenres().add(this.mediumPreferenceGenre.getValue());
		ActiveUser.getActiveUser().getPreferredGenres().add(this.lowPreferenceGenre.getValue());
		ActiveUser.getActiveUser().getAllLikedGames().addAll(this.selectedLikedGames);
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
	 * gets the medium priority genre.
	 *
	 * @return the medium priority genre
	 */
	public ObjectProperty<Genre> getHighPriorityGenre() {
		return this.highPreferenceGenre;
	}

	/**
	 * gets the medium priority genre.
	 *
	 * @return the medium priority genre
	 */
	public ObjectProperty<Genre> getMediumPriorityGenre() {
		return this.mediumPreferenceGenre;
	}

	/**
	 * gets the low priority genre.
	 *
	 * @return the low priority genre
	 */
	public ObjectProperty<Genre> getLowPriorityGenre() {
		return this.lowPreferenceGenre;
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
