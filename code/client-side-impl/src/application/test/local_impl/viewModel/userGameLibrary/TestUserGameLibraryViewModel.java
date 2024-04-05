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
import javafx.embed.swing.JFXPanel;

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
		var testGame = new Game("Test1", new ArrayList<Genre>(), 002, "dev", 2000, 100, 100, 100, 100, "https://steamcdn-a.akamaihd.net/steam/apps/10/header.jpg?t=1528733245", "des");
		new JFXPanel();
		this.viewModel.setSelectedGame(testGame);
		assertNotNull(this.viewModel.getSelectedGameDevelopers());
		assertNotNull(this.viewModel.getLikedGames());
		assertNotNull(this.viewModel.getDislikedGames());
		assertNotNull(this.viewModel.getSelectedGame());
		assertNotNull(this.viewModel.getSelectedGamesListProperty());
		assertNotNull(this.viewModel.getImageProperty());
		assertNotNull(this.viewModel.getGameDescriptionProperty());
	}
	
	/**
	 * Test set selected list.
	 */
	@Test
	public void testSetSelectedList() {
		this.viewModel.setSelectedList("Liked Games");
		this.viewModel.setSelectedList("Disliked Games");
		this.viewModel.setSelectedList("Owned Games");
		
		this.viewModel.removeSelectedGameFromList("Liked Games");
		this.viewModel.removeSelectedGameFromList("Disliked Games");
		this.viewModel.removeSelectedGameFromList("Owned Games");


	}
}
