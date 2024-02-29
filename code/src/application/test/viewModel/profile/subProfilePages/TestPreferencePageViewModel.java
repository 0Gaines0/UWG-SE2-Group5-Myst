package application.test.viewModel.profile.subProfilePages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.viewModel.profile.subProfilePages.PreferencePageViewModel;

public class TestPreferencePageViewModel {
	
	private PreferencePageViewModel viewModel;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new PreferencePageViewModel();
	}
	
	/**
	 * Configure new preferences.
	 */
	@Test
	public void testConfigureNewPreferences() {
		ActiveUser.setActiveUser(new UserProfile());
		this.viewModel.getHighPriorityGenre().setValue(Genre.ACCOUNTING);
		this.viewModel.getMediumPriorityGenre().setValue(Genre.ACTION);
		this.viewModel.getLowPriorityGenre().setValue(Genre.ADVENTURE);
		
		this.viewModel.configureNewUserPreferences();
		
		assertTrue(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ACCOUNTING));
		assertTrue(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ACTION));
		assertTrue(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ADVENTURE));
		
	}
	
	/**
	 * Test add selected game.
	 */
	@Test
	public void testAddSelectedGame() {
		var genre = new ArrayList<Genre>();
		genre.add(Genre.ACCOUNTING);
		var game = new Game("Game", genre, 100);
		
		this.viewModel.addSelectedGame(game);
		assertEquals(1, this.viewModel.getSelectedLikedGames().size());
		
	}
	
}
