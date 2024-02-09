package application.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.viewModel.login.CreatePageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class CreateAccountPage.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class CreateAccountPage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private PasswordField reenterPasswordTextField;

	@FXML
	private TextField userNameTextField;

	@FXML
	private Button cancelButton;

	@FXML
	private Button createAccountButton;

	private CreatePageViewModel createPageViewModel;

	/**
	 * Instantiates a new creates the account page.
	 */
	public CreateAccountPage() {
		this.createPageViewModel = new CreatePageViewModel();
	}

	@FXML
	void initialize() {
		this.fxmlValidComponents();
		this.bindToViewModel();
		this.setUpCreateButton();
		this.setUpCancelButton();
	}

	private void setUpCreateButton() {
		this.createAccountButton.setOnAction(((event) -> {
			if (this.fieldsAreNull()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Account creation failed, username and password fields must be valid");
				errorPopUp.showAndWait();
			} else if (this.fieldsAreEmpty()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Account creation failed, username and password fields must be valid");
				errorPopUp.showAndWait();
			} else if (this.passwordsMatch()) {
				var attemptCreation = this.createPageViewModel.attemptCreateNewAccount();
				if (attemptCreation) {
					var successPopUp = new Alert(AlertType.CONFIRMATION);
					successPopUp.setContentText("Account creation was successful");
					successPopUp.showAndWait();
					this.closeCreateAccountPage(this.createAccountButton);
				} else {
					var errorPopUp = new Alert(AlertType.ERROR);
					errorPopUp.setContentText("Account creation failed, username already exist");
					errorPopUp.showAndWait();
				}
			} else {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Account creation failed, passwords do not match");
				errorPopUp.showAndWait();
			}

		}));
	}

	private boolean fieldsAreNull() {
		var user = this.userNameTextField.textProperty().getValue() == null;
		var password = this.passwordTextField.textProperty().getValue() == null;
		var reenterPassword = this.reenterPasswordTextField.textProperty().getValue() == null;
		
		return user || password || reenterPassword;
	}

	private boolean fieldsAreEmpty() {
		return this.userNameTextField.textProperty().getValue().isBlank()
				|| this.passwordTextField.textProperty().getValue().isBlank()
				|| this.reenterPasswordTextField.textProperty().getValue().isBlank();
	}

	private void setUpCancelButton() {
		this.cancelButton.setOnAction(((event) -> {
			this.closeCreateAccountPage(this.cancelButton);
		}));
	}

	private void closeCreateAccountPage(Button btn) {
		var stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}

	private boolean passwordsMatch() {
		return this.passwordTextField.textProperty().getValue()
				.equals(this.reenterPasswordTextField.textProperty().getValue());
	}

	private void bindToViewModel() {
		this.userNameTextField.textProperty().bindBidirectional(this.createPageViewModel.getUserNameProperty());
		this.passwordTextField.textProperty().bindBidirectional(this.createPageViewModel.getPasswordProperty());
		this.reenterPasswordTextField.textProperty()
				.bindBidirectional(this.createPageViewModel.getReenterPasswordProperty());
	}

	/**
	 * Open create account page.
	 */
	public void openCreateAccountPage() {
		var newStage = new Stage();
		try {
			var loader = new FXMLLoader(getClass().getResource(Main.CREATE_ACCOUNT_WINDOW));
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

	private void fxmlValidComponents() {
		assert this.passwordTextField != null
				: "fx:id=\"passwordTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
		assert this.reenterPasswordTextField != null
				: "fx:id=\"reenterPasswordTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
		assert this.userNameTextField != null
				: "fx:id=\"userNameTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
		assert this.cancelButton != null
				: "fx:id=\"cancelButton\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
		assert this.createAccountButton != null
				: "fx:id=\"createAccountButton\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
	}

}
