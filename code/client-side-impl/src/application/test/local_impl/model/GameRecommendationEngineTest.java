package application.test.local_impl.model;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Main;
import application.model.local_impl.GameRecommendationEngine;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.GameLibrary;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.UserProfile;
import application.model.server_impl.game.GameLibraryManager;

class GameRecommendationEngineTest {

    private GameRecommendationEngine engine;
    private UserProfile testUser;

    @BeforeEach
    void setUp() {
        GameLibrary gameLibrary = new GameLibrary();
        gameLibrary.addGame(new Game("Game 1", Arrays.asList(Genre.ACTION), 1, "Developer 1", 2020, 5, 100, 10, 20, "http://example.com/game1.jpg", "not"));
        gameLibrary.addGame(new Game("Game 2", Arrays.asList(Genre.ADVENTURE), 2, "Developer 2", 2021, 6, 200, 20, 30, "http://example.com/game2.jpg", "yes"));
        gameLibrary.addGame(new Game("Game 3", Arrays.asList(Genre.RPG), 3, "Developer 3", 2022, 7, 300, 30, 40, "http://example.com/game3.jpg", "maybe"));

        this.engine = new GameRecommendationEngine(gameLibrary.getGames());

        this.testUser = new UserProfile("user", "pass");
        List<Genre> testGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.RPG);
        this.testUser.setPreferredGenres(testGenres);
    }

    @Test
    void testGenerateRecommendations() {
        List<Game> recommendations = this.engine.generateRecommendations(this.testUser);
        assertNotNull(recommendations);
        assertFalse(recommendations.isEmpty());
        assertTrue(recommendations.stream().allMatch(game -> game.getGenres().stream().anyMatch(this.testUser.getPreferredGenres()::contains)));
    }

    @Test
    void testGenerateRecommendationsRandom() {
        List<Game> recommendations = this.engine.generateRecommendationsRandom(this.testUser);
        assertNotNull(recommendations);
        assertFalse(recommendations.isEmpty());
        assertTrue(recommendations.stream().allMatch(game -> game.getGenres().stream().anyMatch(this.testUser.getPreferredGenres()::contains)));
    }
    
    @Test
    void testGenerateRecommendationsForSelectedGamesAndGenres() {
		Main.setGames(GameLibraryManager.fetchAndParseGameLibrary().getGames());
    	var game = Main.getGames().get(0);
    	var list = new ArrayList<Game>();
    	list.add(game);
    	assertNotNull(this.engine.generateRecommendationsForSelectedGamesAndGenres(list, game.getGenres()));
    }
}
