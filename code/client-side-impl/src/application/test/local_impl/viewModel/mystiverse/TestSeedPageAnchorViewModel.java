package application.test.local_impl.viewModel.mystiverse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.game.GameLibraryManager;
import application.viewModel.mystiverse.subMystiversePages.SeedPageAnchorViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		assertNotNull(this.viewModel.getSelectedSeedGames());
	}

	@Test
	void testAddSelectedGenre() {
		assertNotNull(this.viewModel.getSelectedSeedGenres());
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
	
	@Test
	void testSettersAndGetters() {
		this.viewModel.setSelectedSeedGenres(new SimpleListProperty<Genre>());
		this.viewModel.setSelectedSeedGames(new SimpleListProperty<Game>());
		this.viewModel.setGeneratedRecommendations(new ArrayList<Game>());
		this.viewModel.setAllGames(new SimpleListProperty<Game>());
		this.viewModel.setAllGenres(new SimpleListProperty<Genre>());
		
		
		assertNotNull(this.viewModel.getGeneratedRecommendations());
		assertNotNull(this.viewModel.getSelectedSeedGenres());
		assertNotNull(this.viewModel.getSelectedSeedGames());
	}
}
