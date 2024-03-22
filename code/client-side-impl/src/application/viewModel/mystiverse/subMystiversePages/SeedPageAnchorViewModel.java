package application.viewModel.mystiverse.subMystiversePages;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.model.local_impl.GameRecommendationEngine;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeedPageAnchorViewModel {

	private List<Genre> selectedSeedGenres;
	private List<Game> selectedSeedGames;
	private ListProperty<Game> allGames;	
	private ListProperty<Genre> allGenres;
	private List<Game> generatedRecommendations;
	
	/**
	 * Instantiates a new seed page anchor view model.
	 */
	public SeedPageAnchorViewModel() {
		this.allGames = new SimpleListProperty<Game>();
		this.allGenres = new SimpleListProperty<Genre>();
		this.selectedSeedGames = new ArrayList<Game>();
		this.selectedSeedGenres = new ArrayList<Genre>();
	}
	
	/**
	 * Generate seeded recommendations.
	 *
	 * @param seedGames the seed games
	 * @param seedGenres the seed genres
	 * @return the list
	 */
	public List<Game> generateSeededRecommendations(List<Game> seedGames, List<Genre> seedGenres) {
		var engine = new GameRecommendationEngine();
		return engine.generateRecommendationsForSelectedGamesAndGenres(seedGames, seedGenres);
	}
	
	/**
	 * Adds the selected game.
	 *
	 * @param newValue the new value
	 */
	public void addSelectedGame(Game newValue) {
		if (!this.selectedSeedGames.contains(newValue)) {
			this.selectedSeedGames.add(newValue);
		}
	}
	
	/**
	 * Adds the selected genre.
	 *
	 * @param newGenre the new genre
	 */
	public void addSelectedGenre(Genre newGenre) {
		if (!this.selectedSeedGenres.contains(newGenre)) {
			this.selectedSeedGenres.add(newGenre);
		}
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
	 * Gets the selected seed genres.
	 *
	 * @return the selected seed genres
	 */
	public List<Genre> getSelectedSeedGenres() {
		return this.selectedSeedGenres;
	}

	/**
	 * Sets the selected seed genres.
	 *
	 * @param selectedSeedGenres the new selected seed genres
	 */
	public void setSelectedSeedGenres(List<Genre> selectedSeedGenres) {
		this.selectedSeedGenres = selectedSeedGenres;
	}

	/**
	 * Gets the selected seed games.
	 *
	 * @return the selected seed games
	 */
	public List<Game> getSelectedSeedGames() {
		return this.selectedSeedGames;
	}

	/**
	 * Sets the selected seed games.
	 *
	 * @param selectedSeedGames the new selected seed games
	 */
	public void setSelectedSeedGames(List<Game> selectedSeedGames) {
		this.selectedSeedGames = selectedSeedGames;
	}

	/**
	 * Gets the generated recommendations.
	 *
	 * @return the generated recommendations
	 */
	public List<Game> getGeneratedRecommendations() {
		return this.generatedRecommendations;
	}

	/**
	 * Sets the generated recommendations.
	 *
	 * @param generatedRecommendations the new generated recommendations
	 */
	public void setGeneratedRecommendations(List<Game> generatedRecommendations) {
		this.generatedRecommendations = generatedRecommendations;
	}

	/**
	 * Gets the all games.
	 *
	 * @return the all games
	 */
	public ListProperty<Game> getAllGames() {
		return this.allGames;
	}

	/**
	 * Sets the all games.
	 *
	 * @param allGames the new all games
	 */
	public void setAllGames(ListProperty<Game> allGames) {
		this.allGames = allGames;
	}

	/**
	 * Gets the all genres.
	 *
	 * @return the all genres
	 */
	public ListProperty<Genre> getAllGenres() {
		return this.allGenres;
	}

	/**
	 * Sets the all genres.
	 *
	 * @param allGenres the new all genres
	 */
	public void setAllGenres(ListProperty<Genre> allGenres) {
		this.allGenres = allGenres;
	}
}
