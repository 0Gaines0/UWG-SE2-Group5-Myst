package application.view.mystiverse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.game.Game;
import application.model.game.Genre;
import application.model.profile.ActiveUser;
import application.viewModel.game.GameCardPageViewModel;
import application.viewModel.mystiverse.MystiverseViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class MystiversePage.''
 * 
 * @author daniel rivera
 * @version sprint 1
 */
public class MystiversePage {

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The all games list view. */
	@FXML
	private ListView<Game> allGamesListView;

	/** The base anchor pane. */
	@FXML
	private AnchorPane baseAnchorPane;

	/** The library nav bar H box. */
	@FXML
	private HBox libraryNavBarHBox;

	/** The myst text navbar. */
	@FXML
	private Text mystTextNavbar;

	/** The mystiverse nav bar H box. */
	@FXML
	private HBox mystiverseNavBarHBox;

	/** The navigation achor pane. */
	@FXML
	private AnchorPane navigationAchorPane;

	/** The parent border pane. */
	@FXML
	private BorderPane parentBorderPane;

	/** The profile image nav bar. */
	@FXML
	private ImageView profileImageNavBar;

	/** The profile nav bar H box. */
	@FXML
	private HBox profileNavBarHBox;

	/** The profile photo nav bar H box. */
	@FXML
	private HBox profilePhotoNavBarHBox;

	/** The sort genre combo box. */
	@FXML
	private ComboBox<Genre> sortGenreComboBox;

	@FXML
	private Button recommendationsButton;

	/** The viewmodel. */
	private MystiverseViewModel viewmodel;

	/**
	 * Instantiates a new mystiverse page.
	 */
	public MystiversePage() {
		this.viewmodel = new MystiverseViewModel();
	}

	/**
	 * Open mystiverse page.
	 */
	public void openMystiversePage() {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(Main.MYSTIVERSE_PAGE));
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

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		this.validateFxml();
		this.setupGamesListView();
		this.setupComboBox();
		this.setupRecommendationsButton();
		this.setupNavBar();
		this.configureProfileImage();
	}

	private void configureProfileImage() {
		var imagePath = ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath();
		if (!imagePath.isBlank()) {
			Image userImage = new Image(imagePath);
			this.profileImageNavBar.setImage(userImage);
		}
	}

	private void setupNavBar() {
		this.libraryNavBarHBox.setOnMouseClicked((event) -> {
			this.redirectToPage(Main.USER_GAME_LIBRARY_WINDOW);
			this.closeMystiversePage();
		});
		this.mystiverseNavBarHBox.setOnMouseClicked((event) -> {
			this.redirectToPage(Main.MYSTIVERSE_PAGE);
			this.closeMystiversePage();

		});
		this.profileNavBarHBox.setOnMouseClicked((event) -> {
			this.redirectToPage(Main.USER_PROFILE_WINDOW);
			this.closeMystiversePage();
		});
		this.profilePhotoNavBarHBox.setOnMouseClicked((event) -> {
			this.redirectToPage(Main.USER_PROFILE_WINDOW);
			this.closeMystiversePage();
		});
	}

	private void closeMystiversePage() {
		Stage stage = (Stage) this.parentBorderPane.getScene().getWindow();
    	stage.close();
	}

	/**
	 * Setup recommendations button.
	 */
	private void setupRecommendationsButton() {
		this.recommendationsButton.setOnAction((event) -> {
			this.viewmodel.generateRecommendations();
			for (Game recommendation : MystiverseViewModel.getRecommendedGames()) {
				GameCardPageViewModel.setCurrRecommendation(recommendation);
				var newStage = new Stage();
				try {
					var loader = new FXMLLoader(getClass().getResource(Main.GAME_CARD_PAGE));
					Parent parent = loader.load();
					var scene = new Scene(parent);
					newStage.initModality(Modality.WINDOW_MODAL);
					newStage.initOwner(((Stage) (parent.getScene().getWindow())));
					newStage.initStyle(StageStyle.UNDECORATED);
					newStage.setTitle(Main.WINDOW_TITLE);
					newStage.setScene(scene);
					newStage.show();
				} catch (IOException error) {
					error.printStackTrace();
				}
			}
		});
	}

	/**
	 * Setup games list view.
	 */
	private void setupGamesListView() {
		this.allGamesListView.getItems().setAll(Main.getGames());
	}

	/**
	 * Setup combo box.
	 */
	private void setupComboBox() {
		this.sortGenreComboBox.getItems().setAll(Genre.values());
		this.sortGenreComboBox.setOnAction((event) -> {
			var selectedGenre = this.sortGenreComboBox.getValue();
			if (selectedGenre != null) {
				var filteredGames = Main.getGames().stream().filter(game -> game.getGenres().contains(selectedGenre))
						.toList();
				if (filteredGames.size() != 0) {
					this.allGamesListView.getItems().setAll(filteredGames);
				} else {
					this.allGamesListView.getItems().setAll(Main.getGames());
				}
			} else {
				this.allGamesListView.getItems().setAll(Main.getGames());
			}
		});
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

	/**
	 * Validate fxml.
	 */
	private void validateFxml() {
		assert this.allGamesListView != null
				: "fx:id=\"allGamesListView\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.baseAnchorPane != null
				: "fx:id=\"baseAnchorPane\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.libraryNavBarHBox != null
				: "fx:id=\"libraryNavBarHBox\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.mystTextNavbar != null
				: "fx:id=\"mystTextNavbar\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.mystiverseNavBarHBox != null
				: "fx:id=\"mystiverseNavBarHBox\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.navigationAchorPane != null
				: "fx:id=\"navigationAchorPane\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.parentBorderPane != null
				: "fx:id=\"parentBorderPane\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.profileImageNavBar != null
				: "fx:id=\"profileImageNavBar\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.profileNavBarHBox != null
				: "fx:id=\"profileNavBarHBox\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.profilePhotoNavBarHBox != null
				: "fx:id=\"profilePhotoNavBarHBox\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.sortGenreComboBox != null
				: "fx:id=\"sortGenreComboBox\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.recommendationsButton != null
				: "fx:id=\"recommendationsButton\" was not injected: check your FXML file 'MystiversePage.fxml'.";
	}

}
