package application.test.viewModel.game;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.UserProfile;
import application.test.TestConstants;
import application.viewModel.game.GameCardPageViewModel;

public class TestGameCardPageViewModel {

    private GameCardPageViewModel viewModel;
    private UserProfile user;

    @BeforeEach
    void setUp() {
        this.viewModel = new GameCardPageViewModel();
        this.user = new UserProfile();
    }

    @Test
    void testSetAndGetCurrRecommendation() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
        GameCardPageViewModel.setCurrRecommendation(game);

        assertEquals(game, GameCardPageViewModel.getCurrRecommendation());
    }

    @Test
    void testAddGameToLikedLibrary() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
        this.user.setAllLikedGames(new ArrayList<>()); 

        assertTrue(this.viewModel.addGameToLikedLibrary(this.user, game));
        assertTrue(this.user.getAllLikedGames().contains(game));
    }

    @Test
    void testAddGameToDislikedLibrary() {
	    Game game = new Game(TestConstants.EXAMPLE_TITLE, Arrays.asList(Genre.ACTION), 1);
        this.user.setAllDislikedGames(new ArrayList<>());

        assertTrue(this.viewModel.addGameToDislikedLibrary(this.user, game));
        assertTrue(this.user.getAllDislikedGames().contains(game));
    }
}
