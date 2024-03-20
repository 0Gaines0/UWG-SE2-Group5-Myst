package application.viewModel.mystiverse.subMystiversePages;

import java.util.List;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;

public class SeedPageAnchorViewModel {

	List<Genre> selectedSeedGenres;
	List<Game> selectedSeedGames;
	
	List<Game> generatedRecommendations;
	
	public SeedPageAnchorViewModel() {
		
	}
}
