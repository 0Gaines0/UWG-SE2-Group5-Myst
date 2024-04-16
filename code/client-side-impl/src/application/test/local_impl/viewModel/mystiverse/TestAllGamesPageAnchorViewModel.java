package application.test.local_impl.viewModel.mystiverse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.game.GameLibraryManager;
import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.UserProfile;
import application.viewModel.mystiverse.subMystiversePages.AllGamesPageAnchorViewModel;

class TestAllGamesPageAnchorViewModel {

    private AllGamesPageAnchorViewModel viewModel;
    private List<Game> allGames;
	private UserProfile user;

    @BeforeEach
    void setUp() {
        this.viewModel = new AllGamesPageAnchorViewModel();
		Main.setGames(GameLibraryManager.fetchAndParseGameLibrary().getGames());
        this.allGames = Main.getGames();
    	this.user = new UserProfile("username", "password");
    	ActiveUser.setActiveUser(this.user);
    	this.viewModel = new AllGamesPageAnchorViewModel();
    }

    @Test
    void testFilterOnSearchWithText() {
        List<Game> filteredGames = this.viewModel.filterOnSearch("game1", this.allGames);
        assertEquals(27075, filteredGames.size());
        assertEquals("Counter-Strike", filteredGames.get(0).getName());
    }

    @Test
    void testFilterOnSearchWithNullText() {
        List<Game> filteredGames = this.viewModel.filterOnSearch(null, this.allGames);
        assertEquals(this.allGames, filteredGames);
    }

    @Test
    void testSearchForGenreWithNonNullGenre() {
        List<Game> filteredGames = this.viewModel.searchForGenre(Genre.ACTION, this.allGames);
        assertEquals(11903, filteredGames.size());
        assertEquals("Counter-Strike", filteredGames.get(0).getName());
    }

    @Test
    void testSearchForGenreWithNullGenre() {
        List<Game> filteredGames = this.viewModel.searchForGenre(null, this.allGames);
        assertEquals(this.allGames, filteredGames);
    }

    @Test
    void testAddGameToInterestedList() {
        Game newGame = Main.getGames().get(0);
        this.viewModel.addGameToInterestedList(newGame);
        List<Game> likedGames = ActiveUser.getActiveUser().getAllLikedGames();
        assertTrue(likedGames.contains(newGame));
    }
    
    @Test
    void testAddGameToDislikedList() {
    	Game newGame = Main.getGames().get(0);
        this.viewModel.addGameToDislikedList(newGame);
        List<Game> dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
        assertTrue(dislikedGames.contains(newGame));
    }
    
    @Test
    void testAddGameToOwnedList() {
    	Game newGame = Main.getGames().get(0);
        this.viewModel.addGameToOwnedList(newGame);
        List<Game> ownedGames = ActiveUser.getActiveUser().getAllOwnedGames();
        assertTrue(ownedGames.contains(newGame));
    }
    
}
