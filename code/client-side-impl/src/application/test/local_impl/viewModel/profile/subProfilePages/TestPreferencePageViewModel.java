package application.test.local_impl.viewModel.profile.subProfilePages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.UserProfile;
import application.viewModel.login.PreferencePageViewModel;

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
		this.viewModel.configureNewUserPreferences();
		
		assertFalse(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ACCOUNTING));
		assertFalse(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ACTION));
		assertFalse(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ADVENTURE));
		
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
