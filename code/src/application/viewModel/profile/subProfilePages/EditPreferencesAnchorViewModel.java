/*
 * 
 */
package application.viewModel.profile.subProfilePages;

import application.model.game.Game;
import application.model.profile.ActiveUser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The Class EditPreferencesAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class EditPreferencesAnchorViewModel {

	private ListProperty<Game> likedGamesProperty;
	private ListProperty<Game> dislikedGamesProperty;
	private ObjectProperty<Game> selectedLikedGameProperty;
	private ObjectProperty<Game> selectedDislikedGameProperty;

	/**
	 * Instantiates a new edits the preferences anchor view model.
	 */
	public EditPreferencesAnchorViewModel() {
		this.likedGamesProperty = new SimpleListProperty<Game>();
		this.dislikedGamesProperty = new SimpleListProperty<Game>();
		this.selectedLikedGameProperty = new SimpleObjectProperty<Game>();
		this.selectedDislikedGameProperty = new SimpleObjectProperty<Game>();
	}
	
	/**
	 * Removes the selected game from liked list.
	 *
	 * @return true, if successful
	 */
	public boolean removeSelectedGameFromLikedList() {
		if (this.selectedLikedGameProperty.getValue() != null) {
			var game = this.selectedLikedGameProperty.getValue();
			ActiveUser.getActiveUser().getAllLikedGames().remove(game);
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
			ActiveUser.getActiveUser().getAllDislikedGames().remove(game);
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
			ActiveUser.getActiveUser().getAllLikedGames().remove(game);
			ActiveUser.getActiveUser().getAllDislikedGames().add(game);
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
}
