package application.test.fileIO;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import application.fileIO.GameLibraryIO;
import application.model.local_impl.game.GameLibrary;
import application.model.local_impl.game.Genre;
import application.test.TestConstants;

class TestGameLibraryIO {
	
	
	
	@Test
	void testParseGamesFromFileSuccessfulParsing() {
		//GameLibraryIO gameLibaryIO = new GameLibraryIO();
	    GameLibrary resultLibrary = GameLibraryIO.parseGamesFromFile();	    
	    assertEquals(27075, resultLibrary.size(), "The library size should match the expected number of games.");	    
	}
	
	@Test
	void testParseGamesFromTextSuccess() {
	    String csvText = TestConstants.IO_HEADER
	    		+ "1,\"\"\"Counter-Strike\"\" \",11/1/2000,Valve,windows;mac;linux,0,Multi-player;Online Multi-Player;Local Multi-Player;Valve Anti-Cheat enabled,Action,Action;FPS;Multiplayer,124534,3339,17612,317,https://steamcdn-a.akamaihd.net/steam/apps/10/header.jpg?t=1528733245,description";

	    GameLibrary resultLibrary = GameLibraryIO.parseGamesFromText(csvText);

	    assertEquals(1, resultLibrary.size(), "Library should contain one game.");
	}
	
	@Test
	void testParseGamesFromTextMalformedData() {
	    String csvText = TestConstants.IO_HEADER 
	    		+ "10,\"\"\"Counter-Strike\"\" \",11/1/2000,Valve,windows;mac;linux,0,Multi-player;Online Multi-Player;Local Multi-Player;Valve Anti-Cheat enabled,Action,Action;FPS;Multiplayer,124534,F,F,F,https://steamcdn-a.akamaihd.net/steam/apps/10/header.jpg?t=1528733245,description";

	    GameLibrary resultLibrary = GameLibraryIO.parseGamesFromText(csvText);
	    assertEquals(0, resultLibrary.size(), "Library should be empty due to malformed data.");
	}
	
	@Test
	void testParseGamesFromTextMissingField() {
	    String csvText = TestConstants.IO_HEADER 
	    		+ "10,\"\"\"Counter-Strike\"\" \",11/1/2000,windows;mac;linux,0,Multi-player;Online Multi-Player;Local Multi-Player;Valve Anti-Cheat enabled,Action,Action;FPS;Multiplayer,124534,F,F,F,https://steamcdn-a.akamaihd.net/steam/apps/10/header.jpg?t=1528733245,description";

	    GameLibrary resultLibrary = GameLibraryIO.parseGamesFromText(csvText);

	    assertEquals(0, resultLibrary.size(), "Library should be empty due to malformed data.");
	}
	
	@Test
	void testParseGamesGoodGenre() {
	    String csvText = TestConstants.IO_HEADER 
	                    + "10,\"\"\"Counter-Strike\"\" \",11/1/2000,Valve,windows;mac;linux,0,Multi-player;Online Multi-Player;Local Multi-Player;Valve Anti-Cheat enabled,Action,Action;FPS;Multiplayer,124534,3339,17612,317,https://steamcdn-a.akamaihd.net/steam/apps/10/header.jpg?t=1528733245,description";

	    GameLibrary resultLibrary = GameLibraryIO.parseGamesFromText(csvText);
	    assertEquals(Genre.ACTION, resultLibrary.getGames().get(0).getGenres().get(0), "Library should contain one game.");
	}
	
	@Test
	void testParseGameBadGenre() {
	    String csvText = TestConstants.IO_HEADER
	                    + "10,\"\"\"Counter-Strike\"\" \",11/1/2000,Valve,windows;mac;linux,0,Multi-player;Online Multi-Player;Local Multi-Player;Valve Anti-Cheat enabled,Fraction,Fraction;FPS;Multiplayer,124534,3339,17612,317,https://steamcdn-a.akamaihd.net/steam/apps/10/header.jpg?t=1528733245,description";

	    GameLibrary resultLibrary = GameLibraryIO.parseGamesFromText(csvText);

	    assertEquals(Genre.MISSING_GENRE, resultLibrary.getGames().get(0).getGenres().get(0), "Library should contain one game.");
	}
	
}
