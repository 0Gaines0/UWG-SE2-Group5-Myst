package application.test.viewModel.mystiverse.subMystiversePages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.server_impl.game.GameLibraryManager;
import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserProfile;
import application.viewModel.mystiverse.subMystiversePages.RecommendationPageAnchorViewModel;
import javafx.embed.swing.JFXPanel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

    @Test
    void testSetupInitialProperties() {
        List<Game> recommendations = new ArrayList<>();
        new JFXPanel();
        Game game = Main.getGames().get(15);
        Game game1 = Main.getGames().get(20);
        recommendations.add(game);
        recommendations.add(game1);

        this.viewModel.setRecommendations(recommendations);
        this.viewModel.setupInitialProperties();

        assertEquals("Half-Life Deathmatch: Source", this.viewModel.getTitleProperty().get());
        assertEquals("Half-Life Deathmatch: Source is a recreation of the first multiplayer game set in the Half-Life universe. Features all the classic weapons and most-played maps, now running on the Source engine.", this.viewModel.getDescProperty().get());
        assertEquals(game.getGenres().toString(), this.viewModel.getGenresProperty().get());
        assertNotNull(this.viewModel.getImageProperty().getValue());
    }

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
