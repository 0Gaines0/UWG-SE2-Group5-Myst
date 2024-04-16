package application.view.UserGameLibraryPage;

import java.io.IOException;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.server_impl.profile.ActiveUser;
import application.viewModel.UserGameLibrary.UserGameLibraryViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserGameLibraryPage {
	
	@FXML
	private AnchorPane baseAnchorPane;
	
	@FXML
	private BorderPane parentBorderPane;

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

	@FXML
	private ImageView profileImageNavBar;
	
	@FXML
    private ComboBox<String> gameListComboBox;
	
	@FXML
	private ContextMenu myGamesListViewContextMenu;
	
	@FXML
    private MenuItem removeGameContextMenuItem;
	
	@FXML
    private Button addGameButton;
	
    @FXML
    private TextArea gameDescriptionTextBox;



	private UserGameLibraryViewModel viewModel;

	/**
	 * Instantiates a new user game library page.
	 */
	public UserGameLibraryPage() {
		this.viewModel = new UserGameLibraryViewModel();
	}

	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {
		this.validateFXMLComponents();
		this.setupComboBox();
		this.setupListView();
		this.setUpRemoveGameMenuItem();
		this.bindToViewModel();
		this.setUpNavBar();
		this.setupAddGameButton();
		this.viewModel.setUpGameLibrary();
		this.setUpGamesListViewListener();
		this.setupGameListsComboBoxListener();
		this.configureProfileImage();
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
		this.myGamesListView.itemsProperty().bindBidirectional(this.viewModel.getSelectedGamesListProperty());
		this.gameTitleTextField.textProperty().bindBidirectional(this.viewModel.getSelectedGameName());
		this.gameDevelopersTextField.textProperty().bindBidirectional(this.viewModel.getSelectedGameDevelopers());
		this.gameGenresListView.itemsProperty().bindBidirectional(this.viewModel.getSelectedGameGenres());
    	this.gamePhotoImageView.imageProperty().bindBidirectional(this.viewModel.getImageProperty());
    	this.gameDescriptionTextBox.textProperty().bindBidirectional(this.viewModel.getGameDescriptionProperty());

	}
	
	private void setupAddGameButton() {
		this.addGameButton.setOnMouseClicked((event) -> {
			var newStage = new Stage();
			try {
				var loader = new FXMLLoader(getClass().getResource(Main.USER_GAME_LIBRARY_ADD_GAME_PAGE));
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
		});
	}

	private void setupListView() {
		this.myGamesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.gameListComboBox.getSelectionModel().select(0);
		this.viewModel.setSelectedList("Liked Games");
	}
	
	private void setupComboBox() {
		ObservableList<String> gameLists = FXCollections.observableArrayList();
		gameLists.add("Liked Games");
		gameLists.add("Disliked Games");
		gameLists.add("Owned Games");
		this.gameListComboBox.setItems(gameLists);
	}

	private void setUpNavBar() {
		this.setUpLibraryNavBarHBox();
		this.setUpMystiverseNavBarHbox();
		this.setUpProfileNavBarHBox();
	}

	private void setUpProfileNavBarHBox() {
		this.profileHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.USER_PROFILE_WINDOW);
			this.closeLibraryWindow();
		}));
	}

	private void closeLibraryWindow() {
		Stage stage = (Stage) this.libraryHBox.getScene().getWindow();
		stage.close();

	}

	private void setUpMystiverseNavBarHbox() {
		this.mystiverseHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.MYSTIVERSE_PAGE);
			this.closeLibraryWindow();
		}));
	}

	private void setUpLibraryNavBarHBox() {
		this.libraryHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.USER_GAME_LIBRARY_WINDOW);
			this.closeLibraryWindow();
		}));
	}

	private void setUpGamesListViewListener() {
		this.myGamesListView.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> this.updateSelectedGame());
	}
	
	private void setupGameListsComboBoxListener() {
		this.gameListComboBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> this.updateSelectedListDisplay());
	}

	private void updateSelectedListDisplay() {
		String selectedList = this.gameListComboBox.getSelectionModel().getSelectedItem();
		this.viewModel.setSelectedList(selectedList);
	}

	private void configureProfileImage() {
		var imagePath = ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath();
		if (!imagePath.isBlank()) {
			Image userImage = new Image(imagePath);
			this.profileImageNavBar.setImage(userImage);
		}
	}
	
	private void setUpRemoveGameMenuItem() {
		this.removeGameContextMenuItem.setOnAction(((event) -> {
			String selectedList = this.gameListComboBox.getSelectionModel().getSelectedItem();
			if (!this.viewModel.removeSelectedGameFromList(selectedList)) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Game could not be remove, select and try again");
				errorPopUp.showAndWait();
			} else {
				this.updateSelectedListDisplay();
				this.myGamesListView.getSelectionModel().clearSelection();
			}
		}));
	}

	/**
	 * Update selected game.
	 */
	@FXML
	public void updateSelectedGame() {
		this.viewModel.setSelectedGame(this.myGamesListView.getSelectionModel().getSelectedItem());
	}

	private void redirectToPage(String pagePath) {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(pagePath));
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

	private void validateFXMLComponents() {
		assert this.addGameButton != null : "fx:id=\"addGameButton\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.communityTextArea != null : "fx:id=\"communityTextArea\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.gameDevelopersTextField != null : "fx:id=\"gameDevelopersTextField\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.gameGenresListView != null : "fx:id=\"gameGenresListView\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.gameListComboBox != null : "fx:id=\"gameListComboBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.gamePhotoImageView != null : "fx:id=\"gamePhotoImageView\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.gameTitleTextField != null : "fx:id=\"gameTitleTextField\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.libraryHBox != null : "fx:id=\"libraryHBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.myGamesListView != null : "fx:id=\"myGamesListView\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.myGamesListViewContextMenu != null : "fx:id=\"myGamesListViewContextMenu\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.mystiverseHBox != null : "fx:id=\"mystiverseHBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.profileHBox != null : "fx:id=\"profileHBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.profileImageNavBar != null : "fx:id=\"profileImageNavBar\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.removeGameContextMenuItem != null : "fx:id=\"removeGameContextMenuItem\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
        assert this.gameDescriptionTextBox != null : "fx:id=\"gameDescriptionTextBox\" was not injected: check your FXML file 'UserGameLibraryPage.fxml'.";
	}
}