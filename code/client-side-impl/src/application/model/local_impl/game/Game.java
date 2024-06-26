package application.model.local_impl.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Stores and manages information for a single Game.
 *
 * @author Thomas Lamont
 * @version Sprint 1
 */
public class Game {

	private String name;
	private List<Genre> genres;
	private int gameID;
	private String developers;
	private int releaseDateYear;
	private int releaseDateMonth;
	private int numberPositiveReviews;
	private int numberNegativeReviews;
	private int averagePlaytime;
	private String gamePhotoLink;
	private double averageReview;
	private int totalNumberOfReviews;
	private String description;
	private String comments;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns this.name
	 * 
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns this.name
	 * 
	 * @return this.name
	 */
	public String getGamePhoto() {
		return this.gamePhotoLink;
	}

	/**
	 * Gets the genres of the games
	 * 
	 * @return A list of genres of the game.
	 */
	public List<Genre> getGenres() {
		return this.genres;
	}

	/**
	 * Gets the game ID.
	 * 
	 * @return The game ID.
	 */
	public int getGameID() {
		return this.gameID;
	}

	/**
	 * Gets the name of the developers.
	 * 
	 * @return The developers' name.
	 */
	public String getDevelopers() {
		return this.developers;
	}

	/**
	 * Gets the release year of the game.
	 * 
	 * @return The release year.
	 */
	public int getReleaseDateYear() {
		return this.releaseDateYear;
	}

	/**
	 * Sets the release date year.
	 *
	 * @param year the new release date year
	 */
	public void setReleaseDateYear(int year) {
		this.releaseDateYear = year;
	}

	/**
	 * Gets the release month of the game.
	 * 
	 * @return The release month.
	 */
	public int getReleaseDateMonth() {
		return this.releaseDateMonth;
	}

	/**
	 * Gets the number of positive reviews.
	 * 
	 * @return The number of positive reviews.
	 */
	public int getNumberPositiveReviews() {
		return this.numberPositiveReviews;
	}

	/**
	 * Gets the number of negative reviews.
	 * 
	 * @return The number of negative reviews.
	 */
	public int getNumberNegativeReviews() {
		return this.numberNegativeReviews;
	}

	/**
	 * Gets the average playtime of the game.
	 * 
	 * @return The average playtime in hours.
	 */
	public int getAveragePlaytime() {
		return this.averagePlaytime;
	}

	/**
	 * Gets the average playtime of the game.
	 * 
	 * @return The average playtime in hours.
	 */
	public double getAverageReview() {
		return this.averageReview;
	}

	/**
	 * Gets the average playtime of the game.
	 * 
	 * @return The average playtime in hours.
	 */
	public int getTotalNumberOfReviews() {
		return this.totalNumberOfReviews;
	}

	/**
	 * Retrieves the concatenated string of all comments associated with this game.
	 *
	 * This method returns a string containing all comments added to this game. Each
	 * comment is separated by a newline character.
	 *
	 * @return a String containing all comments, or an empty string if no comments
	 *         are present.
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Adds a new comment to this game.
	 *
	 * This method appends a new comment to the existing comments string. Each
	 * comment will be separated by a newline character. If there are no existing
	 * comments, it initializes the comments string with the new comment.
	 *
	 * @param newComment the new comment to be added.
	 */
	public void setComments(String newComment) {
		if (this.comments == null) {
			this.comments = newComment + "\n";
		} else {
			this.comments = this.comments + newComment + "\n";
		}
	}

	/**
	 * Constructor for Game with minimal information.
	 * 
	 * @param name   The name of the game.
	 * @param genres The genres of the game.
	 * @param gameID The unique identifier for the game.
	 */
	public Game(String name, List<Genre> genres, int gameID) {
		if (name == null) {
			throw new IllegalArgumentException("Name must not be null");
		}
		this.genres = (genres == null) ? new ArrayList<>() : new ArrayList<>(genres);
		if (this.genres.isEmpty()) {
			this.genres.add(Genre.MISSING_GENRE);
			System.out.println(this.genres);
		}
		if (gameID == 0) {
			throw new IllegalArgumentException("Genre must not be null");
		}
		this.name = name;
		this.gameID = gameID;
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param name                  the name
	 * @param genres                the genres
	 * @param gameID                the game ID
	 * @param developers            the developers
	 * @param releaseDateYear       the release date year
	 * @param releaseDateMonth      the release date month
	 * @param numberPositiveReviews the number positive reviews
	 * @param numberNegativeReviews the number negative reviews
	 * @param averagePlaytime       the average playtime
	 * @param gamePhotoLink         the game photo link
	 * @param description           the description
	 */
	public Game(String name, List<Genre> genres, int gameID, String developers, int releaseDateYear,
			int releaseDateMonth, int numberPositiveReviews, int numberNegativeReviews, int averagePlaytime,
			String gamePhotoLink, String description) {
		this(name, genres, gameID);
		this.developers = developers;
		this.releaseDateYear = releaseDateYear;
		this.releaseDateMonth = releaseDateMonth;
		this.numberPositiveReviews = numberPositiveReviews;
		this.numberNegativeReviews = numberNegativeReviews;
		this.averagePlaytime = averagePlaytime;
		this.gamePhotoLink = gamePhotoLink;
		this.averageReview = this.calculateWeightedAverage(numberPositiveReviews, numberNegativeReviews);
		this.totalNumberOfReviews = numberPositiveReviews + numberNegativeReviews;
		this.description = description;
	}

	private double calculateWeightedAverage(int numberPositiveReviews, int numberNegativeReviews) {
		final double baselineScore = 5.0;
		final double baselineCount = 10.0;
		final double reviewVolumeWeight = 0.5;
		final double maxVolumeEffect = 2.0;
		double totalReviews = numberPositiveReviews + numberNegativeReviews;
		double weightedScore;

		if (totalReviews == 0) {
			return baselineScore;
		} else {
			weightedScore = ((double) numberPositiveReviews + baselineScore * baselineCount - numberNegativeReviews)
					/ (totalReviews + baselineCount);
		}

		double reviewVolumeFactor = Math.min(1.0 + Math.log10(1 + totalReviews) * reviewVolumeWeight, maxVolumeEffect);
		weightedScore *= reviewVolumeFactor;

		weightedScore = Math.max(weightedScore, 0);

		double minScale = 1.0;
		double maxScale = 10.0;
		double normalizedScore = ((weightedScore + 1) / 2) * (maxScale - minScale) + minScale;

		normalizedScore = Math.max(minScale, Math.min(normalizedScore, maxScale));

		return normalizedScore;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Game game = (Game) obj;
		return this.gameID == game.gameID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.gameID);
	}

	@Override
	public String toString() {
		return String.format(this.name);
	}

}
