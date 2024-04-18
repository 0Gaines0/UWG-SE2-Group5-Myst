package application.viewModel.mystiverse.subMystiversePages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.Main;
import application.model.server_impl.GameRecommendationEngine;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeedPageAnchorViewModel {

	private ListProperty<Genre> selectedSeedGenres;
	private ListProperty<Game> selectedSeedGames;
	private ListProperty<Game> allGames;	
	private ListProperty<Genre> allGenres;
	private List<Game> generatedRecommendations;
	
	/**
	 * Instantiates a new seed page anchor view model.
	 */
	public SeedPageAnchorViewModel() {
		this.allGames = new SimpleListProperty<Game>();
		this.allGenres = new SimpleListProperty<Genre>();
		this.selectedSeedGames = new SimpleListProperty<Game>();
		this.selectedSeedGenres = new SimpleListProperty<Genre>();
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
	 * Removes the selected game.
	 *
	 * @param newValue the new value
	 */
	public void removeSelectedGame(Game newValue) {
		if (this.selectedSeedGames.contains(newValue)) {
			this.selectedSeedGames.remove(newValue);
		}
	}

	/**
	 * Removes the selected genre.
	 *
	 * @param newGenre the new genre
	 */
	public void removeSelectedGenre(Genre newGenre) {
		if (this.selectedSeedGenres.contains(newGenre)) {
			this.getSelectedSeedGenres().remove(newGenre);
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
	 * Sets the up selected games list.
	 */
	public void setUpSelectedGamesList() {
		ObservableList<Game> selectedGames = FXCollections.observableArrayList(this.getSelectedSeedGames());
		this.selectedSeedGames.setValue(selectedGames);
	}
	
	/**
	 * Sets the up selected genres list.
	 */
	public void setUpSelectedGenresList() {
		ObservableList<Genre> selectedGenres = FXCollections.observableArrayList(this.getSelectedSeedGenres());
		this.selectedSeedGenres.setValue(selectedGenres);
	}

	/**
	 * Gets the selected seed genres.
	 *
	 * @return the selected seed genres
	 */
	public ListProperty<Genre> getSelectedSeedGenres() {
		return this.selectedSeedGenres;
	}

	/**
	 * Sets the selected seed genres.
	 *
	 * @param selectedSeedGenres the new selected seed genres
	 */
	public void setSelectedSeedGenres(ListProperty<Genre> selectedSeedGenres) {
		this.selectedSeedGenres = selectedSeedGenres;
	}

	/**
	 * Gets the selected seed games.
	 *
	 * @return the selected seed games
	 */
	public ListProperty<Game> getSelectedSeedGames() {
		return this.selectedSeedGames;
	}

	/**
	 * Sets the selected seed games.
	 *
	 * @param selectedSeedGames the new selected seed games
	 */
	public void setSelectedSeedGames(ListProperty<Game> selectedSeedGames) {
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

	/**
	 * Filter games search.
	 *
	 * @param text the text
	 * @param items the items
	 * @return the list
	 */
	public List<Game> filterGamesSearch(String text, ObservableList<Game> items) {
		List<Game> results = new ArrayList<Game>();
		if (text != null) {
			var filteredGames = items.stream().filter(game -> game.getName().toLowerCase().contains(text))
					.toList();
			if (filteredGames.size() != 0) {
				results = filteredGames;
			} else {
				results = Main.getGames();
			}
		} else {
			results = Main.getGames();
		}
		return results;
	}

	/**
	 * Filter genres search.
	 *
	 * @param text the text
	 * @param items the items
	 * @return the list
	 */
	public List<Genre> filterGenresSearch(String text, ObservableList<Genre> items) {
        List<Genre> results = new ArrayList<Genre>();
        ArrayList<Genre> fullList = new ArrayList<Genre>(Arrays.asList(Genre.values()));
        if (text != null && !text.isEmpty()) {
            var filteredResults = items.stream()
                           .filter(genre -> genre.toString().toLowerCase().contains(text)).toList();
            if (filteredResults.size() != 0) {
            	results = filteredResults;
            } else {
            	results = fullList;
            }
        } else {
        	results = fullList;
        }
        return results;
	}
}
