package application.viewModel.profile.subProfilePages;

import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

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
	private ListProperty<Game> likedGames;	
	
	/**
	 * the first time login page view model.
	 */
	public PreferencePageViewModel() {
		this.highPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.mediumPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.lowPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.likedGames = new SimpleListProperty<Game>();
	}
	
	

	
	/**
	 * Configure new user preferences.
	 */
	public void configureNewUserPreferences() {
		ActiveUser.getActiveUser().getPreferredGenres().add(this.highPreferenceGenre.getValue());
		ActiveUser.getActiveUser().getPreferredGenres().add(this.mediumPreferenceGenre.getValue());
		ActiveUser.getActiveUser().getPreferredGenres().add(this.lowPreferenceGenre.getValue());
		ActiveUser.getActiveUser().getAllLikedGames().addAll(this.likedGames);
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
	public ListProperty<Game> getSelectedLikedGames() {
		return this.likedGames;
	}
	
}
