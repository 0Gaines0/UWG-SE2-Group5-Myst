package application.model;

import java.util.AbstractMap;
//import java.util.ArrayList;
//import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.junit.jupiter.api.ClassOrderer.Random;

import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.UserProfile;

/**
 * The GameRecommendationEngine class provides functionality to generate
 * personalized game recommendations for users based on their preferences and
 * gaming history. It considers factors like preferred genres, average playtime,
 * and release year preferences to score and recommend games. Each
 * recommendation run randomly samples a subset of games from the entire game
 * database to ensure variety in the recommendations provided across different
 * executions.
 * 
 * @author Thomas Lamont
 * @version Sprint 1
 */
public class GameRecommendationEngine {

	private List<Game> gameDatabase;
	// private static final int SAMPLE_SIZE = 5000;
	private static final int NUMBER_RECOMMENDATIONS = 5;

	/**
	 * Constructs a GameRecommendationEngine with a specified list of games.
	 * 
	 * @param gameDatabase The complete list of games available for recommendation.
	 */
	public GameRecommendationEngine(List<Game> gameDatabase) {
		this.gameDatabase = gameDatabase;
	}

	/**
	 * Generates a list of game recommendations for the user. This method takes
	 * games from the available database, evaluates them based on the user's
	 * preferences, and returns a sorted list of top recommendations.
	 * 
	 * The recommendations are based on genre preferences, similarity to liked
	 * games, average release year, and average playtime preferences. The method
	 * ensures that disliked, owned, and previously liked games are filtered out
	 * from the recommendations.
	 * 
	 * @param user                    The UserProfile containing the user's gaming
	 *                                preferences and history.
	 * @param numberOfRecommendations The number of top recommendations to return.
	 * @return A List of recommended games, sorted by their recommendation score in
	 *         descending order.
	 */
	public List<Game> generateRecommendations(UserProfile user) {
		List<Genre> userPreferredGenres = user.getPreferredGenres();
		var recommendedGames = gameDatabase.stream()
				.filter(game -> !user.getAllDislikedGames().contains(game) && !user.getAllOwnedGames().contains(game)
						&& !user.getAllLikedGames().contains(game))
				.map(game -> new AbstractMap.SimpleEntry<>(game, calculateGameScore(game, user, userPreferredGenres)))
				.sorted(Comparator.comparing(AbstractMap.SimpleEntry<Game, Double>::getValue).reversed())
				.limit(GameRecommendationEngine.NUMBER_RECOMMENDATIONS).map(AbstractMap.SimpleEntry::getKey)
				.collect(Collectors.toList());

		System.out.println(recommendedGames);
		return recommendedGames;
	}

	/**
	 * Generates a list of game recommendations for the user. This method randomly
	 * samples a subset of games from the available database, evaluates them based
	 * on the user's preferences, and returns a sorted list of top recommendations.
	 * 
	 * The recommendations are based on genre preferences, similarity to liked
	 * games, average release year, and average playtime preferences. The method
	 * ensures that disliked, owned, and previously liked games are filtered out
	 * from the recommendations.
	 * 
	 * @param user                    The UserProfile containing the user's gaming
	 *                                preferences and history.
	 * @param numberOfRecommendations The number of top recommendations to return.
	 * @return A List of recommended games, sorted by their recommendation score in
	 *         descending order.
	 */
	private double calculateGameScore(Game game, UserProfile user, List<Genre> userPreferredGenres) {
		double score = 0.0;
		double genreMatchScore = calculateGenreMatchScore(game, userPreferredGenres);
		double reviewScoreWeight = 0.5;
		double genreMatchWeight = 5.0;

		score += genreMatchScore * genreMatchWeight;
		score += game.getAverageReview() * reviewScoreWeight;

		return score;
	}

	private double calculateGenreMatchScore(Game game, List<Genre> userPreferredGenres) {
		double matchScore = 0.0;
		for (Genre genre : game.getGenres()) {
			if (userPreferredGenres.contains(genre)) {
				matchScore += 1.0;
			}
		}
		return matchScore;
	}
}