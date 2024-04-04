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
import application.viewModel.profile.subProfilePages.EditPreferencesAnchorViewModel;
import javafx.collections.FXCollections;

/**
 * The Class TestEditPreferencesAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestEditPreferencesAnchorViewModel {
	private EditPreferencesAnchorViewModel viewModel;
	private Game testGame;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		this.viewModel = new EditPreferencesAnchorViewModel();
		ActiveUser.setActiveUser(new UserProfile());
		var genre = new ArrayList<Genre>();
		genre.add(Genre.ACCOUNTING);
		this.testGame = new Game("testGame", genre, 1010);
	}

	/**
	 * Test remove selected game from liked list.
	 */
	@Test
	public void testRemoveSelectedGameFromLikedList() {
		ActiveUser.getActiveUser().getAllLikedGames().add(this.testGame);
		this.viewModel.getSelectedLikedGameProperty().set(this.testGame);

		assertTrue(this.viewModel.removeSelectedGameFromLikedList());
		assertTrue(ActiveUser.getActiveUser().getAllLikedGames().contains(this.testGame));
	}

	/**
	 * Test remove selected game from disliked list.
	 */
	@Test
	public void testRemoveSelectedGameFromDislikedList() {
		ActiveUser.getActiveUser().getAllDislikedGames().add(this.testGame);
		
		this.viewModel.getSelectedDislikedGameProperty().set(this.testGame);
		
		assertTrue(this.viewModel.removeSelectedGameFromDislikedList());
		assertTrue(ActiveUser.getActiveUser().getAllDislikedGames().contains(this.testGame));
	}
	
	/**
	 * Test move game from liked list to disliked list.
	 */
	@Test
	public void testMoveGameFromLikedListToDislikedList() {
		ActiveUser.getActiveUser().getAllLikedGames().add(this.testGame);

		this.viewModel.getSelectedLikedGameProperty().set(this.testGame);
		
		assertTrue(this.viewModel.moveGameFromLikedListToDislikedList());
		assertTrue(ActiveUser.getActiveUser().getAllLikedGames().contains(this.testGame));
		assertFalse(ActiveUser.getActiveUser().getAllDislikedGames().contains(this.testGame));
	}
	
	/**
	 * Test move game from disliked list to liked list.
	 */
	@Test
	public void testMoveGameFromDislikedListToLikedList() {
		ActiveUser.getActiveUser().getAllDislikedGames().add(this.testGame);

		this.viewModel.getSelectedDislikedGameProperty().set(this.testGame);
		
		assertTrue(this.viewModel.moveGameFromDislikedListToLikedList());
		assertTrue(ActiveUser.getActiveUser().getAllDislikedGames().contains(this.testGame));
		assertFalse(ActiveUser.getActiveUser().getAllLikedGames().contains(this.testGame));
	}
	
	/**
	 * Test remove genre from preferred genre list.
	 */
	@Test
	public void testRemoveGenreFromPreferredGenreList() {
		ActiveUser.getActiveUser().getPreferredGenres().add(Genre.ACCOUNTING);
		
		this.viewModel.getSelectedPreferredGenreProperty().set(Genre.ACCOUNTING);
		
		assertTrue(this.viewModel.removeSelectedGenreFromPreferredList());
		assertTrue(ActiveUser.getActiveUser().getPreferredGenres().contains(Genre.ACCOUNTING));
	}
	
	/**
	 * Test remove game from liked list but null.
	 */
	@Test
	public void testRemoveGameFromLikedListNull() {
		assertFalse(this.viewModel.removeSelectedGameFromLikedList());
	}
	
	/**
	 * Test remove game from disliked list null.
	 */
	@Test
	public void testRemoveGameFromDislikedListNull() {
		assertFalse(this.viewModel.removeSelectedGameFromDislikedList());
	}
	
	/**
	 * Test move game from liked list to disliked list null.
	 */
	@Test
	public void testMoveGameFromLikedListToDislikedListNull() {
		assertFalse(this.viewModel.moveGameFromLikedListToDislikedList());
	}
	
	/**
	 * Test move game from disliked list to liked list null.
	 */
	@Test
	public void testMoveGameFromDislikedListToLikedListNull() {
		assertFalse(this.viewModel.moveGameFromDislikedListToLikedList());
	}
	
	/**
	 * Test getters.
	 */
	@Test
	public void testGetters() {
		var gameList = new ArrayList<Game>();
		gameList.add(this.testGame);
		
		var genreList = new ArrayList<Genre>();
		genreList.add(Genre.ACCOUNTING);
		
		this.viewModel.getLikedGamesProperty().set(FXCollections.observableArrayList(gameList));
		this.viewModel.getDislikedGamesProperty().set(FXCollections.observableArrayList(gameList));
		this.viewModel.getPreferredGenreProperty().set(FXCollections.observableArrayList(genreList));
		assertEquals(1, this.viewModel.getLikedGamesProperty().getValue().size());
		assertEquals(1, this.viewModel.getDislikedGamesProperty().getValue().size());
		assertEquals(1, this.viewModel.getPreferredGenreProperty().getValue().size());


		
	}
}
