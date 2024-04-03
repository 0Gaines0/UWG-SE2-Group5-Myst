package application.test.viewModel.mystiverse.subMystiversePages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.game.GameLibraryManager;
import application.viewModel.mystiverse.subMystiversePages.SeedPageAnchorViewModel;
import javafx.collections.ObservableList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestSeedPageAnchorViewModel {

	private SeedPageAnchorViewModel viewModel;

	@BeforeEach
	void setUp() {
		this.viewModel = new SeedPageAnchorViewModel();
	}


	@Test 
	void testGenerateSeededRecommendations() { 
		Main.setGames(GameLibraryManager.fetchAndParseGameLibrary().getGames());
		List<Game> seedGames = new ArrayList<>(); 
		List<Genre> seedGenres = new ArrayList<>();
	 	seedGenres.add(Genre.ACTION); 
	 	seedGenres.add(Genre.ADVENTURE); 
	 	List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE); 
	 	Game game1 = new Game("Game 3", new ArrayList<>(defaultGenres), 1);
	 	Game game2 = new Game("Game 4", new ArrayList<>(defaultGenres), 2); seedGames.add(game1);
	 	seedGames.add(game2); 
	 	List<Game> recommendations = this.viewModel.generateSeededRecommendations(seedGames, seedGenres);
	 	assertNotNull(recommendations); 
	}
	 

	@Test
	void testAddSelectedGame() {
		List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE);
		Game game1 = new Game("Game 1", new ArrayList<>(defaultGenres), 1);
		Game game2 = new Game("Game 2", new ArrayList<>(defaultGenres), 2);

		this.viewModel.addSelectedGame(game1);
		assertTrue(this.viewModel.getSelectedSeedGames().contains(game1));

		this.viewModel.addSelectedGame(game2);
		assertTrue(this.viewModel.getSelectedSeedGames().contains(game2));
	}

	@Test
	void testAddSelectedGenre() {
		Genre genre1 = Genre.ACTION;
		Genre genre2 = Genre.ADVENTURE;

		this.viewModel.addSelectedGenre(genre1);
		assertTrue(this.viewModel.getSelectedSeedGenres().contains(genre1));

		this.viewModel.addSelectedGenre(genre2);
		assertTrue(this.viewModel.getSelectedSeedGenres().contains(genre2));
	}

	@Test
	void testSetUpAllGamesList() {
		List<Genre> defaultGenres = Arrays.asList(Genre.ACTION, Genre.ADVENTURE);
		List<Game> games = new ArrayList<>();
		games.add(new Game("Game 1", new ArrayList<>(defaultGenres), 5));
		games.add(new Game("Game 2", new ArrayList<>(defaultGenres), 6));
		games.add(new Game("Game 3", new ArrayList<>(defaultGenres), 7));
		Main.setGames(games);

		this.viewModel.setUpAllGamesList();
		ObservableList<Game> allGamesList = this.viewModel.getAllGames().get();
		assertNotNull(allGamesList);
		assertEquals(games.size(), allGamesList.size());
	}

	@Test
	void testSetUpAllGenresList() {
		this.viewModel.setUpAllGenresList();
		ObservableList<Genre> allGenresList = this.viewModel.getAllGenres().get();
		assertNotNull(allGenresList);
		assertEquals(Genre.values().length, allGenresList.size());
	}
}
