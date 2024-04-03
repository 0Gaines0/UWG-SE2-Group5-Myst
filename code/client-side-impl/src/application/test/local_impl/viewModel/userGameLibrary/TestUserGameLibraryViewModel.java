package application.test.local_impl.viewModel.userGameLibrary;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.UserProfile;
import application.viewModel.UserGameLibrary.UserGameLibraryViewModel;

public class TestUserGameLibraryViewModel {

	private UserGameLibraryViewModel viewModel;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new UserGameLibraryViewModel();
		ActiveUser.setActiveUser(new UserProfile());
		this.viewModel.setUpGameLibrary();
	}

	/**
	 * Test set up game library.
	 */
	@Test
	public void testSetUpGameLibrary() {
		assertNotNull(this.viewModel.getOwnedGames());
	}

	/**
	 * Test get selected game name.
	 */
	@Test
	public void testGetSelectedGameName() {
		var gameName = this.viewModel.getSelectedGameName();
		assertNotNull(gameName);
	}

	/**
	 * Test get selected game developers.
	 */
	@Test
	public void testGetSelectedGameDevelopers() {
		assertNotNull(this.viewModel.getSelectedGameDevelopers());
	}

	/**
	 * Test get selected game genres.
	 */
	@Test
	public void testGetSelectedGameGenres() {
		assertNotNull(this.viewModel.getSelectedGameGenres());
	}

	/**
	 * Test set selected game.
	 */
	@Test
	public void testSetSelectedGame() {
		var testGame = new Game("Test1", new ArrayList<Genre>(), 002);
		this.viewModel.setSelectedGame(testGame);
		assertNotNull(this.viewModel.getSelectedGameDevelopers());
	}
}
