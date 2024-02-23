package application.view.profile.subProfilePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.viewModel.profile.subProfilePages.SettingProfileAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * The Class SettingProfileAnchor.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class SettingProfileAnchor {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TextField changeInformationCurrentPasswordTextField;

	@FXML
	private TextField changeInformationNewPasswordTextField;

	@FXML
	private TextField changeInformationReenterNewPasswordTextField;

	@FXML
	private TextField changeInformationCurrentUsernameTextField;
	
	@FXML
	private TextField changeInformationNewUsernameTextField;

	@FXML
	private TextField changeInformationReenterNewUsernameTextField;

	@FXML
	private TextField userInformationPasswordTextField;

	@FXML
	private TextField userInformationUsernameTextField;

	@FXML
	private Button changeUsernameSaveButton;

	@FXML
	private Button changePasswordSaveButton;
	
	private SettingProfileAnchorViewModel settingProfileAnchorViewModel;

	/**
	 * Instantiates a new setting profile anchor.
	 */
	public SettingProfileAnchor() {
		this.settingProfileAnchorViewModel = new SettingProfileAnchorViewModel();
	}

	/**
	 * Open anchor pane.
	 *
	 * @param parent        the parent
	 * @param newAnchorPath the new anchor path
	 */
	public void openAnchorPane(BorderPane parent, String newAnchorPath) {
		try {
			AnchorPane currentAnchor = (AnchorPane) parent.getCenter();
			var loader = new FXMLLoader(getClass().getResource(newAnchorPath));
			AnchorPane newAnchor = loader.load();

			parent.setCenter(newAnchor);
			parent.getChildren().remove(currentAnchor);
		} catch (IOException error) {

		}
	}

	@FXML
	void initialize() {
		this.validateFXMLComponents();
		this.bindToViewModel();
		this.setUpSaveButtons();
		this.settingProfileAnchorViewModel.setUpUserInformationFields();
		

	}

	private void setUpSaveButtons() {
		this.setUpChangeUsernameSaveButton();
		this.setUpChangePasswordSaveButton();
		
	}

	private void setUpChangePasswordSaveButton() {
		this.changePasswordSaveButton.setOnAction(((event) -> {
			var attemptChangePassword = this.settingProfileAnchorViewModel.attemptToChangePassword();
			if (attemptChangePassword) {
				var errorPopUp = new Alert(AlertType.CONFIRMATION);
				errorPopUp.setContentText("password has been updated");
				errorPopUp.showAndWait();
				this.settingProfileAnchorViewModel.setUpUserInformationFields();
				this.clearChangePasswordFields();
			} else {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Password has not been updated. Possible errors: Incorrect Password Username, Incorrect Reentered Password, Invalid new Password");
				errorPopUp.showAndWait();
			}
		}));
	}

	private void clearChangePasswordFields() {
		this.changeInformationCurrentPasswordTextField.clear();
		this.changeInformationNewPasswordTextField.clear();
		this.changeInformationReenterNewPasswordTextField.clear();
		
	}

	private void setUpChangeUsernameSaveButton() {
		this.changeUsernameSaveButton.setOnAction(((event) -> {
			var attemptChangeUsername = this.settingProfileAnchorViewModel.attemptToChangeUsername();
			if (attemptChangeUsername) {
				var errorPopUp = new Alert(AlertType.CONFIRMATION);
				errorPopUp.setContentText("Username has been updated");
				errorPopUp.showAndWait();
				this.settingProfileAnchorViewModel.setUpUserInformationFields();
				this.clearChangeUsernameFields();
			} else {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Username has not been updated. Possible errors: Incorrect Current Username, Incorrect Reentered Username, New Username already exist, Invalid new Username");
				errorPopUp.showAndWait();
			}
		}));
	}

	private void clearChangeUsernameFields() {
		this.changeInformationCurrentUsernameTextField.clear();
		this.changeInformationNewUsernameTextField.clear();
		this.changeInformationReenterNewUsernameTextField.clear();
		
	}

	private void bindToViewModel() {
		this.changeInformationCurrentPasswordTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getChangeInformationCurrentPasswordProperty());
		this.changeInformationCurrentUsernameTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getChangeInformationCurrentUsernameProperty());
		this.changeInformationNewPasswordTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getChangeInformationNewPasswordProperty());
		this.changeInformationNewUsernameTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getChangeInformationNewUsernameProperty());
		this.changeInformationReenterNewPasswordTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getChangeInformationReenterNewPasswordProperty());
		this.changeInformationReenterNewUsernameTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getChangeInformationReenterNewUsernameProperty());
		this.userInformationPasswordTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getUserInformationPasswordProperty());
		this.userInformationUsernameTextField.textProperty().bindBidirectional(this.settingProfileAnchorViewModel.getUserInformationUsernameProperty());
	}

	private void validateFXMLComponents() {
		assert this.anchorPane != null
				: "fx:id=\"anchorPane\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeInformationCurrentPasswordTextField != null
				: "fx:id=\"changeInformationCurrentPasswordTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeInformationCurrentUsernameTextField != null
				: "fx:id=\"changeInformationCurrentUsernameTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeInformationNewPasswordTextField != null
				: "fx:id=\"changeInformationNewPasswordTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeInformationNewUsernameTextField != null
				: "fx:id=\"changeInformationNewUsernameTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeInformationReenterNewPasswordTextField != null
				: "fx:id=\"changeInformationReenterNewPasswordTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeInformationReenterNewUsernameTextField != null
				: "fx:id=\"changeInformationReenterNewUsernameTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changeUsernameSaveButton != null
				: "fx:id=\"changeUsernameSaveButton\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.userInformationPasswordTextField != null
				: "fx:id=\"userInformationPasswordTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.userInformationUsernameTextField != null
				: "fx:id=\"userInformationUsernameTextField\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
		assert this.changePasswordSaveButton != null
				: "fx:id=\"changePasswordSaveButton\" was not injected: check your FXML file 'SettingsProfileAnchor.fxml'.";
	}

}
