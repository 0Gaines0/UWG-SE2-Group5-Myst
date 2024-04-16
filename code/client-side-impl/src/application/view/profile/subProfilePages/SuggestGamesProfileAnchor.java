package application.view.profile.subProfilePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.local_impl.game.Game;
import application.model.server_impl.profile.ActiveUser;
import application.viewModel.profile.subProfilePages.SuggestGamesProfileAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * The Class SuggestGamesProfileAnchor.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 3
 */
public class SuggestGamesProfileAnchor {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ListView<Game> allGameListView;

	@FXML
	private Button dislikedGameBtn;

	@FXML
	private ListView<Game> gamesSuggestedToUserListView;

	@FXML
	private Button listGameBtn;

	@FXML
	private Button removeGameBtn;

	@FXML
	private TextField searchGamesTextField;

	@FXML
	private Button suggestGameBtn;

	@FXML
	private TextField usernameTextField;
	
	private SuggestGamesProfileAnchorViewModel viewModel;

	/**
	 * Instantiates a new suggest games profile anchor.
	 */
	public SuggestGamesProfileAnchor() {
		this.viewModel = new SuggestGamesProfileAnchorViewModel();
	}	
	
	@FXML
	void initialize() {
		this.validateFXMLComponents();
		this.bindToViewmodel();
		this.setUpBtns();
		this.setUpAllListViews();
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

		}
	}
	
	private void bindToViewmodel() {
		this.usernameTextField.textProperty().bindBidirectional(this.viewModel.getInputtedUsernameProperty());
		this.allGameListView.itemsProperty().bindBidirectional(this.viewModel.getAllGamesProperty());
		this.gamesSuggestedToUserListView.itemsProperty().bindBidirectional(this.viewModel.getSuggestedGamesProperty());
		this.viewModel.getSelectedGameToSuggestProperty().bind(this.allGameListView.getSelectionModel().selectedItemProperty());
		this.setUpSearchGameChangeListener();
	}

	private void setUpSearchGameChangeListener() {
		this.searchGamesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.viewModel.searchAllGamesAndFilter(newValue);
		});
	}

	private void setUpBtns() {
		this.setUpSuggestGameBtn();
		this.setUpLikeGameBtn();
		this.setUpDislikeGameBtn();
		this.setUpRemoveGameBtn();
	}

	private void setUpAllListViews() {
		this.setUpAllGamesListView();
		this.setUpSuggestedGamesListView();
		
	}

	private void setUpAllGamesListView() {
		this.allGameListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.viewModel.setUpAllGamesList();
	}

	private void setUpSuggestedGamesListView() {
		this.viewModel.setUpSuggestedGamesToUserList();
	}

	private void setUpSuggestGameBtn() {
		this.suggestGameBtn.setOnAction(((event) -> {
			if (this.allGameListView.getSelectionModel().selectedItemProperty() != null) {
				if (this.viewModel.isUsernameValid()) {
					this.viewModel.recommendGameToSpecifiedUser();
					var confirmPopUp = new Alert(AlertType.CONFIRMATION);
					confirmPopUp.setContentText("Suggestion successful!");
					confirmPopUp.showAndWait();
				} else {
					var errorPopUp = new Alert(AlertType.ERROR);
					errorPopUp.setContentText("Suggestion failed, username does not exist");
					errorPopUp.showAndWait();
				}
			}
		}));
	}

	private void setUpLikeGameBtn() {
		this.listGameBtn.setOnAction(((event -> {

		})));
	}

	private void setUpDislikeGameBtn() {
		this.dislikedGameBtn.setOnAction(((event -> {

		})));
	}

	private void setUpRemoveGameBtn() {
		this.removeGameBtn.setOnAction(((event -> {

		})));
	}

	private void validateFXMLComponents() {
		assert this.allGameListView != null
				: "fx:id=\"allGameListView\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.dislikedGameBtn != null
				: "fx:id=\"dislikedGameBtn\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.gamesSuggestedToUserListView != null
				: "fx:id=\"gamesSuggestedToUserListView\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.listGameBtn != null
				: "fx:id=\"listGameBtn\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.removeGameBtn != null
				: "fx:id=\"removeGameBtn\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.searchGamesTextField != null
				: "fx:id=\"searchGamesTextField\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.suggestGameBtn != null
				: "fx:id=\"suggestGameBtn\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
		assert this.usernameTextField != null
				: "fx:id=\"usernameTextField\" was not injected: check your FXML file 'SuggestGamesProfileAnchor.fxml'.";
	}

}
