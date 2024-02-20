package application.view.profile;

import java.io.IOException;
import java.util.ResourceBundle;

import application.Main;
import application.model.profile.ActiveUser;
import application.view.profile.subProfilePages.EditPreferencesAnchor;
import application.view.profile.subProfilePages.EditProfileAnchor;
import application.view.profile.subProfilePages.ProfileAnchor;
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
	private HBox wishlistHBox;
	
	
	private UserProfilePageViewModel userProfilePageViewModel;
	private EditProfileAnchor editProfileCodeBehind;
	private ProfileAnchor profileAnchorCodeBehind;
	private EditPreferencesAnchor editPreferencesCodeBehind;

	/**
	 * Instantiates a new user profile page.
	 */
	public UserProfilePage() {
		this.userProfilePageViewModel = new UserProfilePageViewModel();
		this.editProfileCodeBehind = new EditProfileAnchor();
		this.profileAnchorCodeBehind = new ProfileAnchor();
		this.editPreferencesCodeBehind =  new EditPreferencesAnchor();
	}

	@FXML
	void initialize() {
		this.validiateFXMLComponents();
		this.setUpNavBar();
		this.setUpSideBarButtons();
		this.configurePage();
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

	private void redirectToProfilePage() {
		this.profileAnchorCodeBehind.openAnchorPane(this.parentBorderPane,
				Main.PROFILE_ANCHOR_PATH_TWO);
		this.updateProfileImage();
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
			var errorPopUp = new Alert(AlertType.CONFIRMATION);
			errorPopUp.setContentText("Button Click Works!");
			errorPopUp.showAndWait();
			this.updateProfileImage();
		}));
	}

	private void setUpLibraryNavBarHBox() {
		this.libraryNavBarHBox.setOnMouseClicked(((event) -> {
			var errorPopUp = new Alert(AlertType.CONFIRMATION);
			errorPopUp.setContentText("Button Click Works!");
			errorPopUp.showAndWait();
			this.updateProfileImage();
		}));
	}

	private void setUpSettingsHBox() {
		this.settingsHbox.setOnMouseClicked(((event) -> {
			var errorPopUp = new Alert(AlertType.CONFIRMATION);
			errorPopUp.setContentText("Button Click Works!");
			errorPopUp.showAndWait();
			this.updateProfileImage();
		}));
	}

	private void setUpWishListHBox() {
		this.wishlistHBox.setOnMouseClicked(((event) -> {
			var errorPopUp = new Alert(AlertType.CONFIRMATION);
			errorPopUp.setContentText("Button Click Works!");
			errorPopUp.showAndWait();
			this.updateProfileImage();
		}));
	}

	private void setUpEditPreferencesHBox() {
		this.editPreferencesHBox.setOnMouseClicked(((event) -> {
			this.editPreferencesCodeBehind.openAnchorPane(this.parentBorderPane, Main.EDIT_PREFERENCES_ANCHOR);
			this.updateProfileImage();
		}));
	}

	private void setUpEditProfileHBox() {
		this.editProfileHBox.setOnMouseClicked(((event) -> {
			this.editProfileCodeBehind.openAnchorPane(this.parentBorderPane, Main.EDIT_PROFILE_ANCHOR);
			this.updateProfileImage();
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
			newStage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	private void configurePage() {
		this.setProfilePane();
		this.setUsernameLabel();
		this.updateProfileImage();
	}

	private void setUsernameLabel() {
		this.profileUsername.setText(ActiveUser.getActiveUser().getUsername());
		
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
		assert this.wishlistHBox != null
				: "fx:id=\"wishlistHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
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
