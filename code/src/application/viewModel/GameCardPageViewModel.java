package application.viewModel;

import application.model.profile.ActiveUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * the GameCardPageViewModel
 * 
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class GameCardPageViewModel {
	
	private StringProperty titleProperty;
	private StringProperty descriptionProperty;
	private StringProperty genresProperty;

	/**
	 * the game card page view model
	 */
	public GameCardPageViewModel() {
		this.titleProperty = new SimpleStringProperty();
		this.descriptionProperty = new SimpleStringProperty();
		this.genresProperty = new SimpleStringProperty();
	}
	
	/**
	 * Gets the title property.
	 *
	 * @return the title property
	 */
	public StringProperty getTitleProperty() {
		return this.titleProperty;
	}

	/**
	 * Gets the description property.
	 *
	 * @return the description property
	 */
	public StringProperty getDescriptionProperty() {
		return this.descriptionProperty;
	}

	/**
	 * Gets the genres property.
	 *
	 * @return the genres property
	 */
	public StringProperty getGenresProperty() {
		return this.genresProperty;
	}

	
	/**
	 * Adds the game to liked library.
	 *
	 * @return true if the add was successful false other wise
	 */
	public Boolean addGameToLikedLibrary() {
		return ActiveUser.getActiveUser().getAllLikedGames().add(null);
	}

	/**
	 * Adds the game to disliked library.
	 *
	 * @return the boolean
	 */
	public Boolean addGameToDislikedLibrary() {
		return ActiveUser.getActiveUser().getAllDislikedGames().add(null);
	}

}
