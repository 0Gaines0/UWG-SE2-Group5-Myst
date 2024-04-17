package application.viewModel.profile.subProfilePages;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.server_impl.Server;
import application.model.server_impl.ServerConstants;
import application.model.server_impl.profile.ActiveUser;
import application.model.server_impl.profile.credentials.CredentialManager;
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
 * 
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
		ObservableList<Game> suggestedToUserGames = FXCollections
				.observableArrayList(ActiveUser.getActiveUser().getSuggestedToUserGames());
		this.suggestedGamesProperty.setValue(suggestedToUserGames);

	}

	/**
	 * Search all games and filter.
	 *
	 * @param filterValue the filter value
	 */
	public void searchAllGamesAndFilter(String filterValue) {
		var filteredList = new ArrayList<Game>();

		for (var game : Main.getGames()) {
			if (game.getName().toLowerCase().startsWith(filterValue.toLowerCase())) {
				filteredList.add(game);
			} else if (game.getName().toLowerCase().contains(filterValue.toLowerCase())
					&& !filteredList.contains(game)) {
				filteredList.add(game);
			}
		}
		ObservableList<Game> filteredGames = FXCollections.observableArrayList(filteredList);
		this.allGamesProperty.setValue(filteredGames);
	}

	/**
	 * Recommend game to specified user.
	 */
	public void recommendGameToSpecifiedUser() {
		var username = this.inputtedUsernameProperty.get();
		var gameToSuggest = this.selectedGameToSuggestProperty.get();
		
		var json = new JSONObject();
		var gamesToSuggest = new ArrayList<Game>();
		gamesToSuggest.add(gameToSuggest);
		
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_SUGGESTED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);
			var gamesArray = new JSONArray(gamesToSuggest);
			json.put(ServerConstants.KEY_GAMES, gamesArray);
			
			Server.sendRequest(json.toString());
			
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Adds the selected game to liked games.
	 */
	public void addSelectedGameToLikedGames() {
		var game = this.selectedSuggestedGameProperty.getValue();
		var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
		likedGames.add(game);
		ActiveUser.getActiveUser().setAllLikedGames(likedGames);
		this.removeSelectedGameFromSuggestedGames();
	}
	
	/**
	 * Adds the selcted game to disliked games.
	 */
	public void addSelctedGameToDislikedGames() {
		var game = this.selectedSuggestedGameProperty.getValue();
		var dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
		dislikedGames.add(game);
		ActiveUser.getActiveUser().setAllDislikedGames(dislikedGames);
		this.removeSelectedGameFromSuggestedGames();
	}
	
	/**
	 * Removes the selected game from suggested games.
	 */
	public void removeSelectedGameFromSuggestedGames() {
		var game = this.selectedSuggestedGameProperty.getValue();
		var suggestedGames = ActiveUser.getActiveUser().getSuggestedToUserGames();
		suggestedGames.remove(game);
		ActiveUser.getActiveUser().setSuggestedToUserGames(suggestedGames);
		
	}

	/**
	 * Try username.
	 *
	 * @return true, if successful
	 */
	public boolean isUsernameValid() {
		var username = this.inputtedUsernameProperty.get();
		return new CredentialManager().userNameExist(username);
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
