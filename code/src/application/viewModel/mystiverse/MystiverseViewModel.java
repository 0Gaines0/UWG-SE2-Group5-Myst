package application.viewModel.mystiverse;

import java.util.ArrayList;
import java.util.List;

import application.model.GameRecommendationEngine;
import application.model.game.Game;
import application.model.profile.ActiveUser;

/**
 * The Class MystiverseViewModel.
 * 
 * @author daniel rivera
 * @version sprint 1
 */
public class MystiverseViewModel {
	
	private static List<Game> recommendedGames;
	private GameRecommendationEngine engine;

	/**
	 * the mystiverse page view model.
	 */
	public MystiverseViewModel() {
		recommendedGames = new ArrayList<Game>();
		this.engine = new GameRecommendationEngine();
	}
	
	/**
	 * Generate recommendations.
	 */
	public void generateRecommendations() {
		recommendedGames = this.engine.generateRecommendations(ActiveUser.getActiveUser());
	}

	/**
	 * Gets the recommended games.
	 *
	 * @return the recommended games
	 */
	public static List<Game> getRecommendedGames() {
		return recommendedGames;
	}
}
