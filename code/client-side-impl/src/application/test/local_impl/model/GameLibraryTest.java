package application.test.local_impl.model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.GameLibrary;
import application.model.local_impl.game.Genre;
import application.test.local_impl.TestConstants;

class GameLibraryTest {

	private GameLibrary library;
    private Game game1;
    private Game game2;

    @BeforeEach
    void setUp() {
        this.library = new GameLibrary();
        this.game1 = new Game("Game 1", List.of(Genre.ACTION), 1, "Developer 1", 2020, 5, 100, 10, 20, TestConstants.EXAMPLE_IMG_LINK, "who is this");
        this.game2 = new Game("Game 2", List.of(Genre.ADVENTURE), 2, "Developer 2", 2021, 6, 200, 20, 30, TestConstants.EXAMPLE_IMG_LINK, "what is this");
    }

    @Test
    void testAddGame() {
    	this.library.addGame(this.game1);
        assertEquals(1, this.library.size(), "Library should have 1 game after adding.");
    }

    @Test
    void testAddNullGame() {
        assertThrows(IllegalArgumentException.class, () -> this.library.addGame(null), "Adding null game should throw IllegalArgumentException.");
    }

    @Test
    void testAddDuplicateGame() {
    	this.library.addGame(this.game1);
        assertThrows(IllegalArgumentException.class, () -> this.library.addGame(this.game1), "Adding duplicate game should throw IllegalArgumentException.");
    }

    @Test
    void testRemoveGame() {
    	this.library.addGame(this.game1);
        boolean result = this.library.removeGame(this.game1.getGameID());
        assertTrue(result, "Remove should return true when a game is successfully removed.");
        assertEquals(0, this.library.size(), "Library should be empty after removing the game.");
    }

    @Test
    void testRemoveNonexistentGame() {
        boolean result = this.library.removeGame(999); 
        assertFalse(result, "Remove should return false when trying to remove a game that does not exist.");
    }

    @Test
    void testGetGames() {
    	this.library.addGame(this.game1);
    	this.library.addGame(this.game2);
        List<Game> games = this.library.getGames();
        assertEquals(2, games.size(), "Should return a list containing all added games.");
        assertTrue(games.contains(this.game1) && games.contains(this.game2), "List should contain both added games.");
    }

    @Test
    void testFindGameById() {
    	this.library.addGame(this.game1);
        Optional<Game> foundGame = this.library.findGameById(this.game1.getGameID());
        assertTrue(foundGame.isPresent(), "Should find the game by its ID.");
        assertEquals(this.game1, foundGame.get(), "Found game should match the added game.");
    }

    @Test
    void testFindGameByNonexistentId() {
        Optional<Game> foundGame = this.library.findGameById(999);
        assertFalse(foundGame.isPresent(), "Should not find a game with a nonexistent ID.");
    }

    @Test
    void testToString() {
    	this.library.addGame(this.game1);
        String expected = this.game1.toString();
        assertEquals(expected, this.library.toString(), "toString should match the expected string representation.");
    }

}
