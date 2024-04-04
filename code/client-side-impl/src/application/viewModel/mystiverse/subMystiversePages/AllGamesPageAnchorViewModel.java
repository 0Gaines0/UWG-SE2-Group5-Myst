package application.viewModel.mystiverse.subMystiversePages;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.ActiveUser;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class AllGamesPageAnchorViewModel {

	private Property<Image> imageProperty;
	
	/**
	 * Instantiates a new all games page anchor view model.
	 */
	public AllGamesPageAnchorViewModel() {
		this.imageProperty = new SimpleObjectProperty<Image>();
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
			var filteredGames = allGames.stream().filter(game -> game.getName().toLowerCase().contains(text))
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
		var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
		likedGames.add(game);
		ActiveUser.getActiveUser().setAllLikedGames(likedGames);
	}
	
	/**
	 * Adds the game to disliked list.
	 * 
	 * @param game the game
	 */
	public void addGameToDislikedList(Game game) {
		var dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
		dislikedGames.add(game);
		ActiveUser.getActiveUser().setAllDislikedGames(dislikedGames);
	}
	
	/**
	 * Adds game to owned list.
	 * 
	 * @param game the game
	 */
	public void addGameToOwnedList(Game game) {
		var ownedGames = ActiveUser.getActiveUser().getAllOwnedGames();
		ownedGames.add(game);
		ActiveUser.getActiveUser().setAllOwnedGames(ownedGames);
	}
	
	/**
	 * Gets the image property.
	 *
	 * @return the image property
	 */
	public Property<Image> getImageProperty() {
		return this.imageProperty;
	}

	/**
	 * Sets the image.
	 *
	 * @param link the new image
	 */
	public void setImage(String link) {
		var image = new Image(link, true);
		this.imageProperty.setValue(image);
	}
}
