package application.viewModel;

import java.util.List;

import application.model.GameRecommendationEngine;
import application.model.game.Game;
import application.model.game.GameLibrary;
import application.model.game.Genre;
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
public class FirstTimeLoginPageViewModel {

	private ObjectProperty<Genre> highPreferenceGenre;
	private ObjectProperty<Genre> mediumPreferenceGenre;
	private ObjectProperty<Genre> lowPreferenceGenre;
	private ListProperty<Game> ownedGames;
	private GameLibrary gameLibrary;
	private GameRecommendationEngine recommendationEngine;
	
	/**
	 * the first time login page view model
	 */
	public FirstTimeLoginPageViewModel() {
		this.highPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.mediumPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.lowPreferenceGenre = new SimpleObjectProperty<Genre>();
		this.ownedGames = new SimpleListProperty<Game>();
		this.gameLibrary = new GameLibrary();
		
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
		return this.recommendationEngine.generateRecommendation(highPreference, mediumPreference, lowPreference, ownedGames);
	}

	/**
	 * gets the game library
	 * 
	 * @return the game library
	 */
	public GameLibrary getGameLibrary() {
		return this.gameLibrary;
	}

    /**
     * sets the game library 
     * @param gameLibrary
     */
	public void setGameLibrary(GameLibrary gameLibrary) {
		this.gameLibrary = gameLibrary;
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
