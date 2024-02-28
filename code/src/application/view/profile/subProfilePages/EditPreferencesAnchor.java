package application.view.profile.subProfilePages;

import java.io.IOException;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.viewModel.profile.subProfilePages.EditPreferencesAnchorViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * The Class EditPreferencesAnchor.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class EditPreferencesAnchor {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ContextMenu dislikedGamesContextMenu;

	@FXML
	private ListView<Game> dislikedGamesListView;

	@FXML
	private ContextMenu likedGamesContextMenu;

	@FXML
	private ListView<Game> likedGamesListView;

	@FXML
	private MenuItem moveGameToDislikedMenuItem;

	@FXML
	private MenuItem moveGameToLikedMenuItem;

	@FXML
	private MenuItem removeGameDislikedMenuItem;

	@FXML
	private MenuItem removeGameLikedMenuItem;

	@FXML
	private ContextMenu preferredGenreContextMenu;

	@FXML
	private ListView<Genre> preferredGenresListView;

	@FXML
	private MenuItem removeGenreFromPreferredGenre;

	@FXML
	private MenuItem addGenreToPreferredGenreMenuItem;

	private EditPreferencesAnchorViewModel editPreferencesAnchorViewModel;

	/**
	 * Instantiates a new edits the preferences anchor.
	 */
	public EditPreferencesAnchor() {
		this.editPreferencesAnchorViewModel = new EditPreferencesAnchorViewModel();
	}

	/**
	 * Open anchor pane.
	 *
	 * @param parent        the parent
	 * @param newAnchorPath the new anchor path
	 */
	public void openAnchorPane(BorderPane parent, String newAnchorPath) {
		try {
			AnchorPane currentAnchor = (AnchorPane) parent.getCenter();
			var loader = new FXMLLoader(getClass().getResource(newAnchorPath));
			AnchorPane newAnchor = loader.load();
			parent.setCenter(newAnchor);
			parent.getChildren().remove(currentAnchor);
		} catch (IOException error) {
			error.getMessage();
		}
	}

	@FXML
	void initialize() {
		this.validateFXMLComponents();
		this.bindToViewModel();
		this.setUpListViewContextMenus();
		this.populateListViews();
	}

	private void populateListViews() {
		ObservableList<Game> likedGameList = FXCollections
				.observableArrayList(ActiveUser.getActiveUser().getAllLikedGames());
		ObservableList<Game> dislikedGameList = FXCollections
				.observableArrayList(ActiveUser.getActiveUser().getAllDislikedGames());
		ObservableList<Genre> preferredGenreList = FXCollections
				.observableArrayList(ActiveUser.getActiveUser().getPreferredGenres());
		this.likedGamesListView.setItems(null);
		this.dislikedGamesListView.setItems(null);
		this.preferredGenresListView.setItems(null);
		this.likedGamesListView.setItems(likedGameList);
		this.dislikedGamesListView.setItems(dislikedGameList);
		this.preferredGenresListView.setItems(preferredGenreList);

	}

	private void bindToViewModel() {
		this.editPreferencesAnchorViewModel.getSelectedLikedGameProperty()
				.bind(this.likedGamesListView.getSelectionModel().selectedItemProperty());
		this.editPreferencesAnchorViewModel.getSelectedDislikedGameProperty()
				.bind(this.dislikedGamesListView.getSelectionModel().selectedItemProperty());
		this.likedGamesListView.itemsProperty()
				.bindBidirectional(this.editPreferencesAnchorViewModel.getLikedGamesProperty());
		this.dislikedGamesListView.itemsProperty()
				.bindBidirectional(this.editPreferencesAnchorViewModel.getDislikedGamesProperty());
		this.preferredGenresListView.itemsProperty()
				.bindBidirectional(this.editPreferencesAnchorViewModel.getPreferredGenreProperty());
		this.editPreferencesAnchorViewModel.getSelectedPreferredGenreProperty()
				.bind(this.preferredGenresListView.getSelectionModel().selectedItemProperty());
	}

	private void setUpListViewContextMenus() {
		this.setUpMoveGameToDislikedMenuItem();
		this.setUpMoveGameToLikedMenuItem();
		this.setUpRemoveLikedGameMenuItem();
		this.setUpRemoveDislikedGameMenuItem();
		this.setUpRemovePreferredGenre();
	}

	private void setUpRemovePreferredGenre() {
		this.removeGenreFromPreferredGenre.setOnAction(((event -> {
			if (!this.editPreferencesAnchorViewModel.removeSelectedGenreFromPreferredList()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Genre could not be remove, select and try again");
				errorPopUp.showAndWait();
			} else {
				this.updateListViews();
			}

		})));
	}

	private void setUpRemoveDislikedGameMenuItem() {
		this.removeGameDislikedMenuItem.setOnAction(((event) -> {
			if (!this.editPreferencesAnchorViewModel.removeSelectedGameFromDislikedList()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Game could not be remove, select and try again");
				errorPopUp.showAndWait();
			} else {
				this.updateListViews();
			}
		}));
	}

	private void setUpRemoveLikedGameMenuItem() {
		this.removeGameLikedMenuItem.setOnAction(((event) -> {
			if (!this.editPreferencesAnchorViewModel.removeSelectedGameFromLikedList()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Game could not be remove, select and try again");
				errorPopUp.showAndWait();
			} else {
				this.updateListViews();
			}
		}));
	}

	private void setUpMoveGameToLikedMenuItem() {
		this.moveGameToLikedMenuItem.setOnAction(((event) -> {
			if (!this.editPreferencesAnchorViewModel.moveGameFromDislikedListToLikedList()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Game could not be moved, select and try again");
				errorPopUp.showAndWait();
			} else {
				this.updateListViews();
			}
		}));
	}

	private void setUpMoveGameToDislikedMenuItem() {
		this.moveGameToDislikedMenuItem.setOnAction(((event) -> {
			if (!this.editPreferencesAnchorViewModel.moveGameFromLikedListToDislikedList()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Game could not be moved, select and try again");
				errorPopUp.showAndWait();
			} else {
				this.updateListViews();
			}

		}));
	}

	private void updateListViews() {
		this.populateListViews();
	}

	private void validateFXMLComponents() {
		assert this.anchorPane != null
				: "fx:id=\"anchorPane\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.dislikedGamesContextMenu != null
				: "fx:id=\"dislikedGamesContextMenu\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.dislikedGamesListView != null
				: "fx:id=\"dislikedGamesListView\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.likedGamesContextMenu != null
				: "fx:id=\"likedGamesContextMenu\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.likedGamesListView != null
				: "fx:id=\"likedGamesListView\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.moveGameToDislikedMenuItem != null
				: "fx:id=\"moveGameToDislikedMenuItem\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.moveGameToLikedMenuItem != null
				: "fx:id=\"moveGameToLikedMenuItem\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.removeGameDislikedMenuItem != null
				: "fx:id=\"removeGameDislikedMenuItem\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
		assert this.removeGameLikedMenuItem != null
				: "fx:id=\"removeGameLikedMenuItem\" was not injected: check your FXML file 'EditPreferencesAnchor.fxml'.";
	}

}
