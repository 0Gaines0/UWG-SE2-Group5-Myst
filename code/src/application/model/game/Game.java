package application.model.game;

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
		if (genres == null) {
			this.genres.add(Genre.MISSING_GENRE);
		}
		if (gameID == 0) {
			throw new IllegalArgumentException("Genre must not be null");
		}
		this.name = name;
		this.genres = genres;
		this.gameID = gameID;
	}

	
	/**
	 * Instantiates a new game.
	 *
	 * @param name the name
	 * @param genres the genres
	 * @param gameID the game ID
	 * @param developers the developers
	 * @param releaseDateYear the release date year
	 * @param releaseDateMonth the release date month
	 * @param numberPositiveReviews the number positive reviews
	 * @param numberNegativeReviews the number negative reviews
	 * @param averagePlaytime the average playtime
	 * @param gamePhotoLink the game photo link
	 */
	public Game(String name, List<Genre> genres, int gameID, String developers, int releaseDateYear,
			int releaseDateMonth, int numberPositiveReviews, int numberNegativeReviews, int averagePlaytime,
			String gamePhotoLink) {
		this(name, genres, gameID);
		this.developers = developers;
		this.releaseDateYear = releaseDateYear;
		this.releaseDateMonth = releaseDateMonth;
		this.numberPositiveReviews = numberPositiveReviews;
		this.numberNegativeReviews = numberNegativeReviews;
		this.averagePlaytime = averagePlaytime;
		this.gamePhotoLink = gamePhotoLink;
		this.averageReview = (double) numberPositiveReviews/numberNegativeReviews;
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
		//String genresStr = this.genres.get(0).toString();
		String genresStr = this.genres.toString();

		return String.format("Name: %s, Genres: [%s], Game ID: %d", this.name, genresStr, this.gameID);
	}

}
