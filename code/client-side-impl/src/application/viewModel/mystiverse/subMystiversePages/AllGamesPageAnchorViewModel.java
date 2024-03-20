package application.viewModel.mystiverse.subMystiversePages;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ActiveUser;

public class AllGamesPageAnchorViewModel {

	/**
	 * Instantiates a new all games page anchor view model.
	 */
	public AllGamesPageAnchorViewModel() {
		
	}
	
	/**
	 * Filter on search.
	 *
	 * @param text the text
	 * @param allGames the all games
	 * @return the list
	 */
	public List<Game> filterOnSearch(String text, List<Game> allGames) {
		List<Game> results = new ArrayList<Game>();
		if (text != null) {
			var filteredGames = allGames.stream().filter(game -> game.getName().contains(text))
					.toList();
			if (filteredGames.size() != 0) {
				results = filteredGames;
			} else {
				results = Main.getGames();
			}
		} else {
			results = Main.getGames();
		}
		return results;
	}
	
	/**
	 * Search for genre.
	 *
	 * @param selectedGenre the selected genre
	 * @param allGames the all games
	 * @return the list
	 */
	public List<Game> searchForGenre(Genre selectedGenre, List<Game> allGames) {
		List<Game> results = new ArrayList<Game>();
		if (selectedGenre != null) {
			var filteredGames = allGames.stream().filter(game -> game.getGenres().contains(selectedGenre))
					.toList();
			if (filteredGames.size() != 0) {
				results = filteredGames;
			} else {
				results = Main.getGames();
			}
		} else {
			results = Main.getGames();
		}
		return results;
	}
	
	/**
	 * Adds the game to interested list.
	 *
	 * @param game the game
	 */
	public void addGameToInterestedList(Game game) {
		ActiveUser.getActiveUser().getAllLikedGames().add(game);
	}
}
