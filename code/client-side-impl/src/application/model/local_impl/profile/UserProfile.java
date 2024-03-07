package application.model.local_impl.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;

/**
 * The Class UserProfile.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class UserProfile {
	
	private static final int LIMIT_GENRES = 6;
	
	private List<Game> allOwnedGames;
	private List<Game> allLikedGames;
	private List<Game> allDislikedGames;
	private List<Genre> preferredGenres;

	private String username;
	private String password;
	private boolean firstTimeLogin;

	private ProfileAttributes profileAttributes;

	private static final String USERNAME_MUST_NOT_BE_NULL_OR_EMPTY = "username must not be null or empty";
	private static final String PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY = "password must not be null or empty";
	private static final String INPUT_LIST_MUST_NOT_BE_NULL = "inputted game list must not be null";

	/**
	 * Instantiates a new user profile.
	 */
	public UserProfile() {
		this.profileAttributes = new ProfileAttributes();
		this.firstTimeLogin = true;
		this.setUpUserGameData();
	}

	/**
	 * Instantiates a new user profile.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public UserProfile(String username, String password) {
		this();
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		this.username = username;
		this.password = password;
	}

	private void setUpUserGameData() {
		this.allOwnedGames = new ArrayList<Game>();
		this.allLikedGames = new ArrayList<Game>();
		this.allDislikedGames = new ArrayList<Game>();
		this.preferredGenres = new ArrayList<Genre>();
	}

	/**
	 * Gets the all owned games.
	 *
	 * @return the all owned games
	 */
	public List<Game> getAllOwnedGames() {
		return this.allOwnedGames;
	}

	/**
	 * Sets the all owned games.
	 *
	 * @param allOwnedGames the new all owned games
	 */
	public void setAllOwnedGames(List<Game> allOwnedGames) {
		if (allOwnedGames == null) {
			throw new NullPointerException(INPUT_LIST_MUST_NOT_BE_NULL);
		}
		this.allOwnedGames = allOwnedGames;
	}

	/**
	 * Gets the all liked games.
	 *
	 * @return the all liked games
	 */
	public List<Game> getAllLikedGames() {
		return this.allLikedGames;
	}

	/**
	 * Sets the all liked games.
	 *
	 * @param allLikedGames the new all liked games
	 */
	public void setAllLikedGames(List<Game> allLikedGames) {
		if (allLikedGames == null) {
			throw new NullPointerException(INPUT_LIST_MUST_NOT_BE_NULL);
		}
		this.allLikedGames = allLikedGames;
	}

	/**
	 * Gets the all disliked games.
	 *
	 * @return the all disliked games
	 */
	public List<Game> getAllDislikedGames() {
		return this.allDislikedGames;
	}

	/**
	 * Sets the all disliked games.
	 *
	 * @param allDislikedGames the new all disliked games
	 */
	public void setAllDislikedGames(List<Game> allDislikedGames) {
		if (allDislikedGames == null) {
			throw new NullPointerException(INPUT_LIST_MUST_NOT_BE_NULL);
		}
		this.allDislikedGames = allDislikedGames;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		this.password = password;
	}

	/**
	 * gets the preferred genres
	 * 
	 * @return the preferredGenres
	 */
	public List<Genre> getPreferredGenres() {
		return this.preferredGenres;
	}

	/**
	 * Sets the preferred genres.
	 *
	 * @param preferredGenres the new preferred genres
	 */
	public void setPreferredGenres(List<Genre> preferredGenres) {
		if (preferredGenres == null) {
			throw new IllegalArgumentException("preferredGenres cannot be null");
		}
		this.preferredGenres = preferredGenres;
	}

	/**
	 * Gets the profile attributes.
	 *
	 * @return the profile attributes
	 */
	public ProfileAttributes getProfileAttributes() {
		return this.profileAttributes;
	}

	/**
	 * Checks if is first time login.
	 *
	 * @return true, if is first time login
	 */
	public boolean isFirstTimeLogin() {
		return this.firstTimeLogin;
	}

	/**
	 * Sets the first time login.
	 *
	 * @param firstTimeLogin the new first time login
	 */
	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}

	/**
	 * Calculates the breakdown of genre percentages for liked and owned games.
	 *
	 * @return A map of genres to their percentage representation among liked and
	 *         owned games.
	 */
	public Map<Genre, Double> calculateGenrePercentages() {
		Map<Genre, Integer> genreCounts = new HashMap<>();
		int totalGenres = 0;

		for (Game game : this.allLikedGames) {
			for (Genre genre : game.getGenres()) {
				genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
				totalGenres++;
			}
		}

		for (Game game : this.allOwnedGames) {
			for (Genre genre : game.getGenres()) {
				genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
				totalGenres++;
			}
		}

		Map<Genre, Double> genrePercentages = new HashMap<>();
		for (Map.Entry<Genre, Integer> entry : genreCounts.entrySet()) {
			double percentage = 100.0 * entry.getValue() / totalGenres;
			genrePercentages.put(entry.getKey(), percentage);
		}
		
		genrePercentages = genrePercentages.entrySet().stream()
	            .sorted(Map.Entry.<Genre, Double>comparingByValue().reversed())
	            .limit(LIMIT_GENRES)
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return genrePercentages;
	}

	/**
	 * Calculates the average release year of liked and owned games.
	 *
	 * @return The average release year of games.
	 */
	public double calculateAverageReleaseYear() {
		int totalYears = 0;
		int count = 0;

		for (Game game : this.allLikedGames) {
			totalYears += game.getReleaseDateYear();
			count++;
		}

		for (Game game : this.allOwnedGames) {
			totalYears += game.getReleaseDateYear();
			count++;
		}

		if (count == 0) {
			return 0;
		}
		return (double) totalYears / count;
	}

}
