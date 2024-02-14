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
	 * Sets the about me property.
	 *
	 * @param aboutMeProperty the new about me property
	 */
	public void setAboutMeProperty(StringProperty aboutMeProperty) {
		this.aboutMeProperty = aboutMeProperty;
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
	 * Sets the liked games property.
	 *
	 * @param likedGamesProperty the new liked games property
	 */
	public void setLikedGamesProperty(StringProperty likedGamesProperty) {
		this.likedGamesProperty = likedGamesProperty;
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
	 * Sets the disliked games property.
	 *
	 * @param dislikedGamesProperty the new disliked games property
	 */
	public void setDislikedGamesProperty(StringProperty dislikedGamesProperty) {
		this.dislikedGamesProperty = dislikedGamesProperty;
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
	 * Sets the title favorite game property.
	 *
	 * @param titleFavoriteGameProperty the new title favorite game property
	 */
	public void setTitleFavoriteGameProperty(StringProperty titleFavoriteGameProperty) {
		this.titleFavoriteGameProperty = titleFavoriteGameProperty;
	}

	/**
	 * Gets the genres favorite game property.
	 *
	 * @return the genres favorite game property
	 */
	public StringProperty getGenresFavoriteGameProperty() {
		return this.genresFavoriteGameProperty;
	}

	/**
	 * Sets the genres favorite game property.
	 *
	 * @param genresFavoriteGameProperty the new genres favorite game property
	 */
	public void setGenresFavoriteGameProperty(StringProperty genresFavoriteGameProperty) {
		this.genresFavoriteGameProperty = genresFavoriteGameProperty;
	}
}
