package application.test.server_impl.game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import application.model.server_impl.game.GameLibraryManager;

public class TestGameLibraryManager {

	/**
	 * Test fetch and parse game library.
	 */
	@Test
	public void testFetchAndParseGameLibrary() {
		var gameLibrary = GameLibraryManager.fetchAndParseGameLibrary();
		
		assertTrue(!gameLibrary.getGames().isEmpty());
	}
	
}
