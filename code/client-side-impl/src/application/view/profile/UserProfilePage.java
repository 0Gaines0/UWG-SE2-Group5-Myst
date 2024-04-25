package application.view.profile;

import java.io.IOException;
import java.util.ResourceBundle;

import application.Main;
import application.model.server_impl.profile.ActiveUser;
import application.view.UserGameLibraryPage.UserGameLibraryPage;
import application.view.mystiverse.MystiversePage;
import application.view.profile.subProfilePages.EditPreferencesAnchor;
import application.view.profile.subProfilePages.EditProfileAnchor;
import application.view.profile.subProfilePages.ProfileAnchor;
import application.view.profile.subProfilePages.SettingProfileAnchor;
import application.view.profile.subProfilePages.SuggestGamesProfileAnchor;
import application.viewModel.profile.UserProfilePageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;

/**
 * The Class UserProfilePage.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class UserProfilePage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane baseAnchorPane;

	@FXML
	private HBox editPreferencesHBox;

	@FXML
	private HBox editProfileHBox;

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
	private ImageView profileImageSideBar;

	@FXML
	private HBox profileNavBarHBox;

	@FXML
	private Text profileUsername;

	@FXML
	private HBox profileUsernameHBox;

	@FXML
	private HBox settingsHbox;

	@FXML
	private HBox profilePhotoNavBarHBox;

	@FXML
	private AnchorPane sideBar;

	@FXML
	private VBox vSideBox;

	@FXML
	private HBox suggestionsHBox;

	private UserProfilePageViewModel userProfilePageViewModel;
	private EditProfileAnchor editProfileCodeBehind;
	private ProfileAnchor profileAnchorCodeBehind;
	private UserGameLibraryPage userGameLibraryCodeBehind;
	private EditPreferencesAnchor editPreferencesCodeBehind;
	private SettingProfileAnchor profileSettingsAnchorCodeBehind;
	private MystiversePage mystiverseCodeBehind;
	private SuggestGamesProfileAnchor suggestGameProfileAnchor;
	
	/**
	 * Instantiates a new user profile page.
	 */
	public UserProfilePage() {
		this.userProfilePageViewModel = new UserProfilePageViewModel();
		this.editProfileCodeBehind = new EditProfileAnchor();
		this.profileAnchorCodeBehind = new ProfileAnchor();
		this.profileSettingsAnchorCodeBehind = new SettingProfileAnchor();
		this.editPreferencesCodeBehind = new EditPreferencesAnchor();
		this.userGameLibraryCodeBehind = new UserGameLibraryPage();
		this.mystiverseCodeBehind = new MystiversePage();
		this.suggestGameProfileAnchor = new SuggestGamesProfileAnchor();
	}

	@FXML
	void initialize() {
		this.validiateFXMLComponents();
		this.bindToViewModel();
		this.setUpNavBar();
		this.setUpSideBarButtons();
		this.configurePage();
	}

	private void bindToViewModel() {
		this.profileUsername.textProperty().bindBidirectional(this.userProfilePageViewModel.getUsernameTextProperty());

	}

	private void setUpSideBarButtons() {
		this.setUpUserNameHBox();
		this.setUpEditProfileHBox();
		this.setUpEditPreferencesHBox();
		this.setUpWishListHBox();
		this.setUpSettingsHBox();
	}

	private void setUpNavBar() {
		this.setUpLibraryNavBarHBox();
		this.setUpMystiverseNavBarHbox();
		this.setUpProfileNavBarHBox();
		this.setUpProfilePhotoNavBarHBox();
	}

	private void setUpProfilePhotoNavBarHBox() {
		this.profilePhotoNavBarHBox.setOnMouseClicked(((event) -> {
			this.redirectToProfilePage();
		}));
	}

	private void setUpUserNameHBox() {
		this.profileUsernameHBox.setOnMouseClicked(((event) -> {
			this.redirectToProfilePage();
		}));

	}

	private void setUpProfileNavBarHBox() {
		this.profileNavBarHBox.setOnMouseClicked(((event) -> {
			this.redirectToProfilePage();
		}));

	}

	private void setUpMystiverseNavBarHbox() {
		this.mystiverseNavBarHBox.setOnMouseClicked(((event) -> {
			this.mystiverseCodeBehind.redirectToPage(Main.MYSTIVERSE_PAGE);
			this.closeProfilePage();
		}));
	}

	private void closeProfilePage() {
    	Stage stage = (Stage) this.parentBorderPane.getScene().getWindow();
    	stage.close();

	}

	private void setUpLibraryNavBarHBox() {
		this.libraryNavBarHBox.setOnMouseClicked(((event) -> {
			this.userGameLibraryCodeBehind.openUserGameLibraryPage();
			this.closeProfilePage();

		}));
	}

	private void setUpSettingsHBox() {
		this.settingsHbox.setOnMouseClicked(((event) -> {
			this.profileSettingsAnchorCodeBehind.openAnchorPane(this.parentBorderPane, Main.PROFILE_SETTINGS_ANCHOR);
			this.updatePage();
		}));
	}

	private void setUpWishListHBox() {
		this.suggestionsHBox.setOnMouseClicked(((event) -> {
			this.suggestGameProfileAnchor.openAnchorPane(this.parentBorderPane, Main.SUGGEST_GAMES_ANCHOR);
			this.updatePage();
		}));
	}

	private void setUpEditPreferencesHBox() {
		this.editPreferencesHBox.setOnMouseClicked(((event) -> {
			this.editPreferencesCodeBehind.openAnchorPane(this.parentBorderPane, Main.EDIT_PREFERENCES_ANCHOR);
			this.updatePage();
		}));
	}

	private void setUpEditProfileHBox() {
		this.editProfileHBox.setOnMouseClicked(((event) -> {
			this.editProfileCodeBehind.openAnchorPane(this.parentBorderPane, Main.EDIT_PROFILE_ANCHOR);
			this.updatePage();
		}));
	}

	/**
	 * Open user profile page.
	 *
	 */
	public void openUserProfilePage() {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(Main.USER_PROFILE_WINDOW));
			Parent parent = loader.load();
			var scene = new Scene(parent);
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.initOwner(((Stage) (parent.getScene().getWindow())));
			newStage.setTitle(Main.WINDOW_TITLE);
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	private void redirectToProfilePage() {
		this.profileAnchorCodeBehind.openAnchorPane(this.parentBorderPane, Main.PROFILE_ANCHOR_PATH_TWO);
		this.updatePage();
	}

	private void updateProfileImage() {
		if (this.userProfilePageViewModel.profilePictureHasChanged()) {
			var imagePath = ActiveUser.getActiveUser().getProfileAttributes().getUserProfilePicturePath();
			Image userImage = new Image(imagePath);
			this.profileImageNavBar.setImage(userImage);
			this.profileImageSideBar.setImage(userImage);
			this.userProfilePageViewModel.setCachedProfilePicturePath(imagePath);
		}

	}

	private void updatePage() {
		this.updateProfileImage();
		this.updateProfileName();
	}

	private void updateProfileName() {
		this.userProfilePageViewModel.updateUsernameOnPage();

	}

	private void configurePage() {
		this.setProfilePane();
		this.setUsernameLabel();
		this.updateProfileImage();
	}

	private void setUsernameLabel() {
		this.profileUsername.textProperty().setValue(ActiveUser.getActiveUser().getUsername());

	}

	private void setProfilePane() {
		try {
			BorderPane parentContainer = this.parentBorderPane;
			var loader = new FXMLLoader(getClass().getResource(Main.PROFILE_ANCHOR_PATH_ONE));
			AnchorPane newAnchor = loader.load();
			parentContainer.setCenter(newAnchor);
			parentContainer.getChildren().remove(this.baseAnchorPane);

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	private void validiateFXMLComponents() {
		this.validateSomeComponents();
		this.validateOtherComponents();
	}
	
	private void validateOtherComponents() {
		assert this.profileImageSideBar != null
				: "fx:id=\"profileImageSideBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileUsername != null
				: "fx:id=\"profileUsername\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.settingsHbox != null
				: "fx:id=\"settingsHbox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.sideBar != null
				: "fx:id=\"sideBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.vSideBox != null
				: "fx:id=\"vSideBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.suggestionsHBox != null
				: "fx:id=\"suggestionsHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.parentBorderPane != null
				: "fx:id=\"parentBorderPane\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileUsernameHBox != null
				: "fx:id=\"profileUsernameHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
	}

	private void validateSomeComponents() {
		assert this.editPreferencesHBox != null
				: "fx:id=\"editPreferencesHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.editProfileHBox != null
				: "fx:id=\"editProfileHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.libraryNavBarHBox != null
				: "fx:id=\"libraryHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.mystTextNavbar != null
				: "fx:id=\"mystTextNavbar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.mystiverseNavBarHBox != null
				: "fx:id=\"mystiverseHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.navigationAchorPane != null
				: "fx:id=\"navigationAchorPane\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileNavBarHBox != null
				: "fx:id=\"profileHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileImageNavBar != null
				: "fx:id=\"profileImageNavBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profilePhotoNavBarHBox != null
				: "fx:id=\"profilePhotoNavBarHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
	}

}
