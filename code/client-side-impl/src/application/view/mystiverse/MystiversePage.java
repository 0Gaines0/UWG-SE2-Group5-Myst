package application.view.mystiverse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.server_impl.profile.ActiveUser;
import application.view.mystiverse.subMystiversePages.AllGamesPageAnchor;
import application.view.mystiverse.subMystiversePages.RecommendationPageAnchor;
import application.view.mystiverse.subMystiversePages.SeedPageAnchor;
import application.viewModel.mystiverse.MystiverseViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MystiversePage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane baseAnchorPane;

	@FXML
	private HBox libraryNavBarHBox;

	@FXML
	private Text mystTextNavbar;

	@FXML
	private HBox mystiverseNavBarHBox;

	@FXML
	private AnchorPane navigationAchorPane;

	@FXML
	private BorderPane parentBorderPane;

	@FXML
	private ImageView profileImageNavBar;

	@FXML
	private HBox profileNavBarHBox;

	@FXML
	private HBox profilePhotoNavBarHBox;

	@FXML
	private HBox recommendationsTab;

	@FXML
	private HBox seedTab;

	@FXML
	private HBox allGamesTab;

	@FXML
	private AnchorPane sideBar;

	@FXML
	private VBox vSideBox;
	 
	private AllGamesPageAnchor allGamesCodeBehind;
	private RecommendationPageAnchor recommendationCodeBehind;
	private SeedPageAnchor seedCodeBehind;
	private MystiverseViewModel viewModel;

	/**
	 * Instantiates a new mystiverse page.
	 */
	public MystiversePage() {
		this.allGamesCodeBehind = new AllGamesPageAnchor();
		this.recommendationCodeBehind = new RecommendationPageAnchor();
		this.seedCodeBehind = new SeedPageAnchor();
		this.viewModel = new MystiverseViewModel();
	}

	@FXML
	void initialize() {
		this.validateFxml();
		this.setUpNavBar();
		this.setupHboxes();
		this.setPane();
		this.configurePage();
	}
	
	private void configurePage() {
		this.updateProfileImage();
	}
	
	private void updateProfileImage() {
		if (this.viewModel.profilePictureHasChanged()) {
			var imagePath = ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath();
			Image userImage = new Image(imagePath);
			this.profileImageNavBar.setImage(userImage);
			this.viewModel.setCachedProfilePicturePath(imagePath);
		}

	}

	private void setupHboxes() {
		this.recommendationsTab.setOnMouseClicked((event) -> {
			this.recommendationCodeBehind.openAnchorPane(this.parentBorderPane, Main.MYSTIVERSE_PAGE_RECOMMENDATIONS_TAB);
		});
		this.allGamesTab.setOnMouseClicked((event) -> {
			this.allGamesCodeBehind.openAnchorPane(this.parentBorderPane, Main.MYSTIVERSE_PAGE_ALL_GAMES_TAB_TWO);
		});
		this.seedTab.setOnMouseClicked((event) -> {
			this.seedCodeBehind.openAnchorPane(this.parentBorderPane, Main.MYSTIVERSE_PAGE_SEED_TAB);
		});
	}

	private void setPane() {
		try {
			BorderPane parentContainer = this.parentBorderPane;
			var loader = new FXMLLoader(getClass().getResource(Main.MYSTIVERSE_PAGE_ALL_GAMES_TAB_ONE));
			AnchorPane newAnchor = loader.load();
			parentContainer.setCenter(newAnchor);
			parentContainer.getChildren().remove(this.baseAnchorPane);

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	private void setUpNavBar() {
		this.setUpLibraryNavBarHBox();
		this.setUpMystiverseNavBarHbox();
		this.setUpProfileNavBarHBox();
		this.setUpProfilePhotoNavBarHBox();
	}

	private void setUpProfileNavBarHBox() {
		this.profileNavBarHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.USER_PROFILE_WINDOW);
			this.closeMystiverseWindow();
		}));
	}

	private void setUpMystiverseNavBarHbox() {
		this.mystiverseNavBarHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.MYSTIVERSE_PAGE);
			this.closeMystiverseWindow();
		}));
	}

	private void setUpLibraryNavBarHBox() {
		this.libraryNavBarHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.USER_GAME_LIBRARY_WINDOW);
			this.closeMystiverseWindow();
		}));
	}
	
	private void setUpProfilePhotoNavBarHBox() {
		this.profilePhotoNavBarHBox.setOnMouseClicked(((event) -> {
			this.redirectToPage(Main.USER_PROFILE_WINDOW);
			this.closeMystiverseWindow();
		}));
	}

	private void closeMystiverseWindow() {
		Stage stage = (Stage) this.parentBorderPane.getScene().getWindow();
		stage.close();
	}

	/**
	 * Redirect to page.
	 *
	 * @param pagePath the page path
	 */
	public void redirectToPage(String pagePath) {
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

	private void validateFxml() {
		assert this.allGamesTab != null
				: "fx:id=\"allGamesTab\" was not injected: check your FXML file 'MystiversePage.fxml'.";
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
		assert this.recommendationsTab != null
				: "fx:id=\"recommendationsTab\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.seedTab != null : "fx:id=\"seedTab\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.sideBar != null : "fx:id=\"sideBar\" was not injected: check your FXML file 'MystiversePage.fxml'.";
		assert this.vSideBox != null
				: "fx:id=\"vSideBox\" was not injected: check your FXML file 'MystiversePage.fxml'.";
	}
}
