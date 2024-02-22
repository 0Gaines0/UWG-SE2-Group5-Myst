package application.viewModel.profile.subProfilePages;

import application.model.profile.ActiveUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProfileAnchorViewModel {

	private StringProperty aboutMeProperty;
	private StringProperty likedGamesProperty;
	private StringProperty dislikedGamesProperty;
	private StringProperty titleFavoriteGameProperty;
	private StringProperty genresFavoriteGameProperty;
	
	
	/**
	 * Instantiates a new profile anchor view model.
	 */
	public ProfileAnchorViewModel() {
		this.aboutMeProperty = new SimpleStringProperty();
		this.likedGamesProperty = new SimpleStringProperty();
		this.dislikedGamesProperty = new SimpleStringProperty();
		this.titleFavoriteGameProperty = new SimpleStringProperty();
		this.genresFavoriteGameProperty = new SimpleStringProperty();
	}
	
	/**
	 * Sets the up about me description.
	 */
	public void setUpAboutMeDescription() {
		this.aboutMeProperty.setValue(ActiveUser.getActiveUser().getProfileAttributes().getAboutMeDescription());
	}
	
	/**
	 * Sets the up game liked and dislike counters.
	 */
	public void setUpGameLikedAndDislikeCounters() {
		var likedGames = String.valueOf(ActiveUser.getActiveUser().getProfileAttributes().getTotalLikedGames());
		var dislikedGames = String.valueOf(ActiveUser.getActiveUser().getProfileAttributes().getTotalDislikedGame());
		this.likedGamesProperty.setValue(likedGames);
		this.dislikedGamesProperty.setValue(dislikedGames);
	}

	/**
	 * Gets the about me property.
	 *
	 * @return the about me property
	 */
	public StringProperty getAboutMeProperty() {
		return this.aboutMeProperty;
	}

	/**
	 * Gets the liked games property.
	 *
	 * @return the liked games property
	 */
	public StringProperty getLikedGamesProperty() {
		return this.likedGamesProperty;
	}

	/**
	 * Gets the disliked games property.
	 *
	 * @return the disliked games property
	 */
	public StringProperty getDislikedGamesProperty() {
		return this.dislikedGamesProperty;
	}
	/**
	 * Gets the title favorite game property.
	 *
	 * @return the title favorite game property
	 */
	public StringProperty getTitleFavoriteGameProperty() {
		return this.titleFavoriteGameProperty;
	}

	/**
	 * Gets the genres favorite game property.
	 *
	 * @return the genres favorite game property
	 */
	public StringProperty getGenresFavoriteGameProperty() {
		return this.genresFavoriteGameProperty;
	}
}
