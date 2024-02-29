package application.view.UserGameLibraryPage;

import java.io.IOException;

import application.Main;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.view.profile.UserProfilePage;
import application.viewModel.UserGameLibrary.UserGameLibraryViewModel;
import application.viewModel.profile.UserProfilePageViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserGameLibraryPage {
	
	@FXML
	private HBox libraryHBox;
	
	@FXML
	private HBox mystiverseHBox;
	
	@FXML
	private HBox profileHBox;
	
	@FXML
	private ListView<Game> myGamesListView;
	
	@FXML
	private TextField gameTitleTextField;
	
	@FXML
	private TextField gameDevelopersTextField;
	
	@FXML
	private ListView<Genre> gameGenresListView;
	
	@FXML
	private ImageView gamePhotoImageView;
	
	@FXML
	private TextArea communityTextArea;
	
	
	private UserGameLibraryViewModel viewModel;
	
	/**
	 * Instantiates a new user game library page.
	 */
	public UserGameLibraryPage() {
		this.viewModel = new UserGameLibraryViewModel();
		new UserProfilePageViewModel();
		
	}
	
	@FXML
	public void initialize() {
		this.validateFXMLComponents();
		this.setupListView();
		this.bindToViewModel();
		this.setUpNavBar();
		this.viewModel.setUpGameLibrary();
		this.setUpGamesListViewListener();
	}
	

	/**
	 * Open user game library page.
	 */
	public void openUserGameLibraryPage() {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(Main.USER_GAME_LIBRARY_WINDOW));
			Parent parent = loader.load();
			var scene = new Scene(parent);
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.initOwner(((Stage) (parent.getScene().getWindow())));
			newStage.setTitle(Main.WINDOW_TITLE);
			newStage.setScene(scene);
			newStage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	private void bindToViewModel() {
		this.myGamesListView.itemsProperty().bindBidirectional(this.viewModel.getOwnedGames());
		this.gameTitleTextField.textProperty().bindBidirectional(this.viewModel.getSelectedGameName());
		//this.gameDevelopersTextField.textProperty().bindBidirectional(this.viewModel.getSelectedGameDevelopers());
		this.gameGenresListView.itemsProperty().bindBidirectional(this.viewModel.getSelectedGameGenres());
		
	}


	private void setupListView() {
		this.myGamesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	private void setUpNavBar() {
		this.setUpLibraryNavBarHBox();
		this.setUpMystiverseNavBarHbox();
		this.setUpProfileNavBarHBox();
	}
	
	private void setUpProfileNavBarHBox() {
		this.profileHBox.setOnMouseClicked(((event) -> {
			
		}));
	}

	private void setUpMystiverseNavBarHbox() {
		this.mystiverseHBox.setOnMouseClicked(((event) -> {
			var errorPopUp = new Alert(AlertType.CONFIRMATION);
			errorPopUp.setContentText("Button Click Works!");
			errorPopUp.showAndWait();
		}));
	}

	private void setUpLibraryNavBarHBox() {
		this.libraryHBox.setOnMouseClicked(((event) -> {
			var errorPopUp = new Alert(AlertType.CONFIRMATION);
			errorPopUp.setContentText("Button Click Works!");
			errorPopUp.showAndWait();
		}));
	}
	
	private void setUpGamesListViewListener() {
		this.myGamesListView.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> this.updateSelectedGame());
	}
	
	/**
	 * Update selected game.
	 */
	@FXML 
	public void updateSelectedGame() {
		this.viewModel.setSelectedGame(this.myGamesListView.getSelectionModel().getSelectedItem());
	}
	
	private void validateFXMLComponents() {
		 assert communityTextArea != null : "fx:id=\"communityTextArea\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert gameDevelopersTextField != null : "fx:id=\"gameDevelopersTextField\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert gameGenresListView != null : "fx:id=\"gameGenresListView\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert gamePhotoImageView != null : "fx:id=\"gamePhotoImageView\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert gameTitleTextField != null : "fx:id=\"gameTitleTextField\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert libraryHBox != null : "fx:id=\"libraryHBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert myGamesListView != null : "fx:id=\"myGamesListView\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert mystiverseHBox != null : "fx:id=\"mystiverseHBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	        assert profileHBox != null : "fx:id=\"profileHBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";

	}
}