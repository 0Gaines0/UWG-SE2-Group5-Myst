package application.model.server_impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.UserProfile;

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
	private static final int SAMPLE_SIZE = 1000;
	private static final int NUMBER_RECOMMENDATIONS = 10;

	/**
	 * Constructs a GameRecommendationEngine with a specified list of games.
	 */
	public GameRecommendationEngine() {
		this.gameDatabase = Main.getGames();
	}
	
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
	 * @return A List of recommended games, sorted by their recommendation score in
	 *         descending order.
	 */
	public List<Game> generateRecommendations(UserProfile user) {
		List<Genre> userPreferredGenres = user.getPreferredGenres();
		var dislikedGames = user.getAllDislikedGames();
		var likedGames = user.getAllLikedGames();
		var ownedGames = user.getAllOwnedGames();
		var recommendedGames = this.gameDatabase.stream()
				.filter(game -> !dislikedGames.contains(game) && !ownedGames.contains(game)
						&& !likedGames.contains(game))
				.map(game -> new AbstractMap.SimpleEntry<>(game, this.calculateGameScore(game, userPreferredGenres)))
				.sorted(Comparator.comparing(AbstractMap.SimpleEntry<Game, Double>::getValue).reversed())
				.limit(GameRecommendationEngine.NUMBER_RECOMMENDATIONS).map(AbstractMap.SimpleEntry::getKey)
				.collect(Collectors.toList());
		return recommendedGames;
	}
	
	/**
     * Generates a list of game recommendations for the user. This method takes
     * games from the available database, evaluates them based on the user's preferences, and returns
     * a sorted list of top recommendations.
     * 
     * The recommendations are based on genre preferences, similarity to liked games, average release year,
     * and average playtime preferences. The method ensures that disliked, owned, and previously liked games
     * are filtered out from the recommendations.
     * 
     * @param user The UserProfile containing the user's gaming preferences and history.
     * @return A List of recommended games, sorted by their recommendation score in descending order.
     */
	public List<Game> generateRecommendationsRandom(UserProfile user) {
		List<Game> sampledGames = new ArrayList<>(this.gameDatabase);
        Collections.shuffle(sampledGames);
        
        sampledGames = sampledGames.subList(0, Math.min(SAMPLE_SIZE, sampledGames.size()));
        List<Genre> userPreferredGenres = user.getPreferredGenres();
        var recommendedGames = sampledGames.stream()
                .filter(game -> !user.getAllDislikedGames().contains(game) && !user.getAllOwnedGames().contains(game)
                        && !user.getAllLikedGames().contains(game))
                .map(game -> new AbstractMap.SimpleEntry<>(game, this.calculateGameScore(game, userPreferredGenres)))
                .sorted(Comparator.comparing(AbstractMap.SimpleEntry<Game, Double>::getValue).reversed())
                .limit(NUMBER_RECOMMENDATIONS)
                .map(AbstractMap.SimpleEntry::getKey)
                .collect(Collectors.toList());
        return recommendedGames;
	}
	
	/**
	 * Generates a list of game recommendations based on selected games and genres.
	 * This method randomly samples a subset of games from the available database,
	 * evaluates them based on the selected genres, and returns a sorted list of top recommendations.
	 *
	 * The recommendations are based on genre preferences, similarity to selected games,
	 * average release year, and average playtime preferences.
	 *
	 * @param selectedGames   The list of selected games.
	 * @param selectedGenres  The list of selected genres.
	 * @return A List of recommended games, sorted by their recommendation score in descending order.
	 */
	public List<Game> generateRecommendationsForSelectedGamesAndGenres(List<Game> selectedGames, List<Genre> selectedGenres) {
	    List<Game> sampledGames = new ArrayList<>(this.gameDatabase);
	    Collections.shuffle(sampledGames);
	    sampledGames = sampledGames.subList(0, Math.min(SAMPLE_SIZE, sampledGames.size()));

	    var recommendedGames = sampledGames.stream()
	            .filter(game -> !selectedGames.contains(game))
	            .map(game -> new AbstractMap.SimpleEntry<>(game, this.calculateGameScore(game, selectedGenres)))
	            .sorted(Comparator.comparing(AbstractMap.SimpleEntry<Game, Double>::getValue).reversed())
	            .limit(NUMBER_RECOMMENDATIONS)
	            .map(AbstractMap.SimpleEntry::getKey)
	            .collect(Collectors.toList());

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
	 * @return A List of recommended games, sorted by their recommendation score in
	 *         descending order.
	 */
	private double calculateGameScore(Game game, List<Genre> userPreferredGenres) {

	    double score = 0.0;

	    double genreMatchScore = this.calculateGenreMatchScore(game, userPreferredGenres);

	    double genreMatchWeight = 100.0; 
	    double reviewCountFactor = Math.log1p(game.getTotalNumberOfReviews()) * 2; 
	    double reviewScore = Math.sqrt(game.getAverageReview()) * 10; 

	    double reviewScoreWeight = 1;
	    score += genreMatchScore * genreMatchWeight;
	    score += reviewScore * reviewScoreWeight;
	    score += reviewCountFactor;
	    return score;
	}
	
	private double calculateGenreMatchScore(Game game, List<Genre> preferredGenres) {
	    double matchScore = 0.0;
	    int totalGenres = game.getGenres().size();
	    int matchedGenres = 0;	    
	    for (Genre genre : game.getGenres()) {
	        if (preferredGenres.contains(genre)) {
	            matchedGenres += 1;
	        }
	    }	    
	    if (totalGenres > 0) {
	        matchScore = (double) matchedGenres / preferredGenres.size();
	    } else {
	        matchScore = 0;
	    }	    
	    return matchScore * 100;
	}

}