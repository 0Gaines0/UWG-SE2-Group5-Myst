package application.view.profile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.profile.UserProfile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserProfilePage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox editPreferencesHBox;

	@FXML
	private HBox editProfileHBox;

	@FXML
	private HBox libraryHBox;

	@FXML
	private Text libraryTextNavBar;

	@FXML
	private Text mystTextNavbar;

	@FXML
	private HBox mystiverseHBox;

	@FXML
	private Text mystiverseNavBar;

	@FXML
	private AnchorPane navigationAchorPane;

	@FXML
	private HBox profileHBox;

	@FXML
	private ImageView profileImageNavBar;

	@FXML
	private ImageView profileImageSideBar;

	@FXML
	private Text profileTextNavBar;

	@FXML
	private Text profileUsername;

	@FXML
	private HBox settingsHbox;

	@FXML
	private AnchorPane sideBar;

	@FXML
	private VBox vSideBox;

	@FXML
	private HBox wishlistHBox;
	
	private UserProfile activeUser;


	@FXML
	void initialize() {
		this.validiateFXMLComponents();
	}

	public void openUserProfilePage(UserProfile user) {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(Main.USER_PROFILE_WINDOW));
			Parent parent = loader.load();
			UserProfilePage controller = loader.getController();
			controller.setActiveUser(user);
			controller.profileUsername.setText(controller.getActiveUser().getUsername());
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

	private void validiateFXMLComponents() {
		this.validateSomeComponents();
		this.validateOtherComponents();
	}

	private void validateOtherComponents() {
		assert this.profileImageSideBar != null
				: "fx:id=\"profileImageSideBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileTextNavBar != null
				: "fx:id=\"profileTextNavBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
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
	}

	private void validateSomeComponents() {
		assert this.editPreferencesHBox != null
				: "fx:id=\"editPreferencesHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.editProfileHBox != null
				: "fx:id=\"editProfileHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.libraryHBox != null
				: "fx:id=\"libraryHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.libraryTextNavBar != null
				: "fx:id=\"libraryTextNavBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.mystTextNavbar != null
				: "fx:id=\"mystTextNavbar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.mystiverseHBox != null
				: "fx:id=\"mystiverseHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.mystiverseNavBar != null
				: "fx:id=\"mystiverseNavBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.navigationAchorPane != null
				: "fx:id=\"navigationAchorPane\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileHBox != null
				: "fx:id=\"profileHBox\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
		assert this.profileImageNavBar != null
				: "fx:id=\"profileImageNavBar\" was not injected: check your FXML file 'UserProfilePage.fxml'.";
	}

	/**
	 * Gets the active user.
	 *
	 * @return the active user
	 */
	public UserProfile getActiveUser() {
		return this.activeUser;
	}

	/**
	 * Sets the active user.
	 *
	 * @param activeUser the new active user
	 */
	public void setActiveUser(UserProfile activeUser) {
		this.activeUser = activeUser;
	}

}
