package application.test.viewModel.mystiverse.subMystiversePages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.GameRecommendationEngine;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ActiveUser;
import application.viewModel.mystiverse.subMystiversePages.RecommendationPageAnchorViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestRecommendationPageAnchorViewModel {

    private RecommendationPageAnchorViewModel viewModel;

    @BeforeEach
    void setUp() {
    	this.viewModel = new RecommendationPageAnchorViewModel();
    }

    @Test
    void testSetupInitialProperties() {
        List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE);
        List<Game> recommendations = new ArrayList<>();
        Game game = new Game("Test", new ArrayList(defaultGenres), 3);
        recommendations.add(game);

        this.viewModel.setRecommendations(recommendations);
        this.viewModel.setupInitialProperties();

        assertEquals("Test Game", this.viewModel.getTitleProperty().get());
        assertEquals("Test Description", this.viewModel.getDescProperty().get());
        assertEquals("[" + game.getGenres().toString() + "]", this.viewModel.getGenresProperty().get());
        assertNotNull(this.viewModel.getImageProperty().getValue());
    }

    @Test
    void testGenerateRecommendations() {
        GameRecommendationEngine engine = new GameRecommendationEngine();
        List<Game> generatedRecommendations = this.viewModel.generateRecommendations();
        assertEquals(0, generatedRecommendations.size());
    }

    @Test
    void testSkipGame() {
        List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE);
        List<Game> recommendations = new ArrayList<>();
        Game game = new Game("Game", new ArrayList(defaultGenres), 9);
        this.viewModel.getRecommendations().add(game);
        this.viewModel.skipGame(game);
        assertTrue(this.viewModel.getRecommendations().isEmpty());
    }

    @Test
    void testInterestedInGame() {
        List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE);
        List<Game> recommendations = new ArrayList<>();
        Game game = new Game("Some Game", new ArrayList(defaultGenres), 12);
        this.viewModel.getRecommendations().add(game);
        this.viewModel.interestedInGame(game);
        assertTrue(ActiveUser.getActiveUser().getAllLikedGames().contains(game));
        assertTrue(this.viewModel.getRecommendations().isEmpty());
    }

    @Test
    void testNotInterestedInGame() {
        List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE);
        List<Game> recommendations = new ArrayList<>();
        Game game = new Game("Test Game", new ArrayList(defaultGenres), 1);
        this.viewModel.getRecommendations().add(game);
        this.viewModel.notInterestedInGame(game);
        assertTrue(ActiveUser.getActiveUser().getAllDislikedGames().contains(game));
        assertTrue(this.viewModel.getRecommendations().isEmpty());
    }
}
