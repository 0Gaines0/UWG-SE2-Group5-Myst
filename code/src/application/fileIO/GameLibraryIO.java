package application.fileIO;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.GameLibrary;
import application.model.local_impl.game.Genre;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for parsing CSV data into a GameLibrary object. This class
 * provides functionality to read game information from a CSV-formatted string,
 * convert each line of the CSV into a Game object, and then collect these games
 * into a GameLibrary.
 * 
 * @author Thomas Lamont
 * @version Sprint 1
 */
public class GameLibraryIO {

	private static final String DATABASE_FILENAME = "merged_steam_game_database.csv";
	// private static final String DATABASE_FILENAME = "testData.csv";

	/**
	 * Parses a CSV string containing game data and returns a populated GameLibrary
	 * instance.
	 * 
	 * @return A GameLibrary containing all the games parsed from the CSV data.
	 */
	public static GameLibrary parseGamesFromFile() {
		GameLibrary library = new GameLibrary();
		Path path = Paths.get(DATABASE_FILENAME);

		try {
			List<String> lines = Files.readAllLines(path);

			for (int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				List<String> fields = parseCsvLine(line);
				Game game = parseGame(fields);
				if (game != null) {
					library.addGame(game);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading CSV file: " + e.getMessage());
		}
		System.out.println(library.size());
		return library;
	}

	/**
	 * Parses a CSV string containing game data and returns a populated GameLibrary
	 * instance.
	 * 
	 * @param csvText The CSV data as a string, where each line represents a game.
	 * @return A GameLibrary containing all the games parsed from the CSV data.
	 */
	public static GameLibrary parseGamesFromText(String csvText) {
		GameLibrary library = new GameLibrary();
		String[] lines = csvText.split("\n");

		for (int i = 1; i < lines.length; i++) {
			String line = lines[i];
			List<String> fields = parseCsvLine(line);
			Game game = parseGame(fields);
			if (game != null) {
				library.addGame(game);
			}
		}

		return library;
	}

	private static List<String> parseCsvLine(String csvLine) {
		List<String> fields = new ArrayList<>();
		boolean inQuotes = false;
		StringBuilder buffer = new StringBuilder();

		for (char currChar : csvLine.toCharArray()) {
			if (currChar == '"') {
				inQuotes = !inQuotes;
			} else if (currChar == ',' && !inQuotes) {
				fields.add(buffer.toString());
				buffer = new StringBuilder();
			} else {
				buffer.append(currChar);
			}
		}
		fields.add(buffer.toString());

		return fields;
	}

	private static Game parseGame(List<String> fields) {
		try {
			int appId = Integer.parseInt(fields.get(0));
			String name = fields.get(1).replaceAll("^\"|\"$", "");
			String[] releaseDateParts = fields.get(2).split("/");
			int releaseDateMonth = Integer.parseInt(releaseDateParts[0]);
			int releaseDateYear = Integer.parseInt(releaseDateParts[2]);
			String developer = fields.get(3);
			List<Genre> genres = Arrays.stream(fields.get(7).split(";")).map(GameLibraryIO::toGenre)
					.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
			int positiveRatings = Integer.parseInt(fields.get(9));
			int negativeRatings = Integer.parseInt(fields.get(10));
			int averagePlaytime = Integer.parseInt(fields.get(11));
			String gamePhotoLink = fields.get(13);
			String description = fields.get(14);
			Game game = new Game(name, genres, appId, developer, releaseDateYear, releaseDateMonth, positiveRatings,
					negativeRatings, averagePlaytime, gamePhotoLink, description);
			// System.out.println("added" + game);
			return game;
		} catch (Exception e) {
			System.err.println("Error parsing game from CSV fields: " + fields);
			return null;
		}
	}

	private static Optional<Genre> toGenre(String genreStr) {
		String normalized = genreStr.toUpperCase().replace(" ", "_").replace("-", "_");
		try {
			return Optional.of(Genre.valueOf(normalized));
		} catch (IllegalArgumentException e) {
			return Optional.of(Genre.MISSING_GENRE);
		}
	}
}
