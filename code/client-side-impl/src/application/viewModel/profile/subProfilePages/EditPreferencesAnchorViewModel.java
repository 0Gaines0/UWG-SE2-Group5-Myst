/*
 * 
 */
package application.viewModel.profile.subProfilePages;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.ActiveUser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The Class EditPreferencesAnchorViewModel.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class EditPreferencesAnchorViewModel {

	private ListProperty<Game> likedGamesProperty;
	private ListProperty<Game> dislikedGamesProperty;
	private ListProperty<Genre> preferredGenreProperty;
	private ObjectProperty<Game> selectedLikedGameProperty;
	private ObjectProperty<Game> selectedDislikedGameProperty;
	private ObjectProperty<Genre> selectedPreferredGenreProperty;

	/**
	 * Instantiates a new edits the preferences anchor view model.
	 */
	public EditPreferencesAnchorViewModel() {
		this.likedGamesProperty = new SimpleListProperty<Game>();
		this.dislikedGamesProperty = new SimpleListProperty<Game>();
		this.preferredGenreProperty = new SimpleListProperty<Genre>();
		this.selectedLikedGameProperty = new SimpleObjectProperty<Game>();
		this.selectedDislikedGameProperty = new SimpleObjectProperty<Game>();
		this.selectedPreferredGenreProperty = new SimpleObjectProperty<Genre>();
	}

	/**
	 * Removes the selected game from liked list.
	 *
	 * @return true, if successful
	 */
	public boolean removeSelectedGameFromLikedList() {
		if (this.selectedLikedGameProperty.getValue() != null) {
			var game = this.selectedLikedGameProperty.getValue();
			var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
			likedGames.remove(game);
			ActiveUser.getActiveUser().setAllLikedGames(likedGames);
			return true;
		}
		return false;
	}

	/**
	 * Removes the selected genre from preferred list.
	 *
	 * @return true, if successful
	 */
	public boolean removeSelectedGenreFromPreferredList() {
		if (this.selectedPreferredGenreProperty.getValue() != null) {
			var genre = this.selectedPreferredGenreProperty.getValue();
			var genres = ActiveUser.getActiveUser().getPreferredGenres();
			genres.remove(genre);
			ActiveUser.getActiveUser().setPreferredGenres(genres);
			return true;
		}
		return false;
	}

	/**
	 * Removes the selected game from disliked list.
	 *
	 * @return true, if successful
	 */
	public boolean removeSelectedGameFromDislikedList() {
		if (this.selectedDislikedGameProperty.getValue() != null) {
			var game = this.selectedDislikedGameProperty.getValue();
			var dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
			dislikedGames.remove(game);
			ActiveUser.getActiveUser().setAllDislikedGames(dislikedGames);
			return true;
		}
		return false;
	}

	/**
	 * Move game from liked list to disliked list.
	 *
	 * @return true, if successful
	 */
	public boolean moveGameFromLikedListToDislikedList() {
		if (this.selectedLikedGameProperty.getValue() != null) {
			var game = this.selectedLikedGameProperty.getValue();

			var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
			likedGames.remove(game);
			ActiveUser.getActiveUser().setAllLikedGames(likedGames);

			var dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
			dislikedGames.add(game);
			ActiveUser.getActiveUser().setAllDislikedGames(dislikedGames);
			return true;
		}
		return false;
	}

	/**
	 * Move game from disliked list to liked list.
	 *
	 * @return true, if successful
	 */
	public boolean moveGameFromDislikedListToLikedList() {
		if (this.selectedDislikedGameProperty.getValue() != null) {
			var game = this.selectedDislikedGameProperty.getValue();
			ActiveUser.getActiveUser().getAllDislikedGames().remove(game);
			ActiveUser.getActiveUser().getAllLikedGames().add(game);

			var dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
			dislikedGames.remove(game);
			ActiveUser.getActiveUser().setAllDislikedGames(dislikedGames);

			var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
			likedGames.add(game);
			ActiveUser.getActiveUser().setAllLikedGames(likedGames);

			return true;
		}
		return false;
	}

	/**
	 * Gets the liked games property.
	 *
	 * @return the liked games property
	 */
	public ListProperty<Game> getLikedGamesProperty() {
		return this.likedGamesProperty;
	}

	/**
	 * Gets the disliked games property.
	 *
	 * @return the disliked games property
	 */
	public ListProperty<Game> getDislikedGamesProperty() {
		return this.dislikedGamesProperty;
	}

	/**
	 * Gets the selected liked game property.
	 *
	 * @return the selected liked game property
	 */
	public ObjectProperty<Game> getSelectedLikedGameProperty() {
		return this.selectedLikedGameProperty;
	}

	/**
	 * Gets the selected disliked game property.
	 *
	 * @return the selected disliked game property
	 */
	public ObjectProperty<Game> getSelectedDislikedGameProperty() {
		return this.selectedDislikedGameProperty;
	}

	/**
	 * Gets the preferred genre property.
	 *
	 * @return the preferred genre property
	 */
	public ListProperty<Genre> getPreferredGenreProperty() {
		return this.preferredGenreProperty;
	}

	/**
	 * Gets the selected preferred genre property.
	 *
	 * @return the selected preferred genre property
	 */
	public ObjectProperty<Genre> getSelectedPreferredGenreProperty() {
		return this.selectedPreferredGenreProperty;
	}
}
