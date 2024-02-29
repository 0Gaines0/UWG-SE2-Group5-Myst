package application.test.viewModel.mystiverse;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.GameRecommendationEngine;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.viewModel.mystiverse.MystiverseViewModel;

public class TestMystiverseViewModel {

    private MystiverseViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.viewModel = new MystiverseViewModel();
    }

    /**
     * Test get rcommended games.
     */
    @Test
    public void testGetRcommendedGames() {
    	ActiveUser.setActiveUser(new UserProfile());
    	var gamesList = new ArrayList<Game>();
    	gamesList.add(new Game("Test", new ArrayList<Genre>(), 200));
    	gamesList.add(new Game("Test1", new ArrayList<Genre>(), 201));
    	gamesList.add(new Game("Test2", new ArrayList<Genre>(), 202));
    	var engine = new GameRecommendationEngine(gamesList);
    	
    	this.viewModel.setEngine(engine);
    	
    	this.viewModel.generateRecommendations();
    	assertNotNull(MystiverseViewModel.getRecommendedGames());
    }
}
