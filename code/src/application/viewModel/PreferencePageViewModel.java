package application.viewModel;

import java.util.List;

import application.model.GameRecommendationEngine;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.UserProfile;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * the first time login page view model
 * 
 * @author Daniel Rivera 
 * @version Sprint 1
 */
public class PreferencePageViewModel {

	private ObjectProperty<Genre> highPreferenceGenre;
	private ObjectProperty<Genre> mediumPreferenceGenre;
	private ObjectProperty<Genre> lowPreferenceGenre;
	private ListProperty<Game> ownedGames;
	private GameRecommendationEngine recommendationEngine;
	
	/**
	 * the first time login page view model
	 */
	public PreferencePageViewModel() {
		this.highPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.mediumPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.lowPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.ownedGames = new SimpleListProperty<Game>();
	}
	
	/**
	 * generates the recommendations based off of the preferences by the user
	 * 
	 * @param highPreference
	 * @param mediumPreference
	 * @param lowPreference
	 * @param ownedGames
	 * 
	 * @return the recommendations for the user
	 */
	public List<Game> generateRecommendationPreferences(Genre highPreference, Genre mediumPreference, Genre lowPreference, List<Game> ownedGames) {
		UserProfile user = new UserProfile(); //TODO: change this
		user.getPreferredGenres().add(highPreference);
		user.getPreferredGenres().add(mediumPreference);
		user.getPreferredGenres().add(lowPreference);
		user.getAllLikedGames().addAll(ownedGames);
		return this.recommendationEngine.generateRecommendations(user);
	}

	/**
	 * gets the medium priority genre
	 * 
	 * @return the medium priority genre
	 */
	public ObjectProperty<Genre> getHighPriorityGenre() {
		return this.highPreferenceGenre;
	}

	/**
	 * gets the medium priority genre
	 * 
	 * @return the medium priority genre
	 */
	public ObjectProperty<Genre> getMediumPriorityGenre() {
		return this.mediumPreferenceGenre;
	}

	/**
	 * gets the low priority genre
	 * 
	 * @return the low priority genre
	 */
	public ObjectProperty<Genre> getLowPriorityGenre() {
		return this.lowPreferenceGenre;
	}

	/**
	 * gets the owned games list property
	 * 
	 * @return the owned games list property
	 */
	public ListProperty<Game> getOwnedGames() {
		return this.ownedGames;
	}
	
}
