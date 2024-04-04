package application.test.local_impl.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.test.local_impl.TestConstants;

class GameTest {

	
	private static final String SOMETHING = "something";

	@Test
	void testGameConstructorMinimalInfo() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
	    assertNotNull(game);
	    assertEquals(TestConstants.EXAMPLE_TITLE, game.getName());
	    assertEquals(1, game.getGameID());
	    assertTrue(game.getGenres().contains(Genre.ACTION));
	}
	
	@Test
	void testGameConstructorFullInfo() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, "string");
	    assertNotNull(game);
	    assertEquals(TestConstants.EXAMPLE_DEVELOPER, game.getDevelopers());
	    assertEquals(2020, game.getReleaseDateYear());
	    assertEquals(1, game.getReleaseDateMonth());
	    assertEquals(100, game.getNumberPositiveReviews());
	    assertEquals(50, game.getNumberNegativeReviews());
	    assertEquals(10, game.getAveragePlaytime());
	    assertEquals(TestConstants.EXAMPLE_IMG_LINK, game.getGamePhoto());
	    assertTrue(game.getGenres().contains(Genre.ACTION));
	    assertEquals(150, game.getTotalNumberOfReviews());
	}
	
	@Test
	void testCalculateWeightedAverage() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 0, 10, TestConstants.EXAMPLE_IMG_LINK, SOMETHING);
	    assertTrue(game.getAverageReview() >= 1.0 && game.getAverageReview() <= 10.0);
	}
	
	@Test
	void testCalculateWeightedAverageNoReviews() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 0, 0, 10, TestConstants.EXAMPLE_IMG_LINK, SOMETHING);
	    assertEquals(5.0, game.getAverageReview(), "Expected baseline score for no reviews.");
	}
	
	@Test
	void testEquals() {
	    Game game1 = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
	    Game game2 = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
	    Game game3 = new Game("Another Game", Arrays.asList(Genre.ADVENTURE), 2);
	    
	    assertEquals(game1, game2, "Games with the same ID should be equal.");
	    assertNotEquals(game1, game3, "Games with different IDs should not be equal.");
	}
	
	@Test
	void testHashCode() {
	    Game game1 = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
	    Game game2 = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
	    
	    assertEquals(game1.hashCode(), game2.hashCode(), "Hash codes should be equal for games with the same ID.");
	}
	
	@Test
	void testToString() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
	    String expected = "Test Title";
	    assertEquals(expected, game.toString(), "toString should match the expected format.");
	}
	
	@Test
	void testConstructorWithNullName() {
	    assertThrows(IllegalArgumentException.class, () -> new Game(null, Arrays.asList(Genre.ACTION), 1),
	        "Constructor should throw IllegalArgumentException if name is null.");
	}
	@Test
	void testConstructorWithNullGenre() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, null, 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, "seomthign");
	    assertTrue(game.getGenres().contains(Genre.MISSING_GENRE), "Genres should contain MISSING_GENRE when initialized with null.");
	}
	
	@Test
	void testConstructorWithInvalidId() {
	    assertThrows(IllegalArgumentException.class, () -> new Game("TestGame", Arrays.asList(Genre.ACTION), 0),
	        "Constructor should throw IllegalArgumentException if name is null.");
	}
	
	@Test
	void testEqualsSameObject() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, "sumthing");
	    assertTrue(game.equals(game), "A game should be equal to itself.");
	}
	
	@Test
	void testEqualsNullObject() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, SOMETHING);
	    assertFalse(game.equals(null), "A game should not be equal to null.");
	}
	
	@Test
	void testEqualsDifferentClass() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, "somethinbg");
	    String differentClassObject = "I am not a game";
	    assertFalse(game.equals(differentClassObject), "A game should not be equal to an object of a different class.");
	}
	
	@Test
	void testEqualsDifferentGameID() {
	    Game game1 = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, "seomthing");
	    Game game2 = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 2, TestConstants.EXAMPLE_DEVELOPER, 2020, 1, 100, 50, 10, TestConstants.EXAMPLE_IMG_LINK, SOMETHING);
	    assertFalse(game1.equals(game2), "Two games with different IDs should not be equal.");
	}
	
}
