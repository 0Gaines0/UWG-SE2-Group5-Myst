package application.test.viewModel.profile.subProfilePages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.viewModel.profile.subProfilePages.ProfileAnchorViewModel;

/**
 * The Class TestProfileAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestProfileAnchorViewModel {

	private ProfileAnchorViewModel viewModel;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new ProfileAnchorViewModel();
		ActiveUser.setActiveUser(new UserProfile());
	}

	/**
	 * Test set up about me description.
	 */
	@Test
	public void testSetUpAboutMeDescription() {
		var aboutMeDescription = "About me test";
		ActiveUser.getActiveUser().getProfileAttributes().setAboutMeDescription(aboutMeDescription);
		this.viewModel.setUpAboutMeDescription();

		assertEquals(aboutMeDescription, this.viewModel.getAboutMeProperty().getValue());

	}
	
	
	/**
	 * Test set up game liked and dis liked counters.
	 */
	@Test
	public void testSetUpGameLikedAndDisLikedCounters() {
		this.viewModel.setUpGameLikedAndDislikeCounters();
		
		assertEquals(String.valueOf(0), this.viewModel.getLikedGamesProperty().getValue());
		assertEquals(String.valueOf(0), this.viewModel.getDislikedGamesProperty().getValue());
		
	
	}
	
	/**
	 * Test set up genre pie chart data.
	 */
	@Test
	public void testSetUpGenrePieChartData() {
		var genre = new ArrayList<Genre>();
		genre.add(Genre.ACCOUNTING);
		var testGame = new Game("testGame", genre, 100);
		ActiveUser.getActiveUser().getAllOwnedGames().add(testGame);
		
		var data = this.viewModel.setUpGenrePieChartData();
		assertNotNull(data);
	}
	
}
