package application.viewModel.mystiverse.subMystiversePages;

import java.util.List;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;

public class SeedPageAnchorViewModel {

	private List<Genre> selectedSeedGenres;
	private List<Game> selectedSeedGames;
	
	private List<Game> generatedRecommendations;
	
	/**
	 * Instantiates a new seed page anchor view model.
	 */
	public SeedPageAnchorViewModel() {
		
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
}
