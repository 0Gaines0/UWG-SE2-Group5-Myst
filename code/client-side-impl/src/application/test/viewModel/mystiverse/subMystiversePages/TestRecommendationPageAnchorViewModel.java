package application.test.viewModel.mystiverse.subMystiversePages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.GameRecommendationEngine;
import application.model.server_impl.game.GameLibraryManager;
import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserProfile;
import application.viewModel.mystiverse.subMystiversePages.RecommendationPageAnchorViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestRecommendationPageAnchorViewModel {

    private RecommendationPageAnchorViewModel viewModel;
    private UserProfile user;

    @BeforeEach
    void setUp() {
		Main.setGames(GameLibraryManager.fetchAndParseGameLibrary().getGames());
    	this.user = new UserProfile("username", "password");
    	ActiveUser.setActiveUser(this.user);
    	this.viewModel = new RecommendationPageAnchorViewModel();
    }

//    @Test
//    void testSetupInitialProperties() {
//        List<Game> recommendations = new ArrayList<>();
//        Game game = Main.getGames().get(15);
//        Game game1 = Main.getGames().get(20);
//        recommendations.add(game);
//        recommendations.add(game1);
//
//        this.viewModel.setRecommendations(recommendations);
//        this.viewModel.setupInitialProperties();
//
//        assertEquals("Counter-Strike\r\n", this.viewModel.getTitleProperty().get());
//        assertEquals("Test Description", this.viewModel.getDescProperty().get());
//        assertEquals("[" + game.getGenres().toString() + "]", this.viewModel.getGenresProperty().get());
//        assertNotNull(this.viewModel.getImageProperty().getValue());
//    }

    @Test
    void testSkipGame() {
        Game game = Main.getGames().get(9);
        this.viewModel.getRecommendations().add(game);
        this.viewModel.skipGame(game);
        assertTrue(this.viewModel.getRecommendations().isEmpty());
    }

    @Test
    void testInterestedInGame() {
        Game game = Main.getGames().get(0);
        this.viewModel.generateRecommendations();
        this.viewModel.getRecommendations().add(game);
        this.viewModel.interestedInGame(game);
        ActiveUser.getActiveUser().getAllOwnedGames().add(game);
        assertTrue(!ActiveUser.getActiveUser().getAllLikedGames().isEmpty());
    }

    @Test
    void testNotInterestedInGame() {
        Game game = Main.getGames().get(11);
        this.viewModel.getRecommendations().add(game);
        this.viewModel.notInterestedInGame(game);
        assertTrue(this.viewModel.getRecommendations().isEmpty());
    }
}
