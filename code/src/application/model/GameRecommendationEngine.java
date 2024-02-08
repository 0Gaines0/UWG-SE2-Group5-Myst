package application.model;

import java.util.List;

import application.model.game.Game;
import application.model.game.Genre;

public class GameRecommendationEngine {

	/**
	 * the game recommendation engine constructor
	 */
	public GameRecommendationEngine() {
		
	}
	
	/**
	 * generates the recommendations for the user 
	 * 
	 * @param highPreference
	 * @param mediumPreference
	 * @param lowPreference
	 * @param ownedGames
	 * 
	 * @return a list of games that match the users preferences
	 */
	public List<Game> generateRecommendation(Genre highPreference, Genre mediumPreference, Genre lowPreference,
			List<Game> ownedGames) {
		// TODO Auto-generated method stub
		return null;
	}

}
