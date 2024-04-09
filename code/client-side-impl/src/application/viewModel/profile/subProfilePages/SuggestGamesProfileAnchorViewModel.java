package application.viewModel.profile.subProfilePages;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.server_impl.profile.ActiveUser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class SuggestGamesProfileAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 3
 */
public class SuggestGamesProfileAnchorViewModel {

	private ListProperty<Game> allGamesProperty;
	private ObjectProperty<Game> selectedGameToSuggestProperty;
	
	private ListProperty<Game> suggestedGamesProperty;
	private ObjectProperty<Game> selectedSuggestedGameProperty;
	
	private StringProperty inputtedUsernameProperty;
	
	/**
	 * Instantiates a new suggest games profile anchor view model.
	 */
	public SuggestGamesProfileAnchorViewModel() {
		this.allGamesProperty = new SimpleListProperty<Game>();
		this.selectedGameToSuggestProperty = new SimpleObjectProperty<Game>();
		this.suggestedGamesProperty = new SimpleListProperty<Game>();
		this.selectedSuggestedGameProperty = new SimpleObjectProperty<Game>();
		this.inputtedUsernameProperty = new SimpleStringProperty();
	}
	
	/**
	 * Sets the up all games list.
	 */
	public void setUpAllGamesList() {
		ObservableList<Game> allGamesList = FXCollections.observableArrayList(Main.getGames());
		this.allGamesProperty.setValue(allGamesList);
	}
	
	/**
	 * Sets the up suggested games to user list.
	 */
	public void setUpSuggestedGamesToUserList() {
		ObservableList<Game> suggestedToUserGames = FXCollections.observableArrayList(ActiveUser.getActiveUser().getSuggestedToUserGames());
		this.suggestedGamesProperty.setValue(suggestedToUserGames);

	}
	
	/**
	 * Recommend game to specified user.
	 */
	public void recommendGameToSpecifiedUser() {
		
	}

	/**
	 * Gets the all games property.
	 *
	 * @return the all games property
	 */
	public ListProperty<Game> getAllGamesProperty() {
		return this.allGamesProperty;
	}

	/**
	 * Gets the selected game to suggest property.
	 *
	 * @return the selected game to suggest property
	 */
	public ObjectProperty<Game> getSelectedGameToSuggestProperty() {
		return this.selectedGameToSuggestProperty;
	}

	/**
	 * Gets the suggested games property.
	 *
	 * @return the suggested games property
	 */
	public ListProperty<Game> getSuggestedGamesProperty() {
		return this.suggestedGamesProperty;
	}

	/**
	 * Gets the selected suggested game property.
	 *
	 * @return the selected suggested game property
	 */
	public ObjectProperty<Game> getSelectedSuggestedGameProperty() {
		return this.selectedSuggestedGameProperty;
	}

	/**
	 * Gets the inputted username property.
	 *
	 * @return the inputted username property
	 */
	public StringProperty getInputtedUsernameProperty() {
		return this.inputtedUsernameProperty;
	}

}
