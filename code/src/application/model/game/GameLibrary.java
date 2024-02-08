package application.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Manages a collection of games.
 * 
 * @author Thomas Lamont
 * @version Sprint 1
 */
public class GameLibrary {

	private List<Game> games;

	/**
	 * Constructs a new GameLibrary instance.
	 */
	public GameLibrary() {
		this.games = new ArrayList<>();
	}

	/**
	 * Adds a game to the library.
	 * 
	 * @param game The game to add.
	 */
	public void addGame(Game game) {
		if (game == null) {
			throw new IllegalArgumentException("Game must not be null");
		}
		for (Game existingGame : this.games) {
			if (existingGame.equals(game)) {
				throw new IllegalArgumentException("Duplicate game ID: " + game.getGameID());
			}
		}
		this.games.add(game);
	}

	/**
	 * Removes a game from the library by its game ID.
	 * 
	 * @param gameID The ID of the game to remove.
	 * @return true if the game was found and removed, false otherwise.
	 */
	public boolean removeGame(int gameID) {
		return this.games.removeIf(game -> game.getGameID() == gameID);
	}

	/**
	 * Gets the list of all games in the library.
	 * 
	 * @return A list of all games.
	 */
	public List<Game> getGames() {
		return new ArrayList<>(this.games);
	}

	/**
	 * Finds a game by its ID.
	 * 
	 * @param gameID The ID of the game to find.
	 * @return An Optional containing the found game or empty if not found.
	 */
	public Optional<Game> findGameById(int gameID) {
		return this.games.stream().filter(game -> game.getGameID() == gameID).findFirst();
	}

	@Override
	public String toString() {
		return this.games.stream().map(Game::toString).collect(Collectors.joining("\n"));
	}
}
