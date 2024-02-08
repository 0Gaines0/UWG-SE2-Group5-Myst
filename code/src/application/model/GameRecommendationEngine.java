package application.model;

import java.util.ArrayList;
import java.util.List;

import application.model.game.Game;
import application.model.profile.UserProfile;

/**
 * the game recommendation engine
 * 
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class GameRecommendationEngine {

	/**
	 * the game recommendation engine constructor
	 */
	public GameRecommendationEngine() {
		
	}
	

	/**
	 * Generate recommendations.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<Game> generateRecommendations(UserProfile user) {
		ArrayList<Game> recommendedGames = new ArrayList<Game>();
		
		return recommendedGames;
	}

}
