package application.test.viewModel.mystiverse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.GameRecommendationEngine;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.viewModel.mystiverse.MystiverseViewModel;

public class TestMystiverseViewModel {

    private MystiverseViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new MystiverseViewModel();
    }

    @Test
    void testGenerateRecommendations() {
//        // Set up a simple implementation of GameRecommendationEngine
//        GameRecommendationEngine engine = new GameRecommendationEngine() {
//            @Override
//            public List<Game> generateRecommendations(ActiveUser activeUser) {
//                // Provide mock recommendations for testing
//                return List.of(
//                        new Game("Game 1", Arrays.asList(Genre.ACTION), 1, "Developer 1", 2020, 5, 100, 10, 20, "http://example.com/game1.jpg"),
//                        new Game("Game 2", Arrays.asList(Genre.ADVENTURE), 2, "Developer 2", 2021, 6, 200, 20, 30, "http://example.com/game2.jpg"),
//                        new Game("Game 3", Arrays.asList(Genre.RPG), 3, "Developer 3", 2022, 7, 300, 30, 40, "http://example.com/game3.jpg")
//                );
//            }
//        };
//
//        viewModel.generateRecommendations();
//
//        // Check if the recommendations match the expected mock recommendations
//        List<Game> expectedRecommendations = List.of(
//                new Game("Game 1", Arrays.asList(Genre.ACTION), 1, "Developer 1", 2020, 5, 100, 10, 20, "http://example.com/game1.jpg"),
//                new Game("Game 2", Arrays.asList(Genre.ADVENTURE), 2, "Developer 2", 2021, 6, 200, 20, 30, "http://example.com/game2.jpg"),
//                new Game("Game 3", Arrays.asList(Genre.RPG), 3, "Developer 3", 2022, 7, 300, 30, 40, "http://example.com/game3.jpg")
//        );
//
//        assertEquals(expectedRecommendations, MystiverseViewModel.getRecommendedGames());
    }
}
