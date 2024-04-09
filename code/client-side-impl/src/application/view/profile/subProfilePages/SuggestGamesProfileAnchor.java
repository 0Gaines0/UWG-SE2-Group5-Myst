package application.view.profile.subProfilePages;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.local_impl.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

	@FXML
	void initialize() {
		this.validateFXMLComponents();
		this.bindToViewmodel();
		this.setUpBtns();
		this.setUpAllListViews();
	}
	
	private void bindToViewmodel() {

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
		// Method in viewModel
	}

	private void setUpSuggestedGamesListView() {
		// Method in viewmodel
	}

	private void setUpSuggestGameBtn() {
		this.suggestGameBtn.setOnAction(((event -> {

		})));
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
