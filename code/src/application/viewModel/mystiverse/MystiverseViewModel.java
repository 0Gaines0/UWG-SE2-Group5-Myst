package application.viewModel.mystiverse;

import java.util.ArrayList;
import java.util.List;

import application.model.local_impl.GameRecommendationEngine;
import application.model.local_impl.game.Game;
import application.model.local_impl.profile.ActiveUser;

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
	 * Sets the engine.
	 *
	 * @param engine the new engine
	 */
	public void setEngine(GameRecommendationEngine engine) {
		this.engine = engine;
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
