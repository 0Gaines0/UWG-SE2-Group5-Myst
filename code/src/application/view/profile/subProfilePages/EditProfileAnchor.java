package application.view.profile.subProfilePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.game.Game;
import application.model.profile.ActiveUser;
import application.model.profile.UserProfile;
import application.viewModel.profile.subProfilePages.EditProfileAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class EditProfileAnchor {

	@FXML
	private ResourceBundle resources;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private URL location;

	@FXML
	private TextArea aboutMeTextArea;

	@FXML
	private Button chooseAnAvatarButton;

	@FXML
	private TextField gameTitleTextFIeld;

	@FXML
	private TextField genreTitleTextField;

	@FXML
	private Button importAPhototButton;

	@FXML
	private ImageView profileImageView;

	@FXML
	private Button saveEditAboutMeButton;

	@FXML
	private ComboBox<Game> selectAGameComboBox;

	private EditProfileAnchorViewModel editProfileAnchorViewModel;
	/**
	 * Instantiates a new edits the profile anchor.
	 */
	public EditProfileAnchor() {
		this.editProfileAnchorViewModel = new EditProfileAnchorViewModel();
	}

	@FXML
	void initialize() {
		this.validateFXMLComponents();
		this.bindToViewModel();
		this.configurePage();
		this.setUpSaveButton();
	}

	private void configurePage() {
		this.editProfileAnchorViewModel.setAboutMeTextArea();
	}

	private void bindToViewModel() {
		this.aboutMeTextArea.textProperty().bindBidirectional(this.editProfileAnchorViewModel.getAboutMeProperty());		
	}

	/**
	 * Open anchor pane.
	 *
	 * @param activeUser    the active user
	 * @param parent        the parent
	 * @param newAnchorPath the new anchor path
	 */
	public void openAnchorPane(UserProfile activeUser, BorderPane parent, String newAnchorPath) {
		try {
			AnchorPane currentAnchor = (AnchorPane) parent.getCenter();
			var loader = new FXMLLoader(getClass().getResource(newAnchorPath));
			AnchorPane newAnchor = loader.load();
			parent.setCenter(newAnchor);
			parent.getChildren().remove(currentAnchor);
		} catch (IOException error) {
			error.getMessage();
		}
	}

	private void setUpSaveButton() {
		this.saveEditAboutMeButton.setOnAction(((event) -> {
			this.editProfileAnchorViewModel.setActiveUserAboutMe();
		}));
	}

	private void validateFXMLComponents() {
		assert this.anchorPane != null
				: "fx:id=\"anchorPane\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.aboutMeTextArea != null
				: "fx:id=\"aboutMeTextArea\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.chooseAnAvatarButton != null
				: "fx:id=\"chooseAnAvatarButton\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.gameTitleTextFIeld != null
				: "fx:id=\"gameTitleTextFIeld\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.genreTitleTextField != null
				: "fx:id=\"genreTitleTextField\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.importAPhototButton != null
				: "fx:id=\"importAPhototButton\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.profileImageView != null
				: "fx:id=\"profileImageView\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.saveEditAboutMeButton != null
				: "fx:id=\"saveEditAboutMeButton\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
		assert this.selectAGameComboBox != null
				: "fx:id=\"selectAGameComboBox\" was not injected: check your FXML file 'EditProfileAnchor.fxml'.";
	}

}
