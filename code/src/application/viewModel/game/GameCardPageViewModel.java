package application.viewModel.game;

import application.model.game.Game;
import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * the GameCardPageViewModel.
 *
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class GameCardPageViewModel {
	
	/** The curr recommendation. */
	private static Game currRecommendation;

	/**
	 * the game card page view model.
	 */
	public GameCardPageViewModel() {
		
	}
	
	
	/**
	 * Gets the curr recommendation.
	 *
	 * @return the curr recommendation
	 */
	public static Game getCurrRecommendation() {
		return GameCardPageViewModel.currRecommendation;
	}
	
	/**
	 * Sets the curr recommendation.
	 *
	 * @param game the new curr recommendation
	 */
	public static void setCurrRecommendation(Game game) {
		currRecommendation = game;
	}

	/**
	 * Adds the game to liked library.
	 *
	 * @param profile the profile
	 * @param game the game
	 * @return true if the add was successful false other wise
	 */
	public Boolean addGameToLikedLibrary(UserProfile profile, Game game) {
		return profile.getAllLikedGames().add(game);
	}

	/**
	 * Adds the game to disliked library.
	 *
	 * @param profile the profile
	 * @param game the game
	 * @return the boolean
	 */
	public Boolean addGameToDislikedLibrary(UserProfile profile, Game game) {
		return profile.getAllDislikedGames().add(game);
	}

}
