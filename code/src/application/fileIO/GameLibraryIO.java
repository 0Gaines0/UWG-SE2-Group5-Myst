package application.fileIO;

import application.model.game.Game;
import application.model.game.GameLibrary;
import application.model.game.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for parsing CSV data into a GameLibrary object.
 * This class provides functionality to read game information from a CSV-formatted string,
 * convert each line of the CSV into a Game object, and then collect these games into a GameLibrary.
 * 
 * @author Thomas Lamont
 * @version Sprint 1
 */
public class GameLibraryIO {

	/**
     * Parses a CSV string containing game data and returns a populated GameLibrary instance.
     * 
     * @param csvData The CSV data as a string, where each line represents a game.
     * @return A GameLibrary containing all the games parsed from the CSV data.
     */
	public static GameLibrary parseGames(String csvData) {
		GameLibrary library = new GameLibrary();
		List<String> lines = Arrays.asList(csvData.split("\n"));

		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			Game game = parseGame(line);
			if (game != null) {
				library.addGame(game);
			}
		}

		return library;
	}

	private static Game parseGame(String csvLine) {
		String[] fields = csvLine.split("\t");

		try {
			int appId = Integer.parseInt(fields[0]);
			String name = fields[1];
			String[] releaseDateParts = fields[2].split("/");
			int releaseDateMonth = Integer.parseInt(releaseDateParts[0]);
			int releaseDateYear = Integer.parseInt(releaseDateParts[2]);
			String developer = fields[3];
			List<Genre> genres = Arrays.stream(fields[7].split(";")).map(GameLibraryIO::toGenre)
					.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
			int positiveRatings = Integer.parseInt(fields[9]);
			int negativeRatings = Integer.parseInt(fields[10]);
			int averagePlaytime = Integer.parseInt(fields[11]);
			String gamePhotoLink = fields[13];

			return new Game(name, genres, appId, developer, releaseDateYear, releaseDateMonth, positiveRatings,
					negativeRatings, averagePlaytime, gamePhotoLink);
		} catch (NumberFormatException e) {
			System.err.println("Error parsing game from CSV line: " + csvLine);
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("CSV line has missing fields: " + csvLine);
			return null;
		}
	}

	private static Optional<Genre> toGenre(String genreStr) {
		String normalized = genreStr.toUpperCase().replace(" ", "_").replace("-", "_");
		try {
			return Optional.of(Genre.valueOf(normalized));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
