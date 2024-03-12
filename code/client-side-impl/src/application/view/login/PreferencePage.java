package application.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.view.profile.UserProfilePage;
import application.viewModel.login.PreferencePageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * the preferences page
 * 
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class PreferencePage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button cancelButton;

	@FXML
	private Button continueButton;

	@FXML
	private ListView<Genre> genresListView;

	@FXML
	private AnchorPane guiPane;

	@FXML
	private ListView<Game> likedGamesListView;

	@FXML
	private ListView<Genre> selectedGenresListView;

	@FXML
	private ListView<Game> selectedLikedGamesListView;

	private PreferencePageViewModel viewmodel;
	private UserProfilePage userProfileCodeBehind;

	/**
	 * the first time login page constructor
	 */
	public PreferencePage() {
		this.viewmodel = new PreferencePageViewModel();
		this.userProfileCodeBehind = new UserProfilePage();
	}

	/**
	 * Open preference page.
	 */
	public void openPreferencePage() {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(Main.PREFERENCE_PAGE_WINDOW));
			Parent parent = loader.load();
			var scene = new Scene(parent);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.initOwner(((Stage) (parent.getScene().getWindow())));
			newStage.setTitle(Main.WINDOW_TITLE);
			newStage.setScene(scene);
			newStage.show();

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		this.fxmlValidCmponents();
		this.bindToViewModel();
		this.setupButtons();
		this.setupListView();

	}

	private void bindToViewModel() {
		this.likedGamesListView.itemsProperty().bindBidirectional(this.viewmodel.getAllGames());
		this.genresListView.itemsProperty().bindBidirectional(this.viewmodel.getAllGenreProperty());

		this.setUpLikedGamesChangeListener();
		this.setUpLikedGenresChangeListener();
	}

	private void setUpLikedGenresChangeListener() {
		this.genresListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.viewmodel.addSelectedGenre(newValue);
				if (!this.selectedGenresListView.getItems().contains(newValue)) {
					this.selectedGenresListView.getItems().add(newValue);
				}
			}
		});
	}

	private void setUpLikedGamesChangeListener() {
		this.likedGamesListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.viewmodel.addSelectedGame(newValue);
						if (!this.selectedLikedGamesListView.getItems().contains(newValue)) {
							this.selectedLikedGamesListView.getItems().add(newValue);
						}
					}
				});
	}

	private void setupListView() {
		this.likedGamesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.genresListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.viewmodel.setUpAllGamesList();
		this.viewmodel.setUpAllGenresList();
	}

	private void setupButtons() {
		this.cancelButton.setOnAction((event) -> {
			var stage = (Stage) this.cancelButton.getScene().getWindow();
			stage.close();
			this.userProfileCodeBehind.openUserProfilePage();
		});
		this.continueButton.setOnAction((event) -> {
			this.viewmodel.configureNewUserPreferences();
			var stage = (Stage) this.continueButton.getScene().getWindow();
			stage.close();
			this.userProfileCodeBehind.openUserProfilePage();
		});
	}

	private void fxmlValidCmponents() {
		assert this.cancelButton != null
				: "fx:id=\"cancelButton\" was not injected: check your FXML file 'PreferencePage.fxml'.";
		assert this.continueButton != null
				: "fx:id=\"continueButton\" was not injected: check your FXML file 'PreferencePage.fxml'.";
		assert this.genresListView != null
				: "fx:id=\"genresListView\" was not injected: check your FXML file 'PreferencePage.fxml'.";
		assert this.guiPane != null : "fx:id=\"guiPane\" was not injected: check your FXML file 'PreferencePage.fxml'.";
		assert this.likedGamesListView != null
				: "fx:id=\"likedGamesListView\" was not injected: check your FXML file 'PreferencePage.fxml'.";
		assert this.selectedGenresListView != null
				: "fx:id=\"selectedGenresListView\" was not injected: check your FXML file 'PreferencePage.fxml'.";
		assert this.selectedLikedGamesListView != null
				: "fx:id=\"selectedLikedGamesListView\" was not injected: check your FXML file 'PreferencePage.fxml'.";

	}
}
